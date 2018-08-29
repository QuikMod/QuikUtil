// =========================================================================
// <editor-fold defaultstate="collapsed" desc="The MIT License">
// -------------------------------------------------------------------------
//
// Copyright 2018 QuikMod.
//
// Permission is hereby granted, free of charge, to any person obtaining a copy
// of this software and associated documentation files (the "Software"), to deal
// in the Software without restriction, including without limitation the rights
// to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
// copies of the Software, and to permit persons to whom the Software is
// furnished to do so, subject to the following conditions:
//
// The above copyright notice and this permission notice shall be included in
// all copies or substantial portions of the Software.
//
// THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
// IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
// FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
// AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
// LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
// OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
// THE SOFTWARE.
//
// -------------------------------------------------------------------------
// </editor-fold>
// =========================================================================
package com.github.quikmod.quikutil.reflection;

import com.github.quikmod.quikutil.TypeUtil;
import com.google.common.base.Preconditions;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Ryan
 */
public final class ReflectionUtil {

    @Nonnull
    private static final Logger LOG = LoggerFactory.getLogger(ReflectionUtil.class);

    /**
     * Creates a stream of all the constructors for a given class regardless of accessibility. That
     * is, this method ignores if a constructor is private or otherwise protected.
     *
     * @param from the object or class representing the class to stream constructors from.
     * @return a stream containing all the fields for the class or object.
     */
    @Nonnull
    public static final Stream<Constructor<?>> streamConstructors(@Nonnull Object from) {
        Preconditions.checkNotNull(from);
        return Arrays.stream(TypeUtil.classify(from).getConstructors());
    }

    /**
     * Creates a stream of all the fields present in a given object or class regardless of
     * accessibility. I.e. if only a class is passed then the stream will still include instance
     * fields.
     *
     * @param from the object or class to stream the fields from.
     * @return a stream containing all the fields for the class or object.
     */
    @Nonnull
    public static final Stream<TypedField<?>> streamFields(@Nonnull Object from) {
        Preconditions.checkNotNull(from);
        return Arrays.stream(TypeUtil.classify(from).getDeclaredFields()).map(TypedField::of);
    }

    /**
     * Creates a stream of all the fields present in a given object or class that are accessible in
     * that context. I.e. if an object is passed then the stream will include instance fields, but
     * if a class is passed then instance fields will not be included.
     *
     * @param from the object or class to stream the methods from.
     * @return a stream containing all the accessible fields for the class or object.
     */
    @Nonnull
    public static final Stream<TypedField<?>> streamAccessibleFields(@Nonnull Object from) {
        Preconditions.checkNotNull(from);
        Stream<TypedField<?>> stream = streamFields(from);
        if (from instanceof Class) {
            stream = stream.filter(TypedField::isStatic);
        }
        return stream;
    }

    /**
     * Creates a stream of all the methods present in a given object or class regardless of
     * accessibility. I.e. if only a class is passed then the stream will still include instance
     * methods.
     *
     * @param from the object or class to stream the methods from.
     * @return a stream containing all the methods for the class or object.
     */
    @Nonnull
    public static final Stream<Method> streamMethods(@Nonnull Object from) {
        Preconditions.checkNotNull(from);
        return Arrays.stream(TypeUtil.classify(from).getDeclaredMethods());
    }

    /**
     * Creates a stream of all the methods present in a given object or class that are accessible in
     * that context. I.e. if an object is passed then the stream will include instance methods, but
     * if a class is passed then instance methods will not be included.
     *
     * @param from the object or class to stream the methods from.
     * @return a stream containing all the accessible methods for the class or object.
     */
    @Nonnull
    public static final Stream<Method> streamAccessibleMethods(@Nonnull Object from) {
        Preconditions.checkNotNull(from);
        Stream<Method> stream = streamMethods(from);
        if (from instanceof Class) {
            stream = stream.filter(Modifier::isStatic);
        }
        return stream;
    }

