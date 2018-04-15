/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.quikmod.quikutil;

import java.util.Deque;
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
public class StringUtilTest {
    
    public StringUtilTest() {
        // Oh well...
        System.out.println(new StringUtil().toString());
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
     * Test of tokenize method, of class StringUtil.
     */
    @Test
    public void testTokenize() {
        System.out.println("tokenize");
		final String input = "\"Testing This\" 1 2    3\t4";
		final Deque<String> expected = TypeUtil.asDeque("\"Testing This\"", "1", "2", "3", "4");
		assertTrue(TypeUtil.areDequesEqual(expected, StringUtil.tokenize(input)));
    }
    
}
