package com.umpay.rms.gpd.user.api.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * (DbMmsMaterial)实体类
 *
 * @author makejava
 * @since 2020-06-08 14:31:13
 */
public class DbMmsMaterial implements Serializable {
    private static final long serialVersionUID = -20604624533871788L;
    /**
     * 用户id
     */
    private String userId;

    private Long id;

    private String mid;
    /**
    * 素材名称
    */
    private String name;
    /**
    * 素材类型，1，图片，2，声音，3，视频，4 文本
    */
    private String type;
    
    /**
     *  彩信素材原内容,所属表字段为tb_ec_material.file
     */
    private byte[] file;

    /**
     *  转换格式后的素材，做预览使用,所属表字段为tb_ec_material.format_file
     */
    private byte[] formatFile;

    /**
     *  彩信素材原文件名称,所属表字段为tb_ec_material.file_name
     */
    private String fileName;

    /**
    * 文件名
    */
    private String formatFileName;
    /**
    * 大小
    */
    private Long fileSize;
    /**
    * 素材描述
    */
    private String description;
    
    private String creater;
    
    private Date createTime;
    
    private String updater;
    
    private Date updateTime;
    /**
    * 企业id
    */
    private String acctId;
    /**
    * 状态 1 有效 2 无效
    */
    private String delFlag;
    /**
    * 是否作为素材展示,1:是，2:否
    */
    private String isDisplay;

    private int order;

    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public byte[] getFile() {
        return file;
    }

    public void setFile(byte[] file) {
        this.file = file;
    }


    public byte[] getFormatFile() {
        return formatFile;
    }

    public void setFormatFile(byte[] formatFile) {
        this.formatFile = formatFile;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFormatFileName() {
        return formatFileName;
    }

    public void setFormatFileName(String formatFileName) {
        this.formatFileName = formatFileName;
    }

    public Long getFileSize() {
        return fileSize;
    }

    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreater() {
        return creater;
    }

    public void setCreater(String creater) {
        this.creater = creater;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdater() {
        return updater;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getAcctId() {
        return acctId;
    }

    public void setAcctId(String acctId) {
        this.acctId = acctId;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public String getIsDisplay() {
        return isDisplay;
    }

    public void setIsDisplay(String isDisplay) {
        this.isDisplay = isDisplay;
    }

}