package com.fyerp.admin.service;

import com.fyerp.admin.domain.FileInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author:xiasc
 * @Date:2018/7/12
 * @Time:11:09
 **/
public interface FileInfoService {

    FileInfo findOne(Long id);

    List<FileInfo> findAll();

    List<FileInfo> findAll(Sort sort);

    Page<FileInfo> findAll(Pageable page);

    FileInfo save(FileInfo file);

    void delete(Long id);

    void batchSave(List<MultipartFile> list);
}
