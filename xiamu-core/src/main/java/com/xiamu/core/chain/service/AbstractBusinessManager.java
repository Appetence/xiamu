package com.xiamu.core.chain.service;

import com.xiamu.core.chain.BusinessService;
import com.xiamu.core.chain.ServiceManager;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

/**
 * @program: xiamu
 * @description: 职责链条
 * @author: xiamu
 * @create: 2021-03-03 14:38
 */
public abstract class AbstractBusinessManager<Request, Result>
        implements BusinessService<Request, Result>, ServiceManager<BusinessService<Request, Result>> {
    /**
     * 责任链
     */
    protected List<BusinessService<Request, Result>> childrens = new ArrayList<>();
    /**
     * 链头元素
     */
    private BusinessService<Request, Result> child;

    /**
     * 增加一个子元素，然后刷新
     *
     * @param requestResultBusinessService
     */
    @Override
    public void addChild(BusinessService<Request, Result> requestResultBusinessService) {
        childrens.add(requestResultBusinessService);
        this.refresh();
    }

    /**
     * 责任链重新排序
     */
    private void refresh() {
        if (CollectionUtils.isEmpty(childrens)) {
            return;
        }
        //防止每次新增元素后整体的大顺序有变化
        childrens.sort(Comparator.comparingInt(BusinessService<Request, Result>::getOrder));
        this.child = childrens.stream().findFirst().get();
        //将责任链上排序后的每个元素依次连起来
        BusinessService<Request, Result> lastChild = this.child;
        for (BusinessService<Request, Result> businessService : childrens) {
            BusinessService<Request, Result> item = businessService;
            if (Objects.nonNull(item)) {
                lastChild.setChild(item);
                lastChild = item;
            }
        }
    }

    @Override
    public BusinessService<Request, Result> setChild(BusinessService<Request, Result> child) {
        addChild(child);
        return this;
    }

    @Override
    public BusinessService<Request, Result> getChild() {
        return this.child;
    }
}
