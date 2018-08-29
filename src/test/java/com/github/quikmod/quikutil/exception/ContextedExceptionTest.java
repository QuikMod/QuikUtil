/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.quikmod.quikutil.exception;

import com.github.quikmod.quikutil.StringUtil;
import com.google.common.truth.Truth;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Ryan
 */
public class ContextedExceptionTest {
    
    public ContextedExceptionTest() {
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
     * Test of getMessage method, of class ContextedException.
     */
    @Test
    public void testGetMessage() {
        // Log Test Start
        System.out.println("getMessage");
        
        // Create Constants.
        final String testMessage = "Test Message...";
        final String testContext = "ctx1: test value";
        
        // Create Synthetic Message.
        final StringBuilder sb = new StringBuilder();
        sb.append("\n\nException Message:\n\n");
        sb.append(StringUtil.trimEnd(StringUtil.increaseIndent(testMessage)));
        sb.append("\n\nException Context:\n\n");
        sb.append(StringUtil.trimEnd(StringUtil.increaseIndent(testContext)));
        sb.append("\n\nException Stacktrace:\n");
        final String expectedMessage = sb.toString();
        
        // Crate Instance.
        final ContextedException instance = new ContextedException(testMessage);
        instance.getContexts().withEntry("ctx1", "test value");
        
        // Perform Assertions.
        Truth.assertWithMessage("ContextedException.getMessage() returns correct message.")
                .that(instance.getMessage())
                .isEqualTo(expectedMessage);
    }

    /**
     * Test of getRawMessage method, of class ContextedException.
     */
    @Test
    public void testGetRawMessage() {
        // Log Test Start
        System.out.println("getRawMessage");
        
        // Define Constants
        final String testMessage = "Test Message...";
        final ContextedException instance = new ContextedException(testMessage);
        
        // Perform Assertions
        Truth.assertWithMessage("ContextedException.getRawMessage() returns correct raw exception message.")
                .that(instance.getRawMessage())
                .isEqualTo(testMessage);
    }
    
    /**
     * Test of getRawMessage method, of class ContextedException.
     * @throws com.github.quikmod.quikutil.exception.ContextedException
     */
    @Test(expected = ContextedException.class)
    public void testContextedException() throws ContextedException {
        // Log Test Start
        System.out.println("ContextedException");
        
        // Create Instance.
        final ContextedException instance = new ContextedException("Test ContextedException")
                .withContext("ctx1", "Test context 1.")
                .withContext("ctx2", "Test context 2.")
                .withContext("ctx3", "Test context 3.");
        
        // Log Instance Stack Trace.
        instance.printStackTrace();
  
        // Throw the throwable.
        throw instance;
    }
    
    @Test(expected = AssertionError.class)
    public void testExceptionBuilder() throws AssertionError {
        // Log Test Start
        System.out.println("ContextedException");
        
        // Create Instance.
        final ExceptionMessageBuilder eb = new ExceptionMessageBuilder("Testing 1, 2, 3...");
        
        // Add stuff.
        eb.withDescription("This is a test assertion error.");
        eb.withContext("ctx1", "Test context 1.");
        eb.withContext("ctx2", "Test context 2.");
        eb.withContext("ctx3", "Test context 3.");
        
        // Create throwable.
        final AssertionError t = new AssertionError(eb.build());
        
        // Log Instance Stack Trace.
        t.printStackTrace();
  
        // Throw the throwable.
        throw t;
    }
    
}
