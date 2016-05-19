package com.ser.builder.compiler;

import com.ser.exception.GenericException;

/**
 * Created by Brad
 * on 5/18/2016.
 */
public enum JavaCompilerFactory {
    INSTANCE;
    public static String JAVA_COMPILER_PACKAGE = "com.ser.builder.compiler";
    //TODO, maybe cache the compilers?

    public JavaCompiler getCompiler(String compilerClass) throws GenericException {
        if (compilerClass == null) {
            compilerClass = "SimpleJavaCompiler";
        }
        compilerClass = String.format("%s.%s", JAVA_COMPILER_PACKAGE, compilerClass);
        Class<?> currCompilerClass;
        try {
            currCompilerClass = Class.forName(compilerClass);
        } catch (ClassNotFoundException e) {
            try {
                this.getClass().getClassLoader().loadClass(compilerClass);//We didn't find the compiler, but maybe we need to load it
                currCompilerClass = Class.forName(compilerClass);
            } catch (ClassNotFoundException e1) {
                throw new GenericException(e1, GenericException.CLASS_NOT_FOUND);
            }
        }
        try {
            return (JavaCompiler) currCompilerClass.newInstance();
        } catch (Exception e) {
            throw new GenericException(String.format("Could not initialize class %s", compilerClass), GenericException.CLASS_NOT_FOUND);
        }
    }
}