/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.quikmod.quikutil;

import com.google.common.io.Files;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Paths;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.Rule;
import org.junit.rules.TemporaryFolder;


import static org.junit.Assert.*;
import static com.google.common.truth.Truth.*;
import static com.google.common.truth.Truth8.*;
import java.io.BufferedReader;
import java.nio.file.Path;


/**
 *
 * @author Ryan
 */
public class ResourceUtilTest {

    public static final Path TEST_RESOURCES_PATH = Paths.get("src", "test", "resources");
    public static final Path TEST_FILE_PATH_INTERNAL = Paths.get("com", "github", "quikmod", "quikutil", "random.txt");
    public static final Path TEST_FILE_PATH_EXTERNAL = TEST_RESOURCES_PATH.resolve(TEST_FILE_PATH_INTERNAL);
    
    @Rule
    public final TemporaryFolder temp = new TemporaryFolder();

    public ResourceUtilTest() {
        // Oh well...
        System.out.println(new ResourceUtil().toString());
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
     * Test of copyResource method, of class ResourceUtil.
     * @throws java.io.IOException
     */
    @Test
    public void testCopyResource() throws IOException {
        System.out.println("copyResource");
        
        // Create a filesystem to use.
        final File folder1 = temp.newFolder("folder1");
        final File folder2 = temp.newFolder("folder2");
        final File file1 = temp.newFile("file1.txt");
        final File file2 = temp.newFile("file2.txt");
        
        // Copy non-existing.
        ResourceUtil.copyResource(TEST_FILE_PATH_INTERNAL, folder1.toPath().resolve("test.txt"), true);
        ResourceUtil.copyResource(TEST_FILE_PATH_INTERNAL, folder2.toPath().resolve("test.txt"), false);
        
        // Copy existing.
        ResourceUtil.copyResource(TEST_FILE_PATH_INTERNAL, file1.toPath(), false);
        assertThat(Files.equal(TEST_FILE_PATH_EXTERNAL.toFile(), file1));
        ResourceUtil.copyResource(TEST_FILE_PATH_INTERNAL, file2.toPath(), false);
        assertThat(Files.equal(TEST_FILE_PATH_EXTERNAL.toFile(), file1));
        
        // Force an IO failure to see what happens.
        final File filefail = temp.newFile("failfile.txt");
        try (final RandomAccessFile raf = new RandomAccessFile(filefail, "rw")) {
            raf.getChannel().lock();
            ResourceUtil.copyResource(TEST_FILE_PATH_INTERNAL, filefail.toPath(), true);
        }
    }
    
    @Test
    public void testGetMissingResource() throws IOException {
        try (BufferedReader input = ResourceUtil.getResourceAsBufferedReader("not/a/resource")) {
            // Since the resource does not exist, the method should have returned null.
            assertThat(input).named("non-existing resource").isNull();
        }
    }

}
