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
package com.github.quikmod.quikutil.reflection;

import java.lang.reflect.Method;
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
public class ModifierTest {

    public ModifierTest() {
        // Oh well...
        System.out.println(new Modifier().toString());
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
     * Test of isPublic method, of class ModifierUtil.
     */
    @Test
    public void testIsPublic() {
        System.out.println("isPublic");
        for (Method m : System.class.getMethods()) {
            assertEquals(Modifier.isPublic(m.getModifiers()), Modifier.isPublic(m));
        }
    }

    /**
     * Test of isPrivate method, of class ModifierUtil.
     */
    @Test
    public void testIsPrivate() {
        System.out.println("isPrivate");
        for (Method m : System.class.getMethods()) {
            assertEquals(Modifier.isPrivate(m.getModifiers()), Modifier.isPrivate(m));
        }
    }

    /**
     * Test of isProtected method, of class ModifierUtil.
     */
    @Test
    public void testIsProtected() {
        System.out.println("isProtected");
        for (Method m : System.class.getMethods()) {
            assertEquals(Modifier.isProtected(m.getModifiers()), Modifier.isProtected(m));
        }
    }

    /**
     * Test of isStatic method, of class ModifierUtil.
     */
    @Test
    public void testIsStatic() {
        System.out.println("isStatic");
        for (Method m : System.class.getMethods()) {
            assertEquals(Modifier.isStatic(m.getModifiers()), Modifier.isStatic(m));
        }
    }

    /**
     * Test of isFinal method, of class ModifierUtil.
     */
    @Test
    public void testIsFinal() {
        System.out.println("isFinal");
        for (Method m : System.class.getMethods()) {
            assertEquals(Modifier.isFinal(m.getModifiers()), Modifier.isFinal(m));
        }
    }

    /**
     * Test of isSynchronized method, of class ModifierUtil.
     */
    @Test
    public void testIsSynchronized() {
        System.out.println("isSynchronized");
        for (Method m : System.class.getMethods()) {
            assertEquals(Modifier.isSynchronized(m.getModifiers()), Modifier.isSynchronized(m));
        }
    }

    /**
     * Test of isVolatile method, of class ModifierUtil.
     */
    @Test
    public void testIsVolatile() {
        System.out.println("isVolatile");
        for (Method m : System.class.getMethods()) {
            assertEquals(Modifier.isVolatile(m.getModifiers()), Modifier.isVolatile(m));
        }
    }

    /**
     * Test of isTransient method, of class ModifierUtil.
     */
    @Test
    public void testIsTransient() {
        System.out.println("isTransient");
        for (Method m : System.class.getMethods()) {
            assertEquals(Modifier.isTransient(m.getModifiers()), Modifier.isTransient(m));
        }
    }

    /**
     * Test of isNative method, of class ModifierUtil.
     */
    @Test
    public void testIsNative() {
        System.out.println("isNative");
        for (Method m : System.class.getMethods()) {
            assertEquals(Modifier.isNative(m.getModifiers()), Modifier.isNative(m));
        }
    }

    /**
     * Test of isInterface method, of class ModifierUtil.
     */
    @Test
    public void testIsInterface() {
        System.out.println("isInterface");
        for (Method m : System.class.getMethods()) {
            assertEquals(Modifier.isInterface(m.getModifiers()), Modifier.isInterface(m));
        }
    }

    /**
     * Test of isAbstract method, of class ModifierUtil.
     */
    @Test
    public void testIsAbstract() {
        System.out.println("isAbstract");
        for (Method m : System.class.getMethods()) {
            assertEquals(Modifier.isAbstract(m.getModifiers()), Modifier.isAbstract(m));
        }
    }

    /**
     * Test of isStrict method, of class ModifierUtil.
     */
    @Test
    public void testIsStrict() {
        System.out.println("isStrict");
        for (Method m : System.class.getMethods()) {
            assertEquals(Modifier.isStrict(m.getModifiers()), Modifier.isStrict(m));
        }
    }

}
