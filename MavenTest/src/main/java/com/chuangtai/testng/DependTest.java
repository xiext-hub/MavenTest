package com.chuangtai.testng;

import org.testng.annotations.Test;

//依赖测试    被依赖的那个方法如果失败了，则依赖的那个方法就会被忽略掉。
public class DependTest {

    @Test
    public void test1(){
        System.out.println("test1方法执行");
        throw new RuntimeException();  //test1抛出了一个异常，则test2方法就会被忽略，此行如果删掉，则test1和test2都会被执行。
    }

    @Test(dependsOnMethods = {"test1"})
    public void test2(){
        System.out.println("test2方法执行");
    }
}
