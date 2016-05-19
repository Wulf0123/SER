package com.ser.exception;

/**
 * Created by Brad
 * on 5/18/2016.
 */
public class GenericException extends Exception{
    public static int GENERAL_ERROR = 0;
    public static int CLASS_NOT_FOUND = 1;
    private static int NUM_OF_ERROR_TYPES = 2;

    private int type = 0;

    public GenericException(){
        super();
    }

    public GenericException(String message) {
        super(message);
    }

    public GenericException(String message, Throwable cause) {
        super(message, cause);
    }

    public GenericException(Throwable cause) {
        super(cause);
    }

    protected GenericException(String message, Throwable cause,
                        boolean enableSuppression,
                        boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public GenericException(String message, int type) {
        super(message);
        initializeType(type);
    }

    public GenericException(String message, Throwable cause, int type) {
        super(message, cause);
        initializeType(type);
    }

    public GenericException(Throwable cause, int type) {
        super(cause);
        initializeType(type);
    }

    protected GenericException(String message, Throwable cause,
                               boolean enableSuppression,
                               boolean writableStackTrace, int type) {
        super(message, cause, enableSuppression, writableStackTrace);
        initializeType(type);
    }

    private void initializeType(int type){
        if(type >= 0 && type < NUM_OF_ERROR_TYPES) {
            this.type = type;
        } else{
            this.type = 0;
        }
    }

    public int getType(){
        return type;
    }
}
