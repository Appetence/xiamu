package com.xiamu.core.strategy;

/**
 * @program: xiamu
 * @description: 策略服务接口类
 * @author: xiamu
 * @create: 2021-03-03 16:19
 */
public interface StrategyService <R,T>{
    /**
     * 默认策略实现
     */
    String DEFAULT_STRATEGY_NAME = "DEFAULT_STRATEGY";

    /**
     * 执行
     * @param t
     * @return
     */
    R apply(T t);

    /**
     * 获取策略的实现名称
     * @return
     */
    String[] getStrategyNames();



}
