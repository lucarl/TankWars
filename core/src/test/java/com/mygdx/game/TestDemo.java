package com.mygdx.game;
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestDemo {
    @BeforeClass
    public static void beforeClass(){ //First of all
        System.out.println("Before class");
    }
    @AfterClass
    public static void afterClass(){ //Last of all
        System.out.println("After class");
    }
    @Before
    public void before(){ //Before each test method
        System.out.println("Before");
    }
    @After
    public void after(){ //After each test method
        System.out.println("After");
    }

    @Test
    public void testAdd() {
        Demo demo = new Demo();
        //assertTrue(demo.add(1,2) == 3);
    }
}
