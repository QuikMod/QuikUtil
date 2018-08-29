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

import java.lang.reflect.Member;
import javax.annotation.Nonnull;

/**
 *
 * @author Ryan
 */
public final class Modifier extends java.lang.reflect.Modifier {
    
    /**
     * Return {@code true} if the integer argument includes the
     * {@code public} modifier, {@code false} otherwise.
     *
     * @param m a member
     * @return {@code true} if {@code mod} includes the
     * {@code public} modifier; {@code false} otherwise.
     */
    public static boolean isPublic(@Nonnull Member m) {
        return (m.getModifiers() & PUBLIC) != 0;
    }

    /**
     * Return {@code true} if the integer argument includes the
     * {@code private} modifier, {@code false} otherwise.
     *
     * @param m a member
     * @return {@code true} if {@code mod} includes the
     * {@code private} modifier; {@code false} otherwise.
     */
    public static boolean isPrivate(@Nonnull Member m) {
        return (m.getModifiers() & PRIVATE) != 0;
    }

    /**
     * Return {@code true} if the integer argument includes the
     * {@code protected} modifier, {@code false} otherwise.
     *
     * @param m a member
     * @return {@code true} if {@code mod} includes the
     * {@code protected} modifier; {@code false} otherwise.
     */
    public static boolean isProtected(@Nonnull Member m) {
        return (m.getModifiers() & PROTECTED) != 0;
    }

    /**
     * Return {@code true} if the integer argument includes the
     * {@code static} modifier, {@code false} otherwise.
     *
     * @param m a member
     * @return {@code true} if {@code mod} includes the
     * {@code static} modifier; {@code false} otherwise.
     */
    public static boolean isStatic(@Nonnull Member m) {
        return (m.getModifiers() & STATIC) != 0;
    }

    /**
     * Return {@code true} if the integer argument includes the
     * {@code final} modifier, {@code false} otherwise.
     *
     * @param m a member
     * @return {@code true} if {@code mod} includes the
     * {@code final} modifier; {@code false} otherwise.
     */
    public static boolean isFinal(@Nonnull Member m) {
        return (m.getModifiers() & FINAL) != 0;
    }

    /**
     * Return {@code true} if the integer argument includes the
     * {@code synchronized} modifier, {@code false} otherwise.
     *
     * @param m a member
     * @return {@code true} if {@code mod} includes the
     * {@code synchronized} modifier; {@code false} otherwise.
     */
    public static boolean isSynchronized(@Nonnull Member m) {
        return (m.getModifiers() & SYNCHRONIZED) != 0;
    }

    /**
     * Return {@code true} if the integer argument includes the
     * {@code volatile} modifier, {@code false} otherwise.
     *
     * @param m a member
     * @return {@code true} if {@code mod} includes the
     * {@code volatile} modifier; {@code false} otherwise.
     */
    public static boolean isVolatile(@Nonnull Member m) {
        return (m.getModifiers() & VOLATILE) != 0;
    }

    /**
     * Return {@code true} if the integer argument includes the
     * {@code transient} modifier, {@code false} otherwise.
     *
     * @param m a member
     * @return {@code true} if {@code mod} includes the
     * {@code transient} modifier; {@code false} otherwise.
     */
    public static boolean isTransient(@Nonnull Member m) {
        return (m.getModifiers() & TRANSIENT) != 0;
    }

    /**
     * Return {@code true} if the integer argument includes the
     * {@code native} modifier, {@code false} otherwise.
     *
     * @param m a member
     * @return {@code true} if {@code mod} includes the
     * {@code native} modifier; {@code false} otherwise.
     */
    public static boolean isNative(@Nonnull Member m) {
        return (m.getModifiers() & NATIVE) != 0;
    }

    /**
     * Return {@code true} if the integer argument includes the
     * {@code interface} modifier, {@code false} otherwise.
     *
     * @param m a member
     * @return {@code true} if {@code mod} includes the
     * {@code interface} modifier; {@code false} otherwise.
     */
    public static boolean isInterface(@Nonnull Member m) {
        return (m.getModifiers() & INTERFACE) != 0;
    }

    /**
     * Return {@code true} if the integer argument includes the
     * {@code abstract} modifier, {@code false} otherwise.
     *
     * @param m a member
     * @return {@code true} if {@code mod} includes the
     * {@code abstract} modifier; {@code false} otherwise.
     */
    public static boolean isAbstract(@Nonnull Member m) {
        return (m.getModifiers() & ABSTRACT) != 0;
    }

    /**
     * Return {@code true} if the integer argument includes the
     * {@code strictfp} modifier, {@code false} otherwise.
     *
     * @param m a member
     * @return {@code true} if {@code mod} includes the
     * {@code strictfp} modifier; {@code false} otherwise.
     */
    public static boolean isStrict(@Nonnull Member m) {
        return (m.getModifiers() & STRICT) != 0;
    }
    
}
