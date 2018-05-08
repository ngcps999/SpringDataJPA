/*
 * Copyright (c) 2018.
 * 项目名称：fyerp.
 * 模块名称：fyerp
 * 文件名称：OrgService.java
 * 作者：xuda
 * 时间：18-4-9 下午4:01
 *
 */

package com.fyerp.admin.service;

import com.fyerp.admin.domain.Org;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @Author: xuda
 * @Date: 2018/4/9
 * @Time: 下午4:01
 */
public interface OrgService {

    /**
     * 查询单个项目
     *
     * @param orgId
     * @return
     */
    Org findOne(Integer orgId);

    /**
     * 查询所有成员
     *
     * @return
     */
    List<Org> findAll();

    /**
     * 新增/更新项目
     *
     * @param org
     * @return
     */
    Org save(Org org);

    /**
     * 删除项目
     *
     * @param orgId
     */
    void delete(Integer orgId);
}
