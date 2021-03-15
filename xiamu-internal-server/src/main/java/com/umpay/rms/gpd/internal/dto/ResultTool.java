package com.umpay.rms.gpd.internal.dto;

/**
 * @program: rms-gpd
 * @description:
 * @author: xiamu
 * @create: 2020-06-03 15:19
 */
public class ResultTool {
    public static JsonResult success() {
        return new JsonResult(true);
    }

    public static <T> JsonResult<T> success(T data) {
        return new JsonResult(true, data);
    }

    public static JsonResult fail() {
        return new JsonResult(false);
    }

    public static JsonResult fail(ResultCode resultEnum) {
        return new JsonResult(false, resultEnum);
    }

    public static JsonResult failMessage(ResultCode resultEnum,Object message) {
        return new JsonResult(false, resultEnum,message);
    }
}
