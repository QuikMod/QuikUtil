/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.quikmod.quikutil;

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
public class NumUtilTest {
    
    public NumUtilTest() {
        // Oh well...
        System.out.println(new NumUtil().toString());
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
     * Test of toInteger method, of class NumUtil.
     */
    @Test
    public void testToInteger() {
        System.out.println("toInteger");
        assertEquals(Optional.empty(), NumUtil.toInteger(null));
        assertEquals(Optional.empty(), NumUtil.toInteger(""));
        assertEquals(Optional.empty(), NumUtil.toInteger("GAHSDKJfhasd"));
        assertEquals(Optional.of(1), NumUtil.toInteger("1"));
    }

    /**
     * Test of toLong method, of class NumUtil.
     */
    @Test
    public void testToLong() {
        System.out.println("toLong");
        assertEquals(Optional.empty(), NumUtil.toLong(null));
        assertEquals(Optional.empty(), NumUtil.toLong(""));
        assertEquals(Optional.empty(), NumUtil.toLong("GAHSDKJfhasd"));
        assertEquals(Optional.of(1l), NumUtil.toLong("1"));
    }

    /**
     * Test of toFloat method, of class NumUtil.
     */
    @Test
    public void testToFloat() {
        System.out.println("toFloat");
        assertEquals(Optional.empty(), NumUtil.toFloat(null));
        assertEquals(Optional.empty(), NumUtil.toFloat(""));
        assertEquals(Optional.empty(), NumUtil.toFloat("GAHSDKJfhasd"));
        assertEquals(Optional.of(1f), NumUtil.toFloat("1.0"));
    }

    /**
     * Test of toDouble method, of class NumUtil.
     */
    @Test
    public void testToDouble() {
        System.out.println("toDouble");
        assertEquals(Optional.empty(), NumUtil.toDouble(null));
        assertEquals(Optional.empty(), NumUtil.toDouble(""));
        assertEquals(Optional.empty(), NumUtil.toDouble("GAHSDKJfhasd"));
        assertEquals(Optional.of(1d), NumUtil.toDouble("1.0"));
    }

    /**
     * Test of inRange method, of class NumUtil.
     */
    @Test
    public void testInRange_int() {
        System.out.println("inRange_int");
        assertEquals(0, NumUtil.inRange(100, 0, 0));
        assertEquals(0, NumUtil.inRange(-100, 0, 100));
        assertEquals(0, NumUtil.inRange(0, 0, 100));
        assertEquals(10, NumUtil.inRange(10, 0, 100));
        assertEquals(100, NumUtil.inRange(100, 0, 100));
        assertEquals(100, NumUtil.inRange(1000, 0, 100));
    }

    /**
     * Test of inRange method, of class NumUtil.
     */
    @Test
    public void testInRange_long() {
        System.out.println("inRange_long");
        assertEquals(0l, NumUtil.inRange(100l, 0l, 0l));
        assertEquals(0l, NumUtil.inRange(-100l, 0l, 100l));
        assertEquals(0l, NumUtil.inRange(0l, 0l, 100l));
        assertEquals(10l, NumUtil.inRange(10l, 0l, 100l));
        assertEquals(100l, NumUtil.inRange(100l, 0l, 100l));
        assertEquals(100l, NumUtil.inRange(1000l, 0l, 100l));
    }

    /**
     * Test of inRange method, of class NumUtil.
     */
    @Test
    public void testInRange_float() {
        System.out.println("inRange_float");
        assertEquals(0f, NumUtil.inRange(100f, 0f, 0f), 0f);
        assertEquals(0f, NumUtil.inRange(-100f, 0f, 100f), 0f);
        assertEquals(0f, NumUtil.inRange(0f, 0f, 100f), 0f);
        assertEquals(10f, NumUtil.inRange(10f, 0f, 100f), 0f);
        assertEquals(100f, NumUtil.inRange(100f, 0f, 100f), 0f);
        assertEquals(100f, NumUtil.inRange(1000f, 0f, 100f), 0f);
    }

    /**
     * Test of inRange method, of class NumUtil.
     */
    @Test
    public void testInRange_double() {
        System.out.println("inRange_double");
        assertEquals(0d, NumUtil.inRange(100d, 0d, 0d), 0d);
        assertEquals(0d, NumUtil.inRange(-100d, 0d, 100d), 0d);
        assertEquals(0d, NumUtil.inRange(0d, 0d, 100d), 0d);
        assertEquals(10d, NumUtil.inRange(10d, 0d, 100d), 0d);
        assertEquals(100d, NumUtil.inRange(100d, 0d, 100d), 0d);
        assertEquals(100d, NumUtil.inRange(1000d, 0d, 100d), 0d);
    }
    
}
