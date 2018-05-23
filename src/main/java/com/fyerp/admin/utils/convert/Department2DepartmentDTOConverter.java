/*
 * 作者：xuda
 * 创建时间：18-5-22 上午10:03
 * 模块名称：admin
 */

package com.fyerp.admin.utils.convert;

import com.fyerp.admin.domain.Department;
import com.fyerp.admin.domain.dto.DepartmentDTO;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

public class Department2DepartmentDTOConverter {

    public static DepartmentDTO convert(Department department) {
        DepartmentDTO departmentDTO = new DepartmentDTO();
        BeanUtils.copyProperties(department,departmentDTO);
        return departmentDTO;
    }

    public static List<DepartmentDTO> convert(List<Department> departments) {
        List<DepartmentDTO> departmentDTOS = new ArrayList<>();
        for (Department department : departments) {
            DepartmentDTO convert = convert(department);
            departmentDTOS.add(convert);
        }
        return departmentDTOS;
    }
}
