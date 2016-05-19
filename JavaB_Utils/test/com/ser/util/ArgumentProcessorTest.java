package com.ser.util;

import org.junit.Assert;
import org.junit.Test;

import java.util.Map;

/**
 * Created by Brad
 * on 5/18/2016.
 */
public class ArgumentProcessorTest {
    @Test
    public void testNullArgs(){
        Map<String, String> argMap = ArgumentProcessor.process(null);
        Assert.assertTrue(argMap.size() == 0);
    }

    @Test
    public void testEmptyArgs(){
        String[] args = new String[0];
        Map<String, String> argMap = ArgumentProcessor.process(args);
        Assert.assertTrue(argMap.size() == 0);
    }

    @Test
    public void testArgWithoutValue(){
        String arg = "-foo";
        String[] args = new String[]{arg};
        Map<String, String> argMap = ArgumentProcessor.process(args);
        Assert.assertTrue(argMap.size() == 1);
        Assert.assertTrue(argMap.containsKey(arg));
        Assert.assertEquals("", argMap.get(arg));
    }

    @Test
    public void testValueWithoutArg(){
        String value = "bar";
        String[] args = new String[]{value};
        Map<String, String> argMap = ArgumentProcessor.process(args);
        Assert.assertTrue(argMap.size() == 0);
    }

    @Test
    public void testArgWithValue(){
        String arg = "-foo";
        String value = "bar";
        String[] args = new String[]{arg, value};
        Map<String, String> argMap = ArgumentProcessor.process(args);
        Assert.assertTrue(argMap.size() == 1);
        Assert.assertTrue(argMap.containsKey(arg));
        Assert.assertEquals(value, argMap.get(arg));
    }

    @Test
    public void testBackToBackArgs(){
        String arg = "-foo";
        String arg2 = "-foo2";
        String value = "bar";
        String[] args = new String[]{arg, arg2, value};
        Map<String, String> argMap = ArgumentProcessor.process(args);
        Assert.assertTrue(argMap.size() == 2);
        Assert.assertTrue(argMap.containsKey(arg));
        Assert.assertTrue(argMap.containsKey(arg2));
        Assert.assertEquals("", argMap.get(arg));
        Assert.assertEquals(value, argMap.get(arg2));
    }

    @Test
    public void testBackToBackArgsWithValueInTheMiddle(){
        String arg = "-foo";
        String arg2 = "-foo2";
        String value = "bar";
        String[] args = new String[]{arg, value, arg2};
        Map<String, String> argMap = ArgumentProcessor.process(args);
        Assert.assertTrue(argMap.size() == 2);
        Assert.assertTrue(argMap.containsKey(arg));
        Assert.assertTrue(argMap.containsKey(arg2));
        Assert.assertEquals(value, argMap.get(arg));
        Assert.assertEquals("", argMap.get(arg2));
    }

    @Test
    public void testBackToBackArgsWithNoValue(){
        String arg = "-foo";
        String arg2 = "-foo2";
        String[] args = new String[]{arg, arg2};
        Map<String, String> argMap = ArgumentProcessor.process(args);
        Assert.assertTrue(argMap.size() == 2);
        Assert.assertTrue(argMap.containsKey(arg));
        Assert.assertTrue(argMap.containsKey(arg2));
        Assert.assertEquals("", argMap.get(arg));
        Assert.assertEquals("", argMap.get(arg2));
    }
}