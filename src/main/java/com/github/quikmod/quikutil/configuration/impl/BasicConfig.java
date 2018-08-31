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

import com.github.quikmod.quikutil.TypeUtil;
import com.github.quikmod.quikutil.configuration.Config;
import com.github.quikmod.quikutil.configuration.ConfigAdapter;
import com.github.quikmod.quikutil.configuration.ConfigEntry;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;
import java.util.Optional;
import java.util.Set;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 *
 * @author Ryan
 */
public class BasicConfig implements Config {
    
    @Nonnull
    private final ConfigAdapter adapter;
    @Nonnull
    private final ImmutableMap<String, ConfigEntry<?>> entries;

    public BasicConfig(
            @Nonnull ConfigAdapter adapter,
            @Nonnull Set<ConfigEntry<?>> entries
    ) {
        // Validate.
        Preconditions.checkNotNull(adapter, "The configuration adapter associated with a configuration may not be null!");
        Preconditions.checkNotNull(entries, "The set of entries associated with a configuration may not be null!");
        
        // Convert.
        ImmutableMap<String, ConfigEntry<?>> entrymap = entries.stream().collect(ImmutableMap.toImmutableMap(e -> e.getKey(), e -> e));
        
        // Assign.
        this.adapter = adapter;
        this.entries = entrymap;
    }

    @Override
    public <V> boolean hasEntry(String key, Class<V> type) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public <V> Optional<ConfigEntry<V>> getEntry(String key, Class<V> type) {
        // Attempt to get.
        @Nullable
        final ConfigEntry<?> temp = this.entries.get(key);
        
        // Do the thing.
        return Optional.ofNullable(temp)
                .filter(e -> TypeUtil.isType(e.getValueType(), type))
                .map(e -> (ConfigEntry<V>) e);
    }

    @Override
    public ImmutableMap<String, ConfigEntry<?>> getAllEntries() {
        return this.entries;
    }

    @Override
    public ConfigAdapter getAdapter() {
        return this.adapter;
    }

    @Override
    public void refresh() {
        this.entries.forEach((k, v) -> v.refresh());
    }

    @Override
    public void reload(short reloadLevel) {
        this.entries.forEach((k, v) -> v.reload(reloadLevel));
    }
    
}
