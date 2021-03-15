package com.umpay.rms.gpd.internal.exception;

/**
 * 接口请求异常
 *
 * @date 2020/8/17
 */
public class RequestException extends RuntimeException {

    public RequestException() {
        super();
    }

    public RequestException(String message) {
        super(message);
    }
}
