package com.xiamu.core.strategy.service;

import com.xiamu.core.strategy.StrategyContext;
import com.xiamu.core.strategy.StrategyService;
import org.apache.commons.lang3.ArrayUtils;

import javax.annotation.PostConstruct;

/**
 * @program: xiamu
 * @description: 策略器服务抽象类
 * @author: xiamu
 * @create: 2021-03-03 16:25
 */
public abstract class AbstractStrategyService<R, T> implements StrategyService<R, T> {
    /**
     * 策略上下文
     */
    private StrategyContext<StrategyService<R, T>> strategyContext;

    @PostConstruct
    public void init() {
        //初始化时候，初始化某种类型的上下文
        setStrategyContext();
        if (this.strategyContext == null) {
            throw new RuntimeException("初始化策略类错误, 策略上下文不能为空");
        }

        String[] strategyNames = getStrategyNames();

        if (ArrayUtils.isEmpty(strategyNames)) {
            throw new RuntimeException("初始化策略类错误, 获取策略名称不能为空");
        }
        for (String strategyName : strategyNames) {
            strategyContext.addStrategy(strategyName, this);
        }
        afterInit();
    }

    /**
     * 设置策略上下文，所有类必须实现此方法来设置上线文实际内容
     */
    protected abstract void setStrategyContext();

    /**
     * 初始化后处理钩子
     */
    protected void afterInit() {

    }

    public void setStrategyContext(StrategyContext<StrategyService<R, T>> strategyContext) {
        this.strategyContext = strategyContext;
    }
}
