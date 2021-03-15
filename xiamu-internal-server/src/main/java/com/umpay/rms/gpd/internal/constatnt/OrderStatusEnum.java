package com.umpay.rms.gpd.internal.constatnt;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 功能描述
 *
 * @author yueyi2019
 * @date 2020/9/1
 */
@Getter
@AllArgsConstructor
public enum OrderStatusEnum implements CodeEnum  {
    /**
     * 预估订单
     */
    CALL_ORDER_FORECAST(0,"预估订单"),
    /**
     * 订单开始
     */
    STATUS_ORDER_START(1, "订单开始"),

    /**
     * 发起收款
     */
    STATUS_PAY_START(7, "发起收款"),
    /**
     * 支付完成
     */
    STATUS_PAY_END(8, "支付完成");

    private int code;
    private String value;
}
