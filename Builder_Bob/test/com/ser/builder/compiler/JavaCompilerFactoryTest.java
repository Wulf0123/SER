package com.ser.builder.compiler;

import com.ser.exception.GenericException;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Brad
 * on 5/18/2016.
 */
public class JavaCompilerFactoryTest {
    @Test
    public void testGetSimpleJavaCompilerByDefault() throws GenericException {
        JavaCompiler compiler = JavaCompilerFactory.INSTANCE.getCompiler(null);
        Assert.assertNotNull(compiler);
        Assert.assertTrue(compiler instanceof SimpleJavaCompiler);
    }

    @Test
    public void testGetSimpleJavaCompiler() throws GenericException {
        JavaCompiler compiler = JavaCompilerFactory.INSTANCE.getCompiler(SimpleJavaCompiler.class.getSimpleName());
        Assert.assertNotNull(compiler);
        Assert.assertTrue(compiler instanceof SimpleJavaCompiler);
    }

    @Test
    public void testJavaCompilerDoesNotExist() {
        try {
            JavaCompiler compiler = JavaCompilerFactory.INSTANCE.getCompiler("ThisCompilerDoesNotExistAndNeverWill");
            Assert.fail();
        } catch (GenericException e){
            Assert.assertEquals(GenericException.CLASS_NOT_FOUND, e.getType());
        }
    }
}
