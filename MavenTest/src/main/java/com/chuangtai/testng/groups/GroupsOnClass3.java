package com.chuangtai.testng.groups;

import org.testng.annotations.Test;

@Test(groups = "teacher")
public class GroupsOnClass3 {
    public void teacher1() {
        System.out.println("GroupsOnClass3中的teacher1运行辣~~~~");
    }


    public void teacher2() {
        System.out.println("GroupsOnClass3中的teacher2运行辣~~~~");
    }
}
