package com.fyerp.admin.service;

import com.fyerp.admin.domain.QualityControl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

/**
 * @author:xiasc
 * @Date:2018/7/10
 * @Time:17:28
 **/
public interface QualityControlService {
    QualityControl findOne(Integer id);

    Page<QualityControl> findAll(Pageable page);

    List<QualityControl> findAll();

    List<QualityControl> findAll(Sort sort);

    QualityControl save(QualityControl qualityControl);

    void delete(Integer id);
}
