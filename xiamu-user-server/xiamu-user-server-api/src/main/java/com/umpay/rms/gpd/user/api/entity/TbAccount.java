package com.umpay.rms.gpd.user.api.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * (TbAccount)实体类
 *
 * @author makejava
 * @since 2020-06-14 09:46:13
 */
public class TbAccount implements Serializable {
    private static final long serialVersionUID = 923138470188081010L;

    private String accountId;

    private String serial;
    /**
     * 公司名
     */
    private String company;
    /**
     * 1有效，2，冻结
     */
    private Integer status;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 法人证件号
     */
    private String legalPersonPapers;
    /**
     * 法人
     */
    private String legalPerson;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 营业执照
     */
    private byte[] businessLicense;
    /**
     * 信息安全证书
     */
    private byte[] securityCertificate;
    /**
     * 联系人姓名
     */
    private String contactsName;
    /**
     * 联系人地址
     */
    private String contactsAddress;
    /**
     * 联系人qq
     */
    private Long contactsQq;
    /**
     * 联系人微信
     */
    private String contactsWchat;
    /**
     * 状态：1，认证，2，未认证,3,审核中
     */
    private String authenticationStatus;
    /**
     * 认证时间
     */
    private Date registerTime;
    /**
     * 父级账号id
     */
    private String parentAccountId;
    //父级账号name
    private String parentAccountName;
    //手机号
    private String phoneNumber;
    //剩余条数
    private Long balanceNumber;
    //本周发送  貌似暂时没用到
    private int weekSendNumber;
    private Integer pageSize;
    private Integer pageNum;
    private String openTime;
    private String endTime;
    //
    /**
     * 备注
     */
    private String remark;

    //周内发送
    private String weekSend;
    private String[] accountIds;
    //今天回执成功
    private Integer todaySendSuccess;
    //今日发送失败
    private Integer todaySendFail;
    //未知回执
    private Integer todaySendUnKnow;

    //总发送量
    private Integer todaySendSum;

    //角色集合
    private List<DbRole> dbRoles;
    //角色集合
    private DbRole dbRole;
    //手机号
    private String mobile;
    private String securityCertificateUri;//信息安全证书
    private String businessLicenseUri;//营业执照
    private String agreementContentUri;//协议合同
    private Date updateTime;//修改时间
    //码号信息
    private Map<String, String> codeNumbers;


    public DbRole getDbRole() {
        return dbRole;
    }

    public String[] getAccountIds() {
        return accountIds;
    }

    public void setAccountIds(String[] accountIds) {
        this.accountIds = accountIds;
    }

    public void setDbRole(DbRole dbRole) {
        this.dbRole = dbRole;
    }

    public Map<String, String> getCodeNumbers() {
        return codeNumbers;
    }

