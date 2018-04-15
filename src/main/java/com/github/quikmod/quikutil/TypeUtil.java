/*
 */
package com.github.quikmod.quikutil;

import com.google.common.base.Preconditions;
import java.lang.reflect.Array;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Deque;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * A class to help with using types.
 *
 * @author RlonRyan
 */
public final class TypeUtil {

    /**
     * Converts a collection of type A, to an array of type A.
     *
     * @param <T> the type of the elements in the collection.
     * @param collection the collection to convert to an array.
     * @param type the class of the type of the elements in the collection.
     * @return the collection, as an array.
     * @exception NullPointerException if {@code type} is null.
     * @exception IllegalArgumentException if {@code type} is {@link
     * Void#TYPE}.
     * @exception NegativeArraySizeException if the specified {@code length} is negative
     */
    @Nonnull
    public static <T> T[] asArray(@Nonnull Collection<T> collection, @Nonnull Class<T> type) {
        // Validate
        Preconditions.checkNotNull(collection, "Cannot convert a null collection to an array!");
        Preconditions.checkNotNull(type, "The null type is invalid!");

        // Convert: Note this suppression cannot be avoided, given there is no reliable way to convert a class to an array class.
        @SuppressWarnings("unchecked")
        final T[] array = (T[]) Array.newInstance(type, collection.size());
        return collection.toArray(array);
    }

    /**
     * Creates a {@link ArrayList} containing the given elements. Unlike {@link Arrays#asList}, this
     * method returns a mutable list, that allows for addition and subtraction of elements. In order
     * to do so, however, this method has to create a shallow copy of the array, which contributes
     * to memory churn. The copying of elements is done by the {@link ArrayList} implementation.
     *
     * @param <T> the class of the objects in the array.
     * @param elements the elements to represent as a list.
     * @return a list containing all the given elements.
     */
    @Nonnull
    @SafeVarargs
    public static <T> List<T> asList(T... elements) {
        return new ArrayList<>(Arrays.asList(elements));
    }

    /**
     * Creates a {@link ArrayDeque} containing the given elements. Unlike {@link Arrays#asList},
     * this method returns a mutable list, that allows for addition and subtraction of elements. In
     * order to do so, however, this method has to create a shallow copy of the array, which
     * contributes to memory churn. The copying of elements is done by the {@link ArrayDeque}
     * implementation.
     *
     * @param <T> the class of the objects in the array.
     * @param elements the elements to represent as a list.
     * @return a dequeue containing all the given elements.
     */
    @Nonnull
    @SafeVarargs
    public static <T> Deque<T> asDeque(T... elements) {
        return new ArrayDeque<>(Arrays.asList(elements));
    }

    /**
     * Creates a {@link HashSet} containing the given elements. This method suffers from the same
     * issue outlined in {@link TypeUtil#asList}.
     *
     * @param <T> the class of the objects in the array.
     * @param elements the elements to represent as a set.
     * @return a HashSet view of the elements.
     */
    @Nonnull
    @SafeVarargs
    public static <T> Set<T> asSet(T... elements) {
        return new HashSet<>(Arrays.asList(elements));
    }

    /**
     * Determines if two deques are of equal composition and order.
     *
     * @param <T> The common type of the deques, inferred.
     * @param left The first of the two arrays to compare.
     * @param right The second of the two arrays to compare.
     * @return If the deques have the same number of elements, in the same order.
     */
    public static <T> boolean areDequesEqual(@Nullable Deque<T> left, @Nullable Deque<T> right) {
        if (left == right) {
            return true;
        } else if (left == null || right == null) {
            return false;
        } else if (left.size() != right.size()) {
            return false;
        } else if (left.isEmpty()) {
            return true;
        } else {
            final Iterator<T> li = left.iterator();
            final Iterator<T> ri = right.iterator();
            while (li.hasNext()) {
                if (!Objects.equals(li.next(), ri.next())) {
                    return false;
                }
            }
            return true;
        }
    }

    /**
     * Adds all the given elements to the given collection.
     *
     * @param <T> the type of the elements in the collection.
     * @param <C> the type of the collection, containing elements of type {@code T}.
     * @param collection the collection to add the given elements to.
     * @param elements the elements to add to the given collection.
     * @return a reference to the given collection, after having performed the addAll operation on
     * it.
     */
    @Nonnull
    @SafeVarargs
    public static <T, C extends Collection<T>> C addAll(@Nonnull C collection, @Nonnull T... elements) {
        collection.addAll(Arrays.asList(elements));
        return collection;
    }

    /**
     * Tests if a given object is of all given types.
     *
     * @param obj the object to test the types on.
     * @param types the types to test for presence.
     * @return if the given object is of all types.
     * @exception NullPointerException if any type is null.
     */
    @SafeVarargs
    public static boolean isAllTypes(@Nullable Object obj, @Nonnull Class<?>... types) {
        return (obj != null) && isAllTypes(classify(obj), types);
    }

