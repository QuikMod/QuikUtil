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
import com.google.common.base.Preconditions;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.annotation.Nonnull;
import javax.annotation.RegEx;

/**
 * A simple class used for building detailed exception messages useful for improving debugging.
 * 
 * @author Ryan
 */
public final class ExceptionMessageBuilder {

    @Nonnull
    private String title;

    @Nonnull
    private String description;

    @Nonnull
    private final LinkedHashMap<String, String> context;

    @Nonnull
    public ExceptionMessageBuilder() {
        this("Error");
    }

    @Nonnull
    public ExceptionMessageBuilder(@Nonnull String title) {
        this(title, "No description given.");
    }

    @Nonnull
    public ExceptionMessageBuilder(@Nonnull String title, @Nonnull String description) {
        // Validate.
        Preconditions.checkNotNull(title, "The title associated with an ExceptionBuilder instance may not be null!");
        Preconditions.checkNotNull(description, "The description associated with an ExceptionBuilder instance may not be null!");

        // Assign.
        this.title = title;
        this.description = description;
        this.context = new LinkedHashMap<>();
    }

    @Nonnull
    public final String getTitle() {
        return this.title;
    }

    @Nonnull
    public final String getDescription() {
        return this.description;
    }

    @Nonnull
    public final String getContext() {
        final StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String> entry : this.context.entrySet()) {
            sb.append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
        }
        if (sb.length() > 1) {
            sb.setLength(sb.length() - 1);
        }
        return sb.toString();
    }

    @Nonnull
    public final ExceptionMessageBuilder withTitle(@Nonnull String title) {
        Preconditions.checkNotNull(title);
        this.title = title;
        return this;
    }

    @Nonnull
    public final ExceptionMessageBuilder withTitle(@Nonnull @RegEx String format, @Nonnull Object... args) {
        Preconditions.checkNotNull(format);
        Preconditions.checkNotNull(args);
        this.title = String.format(format, args);
        return this;
    }

    @Nonnull
    public final ExceptionMessageBuilder withDescription(@Nonnull String description) {
        Preconditions.checkNotNull(description);
        this.description = description;
        return this;
    }

    @Nonnull
    public final ExceptionMessageBuilder withDescription(@Nonnull @RegEx String format, @Nonnull Object... args) {
        Preconditions.checkNotNull(format);
        Preconditions.checkNotNull(args);
        this.description = String.format(format, args);
        return this;
    }

    @Nonnull
    public final ExceptionMessageBuilder withContext(@Nonnull String key, @Nonnull String format) {
        Preconditions.checkNotNull(key);
        Preconditions.checkNotNull(format);
        this.context.put(key, format);
        return this;
    }

    @Nonnull
    public final ExceptionMessageBuilder withContext(@Nonnull String key, @Nonnull @RegEx String format, @Nonnull Object... args) {
        Preconditions.checkNotNull(key);
        Preconditions.checkNotNull(format);
        Preconditions.checkNotNull(args);
        this.context.put(key, String.format(format, args));
        return this;
    }

    @Nonnull
    public final String build() {
        final StringBuilder sb = new StringBuilder();
        sb.append('\n');
        sb.append("\nTitle:\n");
        sb.append('\n').append(StringUtil.increaseIndent(this.getTitle())).append('\n');
        sb.append("\nDescription:\n");
        sb.append('\n').append(StringUtil.increaseIndent(this.getDescription())).append('\n');
        sb.append("\nContext:\n");
        sb.append('\n').append(StringUtil.increaseIndent(this.getContext())).append('\n');
        sb.append("\nStacktrace:\n");
        return sb.toString();
    }

    @Nonnull
    @Override
    public String toString() {
        return this.build();
    }

}
