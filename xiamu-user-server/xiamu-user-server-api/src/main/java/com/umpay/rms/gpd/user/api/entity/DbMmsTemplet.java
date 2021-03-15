package com.umpay.rms.gpd.user.api.entity;


import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * (DbMmsTemplet)实体类
 *
 * @author makejava
 * @since 2020-06-08 14:31:13
 */
public class DbMmsTemplet implements Serializable {
    private static final long serialVersionUID = -97888075532666593L;
    /**
     * 用户id
     */
    private String templateUserId;

    private String id;
    /**
     * 企业名称
     */
    private String templateAcctName;
    /**
     * 企业ID，即用户id
     */
    private String templateAcctId;
    /**
     * 审核状态,1 待审核 2 审核中，3 审核通过，4审核不过，5平台审核通过
     */
    private String templateCheckStatus;
    /**
     * 审核人
     */
    private String templateChecker;
    /**
     * 审核时间
     */
    private Date templateCheckTime;
    /**
     * 申请不过理由
     */
    private String templateRefuseReason;
    /**
     * 是否删除 1无效 2 有效
     */
    private String templateDelFlag;
    /**
     * 创建者
     */
    private String templateCreater;

    private Date templateCreateTime;
    /**
     * 修改者
     */
    private String templateUpdater;

    private Date templateUpdateTime;
    /**
     * 模版编号
     */
    private String templateId;
    /**
     * 模版名称
     */
    private String templateName;
    /**
     * 短信标题
     */
    private String templateTitle;
    /**
     * 封面
     */
    private byte[] templateCover;
    /**
     * 签名
     */
    private String templateSignature;
    /**
     * 模版大小单位kb
     */
    private Long templateSize;
    /**
     * 是否作为模版使用 1 是 2 否
     */
    private String templetStatus;
    /**
     * 是否支持退订 1 支持，2 不支持
     */
    private Integer templateTd;
    /**
     * 文本内容工具类
     */
    private List<WordUtil> wordUtil;


    private List<MaterialUtil> materialUtils;
    //素材
    private List<DbMmsMaterial> dbMmsMaterialList;
    //网关审核状态
    private List<DbMmsTemplateRelation> relations;
    //开始时间
    private String openTime;
    //结束时间
    private String endTime;
    //封面链接
    private String coverUri;
    //1标示伟网关审核通过查寻
    private String gwFlag;

    private Integer pageSize;

    private Integer pageNum;

    private Integer checkStatusType;

    private List<String> mNumber;

    private Map<String, Object> relationMap;

    public Integer getCheckStatusType() {
        return checkStatusType;
    }

    public void setCheckStatusType(Integer checkStatusType) {
        this.checkStatusType = checkStatusType;
    }

    public String getCoverUri() {
        return coverUri;
    }

    public void setCoverUri(String coverUri) {
        this.coverUri = coverUri;
    }

    public List<String> getmNumber() {
        return mNumber;
    }

    public Map<String, Object> getRelationMap() {
        return relationMap;
    }

    public void setRelationMap(Map<String, Object> relationMap) {
        this.relationMap = relationMap;
    }

    public void setmNumber(List<String> mNumber) {
        this.mNumber = mNumber;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public String getOpenTime() {
        return openTime;
    }

    public void setOpenTime(String openTime) {
        this.openTime = openTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public List<DbMmsMaterial> getDbMmsMaterialList() {
        return dbMmsMaterialList;
    }

    public void setDbMmsMaterialList(List<DbMmsMaterial> dbMmsMaterialList) {
        this.dbMmsMaterialList = dbMmsMaterialList;
    }

    public String getTemplateUserId() {
        return templateUserId;
    }

    public void setTemplateUserId(String templateUserId) {
        this.templateUserId = templateUserId;
    }

    public List<WordUtil> getWordUtil() {
        return wordUtil;
    }

    public void setWordUtil(List<WordUtil> wordUtil) {
        this.wordUtil = wordUtil;
    }

    public List<MaterialUtil> getMaterialUtils() {
        return materialUtils;
    }

    public void setMaterialUtils(List<MaterialUtil> materialUtils) {
        this.materialUtils = materialUtils;
    }

    public List<DbMmsTemplateRelation> getRelations() {
        return relations;
    }

    public void setRelations(List<DbMmsTemplateRelation> relations) {
        this.relations = relations;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTemplateAcctName() {
        return templateAcctName;
    }

    public void setTemplateAcctName(String templateAcctName) {
        this.templateAcctName = templateAcctName;
    }

    public String getTemplateAcctId() {
        return templateAcctId;
    }

    public void setTemplateAcctId(String templateAcctId) {
        this.templateAcctId = templateAcctId;
    }

    public String getTemplateCheckStatus() {
        return templateCheckStatus;
    }

    public void setTemplateCheckStatus(String templateCheckStatus) {
        this.templateCheckStatus = templateCheckStatus;
    }

    public String getTemplateChecker() {
        return templateChecker;
    }

    public void setTemplateChecker(String templateChecker) {
        this.templateChecker = templateChecker;
    }

    public Date getTemplateCheckTime() {
        return templateCheckTime;
    }

    public void setTemplateCheckTime(Date templateCheckTime) {
        this.templateCheckTime = templateCheckTime;
    }

    public String getTemplateRefuseReason() {
        return templateRefuseReason;
    }

    public void setTemplateRefuseReason(String templateRefuseReason) {
        this.templateRefuseReason = templateRefuseReason;
    }

    public String getTemplateDelFlag() {
        return templateDelFlag;
    }

    public void setTemplateDelFlag(String templateDelFlag) {
        this.templateDelFlag = templateDelFlag;
    }

    public String getTemplateCreater() {
        return templateCreater;
    }

    public void setTemplateCreater(String templateCreater) {
        this.templateCreater = templateCreater;
    }

    public Date getTemplateCreateTime() {
        return templateCreateTime;
    }

    public void setTemplateCreateTime(Date templateCreateTime) {
        this.templateCreateTime = templateCreateTime;
    }

    public String getTemplateUpdater() {
        return templateUpdater;
    }

    public void setTemplateUpdater(String templateUpdater) {
        this.templateUpdater = templateUpdater;
    }

    public Date getTemplateUpdateTime() {
        return templateUpdateTime;
    }

    public void setTemplateUpdateTime(Date templateUpdateTime) {
        this.templateUpdateTime = templateUpdateTime;
    }

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public String getTemplateTitle() {
        return templateTitle;
    }

    public void setTemplateTitle(String templateTitle) {
        this.templateTitle = templateTitle;
    }

    public byte[] getTemplateCover() {
        return templateCover;
    }

    public void setTemplateCover(byte[] templateCover) {
        this.templateCover = templateCover;
    }

    public String getTemplateSignature() {
        return templateSignature;
    }

    public void setTemplateSignature(String templateSignature) {
        this.templateSignature = templateSignature;
    }

    public Long getTemplateSize() {
        return templateSize;
    }

    public void setTemplateSize(Long templateSize) {
        this.templateSize = templateSize;
    }

    public String getTempletStatus() {
        return templetStatus;
    }

    public void setTempletStatus(String templetStatus) {
        this.templetStatus = templetStatus;
    }

    public Integer getTemplateTd() {
        return templateTd;
    }

    public void setTemplateTd(Integer templateTd) {
        this.templateTd = templateTd;
    }


    public String getGwFlag() {
        return gwFlag;
    }

    public void setGwFlag(String gwFlag) {
        this.gwFlag = gwFlag;
    }
}