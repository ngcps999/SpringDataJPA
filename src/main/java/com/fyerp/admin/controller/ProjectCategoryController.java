/*
 * 作者：xuda
 * 创建时间：18-5-14 上午9:13
 * 模块名称：admin
 */

package com.fyerp.admin.controller;

import com.fyerp.admin.domain.Project;
import com.fyerp.admin.domain.ProjectCategory;
import com.fyerp.admin.domain.Result;
import com.fyerp.admin.domain.vo.ProjectCategoryVO;
import com.fyerp.admin.enums.ResultEnum;
import com.fyerp.admin.exception.ProjectCategoryException;
import com.fyerp.admin.service.ProjectCategoryService;
import com.fyerp.admin.service.ProjectService;
import com.fyerp.admin.utils.BeanUtils;
import com.fyerp.admin.utils.Constants;
import com.fyerp.admin.utils.ResultUtil;
import com.fyerp.admin.utils.UpdateUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("projectCategory")
@Api(value = "projectCategoryController", description = "项目分类Api")
@Scope("prototype")
public class ProjectCategoryController {

    private final static Logger logger = LoggerFactory.getLogger(ProjectController.class);

    @Autowired
    private ProjectCategoryService categoryService;

    @Autowired
    private ProjectService projectService;


    /**
     * 查询单个项目类型
     *
     * @return
     */
    @ApiOperation(value = "查询单个项目类型", notes = "查询单个项目类型")
    @GetMapping(value = "/find")
    public Result findOneProjectCategory(@RequestParam("id") Integer categoryid) {
        logger.info("findOneProject项目分类");
        return ResultUtil.success(categoryService.findOne(categoryid));
    }

    /**
     * 查询项目列表
     *
     * @return
     */
    @ApiOperation(value = "查询项目分类列表", notes = "查询项目分类列表")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public Result getProjectCategorys(@RequestParam(value = "page", required = false) Integer page,
                                      @RequestParam(value = "size", required = false) Integer size,
                                      @RequestParam(value = "sortBy", required = false, defaultValue = "createTime") String sortParam,
                                      @RequestParam(value = "order", required = false, defaultValue = "DESC") Sort.Direction descOrAsc) {
        Sort sort = new Sort(descOrAsc, sortParam);
        if (page == null && size == null) {
            return ResultUtil.success(categoryService.findAll(sort));
        } else {
            PageRequest request = new PageRequest(page - 1, size);
            return ResultUtil.success(categoryService.findAll(request).getContent());
        }
    }

    /**
     * 创建/更新项目类型
     *
     * @return
     */
    @ApiOperation(value = "添加/更新项目类型", notes = "根据ProjectCategory对象属性创建项目类型")
    @RequestMapping(value = "/addProjectCategory", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Result addProjectCategory(@RequestBody ProjectCategoryVO projectCategoryVO) {
        ProjectCategory projectCategory = new ProjectCategory();
        UpdateUtil.copyNullProperties(projectCategoryVO, projectCategory);
        ProjectCategory projectCategory1 = categoryService.save(projectCategory);
        BeanUtils.copyNotNullProperties(projectCategory, projectCategory1);
        return ResultUtil.success(projectCategoryVO);

    }

//    /**
//     * 更新项目类型下的项目
//     *
//     * @return
//     */
//    @ApiOperation(value = "更新项目类型下的项目", notes = "更新项目类型下的项目")
//    @PutMapping(value = "/updateProjectCategoryProjects")
//    public ProjectCategory updateProjectCategoryProjects(@RequestParam(value = "projectCategoryId", required = true) Integer projectCategoryId, @RequestParam(value = "projectIds", required = true) List<Integer> projectIds) {
//        ProjectCategory projectCategory = categoryService.findOne(projectCategoryId);
//        List<Project> projects = projectService.findAll(projectIds);
//        Set<Project> projectCatProjects = projectCategory.getProjects();
//        for (Project project : projects) {
//            if (projectCatProjects.contains(project)) {
//                continue;
//            }
//            projectCatProjects.add(project);
//        }
//        try {
//            categoryService.save(projectCategory);
//        } catch (Exception e) {
//            throw new RuntimeException("update fail!");
//        }
//        return projectCategory;
//    }

    /**
     * 更新项目
     *
     * @return
     */
    @ApiOperation(value = "更新项目", notes = "更新项目")
    @PutMapping(value = "/save")
    public Result saveProjectCategory(@RequestBody ProjectCategory projectCategory) {

        if (projectCategory.getCategoryId() != 0) {
            ProjectCategory projectCategory1 = categoryService.findOne(projectCategory.getCategoryId());
            //获取project1里的taskIds
            List<Integer> projectIds = projectCategory1.getProjects().stream().map(Project::getProjectId).collect(Collectors.toList());
            Set<Project> categoryProjects = projectCategory.getProjects();
            //根据taskIds查询task库里是否存在，如果不存在就绑定到project1里
            //判断project1里是否包含task,有就继续，没有就添加
            projectService.findAll(projectIds).stream().filter(project -> !categoryProjects.contains(project)).forEach(categoryProjects::add);

            projectCategory.getProjects().stream().map(project -> projectService.save(project)).forEach(categoryProjects::add);

            projectCategory.setProjects(new HashSet<>(categoryProjects));

            ProjectCategory save = categoryService.save(projectCategory);
            Set<Project> tasks = save.getProjects();
            //strategy属性等于2时即删除task
            tasks.removeIf(project -> project.getStrategy() == 2);
            UpdateUtil.copyNullProperties(projectCategory1, save);
            return ResultUtil.success(save);
        }
        Result result = new Result("请传入Id");
        return result;
    }

    /**
     * 删除项目类型下的项目
     *
     * @return
     */
    @ApiOperation(value = "删除项目类型下的项目", notes = "删除项目类型下的项目")
    @PutMapping(value = "/deleteProjects")
    public Result deleteProjectCategoryProjects(@RequestParam(value = "projectCategoryId", required = true) Integer projectCategoryId, @RequestParam(value = "projectIds", required = true) List<Integer> projectIds) {
        ProjectCategory projectCategory = categoryService.findOne(projectCategoryId);
        List<Project> projects = projectService.findAll(projectIds);
        Set<Project> projectCatProjects = projectCategory.getProjects();
        for (Project project : projects) {
            if (projectCatProjects.contains(project)) {
                projectCatProjects.remove(project);
            }
        }

        categoryService.save(projectCategory);

        return ResultUtil.success(projectCategory);
    }

    /**
     * 删除项目分类
     *
     * @param categoryId
     */
    @ApiOperation(value = "删除项目分类", notes = "根据url的id来指定删除项目分类")
    @ApiImplicitParam(name = "id", value = "项目分类ID", required = true, dataType = "Integer", paramType = "path")
    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public Result deleteProjectCategory(@RequestParam("id") Integer categoryId) {
        ProjectCategory projectCategory = categoryService.findOne(categoryId);
        Set<Project> projects = projectCategory.getProjects();
        projects.removeAll(projects);
        categoryService.delete(categoryId);
        return ResultUtil.success(Constants.DELETE_SUCCESS);
    }
}