    /**
     * Tests if a given object is of all given types.
     *
     * @param clazz the class to test the types on.
     * @param types the types to test for presence.
     * @return if the given object is of all types.
     * @exception NullPointerException if any type is null.
     */
    @SafeVarargs
    public static boolean isAllTypes(@Nullable Class<?> clazz, @Nonnull Class<?>... types) {
        // Validate.
        Preconditions.checkNotNull(types, "Cannot compare type to null typeset!");

        // If clazz is null, return false.
        if (clazz == null) {
            return false;
        }

        // Loop.
        for (Class<?> type : types) {
            if (!isType(clazz, type)) {
                return false;
            }
        }

        // Matched all types successfully.
        return true;
    }

    /**
     * Tests if a given object is of any of the given types.
     *
     * @param obj the object to test the types on.
     * @param types the types to test for presence.
     * @return if the given object was of any of the given types.
     * @exception NullPointerException if any of the types are null.
     */
    @SafeVarargs
    public static boolean isAnyType(@Nullable Object obj, @Nonnull Class<?>... types) {
        return (obj != null) && isAnyType(classify(obj), types);
    }

    /**
     * Tests if a given object is of any of the given types.
     *
     * @param clazz the class to test the types on.
     * @param types the types to test for presence.
     * @return if the given object was of any of the given types.
     * @exception NullPointerException if any of the types are null.
     */
    @SafeVarargs
    public static boolean isAnyType(@Nullable Class<?> clazz, @Nonnull Class<?>... types) {
        // Validate.
        Preconditions.checkNotNull(types, "Cannot compare type to null typeset!");

        // If clazz is null, return false.
        if (clazz == null) {
            return false;
        }

        // Loop.
        for (Class<?> type : types) {
            if (isType(clazz, type)) {
                return true;
            }
        }

        // Failed to match any type.
        return false;
    }

    /**
     * Tests if a given object is of the given type.
     *
     * @param obj the object to test the type of.
     * @param type the type to check for.
     * @return if the object was of the given type.
     * @exception NullPointerException if the given type is null.
     */
    public static boolean isType(@Nullable Object obj, @Nonnull Class<?> type) {
        return (obj != null) && isType(classify(obj), type);
    }

    /**
     * Tests if a given class is equal to or a subtype of the given type.
     *
     * @param clazz the class to test the type of.
     * @param type the type to check for.
     * @return if the object was of the given type.
     * @exception NullPointerException if the given class or type is null.
     */
    public static boolean isType(@Nullable Class<?> clazz, @Nonnull Class<?> type) {
        // Validate
        Preconditions.checkNotNull(type, "The null type is considered invalid!");

        // Check
        return (clazz != null) && type.isAssignableFrom(clazz);
    }

    /**
     * Casts an object to the given class. If the cast is impossible, or the given object null, then
     * the empty optional is returned instead.
     *
     * @param <T> the type to cast to.
     * @param obj the object to cast.
     * @param type the class of the type to cast to.
     * @return the casted object, or the empty optional.
     * @exception NullPointerException if the given type is null.
     */
    @Nonnull
    public static <T> Optional<T> cast(@Nullable Object obj, @Nonnull Class<T> type) {
        // Validate.
        Preconditions.checkNotNull(type, "Cannot cast to null type!");

        // Perform cast.
        if (obj != null && type.isAssignableFrom(obj.getClass())) {
            return Optional.of(type.cast(obj));
        } else {
            return Optional.empty();
        }
    }

    /**
     * Determines the class value of the given object, be it from casting to a class or by calling
     * object.getClass().
     *
     * @param obj the object to determine the class value of.
     * @return the class value of the object.
     */
    @Nonnull
    public static Class<?> classify(@Nonnull Object obj) {
        // Validate
        Preconditions.checkNotNull(obj, "Cannot classify a null object!");

        // Classify
        return (obj instanceof Class) ? (Class) obj : obj.getClass();
    }

    /**
     * Determines the object value of the given object, by returning the object itself or returning
     * null in the case that the object is a class.
     *
     * @param obj the object to determine the class value of.
     * @return the class value of the object.
     */
    @Nullable
    public static Object declassify(@Nullable Object obj) {
        // Classify
        return (obj instanceof Class) ? null : obj;
    }

    /**
     * Advances the type of a primitive class to that of the wrapper class. I.e. byte.class -&gt;
     * Byte.class.
     *
     * @param type the type-class to advance.
     * @return the advanced type-class.
     * @exception NullPointerException if the given type is null.
     */
    @Nonnull
    public static Class<?> advance(@Nonnull Class<?> type) {
        // Validate
        Preconditions.checkNotNull(type);

        // Advance
        if (boolean.class.equals(type)) {
            return Boolean.class;
        } else if (byte.class.equals(type)) {
            return Byte.class;
        } else if (char.class.equals(type)) {
            return Character.class;
        } else if (short.class.equals(type)) {
            return Short.class;
        } else if (int.class.equals(type)) {
            return Integer.class;
        } else if (long.class.equals(type)) {
            return Long.class;
        } else if (float.class.equals(type)) {
            return Float.class;
        } else if (double.class.equals(type)) {
            return Double.class;
        } else if (void.class.equals(type)) {
            return Void.class;
        } else {
            return type;
        }
    }

}
