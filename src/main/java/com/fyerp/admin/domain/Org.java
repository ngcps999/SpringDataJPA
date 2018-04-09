/*
 * Copyright (c) 2018.
 * 项目名称：fyerp.
 * 模块名称：fyerp
 * 文件名称：Org.java
 * 作者：xuda
 * 时间：18-4-9 下午3:57
 *
 */

package com.fyerp.admin.domain;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @Author: xuda
 * @Date: 2018/4/9
 * @Time: 下午3:57
 */
@Entity
@DynamicUpdate
@Data
public class Org {
    /**
     * 无参构造
     */
    public Org() {
    }

    @Id
    @GeneratedValue
    private Integer id;

    /**
     * 部门名称
     */
    private String depName;

    /**
     * 父级id号
     */
    private Org parentId;

    /**
     * id路径
     */
    private String path;

    /**
     * 排序
     */
    private Integer order;


    public Integer getpId(){
        if(parentId == null){
            return 0;
        }
        return parentId.getId();
    }

}
