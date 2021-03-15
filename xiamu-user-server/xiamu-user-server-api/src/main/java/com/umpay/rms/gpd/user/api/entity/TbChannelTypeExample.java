package com.umpay.rms.gpd.user.api.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TbChannelTypeExample {
    /**
     * 排序字段,tb_channel_type
     */
    protected String orderByClause;

    /**
     * 是否过滤重复数据,tb_channel_type
     */
    protected boolean distinct;

    /**
     * ,tb_channel_type
     */
    protected List<Criteria> oredCriteria;

    /**
     *  构造查询条件,tb_channel_type
     */
    public TbChannelTypeExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     *  设置排序字段,tb_channel_type
     *
     * @param orderByClause 排序字段
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     *  获取排序字段,tb_channel_type
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     *  设置过滤重复数据,tb_channel_type
     *
     * @param distinct 是否过滤重复数据
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     *  是否过滤重复数据,tb_channel_type
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     *  获取当前的查询条件实例,tb_channel_type
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * ,tb_channel_type
     *
     * @param criteria 过滤条件实例
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * ,tb_channel_type
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     *  创建一个查询条件,tb_channel_type
     */
    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    /**
     *  内部构建查询条件对象,tb_channel_type
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     *  清除查询条件,tb_channel_type
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * 基本动态SQL对象,tb_channel_type
     */
    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(String value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(String value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(String value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(String value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(String value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(String value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLike(String value) {
            addCriterion("id like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotLike(String value) {
            addCriterion("id not like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<String> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<String> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(String value1, String value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(String value1, String value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andNameIsNull() {
            addCriterion("name is null");
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("name is not null");
            return (Criteria) this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("name =", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("name <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("name >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("name >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("name <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("name <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLike(String value) {
            addCriterion("name like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("name not like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("name in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<String> values) {
            addCriterion("name not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("name between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("name not between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andTagIsNull() {
            addCriterion("tag is null");
            return (Criteria) this;
        }

        public Criteria andTagIsNotNull() {
            addCriterion("tag is not null");
            return (Criteria) this;
        }

        public Criteria andTagEqualTo(String value) {
            addCriterion("tag =", value, "tag");
            return (Criteria) this;
        }

        public Criteria andTagNotEqualTo(String value) {
            addCriterion("tag <>", value, "tag");
            return (Criteria) this;
        }

        public Criteria andTagGreaterThan(String value) {
            addCriterion("tag >", value, "tag");
            return (Criteria) this;
        }

        public Criteria andTagGreaterThanOrEqualTo(String value) {
            addCriterion("tag >=", value, "tag");
            return (Criteria) this;
        }

        public Criteria andTagLessThan(String value) {
            addCriterion("tag <", value, "tag");
            return (Criteria) this;
        }

        public Criteria andTagLessThanOrEqualTo(String value) {
            addCriterion("tag <=", value, "tag");
            return (Criteria) this;
        }

        public Criteria andTagLike(String value) {
            addCriterion("tag like", value, "tag");
            return (Criteria) this;
        }

        public Criteria andTagNotLike(String value) {
            addCriterion("tag not like", value, "tag");
            return (Criteria) this;
        }

        public Criteria andTagIn(List<String> values) {
            addCriterion("tag in", values, "tag");
            return (Criteria) this;
        }

        public Criteria andTagNotIn(List<String> values) {
            addCriterion("tag not in", values, "tag");
            return (Criteria) this;
        }

        public Criteria andTagBetween(String value1, String value2) {
            addCriterion("tag between", value1, value2, "tag");
            return (Criteria) this;
        }

        public Criteria andTagNotBetween(String value1, String value2) {
            addCriterion("tag not between", value1, value2, "tag");
            return (Criteria) this;
        }

        public Criteria andOperatorsIsNull() {
            addCriterion("operators is null");
            return (Criteria) this;
        }

        public Criteria andOperatorsIsNotNull() {
            addCriterion("operators is not null");
            return (Criteria) this;
        }

        public Criteria andOperatorsEqualTo(String value) {
            addCriterion("operators =", value, "operators");
            return (Criteria) this;
        }

        public Criteria andOperatorsNotEqualTo(String value) {
            addCriterion("operators <>", value, "operators");
            return (Criteria) this;
        }

        public Criteria andOperatorsGreaterThan(String value) {
            addCriterion("operators >", value, "operators");
            return (Criteria) this;
        }

        public Criteria andOperatorsGreaterThanOrEqualTo(String value) {
            addCriterion("operators >=", value, "operators");
            return (Criteria) this;
        }

        public Criteria andOperatorsLessThan(String value) {
            addCriterion("operators <", value, "operators");
            return (Criteria) this;
        }

        public Criteria andOperatorsLessThanOrEqualTo(String value) {
            addCriterion("operators <=", value, "operators");
            return (Criteria) this;
        }

        public Criteria andOperatorsLike(String value) {
            addCriterion("operators like", value, "operators");
            return (Criteria) this;
        }

        public Criteria andOperatorsNotLike(String value) {
            addCriterion("operators not like", value, "operators");
            return (Criteria) this;
        }

        public Criteria andOperatorsIn(List<String> values) {
            addCriterion("operators in", values, "operators");
            return (Criteria) this;
        }

        public Criteria andOperatorsNotIn(List<String> values) {
            addCriterion("operators not in", values, "operators");
            return (Criteria) this;
        }

        public Criteria andOperatorsBetween(String value1, String value2) {
            addCriterion("operators between", value1, value2, "operators");
            return (Criteria) this;
        }

        public Criteria andOperatorsNotBetween(String value1, String value2) {
            addCriterion("operators not between", value1, value2, "operators");
            return (Criteria) this;
        }

        public Criteria andSendMmsUrlIsNull() {
            addCriterion("send_mms_url is null");
            return (Criteria) this;
        }

        public Criteria andSendMmsUrlIsNotNull() {
            addCriterion("send_mms_url is not null");
            return (Criteria) this;
        }

        public Criteria andSendMmsUrlEqualTo(String value) {
            addCriterion("send_mms_url =", value, "sendMmsUrl");
            return (Criteria) this;
        }

        public Criteria andSendMmsUrlNotEqualTo(String value) {
            addCriterion("send_mms_url <>", value, "sendMmsUrl");
            return (Criteria) this;
        }

        public Criteria andSendMmsUrlGreaterThan(String value) {
            addCriterion("send_mms_url >", value, "sendMmsUrl");
            return (Criteria) this;
        }

        public Criteria andSendMmsUrlGreaterThanOrEqualTo(String value) {
            addCriterion("send_mms_url >=", value, "sendMmsUrl");
            return (Criteria) this;
        }

        public Criteria andSendMmsUrlLessThan(String value) {
            addCriterion("send_mms_url <", value, "sendMmsUrl");
            return (Criteria) this;
        }

        public Criteria andSendMmsUrlLessThanOrEqualTo(String value) {
            addCriterion("send_mms_url <=", value, "sendMmsUrl");
            return (Criteria) this;
        }

        public Criteria andSendMmsUrlLike(String value) {
            addCriterion("send_mms_url like", value, "sendMmsUrl");
            return (Criteria) this;
        }

        public Criteria andSendMmsUrlNotLike(String value) {
            addCriterion("send_mms_url not like", value, "sendMmsUrl");
            return (Criteria) this;
        }

        public Criteria andSendMmsUrlIn(List<String> values) {
            addCriterion("send_mms_url in", values, "sendMmsUrl");
            return (Criteria) this;
        }

        public Criteria andSendMmsUrlNotIn(List<String> values) {
            addCriterion("send_mms_url not in", values, "sendMmsUrl");
            return (Criteria) this;
        }

        public Criteria andSendMmsUrlBetween(String value1, String value2) {
            addCriterion("send_mms_url between", value1, value2, "sendMmsUrl");
            return (Criteria) this;
        }

        public Criteria andSendMmsUrlNotBetween(String value1, String value2) {
            addCriterion("send_mms_url not between", value1, value2, "sendMmsUrl");
            return (Criteria) this;
        }

        public Criteria andSendModelUrlIsNull() {
            addCriterion("send_model_url is null");
            return (Criteria) this;
        }

        public Criteria andSendModelUrlIsNotNull() {
            addCriterion("send_model_url is not null");
            return (Criteria) this;
        }

        public Criteria andSendModelUrlEqualTo(String value) {
            addCriterion("send_model_url =", value, "sendModelUrl");
            return (Criteria) this;
        }

        public Criteria andSendModelUrlNotEqualTo(String value) {
            addCriterion("send_model_url <>", value, "sendModelUrl");
            return (Criteria) this;
        }

        public Criteria andSendModelUrlGreaterThan(String value) {
            addCriterion("send_model_url >", value, "sendModelUrl");
            return (Criteria) this;
        }

        public Criteria andSendModelUrlGreaterThanOrEqualTo(String value) {
            addCriterion("send_model_url >=", value, "sendModelUrl");
            return (Criteria) this;
        }

        public Criteria andSendModelUrlLessThan(String value) {
            addCriterion("send_model_url <", value, "sendModelUrl");
            return (Criteria) this;
        }

        public Criteria andSendModelUrlLessThanOrEqualTo(String value) {
            addCriterion("send_model_url <=", value, "sendModelUrl");
            return (Criteria) this;
        }

        public Criteria andSendModelUrlLike(String value) {
            addCriterion("send_model_url like", value, "sendModelUrl");
            return (Criteria) this;
        }

        public Criteria andSendModelUrlNotLike(String value) {
            addCriterion("send_model_url not like", value, "sendModelUrl");
            return (Criteria) this;
        }

        public Criteria andSendModelUrlIn(List<String> values) {
            addCriterion("send_model_url in", values, "sendModelUrl");
            return (Criteria) this;
        }

        public Criteria andSendModelUrlNotIn(List<String> values) {
            addCriterion("send_model_url not in", values, "sendModelUrl");
            return (Criteria) this;
        }

        public Criteria andSendModelUrlBetween(String value1, String value2) {
            addCriterion("send_model_url between", value1, value2, "sendModelUrl");
            return (Criteria) this;
        }

        public Criteria andSendModelUrlNotBetween(String value1, String value2) {
            addCriterion("send_model_url not between", value1, value2, "sendModelUrl");
            return (Criteria) this;
        }

        public Criteria andEnableIsNull() {
            addCriterion("enable is null");
            return (Criteria) this;
        }

        public Criteria andEnableIsNotNull() {
            addCriterion("enable is not null");
            return (Criteria) this;
        }

        public Criteria andEnableEqualTo(Integer value) {
            addCriterion("enable =", value, "enable");
            return (Criteria) this;
        }

        public Criteria andEnableNotEqualTo(Integer value) {
            addCriterion("enable <>", value, "enable");
            return (Criteria) this;
        }

        public Criteria andEnableGreaterThan(Integer value) {
            addCriterion("enable >", value, "enable");
            return (Criteria) this;
        }

        public Criteria andEnableGreaterThanOrEqualTo(Integer value) {
            addCriterion("enable >=", value, "enable");
            return (Criteria) this;
        }

        public Criteria andEnableLessThan(Integer value) {
            addCriterion("enable <", value, "enable");
            return (Criteria) this;
        }

        public Criteria andEnableLessThanOrEqualTo(Integer value) {
            addCriterion("enable <=", value, "enable");
            return (Criteria) this;
        }

        public Criteria andEnableIn(List<Integer> values) {
            addCriterion("enable in", values, "enable");
            return (Criteria) this;
        }

        public Criteria andEnableNotIn(List<Integer> values) {
            addCriterion("enable not in", values, "enable");
            return (Criteria) this;
        }

        public Criteria andEnableBetween(Integer value1, Integer value2) {
            addCriterion("enable between", value1, value2, "enable");
            return (Criteria) this;
        }

        public Criteria andEnableNotBetween(Integer value1, Integer value2) {
            addCriterion("enable not between", value1, value2, "enable");
            return (Criteria) this;
        }

        public Criteria andCreaterIsNull() {
            addCriterion("creater is null");
            return (Criteria) this;
        }

        public Criteria andCreaterIsNotNull() {
            addCriterion("creater is not null");
            return (Criteria) this;
        }

        public Criteria andCreaterEqualTo(String value) {
            addCriterion("creater =", value, "creater");
            return (Criteria) this;
        }

        public Criteria andCreaterNotEqualTo(String value) {
            addCriterion("creater <>", value, "creater");
            return (Criteria) this;
        }

        public Criteria andCreaterGreaterThan(String value) {
            addCriterion("creater >", value, "creater");
            return (Criteria) this;
        }

        public Criteria andCreaterGreaterThanOrEqualTo(String value) {
            addCriterion("creater >=", value, "creater");
            return (Criteria) this;
        }

        public Criteria andCreaterLessThan(String value) {
            addCriterion("creater <", value, "creater");
            return (Criteria) this;
        }

        public Criteria andCreaterLessThanOrEqualTo(String value) {
            addCriterion("creater <=", value, "creater");
            return (Criteria) this;
        }

        public Criteria andCreaterLike(String value) {
            addCriterion("creater like", value, "creater");
            return (Criteria) this;
        }

        public Criteria andCreaterNotLike(String value) {
            addCriterion("creater not like", value, "creater");
            return (Criteria) this;
        }

        public Criteria andCreaterIn(List<String> values) {
            addCriterion("creater in", values, "creater");
            return (Criteria) this;
        }

        public Criteria andCreaterNotIn(List<String> values) {
            addCriterion("creater not in", values, "creater");
            return (Criteria) this;
        }

        public Criteria andCreaterBetween(String value1, String value2) {
            addCriterion("creater between", value1, value2, "creater");
            return (Criteria) this;
        }

        public Criteria andCreaterNotBetween(String value1, String value2) {
            addCriterion("creater not between", value1, value2, "creater");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andModifierIsNull() {
            addCriterion("modifier is null");
            return (Criteria) this;
        }

        public Criteria andModifierIsNotNull() {
            addCriterion("modifier is not null");
            return (Criteria) this;
        }

        public Criteria andModifierEqualTo(String value) {
            addCriterion("modifier =", value, "modifier");
            return (Criteria) this;
        }

        public Criteria andModifierNotEqualTo(String value) {
            addCriterion("modifier <>", value, "modifier");
            return (Criteria) this;
        }

        public Criteria andModifierGreaterThan(String value) {
            addCriterion("modifier >", value, "modifier");
            return (Criteria) this;
        }

        public Criteria andModifierGreaterThanOrEqualTo(String value) {
            addCriterion("modifier >=", value, "modifier");
            return (Criteria) this;
        }

        public Criteria andModifierLessThan(String value) {
            addCriterion("modifier <", value, "modifier");
            return (Criteria) this;
        }

        public Criteria andModifierLessThanOrEqualTo(String value) {
            addCriterion("modifier <=", value, "modifier");
            return (Criteria) this;
        }

        public Criteria andModifierLike(String value) {
            addCriterion("modifier like", value, "modifier");
            return (Criteria) this;
        }

        public Criteria andModifierNotLike(String value) {
            addCriterion("modifier not like", value, "modifier");
            return (Criteria) this;
        }

        public Criteria andModifierIn(List<String> values) {
            addCriterion("modifier in", values, "modifier");
            return (Criteria) this;
        }

        public Criteria andModifierNotIn(List<String> values) {
            addCriterion("modifier not in", values, "modifier");
            return (Criteria) this;
        }

        public Criteria andModifierBetween(String value1, String value2) {
            addCriterion("modifier between", value1, value2, "modifier");
            return (Criteria) this;
        }

        public Criteria andModifierNotBetween(String value1, String value2) {
            addCriterion("modifier not between", value1, value2, "modifier");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNull() {
            addCriterion("update_time is null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNotNull() {
            addCriterion("update_time is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeEqualTo(Date value) {
            addCriterion("update_time =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(Date value) {
            addCriterion("update_time <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(Date value) {
            addCriterion("update_time >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("update_time >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(Date value) {
            addCriterion("update_time <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("update_time <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<Date> values) {
            addCriterion("update_time in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<Date> values) {
            addCriterion("update_time not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("update_time between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterion("update_time not between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andCtNoteIsNull() {
            addCriterion("ct_note is null");
            return (Criteria) this;
        }

        public Criteria andCtNoteIsNotNull() {
            addCriterion("ct_note is not null");
            return (Criteria) this;
        }

        public Criteria andCtNoteEqualTo(String value) {
            addCriterion("ct_note =", value, "ctNote");
            return (Criteria) this;
        }

        public Criteria andCtNoteNotEqualTo(String value) {
            addCriterion("ct_note <>", value, "ctNote");
            return (Criteria) this;
        }

        public Criteria andCtNoteGreaterThan(String value) {
            addCriterion("ct_note >", value, "ctNote");
            return (Criteria) this;
        }

        public Criteria andCtNoteGreaterThanOrEqualTo(String value) {
            addCriterion("ct_note >=", value, "ctNote");
            return (Criteria) this;
        }

        public Criteria andCtNoteLessThan(String value) {
            addCriterion("ct_note <", value, "ctNote");
            return (Criteria) this;
        }

        public Criteria andCtNoteLessThanOrEqualTo(String value) {
            addCriterion("ct_note <=", value, "ctNote");
            return (Criteria) this;
        }

        public Criteria andCtNoteLike(String value) {
            addCriterion("ct_note like", value, "ctNote");
            return (Criteria) this;
        }

        public Criteria andCtNoteNotLike(String value) {
            addCriterion("ct_note not like", value, "ctNote");
            return (Criteria) this;
        }

        public Criteria andCtNoteIn(List<String> values) {
            addCriterion("ct_note in", values, "ctNote");
            return (Criteria) this;
        }

        public Criteria andCtNoteNotIn(List<String> values) {
            addCriterion("ct_note not in", values, "ctNote");
            return (Criteria) this;
        }

        public Criteria andCtNoteBetween(String value1, String value2) {
            addCriterion("ct_note between", value1, value2, "ctNote");
            return (Criteria) this;
        }

        public Criteria andCtNoteNotBetween(String value1, String value2) {
            addCriterion("ct_note not between", value1, value2, "ctNote");
            return (Criteria) this;
        }
    }

    /**
     * 数据库表 tb_channel_type映射实体
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * 动态SQL对象,tb_channel_type
     */
    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}