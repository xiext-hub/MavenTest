package com.chuangtai.testng.groups;

import org.testng.annotations.AfterGroups;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.Test;

public class GroupsOnMethodTest {

    //方法分组测试
    @Test(groups = "server")
    public void test1() {
        System.out.println("这是服务端组的测试方法1111111111");
    }

    @Test(groups = "server")
    public void test2() {
        System.out.println("这是服务端组的测试方法22222222222");
    }


    @Test(groups = "client")
    public void test3() {
        System.out.println("这是客户端组的测试方法333333333333");
    }


    @Test(groups = "client")
    public void test4() {
        System.out.println("这是客户端组的测试方法4444444444444");
    }

    @BeforeGroups("server")
    public void BeforeGroupsOnServer() {
        System.out.println("这是服务端组运行之前运行的方法~~~~~~~~~~~~~~~~~~");
    }


    @AfterGroups("server")
    public void AfterGroupsOnServer() {
        System.out.println("这是服务端组运行之后运行的方法~~~~~~~~~~~~~~~~~~");
    }



    @BeforeGroups("client")
    public void BeforeGroupsOnClient() {
        System.out.println("这是客户端组运行之前运行的方法~~~~~~~~~~~~~~~~~~");
    }


    @AfterGroups("client")
    public void AfterGroupsOnClient() {
        System.out.println("这是客户端组运行之后运行的方法~~~~~~~~~~~~~~~~~~");
    }
}
