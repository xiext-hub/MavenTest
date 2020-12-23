package com.chuangtai.testng.groups;

import org.testng.annotations.Test;

//类分组测试
@Test(groups = "student")
public class GroupsOnClass1 {
    public void student1() {
        System.out.println("GroupsOnClass1中的student1运行辣~~~~");
    }


    public void student2() {
        System.out.println("GroupsOnClass1中的student2运行辣~~~~");
    }
}
