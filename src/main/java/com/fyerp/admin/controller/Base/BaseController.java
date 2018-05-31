///*
// * 作者：xuda
// * 创建时间：18-5-31 下午2:41
// * 模块名称：admin
// */
//
//package com.fyerp.admin.controller.Base;
//
//import com.fyerp.admin.service.Base.TService;
//import io.swagger.annotations.ApiOperation;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Sort;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.HashSet;
//import java.util.List;
//import java.util.Set;
//
//@RestController
//@RequestMapping(value = "/base")
//public class BaseController<T> {
//
//
//    private final static Logger logger = LoggerFactory.getLogger(T);
//
//    @Autowired
//    private TService<T> tService;
//
//    /**
//     * 查询部门列表
//     *
//     * @return
//     */
//    @ApiOperation(value = "查询部门列表", notes = "查询部门列表")
//    @RequestMapping(value = "/list", method = RequestMethod.GET)
//    public Object gets(@RequestParam(value = "page", required = false) Integer page,
//                                 @RequestParam(value = "size", required = false) Integer size,
//                                 @RequestParam(value = "sortBy", required = false, defaultValue = "createTime") String sortParam,
//                                 @RequestParam(value = "order", required = false, defaultValue = "DESC") Sort.Direction descOrAsc) {
//        logger.info("tList");
//        Sort sort = new Sort(descOrAsc, sortParam);
//        if (page == null && size == null) {
//            return tService.findAll(sort);
//        } else {
//            PageRequest request = new PageRequest(page - 1, size);
//            Page<T> departmentDTOS = tService.findAll(request);
//            return departmentDTOS.getContent();
//        }
//    }
//
//    /**
//     * 查询单个部门
//     *
//     * @return
//     */
//    @ApiOperation(value = "查询单个", notes = "查询单个")
//    @GetMapping(value = "/find")
//    public T findOneDepartment(@RequestParam("id") Number ID) {
//        logger.info("findOneDepartment");
//
//        return tService.findOne(ID);
//    }
//
//
//    /**
//     * 创建
//     *
//     * @return
//     */
//    @ApiOperation(value = "添加部门", notes = "根据department对象属性创建部门")
//    @RequestMapping(value = "/addDep", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
//    public T addDepartment(@RequestParam("id") Number ID,@RequestParam("ids") List<Number> iDs) {
//        T t = tService.findOne(ID);
//
//        List<T> ts = tService.findAll(iDs);
//        Set<T> users1 = new HashSet<>(users);
//
//        t.getClass().getMethod()
//        return tService.save(t);
//    }
//
//    /**
//     * 更新部门
//     *
//     * @return
//     */
////    @ApiOperation(value = "更新部门", notes = "根据部门的id来更新部门")
////    @PutMapping(value = "/update")
////    public Department updateDepartment(@RequestParam("id") Long departmentId) {
////        Department source = departmentService.findOne(departmentId);
////        Department department = new Department();
////        BeanUtils.copyNotNullProperties(source, department);
////        return departmentService.saveAndFlush(department);
////    }
//
//    /**
//     * 更新部门的员工
//     *
//     * @return
//     */
//    @ApiOperation(value = "更新部门的员工", notes = "根据部门的id来更新部门的员工")
//    @PutMapping(value = "/updateUsers")
//    public Department updateDepartmentUsers(@RequestParam(value = "departmentId", required = true) Long departmentId, @RequestParam(value = "userId", required = true) List<Long> userIds) {
//        Department department = departmentService.findOne(departmentId);
//        List<User> users = userService.findAll(userIds);
//        Set<User> departmentUsers = department.getUsers();
//        for (User user : users) {
//            if (departmentUsers.contains(user)) {
//                continue;
//            }
//            departmentUsers.add(user);
//        }
//        try {
//            departmentService.save(department);
//        } catch (Exception e) {
//            throw new RuntimeException("update fail!");
//        }
//        return department;
//    }
//
////    /**
////     * 更新部门的员工
////     *
////     * @return
////     */
////    @ApiOperation(value = "更新部门的员工", notes = "根据部门的id来更新部门的员工")
////    @PutMapping(value = "/updateUsersTest")
////    public Department updateDepartmentUsersTest(@RequestBody Department department) {
////        Department department1 = departmentService.findOne(department.getDepartmentId());
////        UpdateUtil.copyNullProperties(department1,department);
////        List<Long> userIds =new ArrayList<>();
////        for (User user : department.getUsers()) {
////            Long userId = user.getUserId();
////            userIds.add(userId);
////        }
////        List<User> users = userService.findAll(userIds);
////        Set<User> departmentUsers = department.getUsers();
////        for (User user : users) {
////            if (departmentUsers.contains(user)) {
////                continue;
////            }
////            departmentUsers.add(user);
////        }
////        try {
////            departmentService.save(department);
////        } catch (Exception e) {
////            throw new RuntimeException("update fail!");
////        }
////        return department;
////    }
//
//    /**
//     * 删除部门的员工
//     *
//     * @return
//     */
//    @ApiOperation(value = "删除部门员工", notes = "根据部门的id来添加部门员工")
//    @PutMapping(value = "/deleteUsers")
//    public Department deleteDepartmentUsers(@RequestParam(value = "departmentId", required = true) Long departmentId, @RequestParam(value = "userId", required = true) List<Long> userIds) {
//        Department department = departmentService.findOne(departmentId);
//        List<User> users = userService.findAll(userIds);
//        Set<User> departmentUsers = department.getUsers();
//        for (User user : users) {
//            if (departmentUsers.contains(user)) {
//                departmentUsers.remove(user);
//            }
//        }
//        try {
//            departmentService.save(department);
//        } catch (Exception e) {
//            throw new RuntimeException("update fail!");
//        }
//        return department;
//    }
//
////    @ApiOperation(value = "更新部门员工", notes = "根据部门的id来更新部门员工")
////    @PutMapping(value = "/updateTest")
////    public Department updateDepartmentUsersTest(@RequestBody Department department) {
////        Department department1 = departmentService.findOne(department.getDepartmentId());
////        List<Long> userIds = new ArrayList<>();
////        Set<User> userList = department.getUsers();
////        for (User user : userList) {
////            if (user.getUserId() == 0) {
////                User user1 = userService.save(user);
////                userList.add(user1);
////            }
////            userIds.add(user.getUserId());
////        }
////
////        List<User> users = userService.findAll(userIds);
////        Set<User> departmentUsers = department1.getUsers();
////        for (User user : users) {
////            if (departmentUsers.contains(user)) {
////                continue;
////            }
////            departmentUsers.add(user);
////        }
////        try {
////            return departmentService.save(department1);
////        } catch (Exception e) {
////            throw new RuntimeException("update fail!");
////        }
////    }
//
//    /**
//     * 删除部门
//     */
//    @ApiOperation(value = "删除部门", notes = "根据id删除部门")
//    @DeleteMapping(value = "/delete")
//    public void deleteDepartment(@RequestParam("id") Long departmentId) {
//        departmentService.delete(departmentId);
//    }
//
//
//}