    /**
     * Creates a stream of all the values present in a given object or class that are accessible in
     * that context. I.e. if an object is passed then the stream will include instance fields, but
     * if a class is passed then instance fields will not be included.
     *
     * @param from the object or class to stream the methods from.
     * @return a stream containing all the accessible fields for the class or object.
     */
    @Nonnull
    public static final Stream<?> streamValues(@Nonnull Object from) {
        Preconditions.checkNotNull(from);
        final Object accessor = (from instanceof Class) ? null : from;
        return streamAccessibleFields(from)
                .flatMap(f -> f.attemptGetStream(accessor));
    }

    /**
     * Creates a stream of all the values present in a given object or class that are accessible in
     * that context. I.e. if an object is passed then the stream will include instance fields, but
     * if a class is passed then instance fields will not be included.
     *
     * @param <T> the type of the values.
     * @param from the object or class to stream the methods from.
     * @param type the type of value to stream from the class.
     * @return a stream containing all the accessible fields for the class or object.
     */
    @Nonnull
    public static final <T> Stream<T> streamValues(@Nonnull Object from, @Nonnull Class<T> type) {
        Preconditions.checkNotNull(from);
        final Object accessor = (from instanceof Class) ? null : from;
        return streamAccessibleFields(from)
                .flatMap(f -> f.castStream(type))
                .flatMap(f -> f.attemptGetStream(accessor));
    }

    /**
     * Creates a stream of all the values present in a given object or class that are accessible in
     * that context. I.e. if an object is passed then the stream will include instance fields, but
     * if a class is passed then instance fields will not be included.
     *
     * @param <T> the type of the values.
     * @param from the object or class to stream the methods from.
     * @param type the type of value to stream from the class.
     * @param annotations the annotations that the field should have.
     * @return a stream containing all the accessible fields for the class or object.
     */
    @Nonnull
    @SafeVarargs
    public static final <T> Stream<T> streamValues(@Nonnull Object from, @Nonnull Class<T> type, @Nonnull Class<? extends Annotation>... annotations) {
        Preconditions.checkNotNull(from);
        final Object accessor = (from instanceof Class) ? null : from;
        return streamAccessibleFields(from)
                .filter(f -> f.isAnnotationPresent(annotations))
                .flatMap(f -> f.castStream(type))
                .flatMap(f -> f.attemptGetStream(accessor));
    }

    @Nonnull
    public static final Optional<?> attemptGet(@Nullable Object obj, @Nonnull Field field) {
        // Validate
        Preconditions.checkNotNull(field);

        // Determine the object to use as context.
        obj = TypeUtil.declassify(obj);

        // Abort in the case that the field is not static and the object is null.
        if (obj == null && !Modifier.isStatic(field)) {
            LOG.error("Unable to access field: \"{}\" in class \"{}\" from static context!", field.getName(), field.getClass().getCanonicalName());
            return Optional.empty();
        }

        // Fetch
        try {
            field.setAccessible(true);
            return Optional.ofNullable(field.get(obj));
        } catch (IllegalAccessException | IllegalArgumentException e) {
            // Oh well...
            LOG.error("Unable to access field: \"{}\" in class \"{}\"!", field.getName(), field.getClass().getCanonicalName());
            LOG.error("Cause: ", e);
        }

        // Default
        return Optional.empty();
    }

    public static final void attemptSet(@Nullable Object obj, @Nonnull Field field, @Nullable Object value) {
        attemptSet(obj, field, value, false);
    }

