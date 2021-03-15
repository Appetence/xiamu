package com.xiamu.core.strategy;

/**
 * @program: xiamu
 * @description: 策略实现
 * @author: xiamu
 * @create: 2021-03-03 16:16
 */
public interface StrategyContext<S> {
    /**
     * 添加一个策略实现
     *
     * @param strategyName
     * @param strategy
     */
    void addStrategy(String strategyName, S strategy);

    /**
     * 获取策略实现
     *
     * @param strategyName
     * @return
     */
    S getStrategy(String strategyName);
}
