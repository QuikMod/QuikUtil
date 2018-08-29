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

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.annotation.Nonnull;

/**
 *
 * @author Ryan
 */
public final class StringUtil {

	public static final Pattern TOKENIZER = Pattern.compile("\"([^\"]*)\"|(\\S+)");

	/**
	 * Tokenizes the given input string using spaces as delimiters, but ignoring
	 * spaces that are surrounded by quotes.
	 *
	 * @param input the string to tokenize.
	 * @return the tokenized string.
	 */
	public static final Deque<String> tokenize(@Nonnull String input) {
		final Matcher m = TOKENIZER.matcher(input);
		final Deque<String> tokens = new ArrayDeque<>();
		while (m.find()) {
			tokens.add(m.group());
		}
		return tokens;
	}
    
    public static final String increaseIndent(@Nonnull String input) {
        return input.replaceAll("(?m)^", "\t");
    }
    
    public static final String decreaseIndent(@Nonnull String input) {
        return input.replaceAll("(?m)^\t", "");
    }
    
    public static final String trimStart(@Nonnull String input) {
        return input.replaceAll("^\\s+", "");
    }
    
    public static final String trimEnd(@Nonnull String input) {
        return input.replaceAll("\\s+$", "");
    }

    private StringUtil() {
        // Empty private constructor to prevent instantiation.
    }

}
