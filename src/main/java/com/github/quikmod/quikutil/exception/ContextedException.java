/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.quikmod.quikutil.exception;

import com.github.quikmod.quikutil.StringUtil;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 *
 * @author Ryan
 */
public class ContextedException extends Exception {

    private static final long serialVersionUID = 1L;
    
    private final ExceptionContext context;

    /**
     * Creates a new instance of <code>ContextedException</code> without detail message.
     */
    @Nonnull
    public ContextedException() {
        this.context = new SimpleExceptionContext();
    }

    /**
     * Constructs an instance of <code>ContextedException</code> with the specified detail message.
     *
     * @param msg the detail message.
     */
    @Nonnull
    public ContextedException(final String msg) {
        super(msg);
        this.context = new SimpleExceptionContext();
    }
    
    /**
     * Constructs an instance of <code>ContextedException</code> with the specified detail message.
     *
     * @param msg the detail message.
     * @param cause the cause of the exception.
     */
    @Nonnull
    public ContextedException(final String msg, final Throwable cause) {
        super(msg, cause);
        this.context = new SimpleExceptionContext();
    }
    
    @Nonnull
    public ContextedException withContext(@Nonnull String key, @Nullable Object value) {
        this.context.withEntry(key, value);
        return this;
    }

    public ExceptionContext getContexts() {
        return context;
    }

    @Override
    public String getMessage() {
        final StringBuilder sb = new StringBuilder();
        sb.append("\n\nException Message:\n\n");
        sb.append(StringUtil.trimEnd(StringUtil.increaseIndent(super.getMessage())));
        sb.append("\n\nException Context:\n\n");
        sb.append(StringUtil.trimEnd(StringUtil.increaseIndent(this.context.toFormattedString())));
        sb.append("\n\nException Stacktrace:\n");
        return sb.toString();
    }
    
    public String getRawMessage() {
        return super.getMessage();
    }
    
}
