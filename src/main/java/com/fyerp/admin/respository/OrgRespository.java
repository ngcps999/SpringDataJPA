/*
 * Copyright (c) 2018.
 * 项目名称：fyerp.
 * 模块名称：fyerp
 * 文件名称：OrgRespository.java
 * 作者：xuda
 * 时间：18-4-9 下午4:01
 *
 */

package com.fyerp.admin.respository;

import com.fyerp.admin.domain.Org;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author: xuda
 * @Date: 2018/4/9
 * @Time: 下午4:01
 */
public interface OrgRespository extends JpaRepository<Org,Integer> {
}
