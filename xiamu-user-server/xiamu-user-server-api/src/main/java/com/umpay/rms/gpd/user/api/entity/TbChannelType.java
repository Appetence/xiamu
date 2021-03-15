package com.umpay.rms.gpd.user.api.entity;

import java.util.Date;

public class TbChannelType {
    /**
     *  通道编号,所属表字段为tb_channel_type.id
     */
    private String id;

    /**
     *  通道名称,所属表字段为tb_channel_type.name
     */
    private String name;

    /**
     *  通道标签,所属表字段为tb_channel_type.tag
     */
    private String tag;

    /**
     *  运营商 mobile, unicom, telecom,所属表字段为tb_channel_type.operators
     */
    private String operators;

    /**
     *  发送短信接口,所属表字段为tb_channel_type.send_mms_url
     */
    private String sendMmsUrl;

    /**
     *  申请模板接口,所属表字段为tb_channel_type.send_model_url
     */
    private String sendModelUrl;

    /**
     *  是否启用 , 0 不启用， 1 启用,所属表字段为tb_channel_type.enable
     */
    private Integer enable;

    /**
     *  创建人,所属表字段为tb_channel_type.creater
     */
    private String creater;

    /**
     *  创建时间,所属表字段为tb_channel_type.create_time
     */
    private Date createTime;

    /**
     *  修改人,所属表字段为tb_channel_type.modifier
     */
    private String modifier;

    /**
     *  更新时间,所属表字段为tb_channel_type.update_time
     */
    private Date updateTime;

    /**
     *  备注,所属表字段为tb_channel_type.ct_note
     */
    private String ctNote;

    /**
     * 获取 通道编号 字段:tb_channel_type.id
     *
     * @return tb_channel_type.id, 通道编号
     */
    public String getId() {
        return id;
    }

    /**
     * 设置 通道编号 字段:tb_channel_type.id
     *
     * @param id tb_channel_type.id, 通道编号
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * 获取 通道名称 字段:tb_channel_type.name
     *
     * @return tb_channel_type.name, 通道名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置 通道名称 字段:tb_channel_type.name
     *
     * @param name tb_channel_type.name, 通道名称
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 获取 通道标签 字段:tb_channel_type.tag
     *
     * @return tb_channel_type.tag, 通道标签
     */
    public String getTag() {
        return tag;
    }

    /**
     * 设置 通道标签 字段:tb_channel_type.tag
     *
     * @param tag tb_channel_type.tag, 通道标签
     */
    public void setTag(String tag) {
        this.tag = tag == null ? null : tag.trim();
    }

    /**
     * 获取 运营商 mobile, unicom, telecom 字段:tb_channel_type.operators
     *
     * @return tb_channel_type.operators, 运营商 mobile, unicom, telecom
     */
    public String getOperators() {
        return operators;
    }

    /**
     * 设置 运营商 mobile, unicom, telecom 字段:tb_channel_type.operators
     *
     * @param operators tb_channel_type.operators, 运营商 mobile, unicom, telecom
     */
    public void setOperators(String operators) {
        this.operators = operators == null ? null : operators.trim();
    }

    /**
     * 获取 发送短信接口 字段:tb_channel_type.send_mms_url
     *
     * @return tb_channel_type.send_mms_url, 发送短信接口
     */
    public String getSendMmsUrl() {
        return sendMmsUrl;
    }

    /**
     * 设置 发送短信接口 字段:tb_channel_type.send_mms_url
     *
     * @param sendMmsUrl tb_channel_type.send_mms_url, 发送短信接口
     */
    public void setSendMmsUrl(String sendMmsUrl) {
        this.sendMmsUrl = sendMmsUrl == null ? null : sendMmsUrl.trim();
    }

    /**
     * 获取 申请模板接口 字段:tb_channel_type.send_model_url
     *
     * @return tb_channel_type.send_model_url, 申请模板接口
     */
    public String getSendModelUrl() {
        return sendModelUrl;
    }

    /**
     * 设置 申请模板接口 字段:tb_channel_type.send_model_url
     *
     * @param sendModelUrl tb_channel_type.send_model_url, 申请模板接口
     */
    public void setSendModelUrl(String sendModelUrl) {
        this.sendModelUrl = sendModelUrl == null ? null : sendModelUrl.trim();
    }

    /**
     * 获取 是否启用 , 0 不启用， 1 启用 字段:tb_channel_type.enable
     *
     * @return tb_channel_type.enable, 是否启用 , 0 不启用， 1 启用
     */
    public Integer getEnable() {
        return enable;
    }

    /**
     * 设置 是否启用 , 0 不启用， 1 启用 字段:tb_channel_type.enable
     *
     * @param enable tb_channel_type.enable, 是否启用 , 0 不启用， 1 启用
     */
    public void setEnable(Integer enable) {
        this.enable = enable;
    }

    /**
     * 获取 创建人 字段:tb_channel_type.creater
     *
     * @return tb_channel_type.creater, 创建人
     */
    public String getCreater() {
        return creater;
    }

    /**
     * 设置 创建人 字段:tb_channel_type.creater
     *
     * @param creater tb_channel_type.creater, 创建人
     */
    public void setCreater(String creater) {
        this.creater = creater == null ? null : creater.trim();
    }

    /**
     * 获取 创建时间 字段:tb_channel_type.create_time
     *
     * @return tb_channel_type.create_time, 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置 创建时间 字段:tb_channel_type.create_time
     *
     * @param createTime tb_channel_type.create_time, 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取 修改人 字段:tb_channel_type.modifier
     *
     * @return tb_channel_type.modifier, 修改人
     */
    public String getModifier() {
        return modifier;
    }

    /**
     * 设置 修改人 字段:tb_channel_type.modifier
     *
     * @param modifier tb_channel_type.modifier, 修改人
     */
    public void setModifier(String modifier) {
        this.modifier = modifier == null ? null : modifier.trim();
    }

    /**
     * 获取 更新时间 字段:tb_channel_type.update_time
     *
     * @return tb_channel_type.update_time, 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置 更新时间 字段:tb_channel_type.update_time
     *
     * @param updateTime tb_channel_type.update_time, 更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 获取 备注 字段:tb_channel_type.ct_note
     *
     * @return tb_channel_type.ct_note, 备注
     */
    public String getCtNote() {
        return ctNote;
    }

    /**
     * 设置 备注 字段:tb_channel_type.ct_note
     *
     * @param ctNote tb_channel_type.ct_note, 备注
     */
    public void setCtNote(String ctNote) {
        this.ctNote = ctNote == null ? null : ctNote.trim();
    }

    @Override
    public String toString() {
        return "TbChannelType{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", tag='" + tag + '\'' +
                ", operators='" + operators + '\'' +
                ", sendMmsUrl='" + sendMmsUrl + '\'' +
                ", sendModelUrl='" + sendModelUrl + '\'' +
                ", enable=" + enable +
                ", creater='" + creater + '\'' +
                ", createTime=" + createTime +
                ", modifier='" + modifier + '\'' +
                ", updateTime=" + updateTime +
                ", ctNote='" + ctNote + '\'' +
                '}';
    }
}