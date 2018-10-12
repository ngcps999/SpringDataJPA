/*
 * 作者：xuda
 * 创建时间：18-7-5 上午10:42
 * 模块名称：admin
 */

package com.fyerp.admin.service.impl;

import com.fyerp.admin.domain.FileEntity;
import com.fyerp.admin.exception.GenericException;
import com.fyerp.admin.respository.FileSystemService;
import com.fyerp.admin.utils.SpringConfig;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Component
public class FileSystemServiceImpl implements FileSystemService {

    private final Path rootLocation;

    public FileSystemServiceImpl(SpringConfig config) {
        this.rootLocation = Paths.get(config.getLocation());
    }

    /**
     * 初始化存储路径
     */
    @Override
    public void init() {
        try {
            //判断文件夹是否存在，不存在则创建文件夹
            if (!Files.isDirectory(rootLocation)) {
                Files.createDirectories(rootLocation);
            }
        } catch (Exception e) {
            throw new GenericException("不能初始化存储位置", e);
        }
    }

    /**
     * 从磁盘加载指定文件
     *
     * @param filename
     * @return
     */
    @Override
    public Path load(String filename) {
        this.init();
        return rootLocation.resolve(filename);
    }

    /**
     * 将文件缓存到磁盘
     *
     * @param fileEntity
     */
    @Override
    public void store(FileEntity fileEntity) {
        try {
            String fileName = fileEntity.getFileId() + "." + fileEntity.getFileData();
            if (fileEntity.getInputStream() != null) {
                //利用nio将文件从二进制流写入磁盘文件中
                Files.copy(fileEntity.getInputStream(), rootLocation.resolve(fileName),
                        StandardCopyOption.REPLACE_EXISTING);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
