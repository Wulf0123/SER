package com.ser.builder;

import com.ser.builder.compiler.JavaCompiler;
import com.ser.builder.compiler.JavaCompilerFactory;
import com.ser.builder.compiler.SimpleJavaCompiler;
import com.ser.exception.GenericException;
import com.ser.util.ArgumentProcessor;

import java.util.Map;

/**
 * Created by Brad
 * on 5/18/2016.
 */

//This is the API class in the build, ie the entry point for JavaB and externally
public class Bob {
    static String DEFAULT_COMPILER = SimpleJavaCompiler.class.getSimpleName();

    private String compiler = DEFAULT_COMPILER;
    private Status status;

    //Builder pattern, Builder class is below
    private Bob(){
        setStatus(Status.READY);
    }

    private Bob(String compiler){
        this();
        this.compiler = (compiler == null || compiler.length() == 0) ? DEFAULT_COMPILER : compiler;
    }

    String getCompiler(){
        return this.compiler;
    }

    //Only run if called from the command line
    private void run(){
        setStatus(Status.STARTED);
        try {
            JavaCompiler compiler = JavaCompilerFactory.INSTANCE.getCompiler(this.compiler);
            compiler.compile();
            setStatus(Status.FINISHED);
        } catch (GenericException e) {
            //TODO add logging
            setStatus(Status.ERRORED);
        }
    }

    private void setStatus(Status status){
        this.status = status;
    }

    public Status getStatus(){
        return this.status;
    }

    //Sets config values
    static class Builder{
        private String compiler;

        Builder compiler(String compiler){
            this.compiler = compiler;
            return this;
        }

        Bob build(){
            return new Bob(compiler);
        }
    }

    private enum ArgumentKey{
        COMPILER("-compiler"), COMPILER_SHORTHAND("-c");
        private String name;

        ArgumentKey(String name){
            this.name = name;
        }

        @Override
        public String toString(){
            return name;
        }
    }

    enum Status{
        READY("ready"),
        STARTED("started"),
        FINISHED("finished"),
        ERRORED("errored");
        private String type;

        Status(String type){
            this.type = type;
        }

        @Override
        public String toString(){
            return type;
        }
    }

    //Main class when
    public static void main(String[] args){
        Map<String, String> argMap = ArgumentProcessor.process(args);
        Builder builder = new Builder();
        //Add config values here
        getCompilerArgument(builder, argMap);

        Bob bob = builder.build();
        bob.run();
    }

    private static void getCompilerArgument(Builder builder, Map<String, String> argMap){
        if(argMap.containsKey(ArgumentKey.COMPILER.toString())){
            builder.compiler(ArgumentKey.COMPILER.toString());
        }
        if(argMap.containsKey(ArgumentKey.COMPILER_SHORTHAND.toString())){
            builder.compiler(ArgumentKey.COMPILER_SHORTHAND.toString());
        }
    }
}
