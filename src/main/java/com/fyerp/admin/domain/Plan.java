/*
 * 作者：xuda
 * 创建时间：18-4-20 下午4:28
 * 模块名称：admin
 */

package com.fyerp.admin.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import sun.util.calendar.BaseCalendar;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity
@EntityListeners(AuditingEntityListener.class)
@DynamicUpdate
public class Plan {

    @Id
    @GeneratedValue
    @JsonProperty("id")
    private Integer planId;
    @JsonProperty("name")
    private String planName;
    @JsonProperty("content")
    private String planContent;
    @JsonIgnore
    @CreatedDate
    private Date createTime;
    @JsonIgnore
    @LastModifiedDate
    private Date updateTime;

    public Integer getPlanId() {
        return planId;
    }

    public void setPlanId(Integer planId) {
        this.planId = planId;
    }

    public String getPlanName() {
        return planName;
    }

    @Required
    public void setPlanName(String planName) {
        this.planName = planName;
    }

    public String getPlanContent() {
        return planContent;
    }


    public void setPlanContent(String planContent) {
        this.planContent = planContent;
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
