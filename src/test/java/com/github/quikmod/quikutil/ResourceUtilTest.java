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
