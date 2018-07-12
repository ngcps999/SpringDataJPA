/*
 * 作者：xuda
 * 创建时间：18-7-5 上午11:04
 * 模块名称：admin
 */

package com.fyerp.admin.service.impl;

import com.fyerp.admin.domain.FileEntity;
import com.fyerp.admin.exception.GenericException;
import com.fyerp.admin.exception.ItemNotFoundException;
import com.fyerp.admin.respository.FileSystemService;
import com.fyerp.admin.service.FileService;
import com.fyerp.admin.service.StorageService;
import com.fyerp.admin.utils.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@Component
public class StorageServiceImpl implements StorageService {
    private FileSystemService fileSystemStorageService;

    private FileService fileService;

    @Autowired
    public StorageServiceImpl(FileSystemService fileSystemStorageService, FileService fileService) {
        this.fileSystemStorageService = fileSystemStorageService;
        this.fileService = fileService;
    }

    /**
     * 获取所有文件列表
     *
     * @return
     */
    @Override
    public List<FileEntity> findAll() {
        return fileService.findAll();
    }

    /**
     * 加载指定的文件
     *
     * @param fileId
     * @param fileName
     * @return
     */
    @Override
    public Resource loadAsResource(String fileId, String fileName) {
        String[] fileProperties = fileName.split("\\.");
        String fileext = fileProperties[1];
        try {
            Path file = fileSystemStorageService.load(fileId + "." + fileext);
            Resource resource = null;
            if (Files.exists(file)) {
                resource = new UrlResource(file.toUri());
                return resource;
            } else {
                FileEntity fileEntity = fileService.findOne(fileId);
                if (fileEntity != null) {
                    resource = new ByteArrayResource(fileEntity.getFileData());
                    fileSystemStorageService.store(fileEntity);
                    return resource;
                } else {
                    throw new ItemNotFoundException("数据库未找到该文件，文件编号为：" + fileId);
                }
            }
        } catch (Exception e) {
            throw new GenericException("获取指定文件失败！", e.getCause());
        }
    }

    /**
     * 保存文件
     *
     * @param file
     */
    @Override
    public void store(MultipartFile file) {
        FileEntity fileEntity = new FileEntity();
        fileEntity.setFileId(CommonUtil.getUuid());
        fileEntity.setFileType(file.getContentType());
        String[] fileProperties = file.getOriginalFilename().split("\\.");
        fileEntity.setFileExt(fileProperties[1]);
        fileEntity.setFileName(fileProperties[0]);
        try {
            fileEntity.setFileData(file.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            fileService.save(fileEntity);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
