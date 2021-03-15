package com.umpay.rms.gpd.user.util.requset;

import com.umpay.rms.gpd.internal.dto.JsonResult;
import com.umpay.rms.gpd.internal.dto.ResultCode;
import com.umpay.rms.gpd.internal.dto.ResultTool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * @program: rms-gpd
 * @description: request请求工具类
 * @author: xiamu
 * @create: 2020-07-28 10:35
 */
public class RequsetUtil {

    public static Logger logger = LoggerFactory.getLogger(RequsetUtil.class);

    public static JsonResult getJessionId(HttpServletRequest request, String paramKey) {

        Cookie[] cookies = request.getCookies();
        String result = "";
        if (cookies != null && cookies.length > 0) {
            for (Cookie c : cookies) {
                if (c.getName().equals(paramKey)) {
                    result = c.getValue();
                    logger.info("cookie info : {}", c.toString());
                    return ResultTool.success( result);
                }
            }
        }
        return ResultTool.fail(ResultCode.COMMON_FAIL);
    }
}
