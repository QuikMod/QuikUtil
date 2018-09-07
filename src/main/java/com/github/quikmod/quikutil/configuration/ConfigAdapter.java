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
package com.github.quikmod.quikutil.configuration;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 *
 * @author Ryan
 */
public interface ConfigAdapter {

    /**
     * Method to check whether or not the given configuration adapter supports
     * the given type.
     *
     * @param <V> the type to check support for.
     * @param type the type token form of the type to check support for.
     * @return {@literal true} if and only if the given type is supported by the
     * configuration system, {@literal false} otherwise.
     */
    <V> boolean isTypeSupported(@Nonnull Class<V> type);

    /**
     * Method to check whether or not the given configuration adapter supports
     * the given constraint type.
     *
     * This method should return false in the case that the type that the
     * constraint applies to is not supported by the configuration adapter.
     *
     * @param <V> the type that the constraint applies to.
     * @param constraint the constraint to check for support.
     * @return {@literal true} if and only if the given constraint and its type
     * is supported by the configuration system, {@literal false} otherwise.
     */
    <V> boolean isConstraintSupported(@Nonnull ConfigEntryConstraint<V> constraint);

    /**
     * Method to retrieve a configuration value from some source.
     *
     * @param <V> the type of the configuration entry.
     * @param entry the entry to get the value for.
     * @return an optional containing the configuration entry with the given key
     * and type, or the empty optional.
     */
    @Nullable
    <V> V getValue(@Nonnull ConfigEntry<V> entry);

}
