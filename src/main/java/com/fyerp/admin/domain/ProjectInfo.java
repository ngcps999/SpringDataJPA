package com.fyerp.admin.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Data
@EntityListeners(AuditingEntityListener.class)
@DynamicUpdate
public class ProjectInfo extends Project{

    /**
     * 使用设备
     */
    private String equipment;

    /**
     * 飞行平台
     */
    private String flyPlatform;

    /**
     * 飞行高度
     */
    private String flyHeight;

    /**
     * 航摄分辨率
     */
    private String aeroRatio;

    /**
     * 航摄面积
     */
    private String areoArea;

    /**
     * 地图
     */
    private String map;

    @CreatedDate
    private Date createTime;

    @LastModifiedDate
    private Date updateTime;


}
