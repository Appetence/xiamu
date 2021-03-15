package com.umpay.rms.gpd.user.security.request;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

/**
 * @program: rms-gpd
 * @description: request.parameter
 * @author: xiamu
 * @create: 2020-07-26 10:04
 */
public class ParameterRequestWrapper extends HttpServletRequestWrapper {

    private Map<String, String[]> parameterMap = new HashMap<>();

    /**
     * Constructs a request object wrapping the given request.
     *
     * @param request
     * @throws IllegalArgumentException if the request is null
     */
    public ParameterRequestWrapper(HttpServletRequest request) {
        super(request);
        //将参数表，赋予给当前的Map以便于持有request中的参数
        this.parameterMap.putAll(request.getParameterMap());
    }

    /**
     * 重载构造方法
     */

    public ParameterRequestWrapper(HttpServletRequest request, Map<String, Object> extendparameterMap) {
        this(request);
        //这里将扩展参数写入参数表
        addAllParameters(extendparameterMap);
    }

    /**
     * 在获取所有的参数名,必须重写此方法，否则对象中参数值映射不上
     *
     * @return
     */
    @Override
    public Enumeration<String> getParameterNames() {
        return new Vector(parameterMap.keySet()).elements();
    }

    /**
     * 重写getParameter方法
     *
     * @param name 参数名
     * @return 返回参数值
     */
    @Override
    public String getParameter(String name) {
        String[] values = parameterMap.get(name);
        if (values == null || values.length == 0) {
            return null;
        }
        return values[0];
    }

    @Override
    public String[] getParameterValues(String name) {
        String[] values = parameterMap.get(name);
        if (values == null || values.length == 0) {
            return null;
        }
        return values;
    }

    /**
     * 增加多个参数
     *
     * @param otherparameterMap 增加的多个参数
     */
    public void addAllParameters(Map<String, Object> otherparameterMap) {
        for (Map.Entry<String, Object> entry : otherparameterMap.entrySet()) {
            addParameter(entry.getKey(), entry.getValue());
        }
    }

    /**
     * 增加参数
     *
     * @param name  参数名
     * @param value 参数值
     */
    public void addParameter(String name, Object value) {
        if (value != null) {
            if (value instanceof String[]) {
                parameterMap.put(name, (String[]) value);
            } else if (value instanceof String) {
                parameterMap.put(name, new String[]{(String) value});
            } else {
                parameterMap.put(name, new String[]{String.valueOf(value)});
            }
        }
    }
}
