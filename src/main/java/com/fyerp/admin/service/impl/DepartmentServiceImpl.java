/*
 * 作者：xuda
 * 创建时间：18-5-8 上午11:12
 * 模块名称：admin
 */

package com.fyerp.admin.service.impl;

import com.fyerp.admin.domain.*;
import com.fyerp.admin.domain.dto.DepartmentDTO;
import com.fyerp.admin.enums.ResultEnum;
import com.fyerp.admin.exception.DepartmentException;
import com.fyerp.admin.exception.ProjectException;
import com.fyerp.admin.respository.DepartmentRespository;
import com.fyerp.admin.respository.UserRepository;
import com.fyerp.admin.service.DepartmentService;
import com.fyerp.admin.utils.convert.Department2DepartmentDTOConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.net.DatagramPacket;
import java.util.*;

import static com.fyerp.admin.utils.BeanUtils.*;

@Service
public class DepartmentServiceImpl implements DepartmentService {


    @Autowired
    private DepartmentRespository departmentRespository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Department findOne(Long departmentId) {
        return departmentRespository.findOne(departmentId);
    }

    @Override
    public DepartmentDTO findOneDTO(Long departmentId) {

        Department department = departmentRespository.findOne(departmentId);
        DepartmentDTO departmentDTO = new DepartmentDTO();
        copyNotNullProperties(department, departmentDTO);
        return departmentDTO;
    }

    @Override
    public List<Department> findAll() {
        return departmentRespository.findAll();
    }

    @Override
    public List<DepartmentDTO> findAllDTO() {
        List<Department> departments = departmentRespository.findAll();
        List<DepartmentDTO> departmentDTOS = new ArrayList<>();
        copyNotNullProperties(departments, departmentDTOS);
        return departmentDTOS;
    }

    @Override
    public Page<Department> findAll(Pageable pageable) {
        return departmentRespository.findAll(pageable);
    }

    @Override
    public Page<DepartmentDTO> findAllDTO(Pageable pageable) {
        Page<Department> departmentPage = departmentRespository.findAll(pageable);
        List<DepartmentDTO> departmentDTOPage = Department2DepartmentDTOConverter.convert(departmentPage.getContent());
        return new PageImpl<>(departmentDTOPage,pageable,departmentPage.getTotalElements());
    }

    @Override
    public List<Department> findAll(Sort sort) {
        return departmentRespository.findAll(sort);
    }

    @Override
    public List<DepartmentDTO> findAllDTO(Sort sort) {
        List<Department> departments = departmentRespository.findAll(sort);
        List<DepartmentDTO> departmentDTOS =Department2DepartmentDTOConverter.convert(departments);
        return departmentDTOS;
    }


    @Override
    public Department save(Department department) {

        return departmentRespository.save(department);
    }
    @Override
    public DepartmentDTO saveDTO(Department department) {

        Department department1 = departmentRespository.saveAndFlush(department);
        DepartmentDTO departmentDTO = Department2DepartmentDTOConverter.convert(department1);
        return departmentDTO;
    }

    @Override
    public Department saveAndFlush(Department department) {

        return departmentRespository.saveAndFlush(department);
    }

    @Override
    public List<Department> save(List<Department> departments) {
        return departmentRespository.save(departments);
    }

    @Override
    public void delete(Long departmentId) {
        departmentRespository.delete(departmentId);
    }

    @Override
    public Integer deleteA(Long departmentId,Long userId) {
        return departmentRespository.deleteA(userId);
    }

