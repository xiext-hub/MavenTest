package com.chuangtai.springboot;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;



//springboot官方第一个例子   浏览器运行localhost:8888   输出hello world;

@Controller
@EnableAutoConfiguration
public class SampleController {

    @RequestMapping("/")
    @ResponseBody
    String home(){
        return  "hello world";
    }

    public static void main(String[] args) {
        SpringApplication.run(SampleController.class,args);
    }

}
