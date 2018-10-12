/*
 * 作者：xuda
 * 创建时间：18-5-3 上午11:19
 * 模块名称：admin
 */

package com.fyerp.admin.respository;

import com.fyerp.admin.domain.User;
import com.fyerp.admin.respository.BaseRespository.BaseRepository;

import java.util.List;

public interface LoginRespository extends BaseRepository<User,Long> {

    public List<User> findByUsernameAndPassword(String name, String password);

}
