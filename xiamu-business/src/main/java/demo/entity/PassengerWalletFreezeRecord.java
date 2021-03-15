package demo.entity;

import lombok.Data;

import java.util.Date;

/**
 */
@Data
public class PassengerWalletFreezeRecord {
    private Integer id;

    private Integer passengerInfoId;

    private Double freezeCapital;

    private Double freezeGiveFee;

    private Integer orderId;

    private Integer freezeStatus;

    private Date createTime;

    private Date updateTime;

}