    public void setCodeNumbers(Map<String, String> codeNumbers) {
        this.codeNumbers = codeNumbers;
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

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public List<DbRole> getDbRoles() {
        return dbRoles;
    }

    public void setDbRoles(List<DbRole> dbRoles) {
        this.dbRoles = dbRoles;
    }

    public Integer getTodaySendSum() {
        return todaySendSum;
    }

    public void setTodaySendSum(Integer todaySendSum) {
        this.todaySendSum = todaySendSum;
    }

    public Integer getTodaySendSuccess() {
        return todaySendSuccess;
    }

    public void setTodaySendSuccess(Integer todaySendSuccess) {
        this.todaySendSuccess = todaySendSuccess;
    }

    public Integer getTodaySendFail() {
        return todaySendFail;
    }

    public void setTodaySendFail(Integer todaySendFail) {
        this.todaySendFail = todaySendFail;
    }

    public Integer getTodaySendUnKnow() {
        return todaySendUnKnow;
    }

    public void setTodaySendUnKnow(Integer todaySendUnKnow) {
        this.todaySendUnKnow = todaySendUnKnow;
    }

    private List<DbUser> dbUsers;

    public List<DbUser> getDbUsers() {
        return dbUsers;
    }

    public void setDbUsers(List<DbUser> dbUsers) {
        this.dbUsers = dbUsers;
    }

    public String getWeekSend() {
        return weekSend;
    }

    public void setWeekSend(String weekSend) {
        this.weekSend = weekSend;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }


    public Long getBalanceNumber() {
        return balanceNumber;
    }

    public void setBalanceNumber(Long balanceNumber) {
        this.balanceNumber = balanceNumber;
    }

    public int getWeekSendNumber() {
        return weekSendNumber;
    }

    public void setWeekSendNumber(int weekSendNumber) {
        this.weekSendNumber = weekSendNumber;
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

    private List<TbAccountCodeNumber> tbAccountCodeNumber;

    private DbExaminationApproval dbExaminationApproval;

    public String getParentAccountName() {
        return parentAccountName;
    }

    public void setParentAccountName(String parentAccountName) {
        this.parentAccountName = parentAccountName;
    }

    public List<TbAccountCodeNumber> getTbAccountCodeNumber() {
        return tbAccountCodeNumber;
    }

    public void setTbAccountCodeNumber(List<TbAccountCodeNumber> tbAccountCodeNumber) {
        this.tbAccountCodeNumber = tbAccountCodeNumber;
    }

    public DbExaminationApproval getDbExaminationApproval() {
        return dbExaminationApproval;
    }

    public void setDbExaminationApproval(DbExaminationApproval dbExaminationApproval) {
        this.dbExaminationApproval = dbExaminationApproval;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getLegalPersonPapers() {
        return legalPersonPapers;
    }

    public void setLegalPersonPapers(String legalPersonPapers) {
        this.legalPersonPapers = legalPersonPapers;
    }

    public String getLegalPerson() {
        return legalPerson;
    }

    public void setLegalPerson(String legalPerson) {
        this.legalPerson = legalPerson;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getContactsName() {
        return contactsName;
    }

    public void setContactsName(String contactsName) {
        this.contactsName = contactsName;
    }

    public String getContactsAddress() {
        return contactsAddress;
    }

    public void setContactsAddress(String contactsAddress) {
        this.contactsAddress = contactsAddress;
    }

    public Long getContactsQq() {
        return contactsQq;
    }

    public void setContactsQq(Long contactsQq) {
        this.contactsQq = contactsQq;
    }

    public String getContactsWchat() {
        return contactsWchat;
    }

    public void setContactsWchat(String contactsWchat) {
        this.contactsWchat = contactsWchat;
    }

    public String getAuthenticationStatus() {
        return authenticationStatus;
    }

    public void setAuthenticationStatus(String authenticationStatus) {
        this.authenticationStatus = authenticationStatus;
    }

    public Date getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(Date registerTime) {
        this.registerTime = registerTime;
    }


    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public byte[] getBusinessLicense() {
        return businessLicense;
    }

    public void setBusinessLicense(byte[] businessLicense) {
        this.businessLicense = businessLicense;
    }

    public byte[] getSecurityCertificate() {
        return securityCertificate;
    }

    public void setSecurityCertificate(byte[] securityCertificate) {
        this.securityCertificate = securityCertificate;
    }

    public String getParentAccountId() {
        return parentAccountId;
    }

    public void setParentAccountId(String parentAccountId) {
        this.parentAccountId = parentAccountId;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public String getSecurityCertificateUri() {
        return securityCertificateUri;
    }

    public void setSecurityCertificateUri(String securityCertificateUri) {
        this.securityCertificateUri = securityCertificateUri;
    }

    public String getBusinessLicenseUri() {
        return businessLicenseUri;
    }

    public void setBusinessLicenseUri(String businessLicenseUri) {
        this.businessLicenseUri = businessLicenseUri;
    }

    public String getAgreementContentUri() {
        return agreementContentUri;
    }

    public void setAgreementContentUri(String agreementContentUri) {
        this.agreementContentUri = agreementContentUri;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}