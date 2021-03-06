/*
 * 作者：xuda
 * 创建时间：18-5-10 下午2:24
 * 模块名称：admin
 */

package com.fyerp.admin.controller;

import com.fyerp.admin.domain.FileInfo;
import com.fyerp.admin.domain.Result;
import com.fyerp.admin.enums.ResultEnum;
import com.fyerp.admin.service.FileInfoService;
import com.fyerp.admin.utils.FileUtil;
import com.fyerp.admin.utils.ResultUtil;
import io.swagger.annotations.Api;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;

@RestController
@RequestMapping("/file")
@Api(value = "FileController", description = "文件上传下载Api")
public class FileController {
    private static final Logger log = LoggerFactory.getLogger(FileController.class);
    @Value("${file.path}")
    String filePath;
//
//    @Value("${file.name}")
//    String fileName;// 文件名
    String path;

    @Autowired
    private FileInfoService fileInfoService;


    @PostMapping(value = "/upload")
    public Result upload(@RequestParam("file") MultipartFile file) {
        try {
            if (file.isEmpty()) {
                return ResultUtil.error(ResultEnum.PARAM_ERROR);
            }
            // 获取文件名
            String fileName = file.getOriginalFilename();
            // 获取文件的后缀名
            String suffixName = fileName.substring(fileName.lastIndexOf("."));
            log.info("文件的后缀名为：" + suffixName);
            fileName = this.getFileName(filePath,suffixName);
            // 设置文件存储路径
//             = "/Users/xuda/Downloads/";
            path = filePath + fileName+suffixName;
            log.info("文件的路径为：" + path);
            File dest = new File(path);
            log.info(path+" 是否存在  "+dest.exists());
            // 检测是否存在目录
            if (!dest.getParentFile().exists()) {
                dest.getParentFile().mkdirs();// 新建文件夹
            }
            file.transferTo(dest);// 文件写入
            FileInfo fileInfo = new FileInfo(filePath,fileName+suffixName);
            fileInfoService.save(fileInfo);
            return ResultUtil.success(fileInfo);
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResultUtil.error(ResultEnum.PARAM_ERROR);
    }
//
//    @PostMapping("/batch")
//    public String handleFileUpload(HttpServletRequest request) {
//        List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("file");
//        MultipartFile file = null;
//        BufferedOutputStream stream = null;
//        for (int i = 0; i < files.size(); ++i) {
//            file = files.get(i);
////            String filePath = "/Users/xuda/Downloads/";
//            if (!file.isEmpty()) {
//                try {
//                    byte[] bytes = file.getBytes();
//                    stream = new BufferedOutputStream(new FileOutputStream(
//                            new File(filePath + file.getOriginalFilename())));//设置文件路径及名字
//                    stream.write(bytes);// 写入
//                    stream.close();
//                    fileInfoService.save(new FileInfo(filePath,file.getOriginalFilename()));
//                } catch (Exception e) {
//                    stream = null;
//                    return "第 " + i + " 个文件上传失败 ==> "
//                            + e.getMessage();
//                }
//            } else {
//                return "第 " + i
//                        + " 个文件上传失败因为文件为空";
//            }
//        }
//        return "上传成功";
//    }

    @GetMapping("/download")
    public Result downloadFile(@RequestParam("path")String path,
                               @RequestParam("fileName")String fileName,
                               HttpServletResponse response) {
        if (fileName != null) {
            //设置文件路径
            File file = new File(path, fileName);
            if (file.exists()) {
                response.setContentType("application/force-download");// 设置强制下载不打开
                response.addHeader("Content-Disposition", "attachment;fileName=" + fileName);// 设置文件名
                byte[] buffer = new byte[1024];
                FileInputStream fis = null;
                BufferedInputStream bis = null;
                try {
                    fis = new FileInputStream(file);
                    bis = new BufferedInputStream(fis);
                    OutputStream os = response.getOutputStream();
                    int i = bis.read(buffer);
                    while (i != -1) {
                        os.write(buffer, 0, buffer.length);
                        i = bis.read(buffer);
                    }
                    log.info("路径："+file.getPath());
                    return ResultUtil.success();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (bis != null) {
                        try {
                            bis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (fis != null) {
                        try {
                            fis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        return ResultUtil.error(ResultEnum.DOWNLOAD_FAILED);
    }

    private String getFileName(String path,String suffix){
        String result = FileUtil.createFileName();
        File file = new File(path+result+suffix);
        if(file.exists()){
            return getFileName(path,suffix);
        }
        return result;
    }

}
