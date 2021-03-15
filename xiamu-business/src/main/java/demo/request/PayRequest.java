package demo.request;

import lombok.Data;

/**
 * @date 2018/8/16
 */
@Data
public class PayRequest {

    private Integer yid ;
    private Double capital;
    private Double giveFee;
    private String source;
    private Integer rechargeType;
    private Integer orderId;
    private String createUser;
    /**
     * 支付类型：1微信，2支付宝
     */
    private Integer type;

}
