package org.ych.art.utils.utils.http;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.DeleteMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.httpclient.params.HttpClientParams;
import org.apache.commons.httpclient.params.HttpConnectionManagerParams;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.httpclient.protocol.Protocol;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;

public class HttpSendUtils {

    public static PoolingHttpClientConnectionManager poolConnManager = null;

    public static String getRequest(String url) throws HttpResponseException {
        Protocol myhttps = new Protocol("https", new CertSSLProtocolSocketFactory(), 443);
        Protocol.registerProtocol("https", myhttps);
        HttpClient http = new HttpClient();
        String result = "";
        // get请求
        GetMethod get = new GetMethod(url);
        // 设置请求报文头的编码
        // get.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;
        // charset=utf-8");
        get.setRequestHeader("Content-Type", "multipart/form-data;charset=utf-8");

        int statusCode = 0;
        try {
            http.executeMethod(get);
            statusCode = get.getStatusCode();

            ////////////////////////////////
            BufferedReader reader = new BufferedReader(new InputStreamReader(get.getResponseBodyAsStream(), "utf-8"));
            String tmp = null;
            while ((tmp = reader.readLine()) != null) {
                result += tmp + "\r\n";
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        if (statusCode != 200) {
            throw new HttpResponseException(statusCode, removeSlash(result));
        }

        return removeSlash(result);
    }

    public static String postRequest(String url, Map<String, String> param) throws HttpResponseException {

        boolean isHttps = false;
        // https://test-zxpt.baihangcredit.com:8443/api/v1/credit/report
        // 取协议
        int port = 80;
        int pos = url.indexOf("://");
        String proStr = url.substring(0, pos + 1);
        System.out.println("proStr:" + proStr);
        if ("https".equals(proStr)) {
            isHttps = true;
            port = 443;
        } else if (!"http".equals(proStr)) {
            throw new RuntimeException("协议无效：" + proStr);

        }

        // 取端口
        // 找第一个冒号
        int tag1Pos = url.indexOf(":", pos + 3);

        // 找第一个 /
        int tag2Pos = url.indexOf("/", pos + 3);

        System.out.println("tag1Pos:" + tag1Pos + ",\ttag2Pos:" + tag2Pos);
        // / 的位置必须 大于 ：
        if (tag1Pos != -1 && tag2Pos > tag1Pos) {
            // 取 ： 与 /的中间值
            String portStr = url.substring(tag1Pos + 1, tag2Pos);
            System.out.println("portStr:" + portStr);
            port = Integer.valueOf(portStr);

        }

        if (isHttps) {
            Protocol myhttps = new Protocol("https", new CertSSLProtocolSocketFactory(), port);
            Protocol.registerProtocol("https", myhttps);
        }
        String strResult = "";
        HttpClient httpClient = new HttpClient();

        HttpClientParams httpClientParams = httpClient.getParams();
        httpClientParams.setConnectionManagerTimeout(500000);

        // HttpConnectionParams
        HttpConnectionManagerParams httpConnectionManagerParams = httpClient.getHttpConnectionManager().getParams();
        httpConnectionManagerParams.setConnectionTimeout(500000);
        httpConnectionManagerParams.setSoTimeout(500000);

        // httpClient.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new
        // DefaultHttpMethodRetryHandler(0, false));
        int statusCode = 0;
        try {
            // post请求
            PostMethod post = new PostMethod(url);
            // post.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new
            // DefaultHttpMethodRetryHandler(0, false));
            List<NameValuePair> params = new ArrayList<>();
            for (Map.Entry<String, String> entry : param.entrySet()) {
                params.add(new NameValuePair(entry.getKey(), entry.getValue()));
            }
            NameValuePair[] names = new NameValuePair[params.size()];
            for (int i = 0; i < params.size(); i++) {
                names[i] = new NameValuePair(params.get(i).getName(), params.get(i).getValue());
            }
            // 设置请求报文头的编码
            post.setRequestHeader("Content-Type", "application/x-www-form-urlencoded; charset=utf-8");
            // post.setRequestHeader("Content-Type", "multipart/form-data ; charset=utf-8");
            // post.setRequestHeader("Connection", "keep-alive");

            post.setRequestBody(names);
            httpClient.executeMethod(post);

            statusCode = post.getStatusCode();
            BufferedReader reader = new BufferedReader(new InputStreamReader(post.getResponseBodyAsStream(), "utf-8"));
            String tmp = null;
            while ((tmp = reader.readLine()) != null) {
                strResult += tmp + "\r\n";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (statusCode != 200) {
            throw new HttpResponseException(statusCode, removeSlash(strResult));
        }

        return removeSlash(strResult);
    }

    public static String postRequest(String url, String json, Map<String, String> headParam)
            throws HttpResponseException {

        boolean isHttps = false;
        // https://test-zxpt.baihangcredit.com:8443/api/v1/credit/report
        // 取协议
        int port = 80;
        int pos = url.indexOf("://");
        String proStr = url.substring(0, pos);
        System.out.println("proStr:" + proStr);
        if ("https".equals(proStr)) {
            isHttps = true;
            port = 443;
        } else if (!"http".equals(proStr)) {
            throw new RuntimeException("协议无效：" + proStr);

        }

        // 取端口
        // 找第一个冒号
        int tag1Pos = url.indexOf(":", pos + 3);

        // 找第一个 /
        int tag2Pos = url.indexOf("/", pos + 3);

        System.out.println("tag1Pos:" + tag1Pos + ",\ttag2Pos:" + tag2Pos);
        // / 的位置必须 大于 ：
        if (tag1Pos != -1 && tag2Pos > tag1Pos) {
            // 取 ： 与 /的中间值
            String portStr = url.substring(tag1Pos + 1, tag2Pos);
            System.out.println("portStr:" + portStr);
            port = Integer.valueOf(portStr);

        }

        if (isHttps) {
            Protocol myhttps = new Protocol("https", new CertSSLProtocolSocketFactory(), port);
            Protocol.registerProtocol("https", myhttps);
        }

        // Protocol myhttps = new Protocol("https", new CertSSLProtocolSocketFactory(),
        // 443);
        // Protocol.registerProtocol("https", myhttps);
        String strResult = "";
        HttpClient httpClient = new HttpClient();

        HttpClientParams httpClientParams = httpClient.getParams();
        httpClientParams.setConnectionManagerTimeout(500000);

        // HttpConnectionParams
        HttpConnectionManagerParams httpConnectionManagerParams = httpClient.getHttpConnectionManager().getParams();
        httpConnectionManagerParams.setConnectionTimeout(500000);
        httpConnectionManagerParams.setSoTimeout(500000);

        // httpClient.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new
        // DefaultHttpMethodRetryHandler(0, false));
        int statusCode = 0;
        try {
            // post请求
            PostMethod post = new PostMethod(url);
            RequestEntity se = new StringRequestEntity(json, "application/json", "UTF-8");

            post.setRequestEntity(se);
            post.setRequestHeader("Content-Type", "application/json");

            if (headParam != null && headParam.size() > 0) {
                headParam.forEach((k, v) -> {
                    post.setRequestHeader(k, v);
                });
            }

            // 默认的重试策略
            // post.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new
            // DefaultHttpMethodRetryHandler());
            // post.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, 5000);// 设置超时时间
            statusCode = httpClient.executeMethod(post);

            BufferedReader reader = new BufferedReader(new InputStreamReader(post.getResponseBodyAsStream(), "utf-8"));
            String tmp = null;
            while ((tmp = reader.readLine()) != null) {
                strResult += tmp + "\r\n";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (statusCode != 200) {
            throw new HttpResponseException(statusCode, removeSlash(strResult));
        }

        return removeSlash(strResult);
    }

    public static String removeSlash(String result) {
        result = result.replace("\\", "");
        if (result.length() > 0)
            result = result.substring(0, result.length() - 1);
        return result;
    }

    public static String deleteRequest(String url, Map<String, String> dataForm) throws HttpResponseException {
        Protocol myhttps = new Protocol("https", new CertSSLProtocolSocketFactory(), 443);
        Protocol.registerProtocol("https", myhttps);
        String strResult = "";
        HttpClient http = new HttpClient();

        int statusCode = 0;

        try {
            DeleteMethod delete = new DeleteMethod(url);

            delete.setRequestHeader("Content-Type", "application/x-www-form-urlencoded; charset=utf-8");

            List<NameValuePair> data = new ArrayList<NameValuePair>();
            if (dataForm != null) {
                Set<String> keys = dataForm.keySet();
                for (String key : keys) {
                    NameValuePair nameValuePair = new NameValuePair(key, (String) dataForm.get(key));
                    data.add(nameValuePair);
                }
            }

            delete.setQueryString(data.toArray(new NameValuePair[0]));
            http.executeMethod(delete);

            statusCode = delete.getStatusCode();
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(delete.getResponseBodyAsStream(), "utf-8"));
            String tmp = null;
            while ((tmp = reader.readLine()) != null) {
                strResult += tmp + "\r\n";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (statusCode != 200) {
            throw new HttpResponseException(statusCode, removeSlash(strResult));
        }

        return removeSlash(strResult);
    }

}
