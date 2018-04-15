/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.quikmod.quikutil.function;

import com.google.common.base.Preconditions;
import java.util.function.Consumer;
import javax.annotation.Nonnull;

/**
 * Represents an operation that accepts two {@code int}-valued arguments and
 * returns no result.  This is the primitive type specialization of
 * {@link Consumer} for {@code int}.  Unlike most other functional interfaces,
 * {@code IntConsumer2} is expected to operate via side-effects.
 *
 * <p>This is a <a href="package-summary.html">functional interface</a>
 * whose functional method is {@link #accept(int, int)}.
 *
 * @see Consumer
 * @since 1.8
 */
@FunctionalInterface
public interface IntConsumer2 {

    /**
     * Performs this operation on the given arguments.
     *
     * @param a the first input argument
     * @param b the second input argument
     */
    void accept(int a, int b);

    /**
     * Returns a composed {@code IntConsumer2} that performs, in sequence, this
     * operation followed by the {@code after} operation. If performing either
     * operation throws an exception, it is relayed to the caller of the
     * composed operation.  If performing this operation throws an exception,
     * the {@code after} operation will not be performed.
     *
     * @param after the operation to perform after this operation
     * @return a composed {@code IntConsumer} that performs in sequence this
     * operation followed by the {@code after} operation
     * @throws NullPointerException if {@code after} is null
     */
    @Nonnull
    default IntConsumer2 andThen(@Nonnull IntConsumer2 after) {
        Preconditions.checkNotNull(after);
        return (int a, int b) -> { accept(a, b); after.accept(a, b); };
    }
}
