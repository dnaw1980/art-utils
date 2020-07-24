package org.ych.art.utils.utils.http;

/**
 * http 请求响应异常
 *
 * @author 姚赪海
 */
public class HttpResponseException extends Exception {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private int code;

    private String body;

    public HttpResponseException(int code, String body) {
        super("http响应:" + code);

        this.code = code;
        this.body = body;
    }

    public int getCode() {
        return code;
    }

    public String getBody() {
        return body;
    }

}
