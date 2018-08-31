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
package com.github.quikmod.quikutil.configuration.impl;

import com.github.quikmod.quikutil.configuration.ConfigAdapter;
import com.github.quikmod.quikutil.configuration.ConfigEntry;
import com.github.quikmod.quikutil.configuration.ConfigEntryConstraint;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;

/**
 * Basic implementation of the {@link ConfigEntry} interface.
 * 
 * @author Ryan
 * @param <V> the type of the configuration entry value.
 */
public final class BasicConfigEntry<V> implements ConfigEntry<V> {

    @Nonnull
    private final String key;
    @Nonnull
    private final String comment;
    @Nonnull
    private final Class<V> valueType;
    @Nonnull
    private final V defaultValue;
    @Nonnull
    private V priorValue;
    @Nonnull
    private V currentValue;
    @Nonnull
    private V futureValue;
    @Nonnegative
    private final short reloadLevel;
    @Nonnull
    private final ImmutableList<ConfigEntryConstraint<V>> constraints;
    @Nonnull
    private final ConfigAdapter adapter;

    public BasicConfigEntry(
            @Nonnull String key,
            @Nonnull String comment,
            @Nonnull Class<V> valueType,
            @Nonnull V defaultValue,
            @Nonnegative short reloadLevel,
            @Nonnull ImmutableList<ConfigEntryConstraint<V>> constraints,
            @Nonnull ConfigAdapter adapter
    ) {
        // Validate
        Preconditions.checkNotNull(key, "The key associated with a configuration entry may not be null!");
        Preconditions.checkNotNull(comment, "The comment associated with a configuration entry may not be null!");
        Preconditions.checkNotNull(valueType, "The value type token associated with a configuration entry may not be null!");
        Preconditions.checkNotNull(defaultValue, "The default value associated with a configuration entry may not be null!");
        Preconditions.checkArgument(reloadLevel >= 0, "The reload level associated with a configuration entry may not be negative!");
        Preconditions.checkNotNull(constraints, "The list of constraints associated with a configuration entry may not be null!");
        Preconditions.checkNotNull(adapter, "The configuration adapter associated with a configuration entry may not be null!");

        // Assign.
        this.key = key;
        this.comment = comment;
        this.valueType = valueType;
        this.defaultValue = defaultValue;
        this.priorValue = defaultValue;
        this.currentValue = defaultValue;
        this.futureValue = defaultValue;
        this.reloadLevel = reloadLevel;
        this.constraints = constraints;
        this.adapter = adapter;
    }

    @Override
    public String getKey() {
        return this.key;
    }

    @Override
    public String getComment() {
        return this.comment;
    }

    @Override
    public Class<V> getValueType() {
        return this.valueType;
    }

    @Override
    public V getDefaultValue() {
        return this.defaultValue;
    }

    @Override
    public V getPriorValue() {
        return this.priorValue;
    }

    @Override
    public V getValue() {
        return this.currentValue;
    }

    @Override
    public V getFutureValue() {
        return this.futureValue;
    }

    @Override
    public short getReloadLevel() {
        return this.reloadLevel;
    }

    @Override
    public ImmutableList<ConfigEntryConstraint<V>> getConstraints() {
        return this.constraints;
    }

    @Override
    public ConfigAdapter getAdapter() {
        return this.adapter;
    }

    @Override
    public void refresh() {
        this.reload((short) 0);
    }

    @Override
    public void reload(short reloadLevel) {
        // Step 1. Get the new value.
        final V newValue = this.adapter.getValue(this);

        // Step 2. Validate the new value.
        if (newValue == null || !this.constraints.stream().allMatch(c -> c.check(newValue))) {
            // In this case do nothing, we'll just pretend we did not see that value.
            return;
        }

        // Step 3. Transfer over the new value to the next value slot.
        this.futureValue = newValue;

        // Step 4. Check if reload level is high enough to transfer over into current.
        if (reloadLevel < this.reloadLevel) {
            // Looks like we shouldn't update current.
            return;
        }

        // Step 5. Set previous value to current value.
        this.priorValue = this.currentValue;

        // Step 6. Set current value to next value.
        this.currentValue = this.futureValue;
    }

}
