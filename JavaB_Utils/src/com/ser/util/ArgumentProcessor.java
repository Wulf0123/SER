package com.ser.util;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Brad
 * on 5/18/2016.
 */
//Util class to parse arguments in from the command line and return them as a Map
public enum ArgumentProcessor {
    ;

    public static Map<String, String> process(String[] args){
        Map<String, String> argMap = new HashMap<>();
        if(args != null) {
            String lastArg = "";
            for (String arg : args) {
                if (arg.startsWith("-")) {
                    argMap.put(arg, "");
                    lastArg = arg;
                } else if(lastArg.length() > 0){
                    argMap.put(lastArg, arg);
                }
            }
        }
        return argMap;
    }
}
