package com.chuangtai.testng;

import org.testng.annotations.*;

public class BaseTest {

    @Test  //最基本的注解，用来把方法标记为测试的一部分
    public void testCase1() {
        System.out.println("testCase1~~这是测试用例1");
    }


    @Test  //最基本的注解，用来把方法标记为测试的一部分
    public void testCase2() {
        System.out.println("testCase2~~这是测试用例2");
    }

    @BeforeMethod
    public void BeforeMethod() {
        System.out.println("BeforeMethod~~~这是在测试方法之前运行的");
    }

    @AfterMethod
    public void AfterMethod() {
        System.out.println("AfterMethod~~~这是在测试方法之后运行的");
    }


    @BeforeClass
    public void BeforeClass() {
        System.out.println("BeforeClass~~~这是在类运行之前运行的方法");
    }


    @AfterClass
    public void AfterClass() {
        System.out.println("AfterClass~~~这是在类运行之后运行的方法");
    }

    @BeforeSuite
    public void BeforeSuite() {
        System.out.println("BeforeSuite~~~测试套件");
    }

    @AfterSuite
    public void AfterSuite() {
        System.out.println("AfterSuite~~~测试套件");
    }
}
