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

import lombok.Data;
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
@Data
public class Contract {

    @Id
    @GeneratedValue
    private int id;
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

}