    @Override
    public Department updateDepartment(Department department) {
        try {
            if(department.getDepartmentId().intValue() != 0){
                Department department1 = departmentRespository.findOne(department.getDepartmentId());
                //处理task
                Set<Task> department1Tasks = department1.getTasks();
                Iterator<Task> iterator = department1Tasks.iterator();
                if(department.getTasks() != null){
                    Set<Task> newTasks = new HashSet<>();
                    for(Task task : department.getTasks()){
                        boolean isInsert = true;
                        while(iterator.hasNext()){
                            Task oldTask = iterator.next();
                            if(oldTask.getTaskId().intValue() == task.getTaskId().intValue()){
                                iterator.remove();
                                isInsert = false;
                                Task newTask = task;
                                //对修改的和原先的两个task处理合并成一个,需要合并子对象plan
                                if(newTask.getPlans() != null){
                                    Set<Plan> newPlans = new HashSet<>();
                                    for(Plan newPlan : newTask.getPlans()){
                                        Iterator<Plan> planIterator = oldTask.getPlans().iterator();
                                        boolean insertPlan = true;
                                        while (planIterator.hasNext()){
                                            if(newPlan.getPlanId().intValue() == planIterator.next().getPlanId().intValue()){
                                                insertPlan = false;
                                                //替换
                                                newPlans.add(newPlan);
                                                planIterator.remove();
                                                break;
                                            }
                                        }
                                        if(insertPlan){
                                            newPlans.add(newPlan);
                                        }
                                    }
                                    oldTask.getPlans().addAll(newPlans);
                                    newTask.setPlans(oldTask.getPlans());

                                }else{
                                    newTask.setPlans(oldTask.getPlans());
                                }
                                newTasks.add(newTask);
                                break;
                            }
                        }
                        if(isInsert){
                            if(task.getPlans() != null){
                                for(Plan plan : task.getPlans()){
                                    if(plan.getPlanId().intValue() != 0){
                                        //新增task时，task中不允许带有已存在的plan
                                        throw new DepartmentException(ResultEnum.PARAM_ERROR);
                                    }
                                }
                            }
                            newTasks.add(task);
                        }
                    }
                    department1Tasks.addAll(newTasks);
                }
                department.setTasks(department1Tasks);
                //处理user
                Set<User> department1Users = department1.getUsers();
                Iterator<User> userIterator = department1Users.iterator();
                if(department.getUsers() != null){
                    Set<User> newUsers = new HashSet<>();
                    for(User user : department.getUsers()){
                        boolean isInsert = true;
                        while(userIterator.hasNext()){
                            User oldUser = userIterator.next();
                            if(oldUser.getUserId().intValue() == user.getUserId().intValue()){
                                userIterator.remove();
                                isInsert = false;
                                User newUser = user;

                                if(newUser.getRoles() != null){
                                    Set<Role> newRoles = new HashSet<>();
                                    for(Role role : newUser.getRoles()){
                                        Iterator<Role> roleIterator = oldUser.getRoles().iterator();
                                        boolean insertRole = true;
                                        while (roleIterator.hasNext()){
                                            Role oldRole = roleIterator.next();
                                            if(role.getRoleId().intValue() == oldRole.getRoleId().intValue()){
                                                roleIterator.remove();
                                                insertRole = false;
                                                Role newRole = role;

                                                if(newRole.getPermissions() != null){
                                                    Set<Permission> newPermissions = new HashSet<>();
                                                    for(Permission newPermission : newRole.getPermissions()){
                                                        Iterator<Permission> permissionIterator = oldRole.getPermissions().iterator();
                                                        boolean insertPermission = true;
                                                        while ((permissionIterator.hasNext())){
                                                            if(newPermission.getPermissionId().intValue() == permissionIterator.next().getPermissionId().intValue()){
                                                                insertPermission = false;
                                                                newPermissions.add(newPermission);
                                                                permissionIterator.remove();
                                                                break;
                                                            }
                                                        }
                                                        if(insertPermission){
                                                            newPermissions.add(newPermission);
                                                        }
                                                    }
                                                    oldRole.getPermissions().addAll(newPermissions);
                                                    newRole.setPermissions(oldRole.getPermissions());
                                                }


                                                //替换

                                                newRoles.add(role);
                                                roleIterator.remove();
                                                break;
                                            }
                                        }

                                        if(insertRole){
                                            if(role.getPermissions() != null){
                                                for(Permission permission : role.getPermissions()){
                                                    if(permission.getPermissionId().intValue() != 0){
                                                        throw new DepartmentException(ResultEnum.PARAM_ERROR);
                                                    }
                                                }
                                            }
                                            newRoles.add(role);
                                        }
                                    }
                                    oldUser.getRoles().addAll(newRoles);
                                    newUser.setRoles(oldUser.getRoles());

                                }else{
                                    newUser.setRoles(oldUser.getRoles());
                                }
                                newUsers.add(newUser);
                                break;
                            }
                        }
                        if(isInsert){
                            if(user.getRoles() != null){
                                for(Role role : user.getRoles()){
                                    if(role.getRoleId().intValue() != 0){
                                        //新增task时，task中不允许带有已存在的plan
                                        throw new DepartmentException(ResultEnum.PARAM_ERROR);
                                    }
                                }
                            }
                            newUsers.add(user);
                        }
                    }
                    department1Users.addAll(newUsers);
                }
                department.setUsers(department1Users);

                Department save = departmentRespository.save(department);

                Set<Task> taskSet = new HashSet<>();
                for(Task task : save.getTasks()){
                    if(task.getStrategy().intValue() != 2){
                        Set<Plan> planSet = new HashSet<>();
                        for(Plan plan : task.getPlans()){
                            if(plan.getStrategy().intValue() != 2){
                                planSet.add(plan);
                            }
                        }
                        task.setPlans(planSet);
                        taskSet.add(task);
                    }
                }
                save.setTasks(taskSet);

                Set<User> userSet = new HashSet<>();
                for(User user : save.getUsers()){
                    if(user.getStrategy().intValue() != 2){
                        Set<Role> roleSet = new HashSet<>();
                        for(Role role : user.getRoles()){
                            if(role.getStrategy().intValue() != 2){
                                roleSet.add(role);
                            }
                        }
                        user.setRoles(roleSet);
                        userSet.add(user);
                    }
                }
                save.setUsers(userSet);

                return save;
            }
        }catch (Exception e){
            throw new DepartmentException(ResultEnum.PARAM_ERROR);
        }

        return departmentRespository.save(department);
    }

    @Override
    public Integer update(Long departmentId, Long userId) {
        return departmentRespository.update(departmentId,userId);
    }

    @Override
    public Integer insert(Long departmentId, Long userId) {
        return departmentRespository.insert(departmentId,userId);
    }
}
