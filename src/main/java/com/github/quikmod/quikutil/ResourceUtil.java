/*
 */
package com.github.quikmod.quikutil;

import com.google.common.base.Preconditions;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import javax.annotation.Nonnull;
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
				Files.copy(getResourceAsStream(from), to, StandardCopyOption.REPLACE_EXISTING);
			}
		} catch (IOException e) {
            LOG.error("Unable to copy Jar resource!\n\tFrom : \"{}\"\n\tTo   : \"{}\"!\n\tCause:\n\t\t{}", from, to, e.getLocalizedMessage().replaceAll(":\\s+", ":\n\t\t\t"));
		}
	}
    
    /**
	 * Retrieves the requested resource by using the current thread's class
	 * loader or the class loader of this class.
	 *
	 * @param location the location of the desired resource stream.
	 * @return the resource, as a stream.
	 */
    @Nonnull
    public static InputStream getResourceAsStream(@Nonnull Path location) {
        Preconditions.checkNotNull(location, "The resource path may not be null!");
        return getResourceAsStream(location.toString());
	}

	/**
	 * Retrieves the requested resource by using the current thread's class
	 * loader or the class loader of this class.
	 *
	 * @param location the location of the desired resource stream.
	 * @return the resource, as a stream.
	 */
    @Nonnull
	public static InputStream getResourceAsStream(@Nonnull String location) {
        Preconditions.checkNotNull(location, "The resource path may not be null!");
		InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream(location);
		return in != null ? in : ResourceUtil.class.getResourceAsStream(location);
	}

}
