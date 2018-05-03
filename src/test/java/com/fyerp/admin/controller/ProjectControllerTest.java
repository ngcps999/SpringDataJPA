/*
 * 作者：xuda
 * 创建时间：18-4-24 上午9:50
 * 模块名称：admin
 */

package com.fyerp.admin.controller;

import com.fyerp.admin.domain.Project;
import com.fyerp.admin.domain.Role;
import com.fyerp.admin.domain.Task;
import com.fyerp.admin.domain.User;
import com.fyerp.admin.service.ProjectService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;
import java.util.*;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProjectControllerTest {

    @Autowired
    private ProjectService projectService;

    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

    @Test
    public void addProject() {
        Project project = new Project();
        project.setProjectName("cds");
        project.setPlanStartDate(new Date("2018/09/09"));
        project.setPlanEndDate(new Date("2018/09/09"));
        project.setRealStartDate(new Date("2018/09/09"));
        project.setRealEndDate(new Date("2018/09/09"));
        project.setProjectDesc("vasd");
        project.setProjectState(1);
        project.setEquipment("euq");
        project.setAreoArea("asfd");
        project.setAeroRatio("cadc");
        project.setFlyHeight("fadsf");
        project.setFlyPlatform("agfasf");
        project.setMap("fasdf");

        Role role1 = new Role("ROLE_ADMIN1","dasd",true);
        Role role2 = new Role("ROLE_USER1","fasdf",true);
//        List<Role> roles = new ArrayList<>();
//        for (Role role : roles) {
//            roles.add(role1);
//            roles.add(role2);
//        }

        User user1= new User("xaz","da","123",1);
        project.addMenbers(user1);

        Task task1 = new Task("xda","dasd","12",new Date("2018/09/09"),new Date("2018/09/09"),new Date("2018/09/09"),new Date("2018/09/09"));

        project.addTasks(task1);
        projectService.save(project);
    }

    @Test
    public void updateProject() {
    }

    @Test
    public void findByPlanStartDateAfterAndPlanEndDateBefore() {
        List<Project> projects = projectService.findByPlanStartDateAfterAndPlanEndDateBefore(new Date("2017/01/09"), new Date("2019/10/10"));
        for (Project project : projects) {
            System.out.println(project.toString());
        }
    }
}
