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

import com.google.common.collect.ImmutableList;
import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;

/**
 * Class representing a configuration entry.
 *
 * @author Ryan
 *
 * @param <V> the type of the element.
 */
public interface ConfigEntry<V> {

    /**
     * Retrieves the key associated with this configuration entry.
     *
     * @return the key associated with this entry.
     */
    @Nonnull
    String getKey();

    /**
     * Retrieves the comment associated with this configuration entry.
     *
     * Required to return non-null, to prevent null pointer exceptions. In the
     * case there is no comment associated with the entry this method should
     * instead return the empty string.
     *
     * @return the comment associated with this entry.
     */
    @Nonnull
    String getComment();

    /**
     * Retrieves the type token representing this entry's type.
     *
     * @return the class representing this entry's type.
     */
    @Nonnull
    Class<V> getValueType();

    /**
     * Retrieves the default value associated with this entry, as defined by the
     * application.
     *
     * @return the default value associated with this entry.
     */
    @Nonnull
    V getDefaultValue();

    /**
     * Retrieves the value that was previously associated with this entry. In
     * the case that there was no previous value this will return the default
     * value instead.
     *
     * @return the value to be associated with this configuration entry next, or
     * the current value if there exists no upcoming value.
     */
    @Nonnull
    V getPriorValue();

    /**
     * Retrieves the value associated with this entry.
     *
     * @return the value associated with this entry.
     */
    @Nonnull
    V getValue();

    /**
     * Retrieves the value that is to be associated with this entry on the next
     * configuration reload of adequate level.
     *
     * @return the value to be associated with this configuration entry next, or
     * the current value if there exists no upcoming value.
     */
    @Nonnull
    V getFutureValue();

    /**
     * Determines the reload level required for the future value of the
     * configuration entry to become the current value.
     *
     * As level {@literal 255} is the maximum value, a full configuration reload
     * will occur at level 255.
     *
     * @return the reload level required for the configuration entry to fully
     * reload.
     */
    @Nonnegative
    short getReloadLevel();

    /**
     * Retrieves an immutable list of constraints on this configuration entry's
     * value, as defined by the application at the time that the configuration
     * was constructed.
     *
     * @return an immutable list containing all the constraints on the entry's
     * value.
     */
    @Nonnull
    ImmutableList<ConfigEntryConstraint<V>> getConstraints();

    /**
     * Sets the value that is to be associated with this entry on the next
     * configuration reload of adequate level.
     *
     * @param newValue the new value of this configuration element.
     */
    void setValue(@Nonnull V newValue);

    /**
     * Reloads the configuration element at the given update level, by
     * transcribing the elements value from the configuration source.
     *
     * Specifically this method will do the following:
     * <ol>
     * <li><span style="text-decoration:line-through">Fetch the entry's new
     * value from the configuration source.</span></li>
     * <li><span style="text-decoration:line-through">Validate the new fetched
     * value against all constraints.</span></li>
     * <li><span style="text-decoration:line-through">Set the next value to the
     * validated fetched value.</span></li>
     * <li>Checks if the {@link ConfigState#getCurrentLevel()} is less than
     * {@link #getReloadLevel()}</li>
     * <li>Set the previous value to the current value.</li>
     * <li>Set the current value to the next value.</li>
     * </ol>
     */
    void reload();

}
