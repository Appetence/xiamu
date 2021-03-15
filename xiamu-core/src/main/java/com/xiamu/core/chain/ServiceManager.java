package com.xiamu.core.chain;

/**
 * @program: xiamu
 * @description: 策略器
 * @author: xiamu
 * @create: 2021-03-02 19:43
 */
public interface ServiceManager<Child> {

    /**
     * 责任链
     * @param child
     */
    void addChild(Child child);
}
