package com.xiamu.core.strategy.service;

import com.xiamu.core.strategy.StrategyService;
import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

/**
 * @program: xiamu
 * @description: 策略服务上下文抽象类
 * @author: xiamu
 * @create: 2021-03-03 16:32
 */
public abstract class AbstractStrategyServiceContext<R,T>
        extends AbstractStrategyContext<StrategyService<R, T>> implements StrategyService<R, T> {

    @Override
    public R apply(T t) {
        String strategyName = getStrategyName(t);
        if (StringUtils.isBlank(strategyName)) {
            throw new RuntimeException("获取策略实现类名称失败");
        }

        StrategyService<R, T> strategy = getStrategy(strategyName);

        if (Objects.isNull(strategy)) {
            strategy = getStrategy(StrategyService.DEFAULT_STRATEGY_NAME);
        }

        if (Objects.isNull(strategy)) {
            throw new RuntimeException("未找到名称为" + strategyName + "的策略实现类, 同时也没找到默认实现");
        }
        return strategy.apply(t);
    }

    @Override
    public String[] getStrategyNames() {
        return new String[0];
    }

    /**
     * 获取策略实现名称，用于查找
     *
     * @param t 请求参数
     * @return 策略实现名称
     */
    public abstract String getStrategyName(T t);


}
