/*
 * 作者：xuda
 * 创建时间：18-7-5 上午10:22
 * 模块名称：admin
 */

package com.fyerp.admin.service;

import com.fyerp.admin.domain.FileEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Set;

public interface FileService {

    FileEntity findOne(String fileId);

    Page<FileEntity> findAll(Pageable pageable);
    List<FileEntity> findAll(Set<String> fileIds);

    List<FileEntity> findAll();
    List<FileEntity> findAll(List<String> fileIds);
    List<FileEntity> findAll(Sort sort);

    FileEntity save(FileEntity fileEntity);

    void delete(String fileId);

}
