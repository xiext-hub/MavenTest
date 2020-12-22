package com.tingzi.util;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class HttpClientUtil {
    /**
     * HttpClient Get请求
     * @param url - 请求路径
     * @return
     */
    public static Map<String, Object> doGet(String url) {
        Map<String, Object> result = new HashMap<String, Object>();
//        result.put("success", false);
//        result.put("errorMsg", "");
//        result.put("resContent", "");
        // 定义HttpClient
        CloseableHttpClient httpclient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        try {
            // 实例化HTTP方法，创建httpGet
            HttpGet httpGet = new HttpGet(url);
            // 设置超时时间
            RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(3000)
                    .setConnectionRequestTimeout(1000).setSocketTimeout(3000).build();
            httpGet.setConfig(requestConfig);
            // 执行get请求
            response = httpclient.execute(httpGet);
            System.out.println("doGet response status:" + response.getStatusLine());
            //获取响应实体
            HttpEntity resEntity = response.getEntity();
            //判断返回状态是否为200  打印内容
            if (resEntity != null && response.getStatusLine().getStatusCode() == 200) {
                String content = EntityUtils.toString(resEntity);
//                result.put("success", true);
//                result.put("resContent", content);
                System.out.println("doGet response content:" + content);
            }
            EntityUtils.consume(resEntity);
        } catch (Exception e) {
//            result.put("success", false);
//            result.put("errorMsg", e.getMessage());
            System.out.println("doGet Error:" + e);
        } finally {
            org.apache.http.client.utils.HttpClientUtils.closeQuietly(response);
            org.apache.http.client.utils.HttpClientUtils.closeQuietly(httpclient);
        }
        return result;
    }

    /**
     * HttpClient Post请求
     * @param url - 请求路径
     * @param content - 字符串型请求信息
     * @return
     */
    public static Map<String, Object> doPost(String url, String content) {
        return doPost(url, null, null, content, 10000);
    }

    /**
     * HttpClient Post请求
     * @param url - 请求路径
     * @param headers - 请求头
     * @param content - 字符串型请求信息
     * @return
     */
    public static Map<String, Object> doPost(String url, Map<String, String> headers, String content) {
        return doPost(url, headers, null, content, 3000);
    }

    /**
     * HttpClient Post请求
     * @param url - 请求路径
     * @param paramMap - 表单型请求信息
     * @return
     */
    public static Map<String, Object> doPost(String url, Map<String, Object> paramMap) {
        return doPost(url, null, paramMap, null, 3000);
    }

    /**
     * HttpClient Post请求
     * @param url - 请求路径
     * @param headers - 请求头
     * @param paramMap - 表单型请求信息
     * @return
     */
    public static Map<String, Object> doPost(String url, Map<String, String> headers, Map<String, Object> paramMap) {
        return doPost(url, headers, paramMap, null, 3000);
    }

    /**
     * HttpClient Post请求
     * @param url - 请求路径
     * @param headers - 请求头
     * @param paramMap - 请求参数
     * @param content - 请求内容
     * @param timeOut - 请求超时时间(毫秒)
     * @return
     */
    private static Map<String, Object> doPost(String url, Map<String, String> headers, Map<String, Object> paramMap, String content, int timeOut) {
        Map<String, Object> result = new HashMap<String, Object>();
//        result.put("success", false);
//        result.put("errorMsg", "");
//        result.put("resContent", "");
        // 定义HttpClient
        CloseableHttpClient httpclient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        try {
            //创建一个post对象
            HttpPost httpPost = new HttpPost(url);
            // 设置超时时间
            RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(timeOut)
                    .setConnectionRequestTimeout(1000).setSocketTimeout(timeOut).build();
            httpPost.setConfig(requestConfig);
             //设置请求头
            if (headers != null && headers.size() > 0) {
                for (String key : headers.keySet()) {
                    httpPost.addHeader(key, headers.get(key));
                }
            }

             //设置表单型请求信息  模拟一个表单
            if (paramMap != null && paramMap.size() > 0) {
                List<NameValuePair> params = new ArrayList<NameValuePair>();
                for (String key : paramMap.keySet()) {
                    if (paramMap.get(key) != null) {
                        params.add(new BasicNameValuePair(key, String.valueOf(paramMap.get(key))));
                    }
                }
                UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(params, Consts.UTF_8);
                formEntity.setContentType("application/x-www-form-urlencoded; charset=UTF-8");
                //设置请求内容
                httpPost.setEntity(formEntity);
            }
            /**
             * 设置字符串型请求信息
             */
            if (content != null && !"".equals(content)) {
                StringEntity stringEntity = new StringEntity(content, Consts.UTF_8);
                stringEntity.setContentType("application/json; charset=UTF-8");
                //设置请求内容
                httpPost.setEntity(stringEntity);
            }
            //执行post请求
            response = httpclient.execute(httpPost);

            System.out.println("doPost response status:" + response.getStatusLine());
            //获取响应实体
            HttpEntity resEntity = response.getEntity();
            if (resEntity != null && response.getStatusLine().getStatusCode() == 200) {
                String resContent = EntityUtils.toString(resEntity);
//                result.put("success", true);
//                result.put("resContent", resContent);
                System.out.println("doPost response content:" + resContent);
            }
            EntityUtils.consume(resEntity);
        } catch (Exception e) {
            e.printStackTrace();
//            result.put("success", false);
//            result.put("errorMsg", e.getMessage());
            System.out.println("doPost Error:" + e);
        } finally {
            //关闭连接
            org.apache.http.client.utils.HttpClientUtils.closeQuietly(response);
            org.apache.http.client.utils.HttpClientUtils.closeQuietly(httpclient);
        }
        return result;
    }



    /**
     * HttpClient Post请求上传文件
     * @param url - 请求路径
     * @param paramMap - 请求参数
     * @param fileList - 上传文件列表，类型可以为List<String>或List<Map<String, String>>
     * @return
     */
//    public static Map<String, Object> doPost(String url, Map<String, Object> paramMap, List fileList) {
//        Map<String, Object> result = new HashMap<String, Object>();
//        result.put("success", false);
//        result.put("errorMsg", "");
//        result.put("resContent", "");
//        // 定义HttpClient
//        CloseableHttpClient httpclient = HttpClients.createDefault();
//        CloseableHttpResponse response = null;
//        try {
//            HttpPost httppost = new HttpPost(url);
//            // 设置超时时间
//            RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(60000)
//                    .setConnectionRequestTimeout(1000).setSocketTimeout(60000).build();
//            httppost.setConfig(requestConfig);
//            MultipartEntityBuilder builder = MultipartEntityBuilder.create();
//            if (fileList != null) {
//                for (Object fileObj : fileList) {
//                    if (fileObj instanceof String) {
//                        File file = new File(fileObj.toString());
//                        if (file.exists()) {
//                            builder.addBinaryBody(file.getName(), file);
//                        }
//                    } else if (fileObj instanceof Map) {
//                        Map<String, String> fileMap = (Map<String, String>) fileObj;
//                        for (String key : fileMap.keySet()) {
//                            File file = new File(fileMap.get(key));
//                            if (file.exists()) {
//                                builder.addBinaryBody(key, file);
//                            }
//                        }
//                    }
//                }
//            }
//
//            if (paramMap != null) {
//                for (String key : paramMap.keySet()) {
//                    if (paramMap.get(key) != null) {
//                        // 设置请求参数
//                        builder.addTextBody(key, paramMap.get(key).toString(), ContentType.create("text/plain", Consts.UTF_8));
//                    } else {
//                        builder.addTextBody(key, "");
//                    }
//                }
//            }
//            HttpEntity entity = builder.build();// 生成 HTTP POST实体
//            httppost.setEntity(entity);
//            response = httpclient.execute(httppost);
//
//            System.out.println("doPost response status:" + response.getStatusLine());
//            HttpEntity resEntity = response.getEntity();
//            if (resEntity != null && response.getStatusLine().getStatusCode() == 200) {
//                String content = EntityUtils.toString(resEntity);
//                result.put("success", true);
//                result.put("resContent", content);
//                System.out.println("doPost response content:" + content);
//            }
//            EntityUtils.consume(resEntity);
//        } catch (Exception e) {
//            result.put("success", false);
//            result.put("errorMsg", e.getMessage());
//            System.out.println("doPost Error:" + e);
//        } finally {
//            org.apache.http.client.utils.HttpClientUtils.closeQuietly(response);
//            org.apache.http.client.utils.HttpClientUtils.closeQuietly(httpclient);
//        }
//        return result;
//    }
//
//    public static void main(String[] args) {
//        String url = "http://localhost:8080/pos/api/fileUploadApi.action";
//        Map<String, Object> paramMap = new HashMap<String, Object>();
//        paramMap.put("userId", 125031542);
//        paramMap.put("facId", 1542);
//        HttpClientUtil.doPost(url, paramMap);
//    }

}
