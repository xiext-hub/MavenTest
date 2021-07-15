package com.tingzi.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.List;

public class Test {
    public static void main(String[] args) throws Exception {
        File f = new File("/C:/Users/admin/Desktop/json.txt");
        StringBuilder sb = new StringBuilder();
        String line = null;
        BufferedReader br = new BufferedReader(new FileReader(f));
        while (null != (line = br.readLine())) {
            sb.append(line);
        }

        List<JSONObject> jsonObjects = JSON.parseArray(sb.toString(), JSONObject.class);
        System.out.println(jsonObjects.get(1).getInteger("parentId"));
    }
}