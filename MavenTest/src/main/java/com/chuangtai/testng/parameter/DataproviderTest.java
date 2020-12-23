package com.chuangtai.testng.parameter;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.lang.reflect.Method;


//dataProvider参数化
public class DataproviderTest {


    //通过一个方法，传递参数
    @Test(dataProvider = "data")
    public void testDataprovider(String name, int age) {
        System.out.println("name=" + name + ";age=" + age);
    }

    @DataProvider(name = "data")
    public Object[][] providerData() {
        Object[][] obj = new Object[][]{
                {"张三", 10},
                {"李四", 20},
                {"王五", 30}
        };
        return obj;
    }




    //通过不同的方法，传递不同的参数
    @Test(dataProvider = "methodData")
    public void test1(String name, int age) {
        System.out.println("test1方法中的name=" + name + ";age=" + age);
    }

    @Test(dataProvider = "methodData")
    public void test2(String name, int age) {
        System.out.println("test2方法中的name=" + name + ";age=" + age);
    }

    @DataProvider(name = "methodData")
    public Object MethodDataTest(Method method) {
        Object obj[][] = null;
        if (method.getName().equals("test1")) {
            obj = new Object[][]{
                    {"史勇强", 28},
                    {"田海燕", 29}
            };
        } else if (method.getName().equals("test2")) {
            obj = new Object[][]{
                    {"沈露露", 28},
                    {"王青", 30}
            };
        }
        return obj;
    }

}
