/*
 * Copyright (c) 2018.
 * 项目名称：fyerp.
 * 模块名称：fyerp
 * 文件名称：Contract.java
 * 作者：xuda
 * 时间：18-4-10 上午9:42
 *
 */

package com.fyerp.admin.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Objects;

/**
 * 合同
 * @Author: xuda
 * @Date: 2018/4/10
 * @Time: 上午9:42
 */
@Entity
@DynamicUpdate
@EntityListeners(AuditingEntityListener.class)
//@Data
public class Contract {

    @Id
    @GeneratedValue
    private Integer contractId;
    private String contractName;
    private String contractType;
    private String contractCode;
    private String contractState;
    private Double contractMoney;
    private Double contractTaxRate;
    private Double contractTax;
    private String totalMoney;
    private String isChange;
    private String isDestroy;
    private Date effectTime;
    private Date lostEffectTime;
    private Date endTime;
    private Date auditTime;
    private String note;
    @JsonIgnore
    @CreatedDate
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    private Date createTime;
    @JsonIgnore
    @LastModifiedDate
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    private Date updateTime;

    public Integer getContractId() {
        return contractId;
    }

    public void setContractId(Integer contractId) {
        this.contractId = contractId;
    }

    public String getContractName() {
        return contractName;
    }

    @Required
    public void setContractName(String contractName) {
        this.contractName = contractName;
    }

    public String getContractType() {
        return contractType;
    }

    @Required
    public void setContractType(String contractType) {
        this.contractType = contractType;
    }

    public String getContractCode() {
        return contractCode;
    }

    @Required
    public void setContractCode(String contractCode) {
        this.contractCode = contractCode;
    }

    public String getContractState() {
        return contractState;
    }

    @Required
    public void setContractState(String contractState) {
        this.contractState = contractState;
    }

    public Double getContractMoney() {
        return contractMoney;
    }

    @Required
    public void setContractMoney(Double contractMoney) {
        this.contractMoney = contractMoney;
    }

    public Double getContractTaxRate() {
        return contractTaxRate;
    }

    public void setContractTaxRate(Double contractTaxRate) {
        this.contractTaxRate = contractTaxRate;
    }

    public Double getContractTax() {
        return contractTax;
    }

    public void setContractTax(Double contractTax) {
        this.contractTax = contractTax;
    }

    public String getTotalMoney() {
        return totalMoney;
    }

    @Required
    public void setTotalMoney(String totalMoney) {
        this.totalMoney = totalMoney;
    }

    public String getIsChange() {
        return isChange;
    }

    public void setIsChange(String isChange) {
        this.isChange = isChange;
    }

    public String getIsDestroy() {
        return isDestroy;
    }


    public void setIsDestroy(String isDestroy) {
        this.isDestroy = isDestroy;
    }

    public Date getEffectTime() {
        return effectTime;
    }

    public void setEffectTime(Date effectTime) {
        this.effectTime = effectTime;
    }

    public Date getLostEffectTime() {
        return lostEffectTime;
    }

    public void setLostEffectTime(Date lostEffectTime) {
        this.lostEffectTime = lostEffectTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Date getAuditTime() {
        return auditTime;
    }

    public void setAuditTime(Date auditTime) {
        this.auditTime = auditTime;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
