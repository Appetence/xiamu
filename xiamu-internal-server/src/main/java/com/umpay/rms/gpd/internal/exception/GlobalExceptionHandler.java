package com.umpay.rms.gpd.internal.exception;

import com.umpay.rms.gpd.internal.dto.JsonResult;
import com.umpay.rms.gpd.internal.dto.ResponseResult;
import com.umpay.rms.gpd.internal.dto.ResultTool;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingClass;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 *
 */
@ControllerAdvice
@Slf4j
@ConditionalOnMissingClass
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseResult handleException(Exception exception) {
        log.error("GlobalExceptionHandler:{}", exception.getMessage(), exception);
        return ResponseResult.fail(null);
    }

    @ResponseBody
    @ExceptionHandler(value = RuntimeException.class)
    public Map<String, Object> RuntimeExceptionHandler(RuntimeException ex) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("code", 500);
        map.put("mag", ex.getMessage());
        map.put("message", "运行时异常");
        log.error("异常信息：{}", ex);
        //发生异常进行日志记录，写入数据库或者其他处理，此处省略
        return map;
    }

    @ResponseBody
    @ExceptionHandler(value = RequestException.class)
    public Map<String, Object> RequestExceptionHandler(RequestException ex) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("code", 502);
        map.put("mag", ex.getMessage());
        map.put("message", "请求异常");

        //发生异常进行日志记录，写入数据库或者其他处理，此处省略
        return map;
    }

    @ResponseBody
    @ExceptionHandler(value = ParameterException.class)
    public Map<String, Object> RParameterExceptionHandler(ParameterException ex) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("code", 503);
        map.put("mag", ex.getMessage());
        map.put("message", "参数不合法错误");

        //发生异常进行日志记录，写入数据库或者其他处理，此处省略
        return map;
    }


}
