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

import com.github.quikmod.quikutil.configuration.impl.BasicConfig;
import com.github.quikmod.quikutil.configuration.impl.BasicConfigEntry;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import javax.annotation.Nonnull;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Ryan
 */
public class ConfigTest {
    
    public ConfigTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
    
    @Nonnull Config createTestConfig(@Nonnull ConfigAdapter adapter) {
        return new BasicConfig(adapter, createTestConfigSet(adapter));
    }
    
    @Nonnull
    public Set<ConfigEntry<?>> createTestConfigSet(@Nonnull ConfigAdapter adapter) {
        final Set<ConfigEntry<?>> configSet = new HashSet<>();
        configSet.add(new BasicConfigEntry<>("test.int.0", "Test integer #1", Integer.class, 0, (short)0, ImmutableList.of(), adapter));
        return configSet;
    }

    /**
     * Test of hasEntry method, of class Config.
     */
    @Test
    public void testHasEntry() {
        System.out.println("hasEntry");
        final ConfigAdapter adapter = new DummyConfigAdapter();
        final Config instance = createTestConfig(adapter);
        assertTrue(instance.hasEntry("test.int.0", Integer.class));
        assertFalse(instance.hasEntry("test.int.0", Boolean.class));
    }

    /**
     * Test of getEntry method, of class Config.
     */
    @Test
    public void testGetEntry() {
        System.out.println("getEntry");
        final ConfigAdapter adapter = new DummyConfigAdapter();
        final Config instance = createTestConfig(adapter);
        Optional<ConfigEntry<Integer>> result = instance.getEntry("test.int.0", Integer.class);
        assertTrue(result.isPresent());
        assertEquals(result.get().getValue().intValue(), 0);
    }
    
}