    public static final void attemptSet(@Nullable Object obj, @Nonnull Field field, @Nullable Object value, boolean override) {
        // Validate
        Preconditions.checkNotNull(field);

        // Abort in the case that the field is static.
        if (Modifier.isFinal(field)) {
            if (override) {
                try {
                    definalizeField(field);
                } catch (IllegalAccessException e) {
                    LOG.error("Unable to definalize final field: \"{}\"!\n\tCause: {}", field, e.getLocalizedMessage());
                    return;
                }
            } else {
                LOG.error("Cannot modify value of final field: \"{}\" without override!", field);
                return;
            }
        }

        // Abort in the case that the field is not static and the object is null.
        if (obj == null && !Modifier.isStatic(field)) {
            LOG.error("Unable to access field: \"{}\" in class \"{}\" from static context!", field.getName(), field.getClass().getCanonicalName());
            return;
        }
        
        // Abort in the case that the value does not match the field.
        if (value == null && field.getType().isPrimitive()) {
            LOG.error("Cannot assign null value to a primative-typed field: \"{}\"!", field);
            return;
        } else if (value != null && !TypeUtil.isType(value, TypeUtil.advance(field.getType()))) {
            LOG.error("Cannot assign value of type {} to field of type {}!", value.getClass(), field.getType());
        }

        // Fetch
        try {
            field.setAccessible(true);
            Preconditions.checkArgument(Modifier.isFinal(field) == false);
            field.set(obj, TypeUtil.advance(field.getType()).cast(value));
        } catch (IllegalAccessException | IllegalArgumentException e) {
            // Oh well...
            LOG.error("Reflective Access Failure:\n\tField: \"{}\"\n\tCause: {}", field, e.getLocalizedMessage());
        }
    }
    
    public static final void definalizeField(@Nonnull Field field) throws IllegalAccessException {
        try {
            Field modField = Field.class.getDeclaredField("modifiers");
            modField.setAccessible(true);
            modField.setInt(field, field.getModifiers() & ~Modifier.FINAL);
            Preconditions.checkArgument((field.getModifiers() & Modifier.FINAL) == 0);
        } catch (NoSuchFieldException | IllegalArgumentException ignore) {
            // This is impossible.
            throw new AssertionError("An impossible error occurred.", ignore);
        }
    }

    /**
     * Determines if a given class has a constructor with parameter types matching the given types.
     *
     * @param clazz the class to search for a constructor in.
     * @param types the types of the parameters that the constructor should accept.
     * @return if there exists a constructor matching the requisite parameters.
     */
    public static final boolean hasConstructorFor(@Nonnull Class<?> clazz, @Nonnull Class<?>... types) {
        // Validate.
        Preconditions.checkNotNull(clazz);
        Preconditions.checkNotNull(types);

        // Find a constructor.
        try {
            Constructor<?> constructor = clazz.getConstructor(types);
            return constructor != null;
        } catch (SecurityException | NoSuchMethodException e) {
            // Essentially, no.
        }
        // This would just be no.
        return false;
    }

    /**
     * Attempts to instantiate a class by finding and invoking a constructor that takes the given
     * parameters, in order.
     *
     * @param <T> the type of the class to instantiate.
     * @param clazz the class to instantiate.
     * @param parameters the parameters that the class should be instantiated with.
     * @return the new instance, or the empty optional if was unable to instantiate with given
     * parameters.
     */
    @Nonnull
    public static final <T> Optional<T> attemptInstantiate(@Nonnull Class<T> clazz, @Nonnull Object... parameters) {
        Objects.requireNonNull(clazz);
        Objects.requireNonNull(parameters);
        Class<?> types[] = new Class<?>[parameters.length];
        for (int i = 0; i < parameters.length; i++) {
            types[i] = parameters[i].getClass();
        }
        try {
            return Optional.of(clazz.getConstructor(types).newInstance(parameters));
        } catch (SecurityException | IllegalAccessException e) {
            LOG.error("Unable to access constructor for class {0}!", clazz.getName());
        } catch (IllegalArgumentException e) {
            LOG.error("The following error should not have occured!");
            LOG.error("Error: ", e);
        } catch (InstantiationException e) {
            LOG.error("Unable to instantiate class {0}!\nGiven reason is \"{1}\"!", clazz.getName(), e.getMessage());
        } catch (InvocationTargetException e) {
            LOG.error("The constructor for class {0} threw an error!\nGiven reason is: \"{1}\"!", clazz.getName(), e.getCause());
        } catch (NoSuchMethodException e) {
            LOG.error("The class {0} does not provide a constructor with parameters of types: {1}!", clazz.getName(), Arrays.asList(types));
        }
        return Optional.empty();
    }

}
