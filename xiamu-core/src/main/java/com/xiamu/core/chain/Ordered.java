package com.xiamu.core.chain;

/**
 * @program: xiamu
 * @description: 责任链顺序
 * @author: xiamu
 * @create: 2021-03-02 19:42
 */
public interface Ordered {

    /**
     * Useful constant for the highest precedence value.
     * @see Integer#MIN_VALUE
     */
    int HIGHEST_PRECEDENCE = Integer.MIN_VALUE;

    /**
     * Useful constant for the lowest precedence value.
     * @see Integer#MAX_VALUE
     */
    int LOWEST_PRECEDENCE = Integer.MAX_VALUE;

    default int getOrder(){
        return 0;
    }
}
