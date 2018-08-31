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

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import java.util.Optional;
import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;

/**
 *
 * @author Ryan
 */
public interface Config {

    /**
     * Determines if the configuration contains an entry of the given type with
     * the given key.
     *
     * @param <V>
     * @param key
     * @param type
     * @return
     */
    <V> boolean hasEntry(@Nonnull String key, @Nonnull Class<V> type);

    /**
     * Method to retrieve a configuration element with the given type from the
     * configuration.
     *
     * @param <V> the type of the configuration entry.
     * @param key the key for the configuration entry to get.
     * @param type the type of the configuration entry, as a type token.
     * @return an optional containing the configuration entry with the given key
     * and type, or the empty optional.
     */
    @Nonnull
    <V> Optional<ConfigEntry<V>> getEntry(@Nonnull String key, @Nonnull Class<V> type);

    /**
     * Retrieves all entries within the given configuration.
     *
     * @return a {@link ImmutableSet} containing all this configuration's
     * entries.
     */
    @Nonnull
    ImmutableMap<String, ConfigEntry<?>> getAllEntries();

    /**
     * Retrieves the configuration adapter used internally by this
     * configuration.
     *
     * @return the configuration adapter used by this configuration.
     */
    @Nonnull
    ConfigAdapter getAdapter();

    /**
     * Suggests to the configuration that it should reload, at the lowest
     * possible reload level.
     */
    void refresh();

    /**
     * Reloads the configuration at the given update level, by transcribing all
     * the values from the configuration source adapter to each internal
     * element.
     *
     * @param reloadLevel the level to reload the configuration at.
     */
    void reload(@Nonnegative short reloadLevel);

}
