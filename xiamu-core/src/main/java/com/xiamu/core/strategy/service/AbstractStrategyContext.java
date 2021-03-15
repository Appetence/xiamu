package com.xiamu.core.strategy.service;

import com.xiamu.core.strategy.StrategyContext;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: xiamu
 * @description: 策略模式抽象类
 * @author: xiamu
 * @create: 2021-03-03 16:23
 */
public abstract class AbstractStrategyContext<S> implements StrategyContext<S> {
    private Map<String, S> strategyMap = new HashMap<>(16);

    /**
     * 新增一个策略器
     * @param strategyName
     * @param strategy
     */
    @Override
    public void addStrategy(String strategyName, S strategy) {
        strategyMap.put(strategyName, strategy);
    }

    /**
     * 获取一个策略器
     * @param strategyName
     * @return
     */
    @Override
    public S getStrategy(String strategyName) {
        return strategyMap.get(strategyName);
    }
}
