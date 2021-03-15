package com.umpay.rms.gpd.user.api.entity;

import java.math.BigDecimal;
import java.util.Date;

public class TbChannelInfo {
    /**
     *  通道信息编号,所属表字段为tb_channel_info.id
     */
    private String id;

    /**
     *  通道类型编号,所属表字段为tb_channel_info.ct_id
     */
    private String ctId;

    /**
     *  账号,所属表字段为tb_channel_info.api_key
     */
    private String apiKey;

    /**
     *  密码,所属表字段为tb_channel_info.secret_key
     */
    private String secretKey;

    /**
     *  码号,所属表字段为tb_channel_info.vms_code
     */
    private String vmsCode;

    /**
     *  是否可扩展 0 不可扩展， 1 可扩展,所属表字段为tb_channel_info.extensible
     */
    private Integer extensible;

    /**
     *  是否启用 0 禁用， 1 启用,所属表字段为tb_channel_info.enable
     */
    private Integer enable;

    /**
     *  创建人,所属表字段为tb_channel_info.creater
     */
    private String creater;

    /**
     *  创建时间,所属表字段为tb_channel_info.create_time
     */
    private Date createTime;

    /**
     *  修改人,所属表字段为tb_channel_info.modifier
     */
    private String modifier;

    /**
     *  更新时间,所属表字段为tb_channel_info.update_time
     */
    private Date updateTime;

    /**
     *  每条短信价格,所属表字段为tb_channel_info.cost
     */
    private BigDecimal cost;

    /**
     *  通道状态 0正常 1 异常,所属表字段为tb_channel_info.status
     */
    private Integer status;

    /**
     *  短信回执地址,所属表字段为tb_channel_info.mms_resp_url
     */
    private String mmsRespUrl;

    /**
     *  模板审核回执地址,所属表字段为tb_channel_info.model_resp_url
     */
    private String modelRespUrl;

    /**
     *  备注,所属表字段为tb_channel_info.note
     */
    private String note;

    /**
     *  0 默认， 1 非默认,所属表字段为tb_channel_info.is_default
     */
    private Integer isDefault;

    /**
     *  发送速率（只做参考）,所属表字段为tb_channel_info.rate
     */
    private Integer rate;

    /**
     *  扩展号长度,所属表字段为tb_channel_info.extLength
     */
    private Integer extLength;

    private String operatorIp;

    private String sendMmsUrl;

    private String sendModelUrl;
    /**
     * 变量消息推送地址
     */
    private String varSendUrl;
    /**
     * 是否支持变量 1 含有 2 不含有
     */
    private String varFlag;
    /**
     * 关联的通道类型
     */
    private TbChannelType tbChannelType;

    public String getVarSendUrl() {
        return varSendUrl;
    }

    public void setVarSendUrl(String varSendUrl) {
        this.varSendUrl = varSendUrl;
    }

    public String getVarFlag() {
        return varFlag;
    }

    public void setVarFlag(String varFlag) {
        this.varFlag = varFlag;
    }

    /**
     * 获取 通道信息编号 字段:tb_channel_info.id
     *
     * @return tb_channel_info.id, 通道信息编号
     */
    public String getId() {
        return id;
    }

    /**
     * 设置 通道信息编号 字段:tb_channel_info.id
     *
     * @param id tb_channel_info.id, 通道信息编号
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * 获取 通道类型编号 字段:tb_channel_info.ct_id
     *
     * @return tb_channel_info.ct_id, 通道类型编号
     */
    public String getCtId() {
        return ctId;
    }

    /**
     * 设置 通道类型编号 字段:tb_channel_info.ct_id
     *
     * @param ctId tb_channel_info.ct_id, 通道类型编号
     */
    public void setCtId(String ctId) {
        this.ctId = ctId == null ? null : ctId.trim();
    }

    /**
     * 获取 账号 字段:tb_channel_info.api_key
     *
     * @return tb_channel_info.api_key, 账号
     */
    public String getApiKey() {
        return apiKey;
    }

    /**
     * 设置 账号 字段:tb_channel_info.api_key
     *
     * @param apiKey tb_channel_info.api_key, 账号
     */
    public void setApiKey(String apiKey) {
        this.apiKey = apiKey == null ? null : apiKey.trim();
    }

    /**
     * 获取 密码 字段:tb_channel_info.secret_key
     *
     * @return tb_channel_info.secret_key, 密码
     */
    public String getSecretKey() {
        return secretKey;
    }

