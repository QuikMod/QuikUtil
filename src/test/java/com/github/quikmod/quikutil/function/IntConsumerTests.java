/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
