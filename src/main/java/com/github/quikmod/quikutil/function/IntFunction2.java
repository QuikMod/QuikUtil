/*
 *
 */
package com.github.quikmod.quikutil.function;

import java.util.function.Function;

/**
 * Represents a function that accepts int-valued arguments and produces a
 * int-valued result.  This is the {@code int}-consuming primitive specialization for
 * {@link Function}.
 *
 * <p>This is a <a href="package-summary.html">functional interface</a>
 * whose functional method is {@link #apply(int, int)}.
 *
 * @see Function
 * @since 1.8
 */
@FunctionalInterface
public interface IntFunction2 {

    /**
     * Applies this function to the given argument.
     *
     * @param a the first function argument
     * @param b the second function argument
     * @return the function result
     */
    int apply(int a, int b);
    
}
