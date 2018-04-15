/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.quikmod.quikutil;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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
public class TypeUtilTest {
    
    public TypeUtilTest() {
        // Oh well...
        System.out.println(new TypeUtil().toString());
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
	 * Test of asArray method, of class TypeUtil.
	 */
	@Test
	public void testAsArray() {
		System.out.println("asArray");
		String[] expResult = new String[]{"One, Two, Three"};
		String[] result = TypeUtil.asArray(Arrays.asList(expResult), String.class);
		assertArrayEquals(expResult, result);
	}

	/**
	 * Test of asList method, of class TypeUtil.
	 */
	@Test
	public void testAsList() {
		System.out.println("asList");
		Object[] elements = new Object[]{"Testing", 1, 2, 3};
		List expResult = Arrays.asList(elements);
		List result = TypeUtil.asList(elements);
		assertEquals(expResult, result);
	}
	
	/**
	 * Test of asDeque method, of class TypeUtil.
	 */
	@Test
	public void testAsDeque() {
		System.out.println("asDeque");
		Object[] elements = new Object[]{"Testing", 1, 2, 3};
        Deque expected = new ArrayDeque(Arrays.asList(elements));
		Deque result = TypeUtil.asDeque(elements);
		assertTrue(TypeUtil.areDequesEqual(expected, result));
	}

	/**
	 * Test of asSet method, of class TypeUtil.
	 */
	@Test
	public void testAsSet() {
		System.out.println("asSet");
		Object[] elements = new Object[]{"Testing", 1, 2, 3};
        Set expResult = new HashSet(Arrays.asList(elements));
		Set result = TypeUtil.asSet(elements);
		assertEquals(expResult, result);
	}
	
	/**
	 * Test of areDequesEqual method, of class TypeUtil.
	 */
	@Test
	public void testAreDequesEqual() {
		System.out.println("areDequesEqual");
        
        // Test Same
        final Deque<Object> dq = new ArrayDeque<>();
        assertTrue(TypeUtil.areDequesEqual(dq, dq));
        
        // Test Null
        assertFalse(TypeUtil.areDequesEqual(dq, null));
        assertFalse(TypeUtil.areDequesEqual(null, dq));
		
		// Test Empty
		assertTrue(TypeUtil.areDequesEqual(new ArrayDeque<>(), new ArrayDeque<>()));
		
		// Test Equal
		assertTrue(TypeUtil.areDequesEqual(TypeUtil.asDeque(1), TypeUtil.asDeque(1)));
		assertTrue(TypeUtil.areDequesEqual(TypeUtil.asDeque(1, 2, 3, "4"), TypeUtil.asDeque(1, 2, 3, "4")));
		
		// Test Different Element
		assertFalse(TypeUtil.areDequesEqual(TypeUtil.asDeque(0), TypeUtil.asDeque(1)));
		assertFalse(TypeUtil.areDequesEqual(TypeUtil.asDeque(0, 1), TypeUtil.asDeque(1, 0)));
		assertFalse(TypeUtil.areDequesEqual(TypeUtil.asDeque(1, 2, 3, "5", 100), TypeUtil.asDeque(1, 2, 3, "4", 100)));
		
		// Test Different Cardinality
		assertFalse(TypeUtil.areDequesEqual(new ArrayDeque<>(), TypeUtil.asDeque(1)));
		assertFalse(TypeUtil.areDequesEqual(TypeUtil.asDeque(1), TypeUtil.asDeque(1, 0)));
		assertFalse(TypeUtil.areDequesEqual(TypeUtil.asDeque(1, 2, 3, "4"), TypeUtil.asDeque(1, 2, 3, "4", 100)));
	}

	/**
	 * Test of addAll method, of class TypeUtil.
	 */
	@Test
	public void testAddAll() {
		System.out.println("addAll");
		Object[] elements = new Object[] {"Testing", "Testing", "Testing"};
		Object[] added = new Object[]{1, 2, 3};
        Collection collection = new ArrayList(Arrays.asList(elements));
        Collection expResult = new ArrayList(Arrays.asList(elements));
		expResult.addAll(Arrays.asList(added));
		assertEquals(expResult, TypeUtil.addAll(collection, added));
	}

	/**
	 * Test of isAllTypes method, of class TypeUtil.
	 */
	@Test
	public void testIsAllTypes() {
		System.out.println("isAllTypes");
		assertTrue(TypeUtil.isAllTypes("My magical string.", Object.class));
		assertTrue(TypeUtil.isAllTypes("My magical string.", String.class));
		assertTrue(TypeUtil.isAllTypes("My magical string.", String.class, Object.class));
		assertFalse(TypeUtil.isAllTypes("My magical string.", String.class, Object.class, Number.class));
		assertFalse(TypeUtil.isAllTypes("My magical string.", String.class, Number.class));
		assertFalse(TypeUtil.isAllTypes("My magical string.", Object.class, Number.class));
		assertFalse(TypeUtil.isAllTypes("My magical string.", Number.class));
	}

	/**
	 * Test of isAnyType method, of class TypeUtil.
	 */
	@Test
	public void testIsAnyType() {
		System.out.println("isAnyType");
		assertTrue(TypeUtil.isAnyType("My magical string.", Object.class));
		assertTrue(TypeUtil.isAnyType("My magical string.", String.class));
		assertTrue(TypeUtil.isAnyType("My magical string.", String.class, Object.class));
		assertTrue(TypeUtil.isAnyType("My magical string.", String.class, Object.class, Number.class));
		assertTrue(TypeUtil.isAnyType("My magical string.", String.class, Number.class));
		assertTrue(TypeUtil.isAnyType("My magical string.", Object.class, Number.class));
		assertFalse(TypeUtil.isAnyType("My magical string.", Number.class));
		assertFalse(TypeUtil.isAnyType("My magical string.", Number.class, int.class));
	}

	/**
	 * Test of isType method, of class TypeUtil.
	 */
	@Test
	public void testIsType() {
		System.out.println("isType");
		
		// Primative
		assertTrue(TypeUtil.isType(0, Integer.class));
		assertTrue(TypeUtil.isType(0, Object.class));
		assertTrue(TypeUtil.isType(0, Number.class));
		assertFalse(TypeUtil.isType(0, String.class));
		
		// Object
		assertTrue(TypeUtil.isType("STRING", String.class));
		assertTrue(TypeUtil.isType("STRING", Object.class));
		assertFalse(TypeUtil.isType("STRING", Number.class));
		assertFalse(TypeUtil.isType("STRING", Boolean.class));
	}

	/**
	 * Test of cast method, of class TypeUtil.
	 */
	@Test
	public void testCast() {
		System.out.println("cast");
		assertFalse(TypeUtil.cast(null, String.class).isPresent());
		assertFalse(TypeUtil.cast(1, String.class).isPresent());
		assertTrue(TypeUtil.cast("Yup", String.class).isPresent());
	}

    /**
     * Test of classify method, of class TypeUtil.
     */
    @Test
    public void testClassify() {
        System.out.println("classify");
        assertEquals(TypeUtil.class, TypeUtil.classify(TypeUtil.class));
        assertEquals(Object.class, TypeUtil.classify(new Object()));
    }

    /**
     * Test of declassify method, of class TypeUtil.
     */
    @Test
    public void testDeclassify() {
        System.out.println("declassify");
        final Object stand = new Object();
        assertEquals(stand, TypeUtil.declassify(stand));
        assertNull(TypeUtil.declassify(stand.getClass()));
        assertNull(TypeUtil.declassify(Object.class));
    }

    /**
	 * Test of advance method, of class TypeUtil.
	 */
	@Test
	public void testAdvance() {
		System.out.println("advance");
		assertEquals(String.class, TypeUtil.advance(String.class));
		assertEquals(Boolean.class, TypeUtil.advance(boolean.class));
		assertEquals(Boolean.class, TypeUtil.advance(Boolean.class));
		assertEquals(Byte.class, TypeUtil.advance(byte.class));
		assertEquals(Byte.class, TypeUtil.advance(Byte.class));
		assertEquals(Character.class, TypeUtil.advance(char.class));
		assertEquals(Character.class, TypeUtil.advance(Character.class));
		assertEquals(Short.class, TypeUtil.advance(short.class));
		assertEquals(Short.class, TypeUtil.advance(Short.class));
		assertEquals(Integer.class, TypeUtil.advance(int.class));
		assertEquals(Integer.class, TypeUtil.advance(Integer.class));
		assertEquals(Long.class, TypeUtil.advance(long.class));
		assertEquals(Long.class, TypeUtil.advance(Long.class));
		assertEquals(Float.class, TypeUtil.advance(float.class));
		assertEquals(Float.class, TypeUtil.advance(Float.class));
		assertEquals(Double.class, TypeUtil.advance(double.class));
		assertEquals(Double.class, TypeUtil.advance(Double.class));
		assertEquals(Void.class, TypeUtil.advance(void.class));
		assertEquals(Void.class, TypeUtil.advance(Void.class));
	}
    
}
