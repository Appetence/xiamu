package com.xiamu.core;

import com.xiamu.core.chain.BaseService;
import com.xiamu.core.chain.ServiceManager;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

/**
 * @program: xiamu
 * @description: manager abstract class 所有的service manager
 * 类都需要继承此类来完成子类链条元素的添加以及链接 使用 addChild() 方法新增一个子元素后，
 * 然后会调用 refresh() 方法来完成链条的重新排序和链接
 * @author: xiamu
 * @create: 2021-03-03 16:43
 */
public abstract class BaseAbstractServiceManager<Child extends BaseService<Child>> implements ServiceManager<Child> {

    /**
     * 链元素存储
     */
    private List<Child> childrens = new ArrayList<>();
    /**
     * 链头元素
     */
    protected Child child;

    /**
     * 新增一个子元素然后重新刷新链
     *
     * @param child 子元素
     */
    @Override
    public void addChild(Child child) {
        childrens.add(child);
        this.refresh();
    }

    /**
     * 刷新链
     * 对 chhilrens 的元素重新排序，然后链接成链
     */
    private void refresh() {
        if (CollectionUtils.isEmpty(this.childrens)) {
            return;
        }
        /*
         * 为了保证链的有序性，每次排序后重新组装链
         */
        childrens.sort(Comparator.comparingInt(Child::getOrder));
        this.child = childrens.stream().findFirst().get();
        Child lastChild = this.child;
        for (int i = 1; i < childrens.size(); i++) {
            Child item = childrens.get(i);
            if (Objects.nonNull(item)) {
                lastChild.setChild(item);
                lastChild = item;
            }
        }
    }

    public Child getChild() {
        return child;
    }

    public Child setChild(Child child) {
        this.addChild(child);
        return child;
    }
}
