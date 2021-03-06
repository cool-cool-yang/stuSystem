package com.stuSystem.manager.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class StudentExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table student
     *
     * @mbggenerated Tue Jun 09 08:44:08 CST 2020
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table student
     *
     * @mbggenerated Tue Jun 09 08:44:08 CST 2020
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table student
     *
     * @mbggenerated Tue Jun 09 08:44:08 CST 2020
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table student
     *
     * @mbggenerated Tue Jun 09 08:44:08 CST 2020
     */
    public StudentExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table student
     *
     * @mbggenerated Tue Jun 09 08:44:08 CST 2020
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table student
     *
     * @mbggenerated Tue Jun 09 08:44:08 CST 2020
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table student
     *
     * @mbggenerated Tue Jun 09 08:44:08 CST 2020
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table student
     *
     * @mbggenerated Tue Jun 09 08:44:08 CST 2020
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table student
     *
     * @mbggenerated Tue Jun 09 08:44:08 CST 2020
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table student
     *
     * @mbggenerated Tue Jun 09 08:44:08 CST 2020
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table student
     *
     * @mbggenerated Tue Jun 09 08:44:08 CST 2020
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table student
     *
     * @mbggenerated Tue Jun 09 08:44:08 CST 2020
     */
    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table student
     *
     * @mbggenerated Tue Jun 09 08:44:08 CST 2020
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table student
     *
     * @mbggenerated Tue Jun 09 08:44:08 CST 2020
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table student
     *
     * @mbggenerated Tue Jun 09 08:44:08 CST 2020
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

        public Criteria andStuIdIsNull() {
            addCriterion("stu_id is null");
            return (Criteria) this;
        }

        public Criteria andStuIdIsNotNull() {
            addCriterion("stu_id is not null");
            return (Criteria) this;
        }

        public Criteria andStuIdEqualTo(String value) {
            addCriterion("stu_id =", value, "stuId");
            return (Criteria) this;
        }

        public Criteria andStuIdNotEqualTo(String value) {
            addCriterion("stu_id <>", value, "stuId");
            return (Criteria) this;
        }

        public Criteria andStuIdGreaterThan(String value) {
            addCriterion("stu_id >", value, "stuId");
            return (Criteria) this;
        }

        public Criteria andStuIdGreaterThanOrEqualTo(String value) {
            addCriterion("stu_id >=", value, "stuId");
            return (Criteria) this;
        }

        public Criteria andStuIdLessThan(String value) {
            addCriterion("stu_id <", value, "stuId");
            return (Criteria) this;
        }

        public Criteria andStuIdLessThanOrEqualTo(String value) {
            addCriterion("stu_id <=", value, "stuId");
            return (Criteria) this;
        }

        public Criteria andStuIdLike(String value) {
            addCriterion("stu_id like", value, "stuId");
            return (Criteria) this;
        }

        public Criteria andStuIdNotLike(String value) {
            addCriterion("stu_id not like", value, "stuId");
            return (Criteria) this;
        }

        public Criteria andStuIdIn(List<String> values) {
            addCriterion("stu_id in", values, "stuId");
            return (Criteria) this;
        }

        public Criteria andStuIdNotIn(List<String> values) {
            addCriterion("stu_id not in", values, "stuId");
            return (Criteria) this;
        }

        public Criteria andStuIdBetween(String value1, String value2) {
            addCriterion("stu_id between", value1, value2, "stuId");
            return (Criteria) this;
        }

        public Criteria andStuIdNotBetween(String value1, String value2) {
            addCriterion("stu_id not between", value1, value2, "stuId");
            return (Criteria) this;
        }

        public Criteria andStuClassIsNull() {
            addCriterion("stu_class is null");
            return (Criteria) this;
        }

        public Criteria andStuClassIsNotNull() {
            addCriterion("stu_class is not null");
            return (Criteria) this;
        }

        public Criteria andStuClassEqualTo(String value) {
            addCriterion("stu_class =", value, "stuClass");
            return (Criteria) this;
        }

        public Criteria andStuClassNotEqualTo(String value) {
            addCriterion("stu_class <>", value, "stuClass");
            return (Criteria) this;
        }

        public Criteria andStuClassGreaterThan(String value) {
            addCriterion("stu_class >", value, "stuClass");
            return (Criteria) this;
        }

        public Criteria andStuClassGreaterThanOrEqualTo(String value) {
            addCriterion("stu_class >=", value, "stuClass");
            return (Criteria) this;
        }

        public Criteria andStuClassLessThan(String value) {
            addCriterion("stu_class <", value, "stuClass");
            return (Criteria) this;
        }

        public Criteria andStuClassLessThanOrEqualTo(String value) {
            addCriterion("stu_class <=", value, "stuClass");
            return (Criteria) this;
        }

        public Criteria andStuClassLike(String value) {
            addCriterion("stu_class like", value, "stuClass");
            return (Criteria) this;
        }

        public Criteria andStuClassNotLike(String value) {
            addCriterion("stu_class not like", value, "stuClass");
            return (Criteria) this;
        }

        public Criteria andStuClassIn(List<String> values) {
            addCriterion("stu_class in", values, "stuClass");
            return (Criteria) this;
        }

        public Criteria andStuClassNotIn(List<String> values) {
            addCriterion("stu_class not in", values, "stuClass");
            return (Criteria) this;
        }

        public Criteria andStuClassBetween(String value1, String value2) {
            addCriterion("stu_class between", value1, value2, "stuClass");
            return (Criteria) this;
        }

        public Criteria andStuClassNotBetween(String value1, String value2) {
            addCriterion("stu_class not between", value1, value2, "stuClass");
            return (Criteria) this;
        }

        public Criteria andStuNameIsNull() {
            addCriterion("stu_name is null");
            return (Criteria) this;
        }

        public Criteria andStuNameIsNotNull() {
            addCriterion("stu_name is not null");
            return (Criteria) this;
        }

        public Criteria andStuNameEqualTo(String value) {
            addCriterion("stu_name =", value, "stuName");
            return (Criteria) this;
        }

        public Criteria andStuNameNotEqualTo(String value) {
            addCriterion("stu_name <>", value, "stuName");
            return (Criteria) this;
        }

        public Criteria andStuNameGreaterThan(String value) {
            addCriterion("stu_name >", value, "stuName");
            return (Criteria) this;
        }

        public Criteria andStuNameGreaterThanOrEqualTo(String value) {
            addCriterion("stu_name >=", value, "stuName");
            return (Criteria) this;
        }

        public Criteria andStuNameLessThan(String value) {
            addCriterion("stu_name <", value, "stuName");
            return (Criteria) this;
        }

        public Criteria andStuNameLessThanOrEqualTo(String value) {
            addCriterion("stu_name <=", value, "stuName");
            return (Criteria) this;
        }

        public Criteria andStuNameLike(String value) {
            addCriterion("stu_name like", value, "stuName");
            return (Criteria) this;
        }

        public Criteria andStuNameNotLike(String value) {
            addCriterion("stu_name not like", value, "stuName");
            return (Criteria) this;
        }

        public Criteria andStuNameIn(List<String> values) {
            addCriterion("stu_name in", values, "stuName");
            return (Criteria) this;
        }

        public Criteria andStuNameNotIn(List<String> values) {
            addCriterion("stu_name not in", values, "stuName");
            return (Criteria) this;
        }

        public Criteria andStuNameBetween(String value1, String value2) {
            addCriterion("stu_name between", value1, value2, "stuName");
            return (Criteria) this;
        }

        public Criteria andStuNameNotBetween(String value1, String value2) {
            addCriterion("stu_name not between", value1, value2, "stuName");
            return (Criteria) this;
        }

        public Criteria andStuPwdIsNull() {
            addCriterion("stu_pwd is null");
            return (Criteria) this;
        }

        public Criteria andStuPwdIsNotNull() {
            addCriterion("stu_pwd is not null");
            return (Criteria) this;
        }

        public Criteria andStuPwdEqualTo(String value) {
            addCriterion("stu_pwd =", value, "stuPwd");
            return (Criteria) this;
        }

        public Criteria andStuPwdNotEqualTo(String value) {
            addCriterion("stu_pwd <>", value, "stuPwd");
            return (Criteria) this;
        }

        public Criteria andStuPwdGreaterThan(String value) {
            addCriterion("stu_pwd >", value, "stuPwd");
            return (Criteria) this;
        }

        public Criteria andStuPwdGreaterThanOrEqualTo(String value) {
            addCriterion("stu_pwd >=", value, "stuPwd");
            return (Criteria) this;
        }

        public Criteria andStuPwdLessThan(String value) {
            addCriterion("stu_pwd <", value, "stuPwd");
            return (Criteria) this;
        }

        public Criteria andStuPwdLessThanOrEqualTo(String value) {
            addCriterion("stu_pwd <=", value, "stuPwd");
            return (Criteria) this;
        }

        public Criteria andStuPwdLike(String value) {
            addCriterion("stu_pwd like", value, "stuPwd");
            return (Criteria) this;
        }

        public Criteria andStuPwdNotLike(String value) {
            addCriterion("stu_pwd not like", value, "stuPwd");
            return (Criteria) this;
        }

        public Criteria andStuPwdIn(List<String> values) {
            addCriterion("stu_pwd in", values, "stuPwd");
            return (Criteria) this;
        }

        public Criteria andStuPwdNotIn(List<String> values) {
            addCriterion("stu_pwd not in", values, "stuPwd");
            return (Criteria) this;
        }

        public Criteria andStuPwdBetween(String value1, String value2) {
            addCriterion("stu_pwd between", value1, value2, "stuPwd");
            return (Criteria) this;
        }

        public Criteria andStuPwdNotBetween(String value1, String value2) {
            addCriterion("stu_pwd not between", value1, value2, "stuPwd");
            return (Criteria) this;
        }

        public Criteria andStuPhotoIsNull() {
            addCriterion("stu_photo is null");
            return (Criteria) this;
        }

        public Criteria andStuPhotoIsNotNull() {
            addCriterion("stu_photo is not null");
            return (Criteria) this;
        }

        public Criteria andStuPhotoEqualTo(String value) {
            addCriterion("stu_photo =", value, "stuPhoto");
            return (Criteria) this;
        }

        public Criteria andStuPhotoNotEqualTo(String value) {
            addCriterion("stu_photo <>", value, "stuPhoto");
            return (Criteria) this;
        }

        public Criteria andStuPhotoGreaterThan(String value) {
            addCriterion("stu_photo >", value, "stuPhoto");
            return (Criteria) this;
        }

        public Criteria andStuPhotoGreaterThanOrEqualTo(String value) {
            addCriterion("stu_photo >=", value, "stuPhoto");
            return (Criteria) this;
        }

        public Criteria andStuPhotoLessThan(String value) {
            addCriterion("stu_photo <", value, "stuPhoto");
            return (Criteria) this;
        }

        public Criteria andStuPhotoLessThanOrEqualTo(String value) {
            addCriterion("stu_photo <=", value, "stuPhoto");
            return (Criteria) this;
        }

        public Criteria andStuPhotoLike(String value) {
            addCriterion("stu_photo like", value, "stuPhoto");
            return (Criteria) this;
        }

        public Criteria andStuPhotoNotLike(String value) {
            addCriterion("stu_photo not like", value, "stuPhoto");
            return (Criteria) this;
        }

        public Criteria andStuPhotoIn(List<String> values) {
            addCriterion("stu_photo in", values, "stuPhoto");
            return (Criteria) this;
        }

        public Criteria andStuPhotoNotIn(List<String> values) {
            addCriterion("stu_photo not in", values, "stuPhoto");
            return (Criteria) this;
        }

        public Criteria andStuPhotoBetween(String value1, String value2) {
            addCriterion("stu_photo between", value1, value2, "stuPhoto");
            return (Criteria) this;
        }

        public Criteria andStuPhotoNotBetween(String value1, String value2) {
            addCriterion("stu_photo not between", value1, value2, "stuPhoto");
            return (Criteria) this;
        }

        public Criteria andStuSexIsNull() {
            addCriterion("stu_sex is null");
            return (Criteria) this;
        }

        public Criteria andStuSexIsNotNull() {
            addCriterion("stu_sex is not null");
            return (Criteria) this;
        }

        public Criteria andStuSexEqualTo(String value) {
            addCriterion("stu_sex =", value, "stuSex");
            return (Criteria) this;
        }

        public Criteria andStuSexNotEqualTo(String value) {
            addCriterion("stu_sex <>", value, "stuSex");
            return (Criteria) this;
        }

        public Criteria andStuSexGreaterThan(String value) {
            addCriterion("stu_sex >", value, "stuSex");
            return (Criteria) this;
        }

        public Criteria andStuSexGreaterThanOrEqualTo(String value) {
            addCriterion("stu_sex >=", value, "stuSex");
            return (Criteria) this;
        }

        public Criteria andStuSexLessThan(String value) {
            addCriterion("stu_sex <", value, "stuSex");
            return (Criteria) this;
        }

        public Criteria andStuSexLessThanOrEqualTo(String value) {
            addCriterion("stu_sex <=", value, "stuSex");
            return (Criteria) this;
        }

        public Criteria andStuSexLike(String value) {
            addCriterion("stu_sex like", value, "stuSex");
            return (Criteria) this;
        }

        public Criteria andStuSexNotLike(String value) {
            addCriterion("stu_sex not like", value, "stuSex");
            return (Criteria) this;
        }

        public Criteria andStuSexIn(List<String> values) {
            addCriterion("stu_sex in", values, "stuSex");
            return (Criteria) this;
        }

        public Criteria andStuSexNotIn(List<String> values) {
            addCriterion("stu_sex not in", values, "stuSex");
            return (Criteria) this;
        }

        public Criteria andStuSexBetween(String value1, String value2) {
            addCriterion("stu_sex between", value1, value2, "stuSex");
            return (Criteria) this;
        }

        public Criteria andStuSexNotBetween(String value1, String value2) {
            addCriterion("stu_sex not between", value1, value2, "stuSex");
            return (Criteria) this;
        }

        public Criteria andStuMobileIsNull() {
            addCriterion("stu_mobile is null");
            return (Criteria) this;
        }

        public Criteria andStuMobileIsNotNull() {
            addCriterion("stu_mobile is not null");
            return (Criteria) this;
        }

        public Criteria andStuMobileEqualTo(String value) {
            addCriterion("stu_mobile =", value, "stuMobile");
            return (Criteria) this;
        }

        public Criteria andStuMobileNotEqualTo(String value) {
            addCriterion("stu_mobile <>", value, "stuMobile");
            return (Criteria) this;
        }

        public Criteria andStuMobileGreaterThan(String value) {
            addCriterion("stu_mobile >", value, "stuMobile");
            return (Criteria) this;
        }

        public Criteria andStuMobileGreaterThanOrEqualTo(String value) {
            addCriterion("stu_mobile >=", value, "stuMobile");
            return (Criteria) this;
        }

        public Criteria andStuMobileLessThan(String value) {
            addCriterion("stu_mobile <", value, "stuMobile");
            return (Criteria) this;
        }

        public Criteria andStuMobileLessThanOrEqualTo(String value) {
            addCriterion("stu_mobile <=", value, "stuMobile");
            return (Criteria) this;
        }

        public Criteria andStuMobileLike(String value) {
            addCriterion("stu_mobile like", value, "stuMobile");
            return (Criteria) this;
        }

        public Criteria andStuMobileNotLike(String value) {
            addCriterion("stu_mobile not like", value, "stuMobile");
            return (Criteria) this;
        }

        public Criteria andStuMobileIn(List<String> values) {
            addCriterion("stu_mobile in", values, "stuMobile");
            return (Criteria) this;
        }

        public Criteria andStuMobileNotIn(List<String> values) {
            addCriterion("stu_mobile not in", values, "stuMobile");
            return (Criteria) this;
        }

        public Criteria andStuMobileBetween(String value1, String value2) {
            addCriterion("stu_mobile between", value1, value2, "stuMobile");
            return (Criteria) this;
        }

        public Criteria andStuMobileNotBetween(String value1, String value2) {
            addCriterion("stu_mobile not between", value1, value2, "stuMobile");
            return (Criteria) this;
        }

        public Criteria andStuEmailIsNull() {
            addCriterion("stu_email is null");
            return (Criteria) this;
        }

        public Criteria andStuEmailIsNotNull() {
            addCriterion("stu_email is not null");
            return (Criteria) this;
        }

        public Criteria andStuEmailEqualTo(String value) {
            addCriterion("stu_email =", value, "stuEmail");
            return (Criteria) this;
        }

        public Criteria andStuEmailNotEqualTo(String value) {
            addCriterion("stu_email <>", value, "stuEmail");
            return (Criteria) this;
        }

        public Criteria andStuEmailGreaterThan(String value) {
            addCriterion("stu_email >", value, "stuEmail");
            return (Criteria) this;
        }

        public Criteria andStuEmailGreaterThanOrEqualTo(String value) {
            addCriterion("stu_email >=", value, "stuEmail");
            return (Criteria) this;
        }

        public Criteria andStuEmailLessThan(String value) {
            addCriterion("stu_email <", value, "stuEmail");
            return (Criteria) this;
        }

        public Criteria andStuEmailLessThanOrEqualTo(String value) {
            addCriterion("stu_email <=", value, "stuEmail");
            return (Criteria) this;
        }

        public Criteria andStuEmailLike(String value) {
            addCriterion("stu_email like", value, "stuEmail");
            return (Criteria) this;
        }

        public Criteria andStuEmailNotLike(String value) {
            addCriterion("stu_email not like", value, "stuEmail");
            return (Criteria) this;
        }

        public Criteria andStuEmailIn(List<String> values) {
            addCriterion("stu_email in", values, "stuEmail");
            return (Criteria) this;
        }

        public Criteria andStuEmailNotIn(List<String> values) {
            addCriterion("stu_email not in", values, "stuEmail");
            return (Criteria) this;
        }

        public Criteria andStuEmailBetween(String value1, String value2) {
            addCriterion("stu_email between", value1, value2, "stuEmail");
            return (Criteria) this;
        }

        public Criteria andStuEmailNotBetween(String value1, String value2) {
            addCriterion("stu_email not between", value1, value2, "stuEmail");
            return (Criteria) this;
        }

        public Criteria andStuBirthdayIsNull() {
            addCriterion("stu_birthday is null");
            return (Criteria) this;
        }

        public Criteria andStuBirthdayIsNotNull() {
            addCriterion("stu_birthday is not null");
            return (Criteria) this;
        }

        public Criteria andStuBirthdayEqualTo(Date value) {
            addCriterion("stu_birthday =", value, "stuBirthday");
            return (Criteria) this;
        }

        public Criteria andStuBirthdayNotEqualTo(Date value) {
            addCriterion("stu_birthday <>", value, "stuBirthday");
            return (Criteria) this;
        }

        public Criteria andStuBirthdayGreaterThan(Date value) {
            addCriterion("stu_birthday >", value, "stuBirthday");
            return (Criteria) this;
        }

        public Criteria andStuBirthdayGreaterThanOrEqualTo(Date value) {
            addCriterion("stu_birthday >=", value, "stuBirthday");
            return (Criteria) this;
        }

        public Criteria andStuBirthdayLessThan(Date value) {
            addCriterion("stu_birthday <", value, "stuBirthday");
            return (Criteria) this;
        }

        public Criteria andStuBirthdayLessThanOrEqualTo(Date value) {
            addCriterion("stu_birthday <=", value, "stuBirthday");
            return (Criteria) this;
        }

        public Criteria andStuBirthdayIn(List<Date> values) {
            addCriterion("stu_birthday in", values, "stuBirthday");
            return (Criteria) this;
        }

        public Criteria andStuBirthdayNotIn(List<Date> values) {
            addCriterion("stu_birthday not in", values, "stuBirthday");
            return (Criteria) this;
        }

        public Criteria andStuBirthdayBetween(Date value1, Date value2) {
            addCriterion("stu_birthday between", value1, value2, "stuBirthday");
            return (Criteria) this;
        }

        public Criteria andStuBirthdayNotBetween(Date value1, Date value2) {
            addCriterion("stu_birthday not between", value1, value2, "stuBirthday");
            return (Criteria) this;
        }

        public Criteria andStuLevelIsNull() {
            addCriterion("stu_level is null");
            return (Criteria) this;
        }

        public Criteria andStuLevelIsNotNull() {
            addCriterion("stu_level is not null");
            return (Criteria) this;
        }

        public Criteria andStuLevelEqualTo(Integer value) {
            addCriterion("stu_level =", value, "stuLevel");
            return (Criteria) this;
        }

        public Criteria andStuLevelNotEqualTo(Integer value) {
            addCriterion("stu_level <>", value, "stuLevel");
            return (Criteria) this;
        }

        public Criteria andStuLevelGreaterThan(Integer value) {
            addCriterion("stu_level >", value, "stuLevel");
            return (Criteria) this;
        }

        public Criteria andStuLevelGreaterThanOrEqualTo(Integer value) {
            addCriterion("stu_level >=", value, "stuLevel");
            return (Criteria) this;
        }

        public Criteria andStuLevelLessThan(Integer value) {
            addCriterion("stu_level <", value, "stuLevel");
            return (Criteria) this;
        }

        public Criteria andStuLevelLessThanOrEqualTo(Integer value) {
            addCriterion("stu_level <=", value, "stuLevel");
            return (Criteria) this;
        }

        public Criteria andStuLevelIn(List<Integer> values) {
            addCriterion("stu_level in", values, "stuLevel");
            return (Criteria) this;
        }

        public Criteria andStuLevelNotIn(List<Integer> values) {
            addCriterion("stu_level not in", values, "stuLevel");
            return (Criteria) this;
        }

        public Criteria andStuLevelBetween(Integer value1, Integer value2) {
            addCriterion("stu_level between", value1, value2, "stuLevel");
            return (Criteria) this;
        }

        public Criteria andStuLevelNotBetween(Integer value1, Integer value2) {
            addCriterion("stu_level not between", value1, value2, "stuLevel");
            return (Criteria) this;
        }

        public Criteria andStuOnlineIsNull() {
            addCriterion("stu_online is null");
            return (Criteria) this;
        }

        public Criteria andStuOnlineIsNotNull() {
            addCriterion("stu_online is not null");
            return (Criteria) this;
        }

        public Criteria andStuOnlineEqualTo(Integer value) {
            addCriterion("stu_online =", value, "stuOnline");
            return (Criteria) this;
        }

        public Criteria andStuOnlineNotEqualTo(Integer value) {
            addCriterion("stu_online <>", value, "stuOnline");
            return (Criteria) this;
        }

        public Criteria andStuOnlineGreaterThan(Integer value) {
            addCriterion("stu_online >", value, "stuOnline");
            return (Criteria) this;
        }

        public Criteria andStuOnlineGreaterThanOrEqualTo(Integer value) {
            addCriterion("stu_online >=", value, "stuOnline");
            return (Criteria) this;
        }

        public Criteria andStuOnlineLessThan(Integer value) {
            addCriterion("stu_online <", value, "stuOnline");
            return (Criteria) this;
        }

        public Criteria andStuOnlineLessThanOrEqualTo(Integer value) {
            addCriterion("stu_online <=", value, "stuOnline");
            return (Criteria) this;
        }

        public Criteria andStuOnlineIn(List<Integer> values) {
            addCriterion("stu_online in", values, "stuOnline");
            return (Criteria) this;
        }

        public Criteria andStuOnlineNotIn(List<Integer> values) {
            addCriterion("stu_online not in", values, "stuOnline");
            return (Criteria) this;
        }

        public Criteria andStuOnlineBetween(Integer value1, Integer value2) {
            addCriterion("stu_online between", value1, value2, "stuOnline");
            return (Criteria) this;
        }

        public Criteria andStuOnlineNotBetween(Integer value1, Integer value2) {
            addCriterion("stu_online not between", value1, value2, "stuOnline");
            return (Criteria) this;
        }

        public Criteria andStuEnschIsNull() {
            addCriterion("stu_enSch is null");
            return (Criteria) this;
        }

        public Criteria andStuEnschIsNotNull() {
            addCriterion("stu_enSch is not null");
            return (Criteria) this;
        }

        public Criteria andStuEnschEqualTo(Date value) {
            addCriterion("stu_enSch =", value, "stuEnsch");
            return (Criteria) this;
        }

        public Criteria andStuEnschNotEqualTo(Date value) {
            addCriterion("stu_enSch <>", value, "stuEnsch");
            return (Criteria) this;
        }

        public Criteria andStuEnschGreaterThan(Date value) {
            addCriterion("stu_enSch >", value, "stuEnsch");
            return (Criteria) this;
        }

        public Criteria andStuEnschGreaterThanOrEqualTo(Date value) {
            addCriterion("stu_enSch >=", value, "stuEnsch");
            return (Criteria) this;
        }

        public Criteria andStuEnschLessThan(Date value) {
            addCriterion("stu_enSch <", value, "stuEnsch");
            return (Criteria) this;
        }

        public Criteria andStuEnschLessThanOrEqualTo(Date value) {
            addCriterion("stu_enSch <=", value, "stuEnsch");
            return (Criteria) this;
        }

        public Criteria andStuEnschIn(List<Date> values) {
            addCriterion("stu_enSch in", values, "stuEnsch");
            return (Criteria) this;
        }

        public Criteria andStuEnschNotIn(List<Date> values) {
            addCriterion("stu_enSch not in", values, "stuEnsch");
            return (Criteria) this;
        }

        public Criteria andStuEnschBetween(Date value1, Date value2) {
            addCriterion("stu_enSch between", value1, value2, "stuEnsch");
            return (Criteria) this;
        }

        public Criteria andStuEnschNotBetween(Date value1, Date value2) {
            addCriterion("stu_enSch not between", value1, value2, "stuEnsch");
            return (Criteria) this;
        }

        public Criteria andStuLastloginIsNull() {
            addCriterion("stu_lastLogin is null");
            return (Criteria) this;
        }

        public Criteria andStuLastloginIsNotNull() {
            addCriterion("stu_lastLogin is not null");
            return (Criteria) this;
        }

        public Criteria andStuLastloginEqualTo(Date value) {
            addCriterion("stu_lastLogin =", value, "stuLastlogin");
            return (Criteria) this;
        }

        public Criteria andStuLastloginNotEqualTo(Date value) {
            addCriterion("stu_lastLogin <>", value, "stuLastlogin");
            return (Criteria) this;
        }

        public Criteria andStuLastloginGreaterThan(Date value) {
            addCriterion("stu_lastLogin >", value, "stuLastlogin");
            return (Criteria) this;
        }

        public Criteria andStuLastloginGreaterThanOrEqualTo(Date value) {
            addCriterion("stu_lastLogin >=", value, "stuLastlogin");
            return (Criteria) this;
        }

        public Criteria andStuLastloginLessThan(Date value) {
            addCriterion("stu_lastLogin <", value, "stuLastlogin");
            return (Criteria) this;
        }

        public Criteria andStuLastloginLessThanOrEqualTo(Date value) {
            addCriterion("stu_lastLogin <=", value, "stuLastlogin");
            return (Criteria) this;
        }

        public Criteria andStuLastloginIn(List<Date> values) {
            addCriterion("stu_lastLogin in", values, "stuLastlogin");
            return (Criteria) this;
        }

        public Criteria andStuLastloginNotIn(List<Date> values) {
            addCriterion("stu_lastLogin not in", values, "stuLastlogin");
            return (Criteria) this;
        }

        public Criteria andStuLastloginBetween(Date value1, Date value2) {
            addCriterion("stu_lastLogin between", value1, value2, "stuLastlogin");
            return (Criteria) this;
        }

        public Criteria andStuLastloginNotBetween(Date value1, Date value2) {
            addCriterion("stu_lastLogin not between", value1, value2, "stuLastlogin");
            return (Criteria) this;
        }

        public Criteria andStuRegloginIsNull() {
            addCriterion("stu_regLogin is null");
            return (Criteria) this;
        }

        public Criteria andStuRegloginIsNotNull() {
            addCriterion("stu_regLogin is not null");
            return (Criteria) this;
        }

        public Criteria andStuRegloginEqualTo(Date value) {
            addCriterion("stu_regLogin =", value, "stuReglogin");
            return (Criteria) this;
        }

        public Criteria andStuRegloginNotEqualTo(Date value) {
            addCriterion("stu_regLogin <>", value, "stuReglogin");
            return (Criteria) this;
        }

        public Criteria andStuRegloginGreaterThan(Date value) {
            addCriterion("stu_regLogin >", value, "stuReglogin");
            return (Criteria) this;
        }

        public Criteria andStuRegloginGreaterThanOrEqualTo(Date value) {
            addCriterion("stu_regLogin >=", value, "stuReglogin");
            return (Criteria) this;
        }

        public Criteria andStuRegloginLessThan(Date value) {
            addCriterion("stu_regLogin <", value, "stuReglogin");
            return (Criteria) this;
        }

        public Criteria andStuRegloginLessThanOrEqualTo(Date value) {
            addCriterion("stu_regLogin <=", value, "stuReglogin");
            return (Criteria) this;
        }

        public Criteria andStuRegloginIn(List<Date> values) {
            addCriterion("stu_regLogin in", values, "stuReglogin");
            return (Criteria) this;
        }

        public Criteria andStuRegloginNotIn(List<Date> values) {
            addCriterion("stu_regLogin not in", values, "stuReglogin");
            return (Criteria) this;
        }

        public Criteria andStuRegloginBetween(Date value1, Date value2) {
            addCriterion("stu_regLogin between", value1, value2, "stuReglogin");
            return (Criteria) this;
        }

        public Criteria andStuRegloginNotBetween(Date value1, Date value2) {
            addCriterion("stu_regLogin not between", value1, value2, "stuReglogin");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table student
     *
     * @mbggenerated do_not_delete_during_merge Tue Jun 09 08:44:08 CST 2020
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table student
     *
     * @mbggenerated Tue Jun 09 08:44:08 CST 2020
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