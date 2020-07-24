package org.ych.art.utils.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.httpclient.ConnectTimeoutException;
import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.UsernamePasswordCredentials;
import org.apache.commons.httpclient.auth.AuthScope;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;

//@Slf4j
public class WebUtil {

    /**
     * 字符集类型转换
     *
     * @param str    字符串
     * @param encode 字符集类型 UTF-8 GBK
     * @return 字符串
     * @throws Exception
     */
    public static String convert(String str, String encode) {
        StringBuffer returnvalue = new StringBuffer();
        try {
            if ("".equals(str) || str == null) {
                return returnvalue.toString();
            } else {
                if ("".equals(encode) || encode == null) {
                    encode = "UTF-8";
                }
                for (int i = 0; i < str.length(); i++) {
                    int charLength = String.valueOf(str.charAt(i)).getBytes().length;
                    if (charLength > 1) {
                        returnvalue.append(URLEncoder.encode(String.valueOf(str.charAt(i)), encode));
                    } else {
                        returnvalue.append(str.charAt(i));
                    }
                }
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            // Log(e.getMessage());
        }
        return returnvalue.toString();
    }

    /**
     * 获取HTML代码
     *
     * @param url
     * @param encode
     * @return
     */
    public static String urlGet(String url, String encode) {
        String returnvalue = "";
        HttpClient httpClient = new HttpClient();

        try {

            // 创建get请求连接
            GetMethod getMethod = new GetMethod(convert(url, encode));
            // 设置字符集
            getMethod.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, encode);
            // 使用系统提供的默认的恢复策略
            getMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());

            int statusCode = httpClient.executeMethod(getMethod);

            returnvalue = getMethod.getResponseBodyAsString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return returnvalue;
    }

    /**
     * URL请求(post方式)
     *
     * @param url
     * @param headInfo
     * @return
     */
    public static String urlPost(String url, HashMap<String, String> headInfo) {
        String returnvalue = "";
        HttpClient httpClient = new HttpClient();

        PostMethod postMethod = new UTF8PostMethod(url);

        if (headInfo != null) {
            NameValuePair[] data = new NameValuePair[headInfo.size()];

            // 添加包头信息
            if (headInfo != null) {
                int i = 0;
                Iterator iter = headInfo.entrySet().iterator();
                while (iter.hasNext()) {
                    Map.Entry entry = (Map.Entry) iter.next();
                    data[i] = new NameValuePair((String) entry.getKey(), (String) entry.getValue());
                    i++;
                }
            }
            postMethod.setRequestBody(data);
        }
        try {
            int statusCode = httpClient.executeMethod(postMethod);
            if (statusCode != HttpStatus.SC_OK) {
                System.out.println("html请求出错" + postMethod.getStatusLine());
            }
            returnvalue = new String(postMethod.getResponseBodyAsString().getBytes("ISO-8859-1"), "utf-8");
            // returnvalue = postMethod.getResponseBodyAsStream(),"utf-8");
        } catch (ConnectTimeoutException e) {
            e.printStackTrace();
            System.out.println("html请求超时:" + url);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("html请求出错:url:" + url + postMethod.getStatusLine());
        }
        return returnvalue;
    }

}
