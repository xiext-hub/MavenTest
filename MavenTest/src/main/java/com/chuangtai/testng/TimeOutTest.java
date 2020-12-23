package com.chuangtai.testng;

import org.testng.annotations.Test;


//  超时测试  表示如果单元测试花费的时间超过指定的毫秒数，则TestNG将中止测试并将其推向失败
public class TimeOutTest {
    @Test(timeOut = 3000)//单位是毫秒值   3秒
    public void testSuccess() throws InterruptedException {
        Thread.sleep(2000);
    }


    @Test(timeOut = 2000)
    public void  testFaild() throws InterruptedException {
        Thread.sleep(3000);
    }

}
