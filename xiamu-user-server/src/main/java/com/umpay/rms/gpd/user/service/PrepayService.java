package com.umpay.rms.gpd.user.service;


public interface PrepayService {

    /**
     * 预付费充值.
     * <p>
     *
     * @param acctId 企业标识
     * @param amount 充值金额
     * @return 充值后的余额
     */
    public long charge(String acctId, long amount);

    /**
     * 根据企业标识获取该客户的余额.
     * <p>
     *
     * @param acctId 企业标识
     * @return 返回空表示该企业余额未同步到缓存；其它返回余额，其中0表示余额为0
     */
    public Long getBalance(String acctId);

    /**
     * 预付费扣费.
     * <p>
     *
     * @param acctId     企业标识
     * @param billAmount 扣费金额
     * @return 返回余额，返回负值表示余额不足
     */
    public long billing(String acctId, long billAmount);

    /**
     * 预付费扣费回退.
     * <p>
     *
     * @param acctId     企业标识
     * @param billAmount 回退金额
     * @return
     */
    public long refund(String acctId, long billAmount);

    /**
     * 清空账户余额.
     * <p>
     *
     * @param accctId
     */
    public void clear(String accctId);
}
