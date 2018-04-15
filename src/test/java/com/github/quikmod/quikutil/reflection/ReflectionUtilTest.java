/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.quikmod.quikutil.reflection;

import java.lang.reflect.Field;
import java.util.Optional;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static com.google.common.truth.Truth.*;
import static com.google.common.truth.Truth8.*;

/**
 *
 * @author Ryan
 */
public class ReflectionUtilTest {
    
    public int public_int = 0;
    public final int public_final_int = 0;
    public static int public_static_int = 0;
    public static final int public_static_final_int = 0;
    
    private int private_int = 0;
    private final int private_final_int = 0;
    private static int private_static_int = 0;
    private static final int private_static_final_int = 0;
    
    protected int protected_int = 0;
    protected final int protected_final_int = 0;
    protected static int protected_static_int = 0;
    protected static final int protected_static_final_int = 0;
    
    public ReflectionUtilTest() {
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
     * Test of streamConstructors method, of class ReflectionUtil.
     */
    @Test
    public void testStreamConstructors() {
        System.out.println("streamConstructors");
        ReflectionUtil
                .streamConstructors(this)
                .forEach(System.out::println);
    }

    /**
     * Test of streamFields method, of class ReflectionUtil.
     */
    @Test
    public void testStreamFields() {
        System.out.println("streamFields");
        ReflectionUtil
                .streamFields(this)
                .forEach(System.out::println);
    }

    /**
     * Test of streamAccessibleFields method, of class ReflectionUtil.
     */
    @Test
    public void testStreamAccessibleFields() {
        System.out.println("streamAccessibleFields");
        ReflectionUtil
                .streamAccessibleFields(this)
                .forEach(System.out::println);
    }

    /**
     * Test of streamMethods method, of class ReflectionUtil.
     */
    @Test
    public void testStreamMethods() {
        System.out.println("streamMethods");
        ReflectionUtil
                .streamMethods(this)
                .forEach(System.out::println);
    }

    /**
     * Test of streamAccessibleMethods method, of class ReflectionUtil.
     */
    @Test
    public void testStreamAccessibleMethods() {
        System.out.println("streamAccessibleMethods");
        ReflectionUtil
                .streamAccessibleMethods(this)
                .forEach(System.out::println);
    }

    /**
     * Test of streamValues method, of class ReflectionUtil.
     */
    @Test
    public void testStreamValues() {
        System.out.println("streamValues");
        ReflectionUtil
                .streamValues(this)
                .forEach(System.out::println);
    }

    /**
     * Test of attemptGet method, of class ReflectionUtil.
     */
    @Test
    public void testAttemptGet() {
        System.out.println("attemptGet");
        assertThat(true).isTrue();
    }

    /**
     * Test of attemptSet method, of class ReflectionUtil.
     */
    @Test
    public void testAttemptSet() {
        System.out.println("attemptSet");
        ReflectionUtil
                .streamFields(this)
                .map(f -> f.toValueStringShort(this))
                .map(s -> "pre : " + s)
                .forEach(System.out::println);
        ReflectionUtil
                .streamAccessibleFields(this.getClass())
                .flatMap(f -> f.castStream(Integer.class))
                .forEach(f -> f.attemptSet(null, 100, true));
        ReflectionUtil
                .streamFields(this)
                .map(f -> f.toValueStringShort(this))
                .map(s -> "post: " + s)
                .forEach(System.out::println);
        assertThat(true).isTrue();
    }

    /**
     * Test of hasConstructorFor method, of class ReflectionUtil.
     */
    @Test
    public void testHasConstructorFor() {
        System.out.println("hasConstructorFor");
        assertThat(ReflectionUtil.hasConstructorFor(this.getClass())).isTrue();
        assertThat(ReflectionUtil.hasConstructorFor(this.getClass(), Void.class)).isFalse();
        assertThat(ReflectionUtil.hasConstructorFor(Cloneable.class)).isFalse();
    }

    /**
     * Test of attemptInstantiate method, of class ReflectionUtil.
     */
    @Test
    public void testAttemptInstantiate() {
        System.out.println("attemptInstantiate");
        assertThat(ReflectionUtil.attemptInstantiate(this.getClass())).isPresent();
    }
    
}
