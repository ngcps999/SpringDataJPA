package com.fyerp.admin.service.impl;

import com.fyerp.admin.domain.FileInfo;
import com.fyerp.admin.respository.FileInfoRepository;
import com.fyerp.admin.service.FileInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author:xiasc
 * @Date:2018/7/12
 * @Time:11:12
 **/
@Service
public class FileInfoServiceImpl implements FileInfoService {

    @Autowired
    private FileInfoRepository repository;

    @Override
    public FileInfo findOne(Long id) {
        return repository.findOne(id);
    }

    @Override
    public List<FileInfo> findAll() {
        return repository.findAll();
    }

    @Override
    public List<FileInfo> findAll(Sort sort) {
        return repository.findAll(sort);
    }

    @Override
    public Page<FileInfo> findAll(Pageable page) {
        return repository.findAll(page);
    }

    @Override
    public FileInfo save(FileInfo file) {
        return repository.save(file);
    }

    @Override
    public void delete(Long id) {
        repository.delete(id);
    }

    @Override
    public void batchSave(List<MultipartFile> list) {
        if(list != null && list.size() > 0){

        }
    }
}
