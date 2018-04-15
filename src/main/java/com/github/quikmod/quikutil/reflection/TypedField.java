/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.quikmod.quikutil.reflection;

import com.github.quikmod.quikutil.TypeUtil;
import com.google.common.base.Preconditions;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Optional;
import java.util.stream.Stream;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 *
 * @author Ryan
 *
 * @param <T> the field type, automatically promoted, if was a primitive type.
 */
public final class TypedField<T> {

    private final Field field;
    private final Class<T> type;

    @Nonnull
    public static TypedField<?> of(@Nonnull Field field) {
        // Validate
        Preconditions.checkNotNull(field, "The field that a TypedField wraps may not be null!");

        // Wrap
        return new TypedField<>(field, TypeUtil.advance(field.getType()));
    }

    private TypedField(@Nonnull Field field, @Nonnull Class<T> type) {
        this.field = field;
        this.type = type;
    }

    @Nonnull
    public Field getField() {
        return field;
    }

    @Nonnull
    public Class<T> getType() {
        return type;
    }

    @Nullable
    public T get(@Nullable Object from) throws IllegalAccessException, IllegalArgumentException {
        return (T) this.field.get(from);
    }
    
    @Nonnull
    public Optional<T> attemptGet(@Nullable Object obj) {
        return (Optional<T>) ReflectionUtil.attemptGet(obj, this.field);
    }
    
    @Nonnull
    public Stream<T> attemptGetStream(@Nullable Object obj) {
        return attemptGet(obj).map(Stream::of).orElseGet(Stream::empty);
    }

    public void set(@Nullable Object obj, @Nullable T value) throws IllegalArgumentException, IllegalAccessException {
        this.field.set(obj, this.field);
    }
    
    public void attemptSet(@Nullable Object obj, @Nullable T value) {
        ReflectionUtil.attemptSet(obj, this.field, value);
    }
    
    public void attemptSet(@Nullable Object obj, @Nullable T value, boolean override) {
        ReflectionUtil.attemptSet(obj, this.field, value, override);
    }
    
    public <S> Optional<TypedField<S>> castOptional(@Nonnull Class<S> type) {
        if (TypeUtil.isType(this.type, type)) {
            return Optional.of((TypedField<S>)this);
        } else {
            return Optional.empty();
        }
    }
    
    public <S> Stream<TypedField<S>> castStream(@Nonnull Class<S> type) {
        if (TypeUtil.isType(this.type, type)) {
            return Stream.of((TypedField<S>)this);
        } else {
            return Stream.empty();
        }
    }
    
    public final boolean isAnnotationPresent(@Nonnull Class<? extends Annotation> anno) {
        return this.field.isAnnotationPresent(anno);
    }
    
    @SafeVarargs
    public final boolean isAnnotationPresent(@Nonnull Class<? extends Annotation>... annos) {
        for(Class<? extends Annotation> anno : annos) {
            if (!isAnnotationPresent(anno)) {
                return false;
            }
        }
        return true;
    }
    
    public boolean isAccessible(@Nullable Object obj) {
        return this.isStatic() || ((obj != null) && (TypeUtil.isType(obj, this.field.getDeclaringClass())));
    }

    public boolean isPublic() {
        return Modifier.isPublic(field);
    }
    
    public boolean isPrivate() {
        return Modifier.isPrivate(field);
    }
    
    public boolean isProtected() {
        return Modifier.isProtected(field);
    }
    
    public boolean isTransient() {
        return Modifier.isTransient(field);
    }
    
    public boolean isVolatile() {
        return Modifier.isVolatile(field);
    }
    
    public boolean isStatic() {
        return Modifier.isStatic(field);
    }
    
    public boolean isFinal() {
        return Modifier.isFinal(field);
    }

    @Override
    public boolean equals(Object obj) {
        return (this == obj) || ((obj instanceof TypedField) && (this.field.equals(((TypedField) obj).field)));
    }

    @Override
    public int hashCode() {
        return this.field.hashCode();
    }

    @Nonnull
    @Override
    public String toString() {
        return this.field.toString();
    }
    
    @Nonnull
    public String toStringShort() {
        return Modifier.toString(this.field.getModifiers()) + " " + this.field.getName();
    }
    
    @Nonnull
    public String toGenericString() {
        return this.field.toGenericString();
    }
    
    @Nonnull
    public String toValueStringShort(@Nullable Object obj) {
        return this.toStringShort()+ " = " + this.attemptGet(obj).map(Object::toString).orElse("<unknown>");
    }
    
    @Nonnull
    public String toValueString(@Nullable Object obj) {
        return this.toString() + " = " + this.attemptGet(obj).map(Object::toString).orElse("<unknown>");
    }

}
