/*
 * Copyright (c) 2018.
 * 项目名称：fyerp.
 * 模块名称：fyerp
 * 文件名称：OrgServiceImpl.java
 * 作者：xuda
 * 时间：18-4-9 下午4:02
 *
 */

package com.fyerp.admin.service.impl;

import com.fyerp.admin.domain.Org;
import com.fyerp.admin.respository.OrgRespository;
import com.fyerp.admin.service.OrgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: xuda
 * @Date: 2018/4/9
 * @Time: 下午4:02
 */
@Service
public class OrgServiceImpl implements OrgService {

    @Autowired
    private OrgRespository orgRespository;

    @Override
    public Org findOne(Integer orgId) {
        return orgRespository.findOne(orgId);
    }

    @Override
    public List<Org> findAll() {
        return orgRespository.findAll();
    }

    @Override
    public Org save(Org org) {
        return orgRespository.save(org);
    }

    @Override
    public void delete(Integer orgId) {
        orgRespository.delete(orgId);
    }
}
