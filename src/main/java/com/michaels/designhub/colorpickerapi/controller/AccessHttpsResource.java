package com.michaels.designhub.colorpickerapi.controller;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;

public class AccessHttpsResource {
 
    /**
     * @param args
    */
    public static void main(String[] args)throws Exception {


        //"Authorization" : vars.token,
                //"Content-Type" : "application/json"

        //HTTPSUtil.initAccess();
        System.setProperty( "javax.net.ssl.trustStore","D:\\michaels\\design hub\\tibcoapiintegrationpre.michaels.com.jks");
        //System.setProperty("jsse.enableSNIExtension", "true");
        System.setProperty( "javax.net.ssl.trustStorePassword",   "***REMOVED***");
        System.out.printf(sendPost("https://tibcoapiintegrationpre.michaels.com:443/promorequest","store_id=''"));

    }


    /**
     * 向指定URL发送POST方法的请求
     *
     * @param url
     *            发送请求的URL
     * @param param
     *            请求参数，请求参数应该是name1=value1&name2=value2的形式。
     * @return URL所代表远程资源的响应
     */
    public static String sendPost(String url, String param) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("Authorization", "Bearer TYD8y2JHUEc2jgos9GZfvRRvnGayXb37WUEkgY0Vv3ucWkZZmB7qpwYCIPvo9A7AjLhabjjcAUMbwdjnaghUQ8qVpEvzAxoxrnN25ieTsZrsOZFOpxzPkZUgFrDEtq3QPDazqN8Xw4jLUE4v3rpYJPqkKcFnFqcL4sUmnNvQ9AsLS8AYFEaqsjxeomKP0d9ZUxKI9mXXMgsIy7rMYqqdmUGyoXVeaNZjCNNU4sKmEfa0FShT5iG8KrHEFa96pZHC");
            conn.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);

            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += "/n" + line;
            }
        } catch (Exception e) {
            System.out.println("发送POST请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输出流、输入流
        finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return result;
    }


    private static String readResponseBody(InputStream inputStream) throws IOException {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, Charset.forName("UTF-8")));
            StringBuffer sb = new StringBuffer();
            String buff = null;
            while((buff = br.readLine()) != null){
                sb.append(buff+"\n");
            }
            return sb.toString();
        } finally {
            inputStream.close();
        }
    }
 
}