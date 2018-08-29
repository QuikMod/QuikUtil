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
public class ContextedRuntimeExceptionTest {
    
    public ContextedRuntimeExceptionTest() {
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
     * Test of getMessage method, of class ContextedRuntimeException.
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
        final ContextedRuntimeException instance = new ContextedRuntimeException(testMessage);
        instance.getContext().withEntry("ctx1", "test value");
        
        // Perform Assertions.
        Truth.assertWithMessage("ContextedRuntimeException.getMessage() returns correct message.")
                .that(instance.getMessage())
                .isEqualTo(expectedMessage);
    }

    /**
     * Test of getRawMessage method, of class ContextedRuntimeException.
     */
    @Test
    public void testGetRawMessage() {
        // Log Test Start
        System.out.println("getRawMessage");
        
        // Define Constants
        final String testMessage = "Test Message...";
        final ContextedRuntimeException instance = new ContextedRuntimeException(testMessage);
        
        // Perform Assertions
        Truth.assertWithMessage("ContextedRuntimeException.getRawMessage() returns correct raw exception message.")
                .that(instance.getRawMessage())
                .isEqualTo(testMessage);
    }
    
    /**
     * Test of getRawMessage method, of class ContextedRuntimeException.
     * @throws com.github.quikmod.quikutil.exception.ContextedRuntimeException
     */
    @Test(expected = ContextedRuntimeException.class)
    public void testContextedRuntimeException() throws ContextedRuntimeException {
        // Log Test Start
        System.out.println("ContextedRuntimeException");
        
        // Create Instance.
        final ContextedRuntimeException instance = new ContextedRuntimeException("Test ContextedRuntimeException")
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
        System.out.println("ContextedRuntimeException");
        
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
