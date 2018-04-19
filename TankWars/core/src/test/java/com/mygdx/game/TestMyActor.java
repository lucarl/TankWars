package com.mygdx.game;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestMyActor {
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
}
