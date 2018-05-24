/*
 */
package com.github.quikmod.quikutil;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
	public static Deque<String> tokenize(String input) {
		final Matcher m = TOKENIZER.matcher(input);
		Deque<String> tokens = new ArrayDeque<>();
		while (m.find()) {
			tokens.add(m.group());
		}
		return tokens;
	}

    private StringUtil() {
        // Empty private constructor to prevent instantiation.
    }

}
