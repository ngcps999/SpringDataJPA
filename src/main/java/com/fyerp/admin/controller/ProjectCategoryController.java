/*
 * 作者：xuda
 * 创建时间：18-5-14 上午9:13
 * 模块名称：admin
 */

package com.fyerp.admin.controller;

import com.fyerp.admin.domain.Project;
import com.fyerp.admin.domain.ProjectCategory;
import com.fyerp.admin.domain.Result;
import com.fyerp.admin.service.ProjectCategoryService;
import com.fyerp.admin.service.ProjectService;
import com.fyerp.admin.utils.ResultUtil;
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

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    public ProjectCategory findOneProjectCategory(@RequestParam("id") Integer categoryid) {
        logger.info("findOneProject项目分类");
        try {
            return categoryService.findOne(categoryid);
        } catch (Exception e){
            throw new RuntimeException("项目分类不存在！");
        }
    }

    /**
     * 查询项目列表
     *
     * @return
     */
    @ApiOperation(value = "查询项目分类列表", notes = "查询项目分类列表")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public Object getProjectCategorys(@RequestParam(value = "page", required = false) Integer page,
                                      @RequestParam(value = "size", required = false) Integer size,
                                      @RequestParam(value = "sortBy", required = false, defaultValue = "createTime") String sortParam,
                                      @RequestParam(value = "order", required = false, defaultValue = "DESC") Sort.Direction descOrAsc) {
        Sort sort = new Sort(descOrAsc, sortParam);
        if (page == null && size == null) {
            return categoryService.findAll(sort);
        } else {
            PageRequest request = new PageRequest(page - 1, size);
            return categoryService.findAll(request).getContent();
        }
    }

    /**
     * 创建/更新项目类型
     *
     * @return
     */
    @ApiOperation(value = "添加/更新项目类型", notes = "根据ProjectCategory对象属性创建项目类型")
    @RequestMapping(value = "/addProjectCategory", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public ProjectCategory addProjectCategory(@RequestParam(value = "name", required = true) String categoryName,
                                              @RequestParam(value = "description", required = false) String categoryDesc,
                                              @RequestParam(value = "projectIds", required = true) List<Integer> projectIds) {
        ProjectCategory projectCategory = new ProjectCategory();
        projectCategory.setCategoryName(categoryName);
        projectCategory.setCategoryDesc(categoryDesc);

        List<Project> projects = projectService.findAll(projectIds);
        Set<Project> projects1 = new HashSet<>(projects);

        projectCategory.setProjects(projects1);
        return categoryService.save(projectCategory);
    }

    /**
     * 更新项目类型下的项目
     *
     * @return
     */
    @ApiOperation(value = "更新项目类型下的项目", notes = "更新项目类型下的项目")
    @PutMapping(value = "/updateProjectCategoryProjects")
    public ProjectCategory updateProjectCategoryProjects(@RequestParam(value = "projectCategoryId", required = true) Integer projectCategoryId, @RequestParam(value = "projectIds", required = true) List<Integer> projectIds) {
        ProjectCategory projectCategory = categoryService.findOne(projectCategoryId);
        List<Project> projects = projectService.findAll(projectIds);
        Set<Project> projectCatProjects = projectCategory.getProjects();
        for (Project project : projects) {
            if (projectCatProjects.contains(project)) {
                continue;
            }
            projectCatProjects.add(project);
        }
        try {
            categoryService.save(projectCategory);
        } catch (Exception e) {
            throw new RuntimeException("update fail!");
        }
        return projectCategory;
    }

    /**
     * 删除项目类型下的项目
     *
     * @return
     */
    @ApiOperation(value = "删除项目类型下的项目", notes = "删除项目类型下的项目")
    @PutMapping(value = "/deleteProjectCategoryProjects")
    public ProjectCategory deleteProjectCategoryProjects(@RequestParam(value = "projectCategoryId", required = true) Integer projectCategoryId, @RequestParam(value = "projectIds", required = true) List<Integer> projectIds) {
        ProjectCategory projectCategory = categoryService.findOne(projectCategoryId);
        List<Project> projects = projectService.findAll(projectIds);
        Set<Project> projectCatProjects = projectCategory.getProjects();
        for (Project project : projects) {
            if (projectCatProjects.contains(project)) {
                projectCatProjects.remove(project);
            }
        }
        try {
            categoryService.save(projectCategory);
        } catch (Exception e) {
            throw new RuntimeException("update fail!");
        }
        return projectCategory;
    }

    /**
     * 删除项目分类
     *
     * @param categoryId
     */
    @ApiOperation(value = "删除项目分类", notes = "根据url的id来指定删除项目分类")
    @ApiImplicitParam(name = "id", value = "项目分类ID", required = true, dataType = "Integer", paramType = "path")
    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public void deleteProjectCategory(@RequestParam("id") Integer categoryId) {
        try {
            categoryService.delete(categoryId);
        }catch (Exception e) {
            throw new RuntimeException("项目分类下有项目，请先删除关联项目");
        }
    }
}
