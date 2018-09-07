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

/**
 * Class representing a constraint on the value that a configuration entry may
 * take.
 *
 * @author Ryan
 * @param <V> the type of the configuration entry's value.
 */
public interface ConfigEntryConstraint<V> {

    /**
     * Retrieves the value type that this constraint applies to.
     *
     * @return a type token representing the type of value that this constraint
     * applies to.
     */
    @Nonnull
    Class<V> getValueType();

    /**
     * Checks if the given value meets this constraint.
     *
     * @param value the value to check this constraint against.
     * @return {@literal true} if and only if the value meets this constraint,
     * false otherwise.
     */
    boolean check(@Nonnull V value);

}
