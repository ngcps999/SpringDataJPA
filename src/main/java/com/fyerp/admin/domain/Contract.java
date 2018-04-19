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

//import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * 合同
 * @Author: xuda
 * @Date: 2018/4/10
 * @Time: 上午9:42
 */
@Entity
@DynamicUpdate
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
    private Timestamp effectTime;
    private Timestamp lostEffectTime;
    private Timestamp endTime;
    private Timestamp auditTime;
    private String note;

    public int getContractId() {
        return contractId;
    }

    public void setContractId(int contractId) {
        this.contractId = contractId;
    }

    public String getContractName() {
        return contractName;
    }

    public void setContractName(String contractName) {
        this.contractName = contractName;
    }

    public String getContractType() {
        return contractType;
    }

    public void setContractType(String contractType) {
        this.contractType = contractType;
    }

    public String getContractCode() {
        return contractCode;
    }

    public void setContractCode(String contractCode) {
        this.contractCode = contractCode;
    }

    public String getContractState() {
        return contractState;
    }

    public void setContractState(String contractState) {
        this.contractState = contractState;
    }

    public Double getContractMoney() {
        return contractMoney;
    }

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

    public Timestamp getEffectTime() {
        return effectTime;
    }

    public void setEffectTime(Timestamp effectTime) {
        this.effectTime = effectTime;
    }

    public Timestamp getLostEffectTime() {
        return lostEffectTime;
    }

    public void setLostEffectTime(Timestamp lostEffectTime) {
        this.lostEffectTime = lostEffectTime;
    }

    public Timestamp getEndTime() {
        return endTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }

    public Timestamp getAuditTime() {
        return auditTime;
    }

    public void setAuditTime(Timestamp auditTime) {
        this.auditTime = auditTime;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
