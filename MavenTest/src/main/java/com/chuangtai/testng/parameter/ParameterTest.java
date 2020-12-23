package com.chuangtai.testng.parameter;


import org.testng.annotations.Parameters;
import org.testng.annotations.Test;


//通过xml文件参数化
public class ParameterTest {
    @Test
    @Parameters({"name", "age"})
    public void Parameter1(String name, int age) {
        System.out.println("name = " + name + ";age =" + age);
    }
}
