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
package com.github.quikmod.quikutil.function;

import com.github.quikmod.quikutil.NumUtil;
import com.github.quikmod.quikutil.function.IntConsumer1;
import com.github.quikmod.quikutil.function.IntConsumer2;
import com.github.quikmod.quikutil.function.IntConsumer3;
import java.util.Optional;
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
public class IntConsumerTests {
    
    public IntConsumerTests() {
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

    /**
     * Test of IntConsumer interface.
     */
    @Test
    public void testIntConsumer1AndThen() {
        System.out.println("andThen");
        
        // IntConsumer1
        final IntConsumer1 intConsumer1 = (a) -> System.out.printf("ic1: %1$d%n", a);
        intConsumer1.andThen(intConsumer1).accept(0);
        
        // IntConsumer3
        final IntConsumer2 intConsumer2 = (a, b) -> System.out.printf("ic1: %1$d, %2$d%n", a, b);
        intConsumer2.andThen(intConsumer2).accept(0, 1);
        
        // IntConsumer3
        final IntConsumer3 intConsumer3 = (a, b, c) -> System.out.printf("ic1: %1$d, %2$d, %3$d%n", a, b, c);
        intConsumer3.andThen(intConsumer3).accept(0, 1, 2);
    }
    
}
