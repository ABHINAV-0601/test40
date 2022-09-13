package com.michaels.designhub.colorpickerapi.controller;

import javax.net.ssl.*;
import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;
import java.security.KeyStore;


/**
 * #2
 * HTTPS 双向认证 - use truststore
 * 原生方式
 * @Author Ye_Wenda
 * @Date 7/11/2017
 */
public class HttpsTruststoreNativeDemo1 {
    // 客户端证书路径，用了本地绝对路径，需要修改
    private final static String CLIENT_CERT_FILE = "D:\\michaels\\design hub\\DevDesignHub.jks";
    // 客户端证书密码
    private final static String CLIENT_PWD = "changeit";
    // 信任库路径
    private final static String TRUST_STRORE_FILE = "D:\\michaels\\design hub\\mik-dev.jks";
    // 信任库密码
    private final static String TRUST_STORE_PWD = "changeit";
 
 
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
 
    public static void httpsCall() throws Exception {
        // 初始化密钥库
        KeyManagerFactory keyManagerFactory = KeyManagerFactory
                .getInstance("SunX509");
        KeyStore keyStore = getKeyStore(CLIENT_CERT_FILE, CLIENT_PWD, "JKS");
        keyManagerFactory.init(keyStore, CLIENT_PWD.toCharArray());
 
        // 初始化信任库
        TrustManagerFactory trustManagerFactory = TrustManagerFactory
                .getInstance("SunX509");
        KeyStore trustkeyStore = getKeyStore(TRUST_STRORE_FILE, TRUST_STORE_PWD,"JKS");
        trustManagerFactory.init(trustkeyStore);
 
        // 初始化SSL上下文
        SSLContext ctx = SSLContext.getInstance("SSL");
        ctx.init(keyManagerFactory.getKeyManagers(), trustManagerFactory
                .getTrustManagers(), null);
        SSLSocketFactory sf = ctx.getSocketFactory();
 
        HttpsURLConnection.setDefaultSSLSocketFactory(sf);
        String url = "https://testapi.michaels.com:443/dev/mikcommon-sapi/v1/colors?locale=fr_CAN";
        URL urlObj = new URL(url);
        HttpsURLConnection con = (HttpsURLConnection) urlObj.openConnection();
        con.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/56.0.2924.87 Safari/537.36");
        con.setRequestProperty("Accept-Language", "zh-CN;en-US,en;q=0.5");
       // con.setRequestProperty("Authorization","");
        con.setRequestProperty("client_id","Irvc6omdGqSlhDzsKHg3");
        con.setRequestProperty("client_secret","fq7ALK1SiJvohVGdICBU");
        con.setRequestProperty("applicationId","mikcommon-eapi");
       // con.setRequestProperty("training_flag","");
        con.setRequestMethod("GET");
 
        String response = readResponseBody(con.getInputStream());
        System.out.println(response);
    }
 
    /**
     * 获得KeyStore
     *
     * @param keyStorePath
     * @param password
     * @return
     * @throws Exception
     */
    private static KeyStore getKeyStore(String keyStorePath, String password,String type)
            throws Exception {
        FileInputStream is = new FileInputStream(keyStorePath);
        KeyStore ks = KeyStore.getInstance(type);
        ks.load(is, password.toCharArray());
        is.close();
        return ks;
    }
 
 
    public static void main(String[] args) throws Exception {
        httpsCall();
    }
 
}