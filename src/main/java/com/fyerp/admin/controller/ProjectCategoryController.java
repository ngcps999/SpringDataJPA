/*
 * 作者：xuda
 * 创建时间：18-5-14 上午9:13
 * 模块名称：admin
 */

package com.fyerp.admin.controller;

import com.fyerp.admin.domain.ProjectCategory;
import com.fyerp.admin.domain.Result;
import com.fyerp.admin.service.ProjectCategoryService;
import com.fyerp.admin.utils.ResultUtil;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("projectCategory")
public class ProjectCategoryController {

    private final static Logger logger = LoggerFactory.getLogger(ProjectController.class);

    @Autowired
    private ProjectCategoryService categoryService;


    /**
     * 查询单个项目分类
     *
     * @return
     */
    @ApiOperation(value = "查询单个项目分类", notes = "查询单个项目分类")
//    @GetMapping(value = "/findOneCategory/{id}")
    public Result<ProjectCategory> findOneProjectCategory(@PathVariable("id") Integer categoryid) {
        logger.info("findOneProject项目分类");
        if (categoryid != null) {
            return ResultUtil.success(categoryService.findOne(categoryid));
        } else {
            throw new RuntimeException("项目分类为空");
        }
    }

    /**
     * 查询项目列表
     *
     * @return
     */
    @ApiOperation(value = "查询项目分类列表", notes = "查询项目分类列表")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<ProjectCategory> getProjectCategorys(@RequestParam(value = "page", required = false) Integer page,
                                                     @RequestParam(value = "size", required = false) Integer size,
                                                     @RequestParam(value = "sort_param", required = false, defaultValue = "createTime") String sortParam,
                                                     @RequestParam(value = "sort_desc|asc", required = false, defaultValue = "DESC") Sort.Direction descOrAsc) {
        Sort sort = new Sort(descOrAsc, sortParam);
        if (page == null && size == null) {
            return categoryService.findAll(sort);
        } else {
            PageRequest request = new PageRequest(page - 1, size);
            return (List<ProjectCategory>) categoryService.findAll(request);
        }
    }

    /**
     * 按项目类目编号查询
     *
     * @return
     */
    @ApiOperation(value = "按项目类目编号查询", notes = "按项目类目编号查询")
    @GetMapping(value = "/findOne/{id}")
    public Result<ProjectCategory> findByCategoryType(@PathVariable("id") Integer categoryId) {
        return ResultUtil.success(categoryService.findOne(categoryId));
    }

    @ApiOperation(value = "创建项目分类", notes = "根据Project对象创建项目分类")
    @PostMapping(value = "/add")
    public ProjectCategory addProjectCategory(@RequestBody ProjectCategory projectCategory) {
        return categoryService.save(projectCategory);
    }

    @ApiOperation(value = "更新项目分类", notes = "根据项目分类的id来更新项目分类信息")
    @PutMapping(value = "/update")
    public ProjectCategory updateProjectCategory(@RequestBody ProjectCategory projectCategory) {
        return categoryService.save(projectCategory);
    }

    /**
     * 删除项目分类
     *
     * @param categoryId
     */
    @ApiOperation(value = "删除项目分类", notes = "根据url的id来指定删除项目分类")
    @ApiImplicitParam(name = "id", value = "项目分类ID", required = true, dataType = "Integer", paramType = "path")
    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public Result<ProjectCategory> deleteProjectCategory(@RequestParam("id") Integer categoryId) {
        categoryService.delete(categoryId);
        return ResultUtil.success(categoryId);
    }
}
