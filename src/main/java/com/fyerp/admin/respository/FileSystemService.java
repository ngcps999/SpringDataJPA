/*
 * 作者：xuda
 * 创建时间：18-7-5 上午10:41
 * 模块名称：admin
 */

package com.fyerp.admin.respository;

import com.fyerp.admin.domain.FileEntity;

import java.nio.file.Path;

public interface FileSystemService {

    /**
     * 初始化存储路径
     */
    void init();

    /**
     * 从磁盘加载指定文件
     * @param filename
     * @return
     */
    Path load(String filename);

    /**
     * 将文件缓存到磁盘
     * @param fileEntity
     */
    void store(FileEntity fileEntity);
}
