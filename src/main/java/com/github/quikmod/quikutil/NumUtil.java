/*
 */
package com.github.quikmod.quikutil;

import java.util.Optional;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 *
 * @author RlonRyan
 */
public final class NumUtil {
    
    @Nonnull
    public static final Optional<Integer> toInteger(@Nullable String string) {
        // Check if null.
        if (string == null || string.isEmpty()) {
            return Optional.empty();
        }
        
        // Attempt to decode.
        try {
            return Optional.of(Integer.decode(string));
        } catch (NumberFormatException e) {
            return Optional.empty();
        }
    }
    
    @Nonnull
    public static final Optional<Long> toLong(@Nullable String string) {
        // Check if null.
        if (string == null || string.isEmpty()) {
            return Optional.empty();
        }
        
        // Attempt to decode.
        try {
            return Optional.of(Long.decode(string));
        } catch (NumberFormatException e) {
            return Optional.empty();
        }
    }
    
    @Nonnull
    public static final Optional<Float> toFloat(@Nullable String string) {
        // Check if null.
        if (string == null || string.isEmpty()) {
            return Optional.empty();
        }
        
        // Attempt to decode.
        try {
            return Optional.of(Float.valueOf(string));
        } catch (NumberFormatException e) {
            return Optional.empty();
        }
    }
    
    @Nonnull
    public static final Optional<Double> toDouble(@Nullable String string) {
        // Check if null.
        if (string == null || string.isEmpty()) {
            return Optional.empty();
        }
        
        // Attempt to decode.
        try {
            return Optional.of(Double.valueOf(string));
        } catch (NumberFormatException e) {
            return Optional.empty();
        }
    }
	
	/**
	 * Brings an integer into a specified range.
	 *
	 * @param value The value to bring into the range.
	 * @param min The minimum value, inclusive.
	 * @param max The maximum value, inclusive.
	 * @return The in-bounded value.
	 */
	public static int inRange(int value, int min, int max) {
		return value < min ? min : value > max ? max : value;
	}
    
    /**
	 * Brings an integer into a specified range.
	 *
	 * @param value The value to bring into the range.
	 * @param min The minimum value, inclusive.
	 * @param max The maximum value, inclusive.
	 * @return The in-bounded value.
	 */
	public static long inRange(long value, long min, long max) {
		return value < min ? min : value > max ? max : value;
	}
	
	/**
	 * Brings an integer into a specified range.
	 *
	 * @param value The value to bring into the range.
	 * @param min The minimum value, inclusive.
	 * @param max The maximum value, inclusive.
	 * @return The in-bounded value.
	 */
	public static float inRange(float value, float min, float max) {
		return value < min ? min : value > max ? max : value;
	}
	
	/**
	 * Brings an integer into a specified range.
	 *
	 * @param value The value to bring into the range.
	 * @param min The minimum value, inclusive.
	 * @param max The maximum value, inclusive.
	 * @return The in-bounded value.
	 */
	public static double inRange(double value, double min, double max) {
		return value < min ? min : value > max ? max : value;
	}
	
}
