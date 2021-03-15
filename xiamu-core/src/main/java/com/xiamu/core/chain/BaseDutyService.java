package com.xiamu.core.chain;

/**
 * @program: xiamu
 * @description: 职责链判断是否该自己处理
 * @author: xiamu
 * @create: 2021-03-02 19:48
 */
public interface BaseDutyService<Request> {

    /**
     *
     * @param request
     * @return
     */
    boolean isMyDuty(Request request);
}
