package com.bloom.qingstor;

/**
 * @author curry
 * Created by on 2022-07-27 上午10:14
 */

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

import javax.net.ssl.*;
import java.io.*;
import java.net.URL;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Logger;

/**
 * HTTP协议传输工具类
 */
public class HttpUtil {
    private static Logger       logger             = Logger.getLogger("HttpUtil");

    /** 用于格式化鉴权头域,给"X-WSSE"参数赋值 */
    private static final String WSSE_HEADER_FORMAT = "UsernameToken Username=\"%s\",PasswordDigest=\"%s\",Nonce=\"%s\",Created=\"%s\"";

    private static final int    HTTP_STATUS_OK     = 200;

    /**
     * 向指定 URL发送POST方法的请求
     *
     * @param appKey
     * @param appSecret
     * @param url
     * @param jsonBody
     * @return
     */
    public static String sendPost(String appKey, String appSecret, String url, String jsonBody) {
        DataOutputStream out = null;
        BufferedReader in = null;
        StringBuffer result = new StringBuffer();
        HttpsURLConnection connection = null;
        InputStream is = null;
        HostnameVerifier hv = new HostnameVerifier() {

            @Override
            public boolean verify(String hostname, SSLSession session) {
                return true;
            }
        };
        try {
            trustAllHttpsCertificates();
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        try {
            URL realUrl = new URL(url);
            connection = (HttpsURLConnection) realUrl.openConnection();

            connection.setHostnameVerifier(hv);
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
            connection.setRequestProperty("Authorization",
                "WSSE realm=\"SDP\", profile=\"UsernameToken\", type=\"Appkey\"");
            connection.setRequestProperty("X-WSSE", buildWsseHeader(appKey, appSecret));

            logger.info("RequestBody is : " + jsonBody);

            connection.connect();
            out = new DataOutputStream(connection.getOutputStream());
            out.writeBytes(jsonBody);
            out.flush();
            out.close();

            int status = connection.getResponseCode();
            if (HTTP_STATUS_OK == status) {
                is = connection.getInputStream();
            } else {
                is = connection.getErrorStream();
            }
            in = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            String line = "";
            while ((line = in.readLine()) != null) {
                result.append(line);
            }
        } catch (Exception e) {
            logger.info("Send Post request catch exception: " + e.toString());
        } finally {
            IOUtils.closeQuietly(out);
            IOUtils.closeQuietly(is);
            IOUtils.closeQuietly(in);
            if (null != connection) {
                IOUtils.close(connection);
            }
        }
        return result.toString();
    }

    /**
     * 向指定 URL发送PUT方法的请求
     *
     * @param appKey
     * @param appSecret
     * @param url
     * @param jsonBody
     * @return
     */
    public static String sendPut(String appKey, String appSecret, String url, String jsonBody) {
        DataOutputStream out = null;
        BufferedReader in = null;
        StringBuffer result = new StringBuffer();
        HttpsURLConnection connection = null;
        InputStream is = null;
        HostnameVerifier hv = new HostnameVerifier() {

            @Override
            public boolean verify(String hostname, SSLSession session) {
                return true;
            }
        };
        try {
            trustAllHttpsCertificates();
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        try {
            URL realUrl = new URL(url);
            connection = (HttpsURLConnection) realUrl.openConnection();

            connection.setHostnameVerifier(hv);
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setRequestMethod("PUT");
            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
            connection.setRequestProperty("Authorization",
                "WSSE realm=\"SDP\", profile=\"UsernameToken\", type=\"Appkey\"");
            connection.setRequestProperty("X-WSSE", buildWsseHeader(appKey, appSecret));

            logger.info("RequestBody is : " + jsonBody);

            connection.connect();
            out = new DataOutputStream(connection.getOutputStream());
            out.writeBytes(jsonBody);
            out.flush();
            out.close();

            int status = connection.getResponseCode();
            if (HTTP_STATUS_OK == status) {
                is = connection.getInputStream();
            } else {
                is = connection.getErrorStream();
            }
            in = new BufferedReader(new InputStreamReader(is));
            String line;
            while ((line = in.readLine()) != null) {
                result.append(line);
            }
        } catch (Exception e) {
            logger.info("Send Put request catch exception: " + e.toString());
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(out);
            IOUtils.closeQuietly(is);
            IOUtils.closeQuietly(in);
            if (null != connection) {
                IOUtils.close(connection);
            }
        }
        return result.toString();
    }

    /**
     * 向指定 URL发送DELETE方法的请求
     *
     * @param appKey
     * @param appSecret
     * @param url
     * @param params
     * @return
     */
    public static String sendDelete(String appKey, String appSecret, String url, String params) {
        BufferedReader in = null;
        StringBuffer result = new StringBuffer();
        HttpsURLConnection connection = null;
        InputStream is = null;

        HostnameVerifier hv = new HostnameVerifier() {

            @Override
            public boolean verify(String hostname, SSLSession session) {
                return true;
            }
        };
        try {
            trustAllHttpsCertificates();
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        try {
            String realPath = url + (StringUtils.isEmpty(params) ? "" : "?" + params);
            URL realUrl = new URL(realPath);
            connection = (HttpsURLConnection) realUrl.openConnection();

            connection.setHostnameVerifier(hv);
            connection.setDoInput(true);
            connection.setRequestMethod("DELETE");
            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
            connection.setRequestProperty("Authorization",
                "WSSE realm=\"SDP\", profile=\"UsernameToken\", type=\"Appkey\"");
            connection.setRequestProperty("X-WSSE", buildWsseHeader(appKey, appSecret));

            logger.info("RequestBody is : " + params);

            connection.connect();

            int status = connection.getResponseCode();
            if (HTTP_STATUS_OK == status) {
                is = connection.getInputStream();
            } else {
                is = connection.getErrorStream();
            }
            in = new BufferedReader(new InputStreamReader(is));
            String line;
            while ((line = in.readLine()) != null) {
                result.append(line);
            }
        } catch (Exception e) {
            logger.info("Send DELETE request catch exception: " + e.toString());
        } finally {
            IOUtils.closeQuietly(is);
            IOUtils.closeQuietly(in);
            if (null != connection) {
                IOUtils.close(connection);
            }
        }
        return result.toString();
    }

    /**
     * 向指定 URL发送GET方法的请求
     *
     * @param appKey
     * @param appSecret
     * @param url
     * @param params
     * @return
     */
    public static String sendGet(String appKey, String appSecret, String url, String params) {
        BufferedReader in = null;
        StringBuffer result = new StringBuffer();
        HttpsURLConnection connection = null;
        InputStream is = null;

        HostnameVerifier hv = new HostnameVerifier() {

            @Override
            public boolean verify(String hostname, SSLSession session) {
                return true;
            }
        };
        try {
            trustAllHttpsCertificates();
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        try {
            String realPath = url + (StringUtils.isEmpty(params) ? "" : "?" + params);
            URL realUrl = new URL(realPath);
            connection = (HttpsURLConnection) realUrl.openConnection();

            connection.setHostnameVerifier(hv);
            connection.setDoInput(true);
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
            connection.setRequestProperty("Authorization",
                "WSSE realm=\"SDP\", profile=\"UsernameToken\", type=\"Appkey\"");
            connection.setRequestProperty("X-WSSE", buildWsseHeader(appKey, appSecret));
            connection.setInstanceFollowRedirects(false); //设置本次连接不自动处理重定向

            logger.info("RequestBody is : " + params);

            connection.connect();

            int status = connection.getResponseCode();
            if (301 == status) { //获取录音文件下载地址
                return connection.getHeaderField("Location");
            } else if (HTTP_STATUS_OK == status) { //查询绑定信息
                is = connection.getInputStream();
            } else { //获取错误码
                is = connection.getErrorStream();
            }
            in = new BufferedReader(new InputStreamReader(is));
            String line;
            while ((line = in.readLine()) != null) {
                result.append(line);
            }
        } catch (Exception e) {
            logger.info("Send GET request catch exception: " + e.toString());
        } finally {
            IOUtils.closeQuietly(is);
            IOUtils.closeQuietly(in);
            if (null != connection) {
                IOUtils.close(connection);
            }
        }
        return result.toString();
    }

    /**
     * 键值对转查询url
     *
     * @param map
     * @return
     */
    public static String map2UrlEncodeString(Map<String, Object> map) {
        if (null == map || map.isEmpty()) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        String temp = "";

        for (String s : map.keySet()) {
            try {
                temp = URLEncoder.encode(String.valueOf(map.get(s)), "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            sb.append(s).append("=").append(temp).append("&");
        }
        return sb.deleteCharAt(sb.length() - 1).toString();
    }

    /**
     * 生成X-WSSE参数值
     *
     * @param appKey
     * @param appSecret
     * @return
     */
    static String buildWsseHeader(String appKey, String appSecret) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        String time = sdf.format(new Date());
        String nonce = UUID.randomUUID().toString().replace("-", "");

        MessageDigest md;
        byte[] passwordDigest = null;

        try {
            md = MessageDigest.getInstance("SHA-256");
            md.update((nonce + time + appSecret).getBytes());
            passwordDigest = md.digest();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        String passwordDigestBase64Str = Base64.getEncoder().encodeToString(passwordDigest);
        return String.format(WSSE_HEADER_FORMAT, appKey, passwordDigestBase64Str, nonce, time);
    }

    /**
     * 忽略SSL证书校验
     *
     * @throws Exception
     */
    static void trustAllHttpsCertificates() throws Exception {
        TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
            @Override
            public void checkClientTrusted(X509Certificate[] chain,
                                           String authType) throws CertificateException {
                return;
            }

            @Override
            public void checkServerTrusted(X509Certificate[] chain,
                                           String authType) throws CertificateException {
                return;
            }

            @Override
            public X509Certificate[] getAcceptedIssuers() {
                return null;
            }
        } };
        SSLContext sc = SSLContext.getInstance("SSL");
        sc.init(null, trustAllCerts, null);
        HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
    }

    /**
     * 向指定 URL发送GET方法的请求
     *
     * @param appKey
     * @param appSecret
     * @param url
     * @param params
     * @return
     */
    public static InputStream me(String url) {
        HttpsURLConnection connection = null;
        InputStream is = null;
        HostnameVerifier hv = new HostnameVerifier() {
            @Override
            public boolean verify(String hostname, SSLSession session) {
                return true;
            }
        };
        try {
            trustAllHttpsCertificates();
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        try {
            String realPath = url;
            URL realUrl = new URL(realPath);
            connection = (HttpsURLConnection) realUrl.openConnection();

            connection.setHostnameVerifier(hv);
            connection.setDoInput(true);
            connection.setInstanceFollowRedirects(false); //设置本次连接不自动处理重定向

            connection.connect();
            return connection.getInputStream();

        } catch (Exception e) {
            logger.info("Send GET request catch exception: " + e.toString());
        } finally {
            //            IOUtils.closeQuietly(is);
            //            if (null != connection) {
            //                IOUtils.close(connection);
            //            }
        }
        return null;
    }

    public static void main(String[] args) {
        me("https://1.189.75.74:5443/sp-uph80k0gmnqg3g5h03xw9w7b2h26/22072610390212037915654.wav?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Date=20220726T104020Z&X-Amz-SignedHeaders=host&X-Amz-Expires=604800&X-Amz-Credential=CFD20CAFEDFD43262E2D%2F20220726%2F%2Fs3%2Faws4_request&X-Amz-Signature=326d58d3a7e9e9e079c54a6d3250672d1f35f4c14a3a1ff531348f424898b6ef");
    }
}