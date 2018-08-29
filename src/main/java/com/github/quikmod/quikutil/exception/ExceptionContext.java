/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.quikmod.quikutil.exception;

import java.util.Map;
import java.util.stream.Stream;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 *
 * @author Ryan
 */
public interface ExceptionContext {
    
    @Nonnull
    ExceptionContext withEntry(@Nonnull String key, @Nullable Object value);
    
    @Nonnull
    Stream<Map.Entry<String, Object>> getEntries();
    
    @Nonnull
    String toFormattedString();
    
}
