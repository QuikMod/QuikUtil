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
