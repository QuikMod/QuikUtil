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

import com.google.common.base.Preconditions;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author RlonRyan
 */
public final class ResourceUtil {
    
    // The logger instance.
    private static final Logger LOG = LoggerFactory.getLogger(ResourceUtil.class);

	/**
	 * Copies a file from inside the jar to the specified location outside the
	 * jar, retaining the file name. The default copy action is to not overwrite
	 * an existing file.
	 *
	 * @param from the location of the internal resource (inside the jar).
	 * @param to the location to copy the resource to.
	 * @param overwrite if the copy task should overwrite existing files.
	 */
	public static void copyResource(@Nonnull Path from, @Nonnull Path to, boolean overwrite) {
        // Validate.
        Preconditions.checkNotNull(from, "The resource path to copy from may not be null!");
        Preconditions.checkNotNull(to, "The destination path may not be null!");
        
        // Do the thing.
		try {
			if (overwrite || !Files.exists(to)) {
				Files.createDirectories(to.getParent());
				Files.copy(getResourceAsInputStream(from.toString()), to, StandardCopyOption.REPLACE_EXISTING);
			}
		} catch (IOException e) {
            LOG.error("Unable to copy Jar resource!\n\tFrom : \"{}\"\n\tTo   : \"{}\"!\n\tCause:\n\t\t{}", from, to, e.getLocalizedMessage().replaceAll(":\\s+", ":\n\t\t\t"));
		}
	}

	/**
	 * Retrieves the requested resource by using the current thread's class
	 * loader or the class loader of this class.
     * 
     * TODO: Convert to optional return type.
	 *
	 * @param location the location of the desired resource stream.
	 * @return the resource, as a stream, or null if the resource could not be found.
	 */
    @Nullable
	public static InputStream getResourceAsInputStream(@Nonnull String location) {
        Preconditions.checkNotNull(location, "The resource path may not be null!");
		InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream(location);
		return in != null ? in : ResourceUtil.class.getResourceAsStream(location);
	}
    
    /**
	 * Retrieves the requested resource by using the current thread's class
	 * loader or the class loader of this class.
	 *
	 * @param location the location of the desired resource stream.
	 * @return the resource, as an InputStreamReader, or null if the resource could not be found.
	 */
    @Nullable
	public static InputStreamReader getResourceAsInputStreamReader(@Nonnull String location) {
        // First, get the resource as a stream.
        final InputStream in = getResourceAsInputStream(location);
        
        // If the input stream is null, abort.
        if (in == null) {
            return null;
        }
        
        // Next, wrap the stream in a stream reader.
        final InputStreamReader inr = new InputStreamReader(in);
        
        // Finally, return the input stream reader.
        return inr;
	}
    
    /**
	 * Retrieves the requested resource by using the current thread's class
	 * loader or the class loader of this class.
	 *
	 * @param location the location of the desired resource stream.
	 * @return the resource, as a BufferedReader, or null if the resource could not be found.
	 */
    @Nullable
    public static BufferedReader getResourceAsBufferedReader(@Nonnull String location) {
        // First, get the resource as a stream.
        final InputStreamReader in = getResourceAsInputStreamReader(location);
        
        // If the input stream is null, abort.
        if (in == null) {
            return null;
        }
        
        // Next, wrap the InputStreamReader in a buffered reader.
        final BufferedReader br = new BufferedReader(in);
        
        // Finally, return the buffered reader.
        return br;
	}

}
