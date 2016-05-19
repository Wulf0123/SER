package com.ser.builder.compiler;

import java.util.Map;

/**
 * Created by Brad
 * on 5/18/2016.
 */
public interface JavaCompiler {
    void compile();

    void addArguments(Map<String, String> args);
}
