package com.xiamu.core.chain;

/**
 * @program: xiamu
 * @description: 通用业务接口
 * @author: xiamu
 * @create: 2021-03-02 19:40
 */
public interface BusinessService<Request,Result> extends BaseService<BusinessService<Request,Result>> {
    /**
     * business function
     * @param request
     * @return
     */
    Result apply(Request request);
}
