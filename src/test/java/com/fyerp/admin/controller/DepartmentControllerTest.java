/*
 * 作者：xuda
 * 创建时间：18-5-8 下午3:29
 * 模块名称：admin
 */

package com.fyerp.admin.controller;

import com.fyerp.admin.domain.Department;
import com.fyerp.admin.domain.Result;
import com.fyerp.admin.domain.Task;
import com.fyerp.admin.domain.User;
import com.fyerp.admin.domain.vo.UserVO;
import com.fyerp.admin.service.DepartmentService;
import com.fyerp.admin.utils.ResultUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class DepartmentControllerTest {

    @Autowired
    private DepartmentService departmentService;

    @Test
    public void findOneDepartment() {
        Department department = departmentService.findOne(1l);
        List<User> users = department.getUsers();
        List<UserVO> userVOS = new ArrayList<>();
        for (User user : users) {
            UserVO userVO = new UserVO();
            BeanUtils.copyProperties(users,userVOS);
            userVOS.add(userVO);
            System.out.println(user);
        }


        Department department1 = new Department();
        department1.setDepartmentId(department.getDepartmentId());
        department1.setDepName(department.getDepName());
        department1.setUsers(users);
        department1.setOrg(department.getOrg());

        System.out.println(department1);

    }
}