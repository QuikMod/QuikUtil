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
package com.github.quikmod.quikutil.exception;

import com.github.quikmod.quikutil.StringUtil;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * A class representing an exception with additional associated contextual
 * information.
 *
 * This class is intended as to provide additional information in stack traces
 * as to aid in the debugging process.
 * 
 * @see ContextedException
 *
 * @author Ryan
 */
public class ContextedRuntimeException extends RuntimeException {

    /**
     * The serialization version.
     */
    private static final long serialVersionUID = 20180829L;

    /**
     * The exception context associated with this exception.
     */
    private final ExceptionContext context;

    /**
     * Creates a new instance of <code>ContextedException</code> without detail
     * message.
     */
    @Nonnull
    public ContextedRuntimeException() {
        this.context = new SimpleExceptionContext();
    }

    /**
     * Constructs an instance of <code>ContextedRuntimeException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    @Nonnull
    public ContextedRuntimeException(final String msg) {
        super(msg);
        this.context = new SimpleExceptionContext();
    }

    /**
     * Constructs an instance of <code>ContextedRuntimeException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     * @param cause the cause of the exception.
     */
    @Nonnull
    public ContextedRuntimeException(final String msg, final Throwable cause) {
        super(msg, cause);
        this.context = new SimpleExceptionContext();
    }

    /**
     * Associates the given contextual key-value pair to this exception's
     * context.
     *
     * @param key a string defining the contextual element.
     * @param value the value associated with the given context key.
     * @return the exception instance, for method chaining.
     */
    @Nonnull
    public ContextedRuntimeException withContext(@Nonnull String key, @Nullable Object value) {
        this.context.withEntry(key, value);
        return this;
    }

    /**
     * Retrieves the {@link ExceptionContext} associated with this exception.
     *
     * @return the {@link ExceptionContext} associated with this exception.
     */
    @Nonnull
    public ExceptionContext getContext() {
        return context;
    }

    /**
     * Computes and returns the formatted exception message, containing full
     * contextual information.
     *
     * @return a formatted exception message.
     */
    @Override
    public String getMessage() {
        final StringBuilder sb = new StringBuilder();
        sb.append("\n\nException Message:\n\n");
        sb.append(StringUtil.trimEnd(StringUtil.increaseIndent(this.getRawMessage())));
        sb.append("\n\nException Context:\n\n");
        sb.append(StringUtil.trimEnd(StringUtil.increaseIndent(this.context.toFormattedString())));
        sb.append("\n\nException Stacktrace:\n");
        return sb.toString();
    }

    /**
     * Retrieves the raw exception message, containing only the raw exception
     * message without any contextual information.
     *
     * @return the raw exception message.
     */
    public String getRawMessage() {
        return super.getMessage();
    }

}
