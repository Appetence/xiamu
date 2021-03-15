package com.xiamu.core.chain;

/**
 * @program: xiamu
 * @description: 责任链模式 公共接口
 * @author: xiamu
 * @create: 2021-03-02 19:40
 */
public interface BaseService<T>  extends Ordered {

    /**
     * set child
     * @param child
     * @return
     */
    T setChild(T child);

    /**
     * get childs
     * @return
     */
    T getChild();
}
