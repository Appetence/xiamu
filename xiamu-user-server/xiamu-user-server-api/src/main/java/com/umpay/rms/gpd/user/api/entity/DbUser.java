package com.umpay.rms.gpd.user.api.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * (DbUser)实体类
 *
 * @author makejava
 * @since 2020-06-04 16:15:43
 */
public class DbUser implements Serializable {
    private static final long serialVersionUID = 827126508928674592L;

    private Integer id;
    /**
     * 企业名称
     */
    private String name;
    /**
     * 企业编号
     */
    private String serial;
    /**
     * 登录用户名(手机号)
     */
    private String username;
    /**
     * 登录密码
     */
    private String password;
    /**
     * 盐
     */
    private String salt;

    private Date createTime;
    /**
     * 法人
     */
    private String legalPerson;
    /**
     * 法人证件号
     */
    private String legalPersonPapers;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 营业执照
     */
    private String businessLicense;
    /**
     * 信息安全证书
     */
    private String securityCertificate;
    /**
     * 余额预警
     */
    private Integer warning;
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
    private Integer contactsQq;
    /**
     * 联系人微信
     */
    private String contactsWchat;
    /**
     * 状态：1，认证，2，未认证
     */
    private String authenticationStatus;
    /**
     * 认证时间
     */
    private Date registerTime;
    /**
     * 状态，1，有效，2，无效
     */
    private Integer status;
    private String remark;//备注
    private String accountId;//账户id
    private String parentAccountId;//父级账户id

    private List<DbRole> roles;//角色集合
    private List<DbPermission> permissions;//权限集合

    private TbAccount tbAccount;
    private String endtime;

    private String opentime;

    private Integer companyId;
    private String privateKey;
    private String publicKey;
    private String company;

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }

    public String getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }

    public TbAccount getTbAccount() {
        return tbAccount;
    }

    public void setTbAccount(TbAccount tbAccount) {
        this.tbAccount = tbAccount;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public String getOpenTime() {
        return opentime;
    }

    public void setOpenTime(String openTime) {
        this.opentime = openTime;
    }

    public String getEndTIme() {
        return endtime;
    }

    public void setEndTIme(String endTIme) {
        this.endtime = endTIme;
    }

    public List<DbRole> getRoles() {
        return roles;
    }

    public void setRoles(List<DbRole> roles) {
        this.roles = roles;
    }

    public List<DbPermission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<DbPermission> permissions) {
        this.permissions = permissions;
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

    public String getParentAccountId() {
        return parentAccountId;
    }

    public void setParentAccountId(String parent_accountId) {
        this.parentAccountId = parent_accountId;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getLegalPerson() {
        return legalPerson;
    }

    public void setLegalPerson(String legalPerson) {
        this.legalPerson = legalPerson;
    }

    public String getlegalPersonPapers() {
        return legalPersonPapers;
    }

    public void setlegalPersonPapers(String legalPersonPapers) {
        this.legalPersonPapers = legalPersonPapers;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBusinessLicense() {
        return businessLicense;
    }

    public void setBusinessLicense(String businessLicense) {
        this.businessLicense = businessLicense;
    }

    public String getSecurityCertificate() {
        return securityCertificate;
    }

    public void setSecurityCertificate(String securityCertificate) {
        this.securityCertificate = securityCertificate;
    }

    public Integer getWarning() {
        return warning;
    }

    public void setWarning(Integer warning) {
        this.warning = warning;
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

    public Integer getContactsQq() {
        return contactsQq;
    }

    public void setContactsQq(Integer contactsQq) {
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }


    public String getSerial() {

        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }
}