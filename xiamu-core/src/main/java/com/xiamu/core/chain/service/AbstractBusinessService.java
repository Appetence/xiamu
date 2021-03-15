package com.xiamu.core.chain.service;

import com.xiamu.core.chain.BaseDutyService;
import com.xiamu.core.chain.BaseService;
import com.xiamu.core.chain.BusinessService;
import com.xiamu.core.chain.ServiceManager;

/**
 * @program: xiamu
 * @description: 职责链实现
 * @author: xiamu
 * @create: 2021-03-02 19:52
 */
public abstract class AbstractBusinessService<Request, Result>
        implements BaseService<BusinessService<Request, Result>>, BusinessService<Request, Result>, BaseDutyService<Request> {

    /**
     * 下一个节点
     */
    protected BusinessService<Request, Result> child;
    /**
     * 责任链
     */
    protected ServiceManager<BusinessService<Request, Result>> serviceServiceManager;

    public void setServiceServiceManager(ServiceManager<BusinessService<Request, Result>> serviceServiceManager) {
        this.serviceServiceManager = serviceServiceManager;
        //责任链添加
        this.serviceServiceManager.addChild(this);
    }

    /**
     * 设置责任链
     *
     * @param child
     * @return
     */
    @Override
    public BusinessService<Request, Result> setChild(BusinessService<Request, Result> child) {
        this.child = child;
        return child;
    }

    @Override
    public BusinessService<Request, Result> getChild() {
        return this.child;
    }

    @Override
    public Result apply(Request request) {
        if (isMyDuty(request)) {
            return doApplay(request);
        } else {
            if (this.child == null) {
                throw new RuntimeException("未找到具体实现类");
            } else {
                return this.child.apply(request);
            }
        }
    }

    /**
     * 执行子类具体实现类
     *
     * @param request
     * @return
     */
    public abstract Result doApplay(Request request);
}