    /**
     * 设置 密码 字段:tb_channel_info.secret_key
     *
     * @param secretKey tb_channel_info.secret_key, 密码
     */
    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey == null ? null : secretKey.trim();
    }

    /**
     * 获取 码号 字段:tb_channel_info.vms_code
     *
     * @return tb_channel_info.vms_code, 码号
     */
    public String getVmsCode() {
        return vmsCode;
    }

    /**
     * 设置 码号 字段:tb_channel_info.vms_code
     *
     * @param vmsCode tb_channel_info.vms_code, 码号
     */
    public void setVmsCode(String vmsCode) {
        this.vmsCode = vmsCode == null ? null : vmsCode.trim();
    }

    /**
     * 获取 是否可扩展 0 不可扩展， 1 可扩展 字段:tb_channel_info.extensible
     *
     * @return tb_channel_info.extensible, 是否可扩展 0 不可扩展， 1 可扩展
     */
    public Integer getExtensible() {
        return extensible;
    }

    /**
     * 设置 是否可扩展 0 不可扩展， 1 可扩展 字段:tb_channel_info.extensible
     *
     * @param extensible tb_channel_info.extensible, 是否可扩展 0 不可扩展， 1 可扩展
     */
    public void setExtensible(Integer extensible) {
        this.extensible = extensible;
    }

    /**
     * 获取 是否启用 0 禁用， 1 启用 字段:tb_channel_info.enable
     *
     * @return tb_channel_info.enable, 是否启用 0 禁用， 1 启用
     */
    public Integer getEnable() {
        return enable;
    }

    /**
     * 设置 是否启用 0 禁用， 1 启用 字段:tb_channel_info.enable
     *
     * @param enable tb_channel_info.enable, 是否启用 0 禁用， 1 启用
     */
    public void setEnable(Integer enable) {
        this.enable = enable;
    }

    /**
     * 获取 创建人 字段:tb_channel_info.creater
     *
     * @return tb_channel_info.creater, 创建人
     */
    public String getCreater() {
        return creater;
    }

    /**
     * 设置 创建人 字段:tb_channel_info.creater
     *
     * @param creater tb_channel_info.creater, 创建人
     */
    public void setCreater(String creater) {
        this.creater = creater == null ? null : creater.trim();
    }

    /**
     * 获取 创建时间 字段:tb_channel_info.create_time
     *
     * @return tb_channel_info.create_time, 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置 创建时间 字段:tb_channel_info.create_time
     *
     * @param createTime tb_channel_info.create_time, 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取 修改人 字段:tb_channel_info.modifier
     *
     * @return tb_channel_info.modifier, 修改人
     */
    public String getModifier() {
        return modifier;
    }

    /**
     * 设置 修改人 字段:tb_channel_info.modifier
     *
     * @param modifier tb_channel_info.modifier, 修改人
     */
    public void setModifier(String modifier) {
        this.modifier = modifier == null ? null : modifier.trim();
    }

    /**
     * 获取 更新时间 字段:tb_channel_info.update_time
     *
     * @return tb_channel_info.update_time, 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置 更新时间 字段:tb_channel_info.update_time
     *
     * @param updateTime tb_channel_info.update_time, 更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 获取 每条短信价格 字段:tb_channel_info.cost
     *
     * @return tb_channel_info.cost, 每条短信价格
     */
    public BigDecimal getCost() {
        return cost;
    }

    /**
     * 设置 每条短信价格 字段:tb_channel_info.cost
     *
     * @param cost tb_channel_info.cost, 每条短信价格
     */
    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    /**
     * 获取 通道状态 0正常 1 异常 字段:tb_channel_info.status
     *
     * @return tb_channel_info.status, 通道状态 0正常 1 异常
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置 通道状态 0正常 1 异常 字段:tb_channel_info.status
     *
     * @param status tb_channel_info.status, 通道状态 0正常 1 异常
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取 短信回执地址 字段:tb_channel_info.mms_resp_url
     *
     * @return tb_channel_info.mms_resp_url, 短信回执地址
     */
    public String getMmsRespUrl() {
        return mmsRespUrl;
    }

    /**
     * 设置 短信回执地址 字段:tb_channel_info.mms_resp_url
     *
     * @param mmsRespUrl tb_channel_info.mms_resp_url, 短信回执地址
     */
    public void setMmsRespUrl(String mmsRespUrl) {
        this.mmsRespUrl = mmsRespUrl == null ? null : mmsRespUrl.trim();
    }

    /**
     * 获取 模板审核回执地址 字段:tb_channel_info.model_resp_url
     *
     * @return tb_channel_info.model_resp_url, 模板审核回执地址
     */
    public String getModelRespUrl() {
        return modelRespUrl;
    }

    /**
     * 设置 模板审核回执地址 字段:tb_channel_info.model_resp_url
     *
     * @param modelRespUrl tb_channel_info.model_resp_url, 模板审核回执地址
     */
    public void setModelRespUrl(String modelRespUrl) {
        this.modelRespUrl = modelRespUrl == null ? null : modelRespUrl.trim();
    }

    /**
     * 获取 备注 字段:tb_channel_info.note
     *
     * @return tb_channel_info.note, 备注
     */
    public String getNote() {
        return note;
    }

    /**
     * 设置 备注 字段:tb_channel_info.note
     *
     * @param note tb_channel_info.note, 备注
     */
    public void setNote(String note) {
        this.note = note == null ? null : note.trim();
    }

    /**
     * 获取 0 默认， 1 非默认 字段:tb_channel_info.is_default
     *
     * @return tb_channel_info.is_default, 0 默认， 1 非默认
     */
    public Integer getIsDefault() {
        return isDefault;
    }

    /**
     * 设置 0 默认， 1 非默认 字段:tb_channel_info.is_default
     *
     * @param isDefault tb_channel_info.is_default, 0 默认， 1 非默认
     */
    public void setIsDefault(Integer isDefault) {
        this.isDefault = isDefault;
    }

    /**
     * 获取 发送速率（只做参考） 字段:tb_channel_info.rate
     *
     * @return tb_channel_info.rate, 发送速率（只做参考）
     */
    public Integer getRate() {
        return rate;
    }

    /**
     * 设置 发送速率（只做参考） 字段:tb_channel_info.rate
     *
     * @param rate tb_channel_info.rate, 发送速率（只做参考）
     */
    public void setRate(Integer rate) {
        this.rate = rate;
    }

    /**
     * 获取 扩展号长度 字段:tb_channel_info.extlength
     *
     * @return tb_channel_info.extlength, 扩展号长度
     */
    public Integer getExtLength() {
        return extLength;
    }

    /**
     * 设置 扩展号长度 字段:tb_channel_info.extlength
     *
     * @param extLength tb_channel_info.extlength, 扩展号长度
     */
    public void setExtLength(Integer extLength) {
        this.extLength = extLength;
    }

    public TbChannelType getTbChannelType() {
        return tbChannelType;
    }

    public void setTbChannelType(TbChannelType tbChannelType) {
        this.tbChannelType = tbChannelType;
    }

    public String getOperatorIp() {
        return operatorIp;
    }

    public void setOperatorIp(String operatorIp) {
        this.operatorIp = ("".equals(operatorIp)) ? null : operatorIp;
    }

    public String operator;

    public String getSendMmsUrl() {
        return sendMmsUrl;
    }

    public void setSendMmsUrl(String sendMmsUrl) {
        this.sendMmsUrl = sendMmsUrl;
    }

    public String getSendModelUrl() {
        return sendModelUrl;
    }

    public void setSendModelUrl(String sendModelUrl) {
        this.sendModelUrl = sendModelUrl;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    @Override
    public String toString() {
        return "TbChannelInfo{" +
                "id='" + id + '\'' +
                ", ctId='" + ctId + '\'' +
                ", apiKey='" + apiKey + '\'' +
                ", secretKey='" + secretKey + '\'' +
                ", vmsCode='" + vmsCode + '\'' +
                ", extensible=" + extensible +
                ", enable=" + enable +
                ", creater='" + creater + '\'' +
                ", createTime=" + createTime +
                ", modifier='" + modifier + '\'' +
                ", updateTime=" + updateTime +
                ", cost=" + cost +
                ", status=" + status +
                ", mmsRespUrl='" + mmsRespUrl + '\'' +
                ", modelRespUrl='" + modelRespUrl + '\'' +
                ", note='" + note + '\'' +
                ", isDefault=" + isDefault +
                ", rate=" + rate +
                ", extLength=" + extLength +
                ", operatorIp='" + operatorIp + '\'' +
                ", sendMmsUrl='" + sendMmsUrl + '\'' +
                ", sendModelUrl='" + sendModelUrl + '\'' +
                ", tbChannelType=" + tbChannelType +
                '}';
    }
}