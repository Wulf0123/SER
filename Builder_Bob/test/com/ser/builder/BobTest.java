package com.ser.builder;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Brad
 * on 5/18/2016.
 */
public class BobTest {
    @Test
    public void testBuilder(){
        Bob.Builder builder = new Bob.Builder();
        Bob bob = builder.build();
        Assert.assertNotNull(bob);
        Assert.assertEquals(Bob.Status.READY, bob.getStatus());
    }

    @Test
    public void testBuilderBuildsArguments(){
        Bob.Builder builder = new Bob.Builder();
        Bob bob = builder.build();
        Assert.assertEquals(Bob.DEFAULT_COMPILER, bob.getCompiler());
        Assert.assertEquals(Bob.Status.READY, bob.getStatus());
    }

    @Test
    public void testBuilderBuildArgumentsWithCompiler(){
        String badCompilerName = "ThisCompilerDoesNotExistAndNeverWill";
        Bob.Builder builder = new Bob.Builder();
        builder = builder.compiler(badCompilerName);
        Bob bob = builder.build();
        Assert.assertNotEquals(Bob.DEFAULT_COMPILER, bob.getCompiler());
        Assert.assertEquals(badCompilerName, bob.getCompiler());
        Assert.assertEquals(Bob.Status.READY, bob.getStatus());
    }
}