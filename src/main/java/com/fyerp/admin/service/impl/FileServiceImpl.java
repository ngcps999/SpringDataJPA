/*
 * 作者：xuda
 * 创建时间：18-7-5 上午10:28
 * 模块名称：admin
 */

package com.fyerp.admin.service.impl;

import com.fyerp.admin.domain.FileEntity;
import com.fyerp.admin.respository.FileRespository;
import com.fyerp.admin.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
@Transactional(readOnly = true,transactionManager = "transactionManager")
public class FileServiceImpl implements FileService {

    @Autowired
    private FileRespository fileRespository;

    @Override
    public FileEntity findOne(String fileId) {
        return fileRespository.findOne(fileId);
    }

    @Override
    public Page<FileEntity> findAll(Pageable pageable) {
        return fileRespository.findAll(pageable);
    }

    @Override
    public List<FileEntity> findAll(Set<String> fileIds) {
        return fileRespository.findAll(fileIds);
    }

    @Override
    public List<FileEntity> findAll() {
        return fileRespository.findAll();
    }

    @Override
    public List<FileEntity> findAll(List<String> fileIds) {
        return fileRespository.findAll(fileIds);
    }

    @Override
    public List<FileEntity> findAll(Sort sort) {
        return fileRespository.findAll(sort);
    }

    @Override
    public FileEntity save(FileEntity fileEntity) {
        return fileRespository.save(fileEntity);
    }

    @Override
    public void delete(String id) {
        fileRespository.delete(id);
    }
}
