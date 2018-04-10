/*
 * Copyright (c) 2018.
 * 项目名称：fyerp.
 * 模块名称：fyerp
 * 文件名称：FuncRespository.java
 * 作者：xuda
 * 时间：18-4-10 下午2:25
 *
 */

package com.fyerp.admin.respository;

import com.fyerp.admin.domain.Func;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Author: xuda
 * @Date: 2018/4/10
 * @Time: 下午2:25
 */
public interface FuncRespository extends JpaRepository<Func,Integer> {
}
