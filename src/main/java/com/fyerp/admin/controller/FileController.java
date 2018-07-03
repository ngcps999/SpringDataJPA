/*
 * 作者：xuda
 * 创建时间：18-5-10 下午2:24
 * 模块名称：admin
 */

package com.fyerp.admin.controller;

import com.fyerp.admin.domain.FileInfo;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/upload")
@Api(value = "FileController", description = "文件上传下载Api")
public class FileController {
    //存放文件的路径 ，这里直接放到controller文件夹下

    File file = new File("/data/tomcat/files");

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public void testUploadFile(HttpServletRequest req, MultipartHttpServletRequest multiReq) throws IOException {
        FileOutputStream fos = new FileOutputStream(file);
        FileInputStream fs = (FileInputStream) multiReq.getFile("file").getInputStream();
        byte[] buffer = new byte[1024];
        int len=0;
        while ((len = fs.read(buffer)) != -1) {
            fos.write(buffer, 0, len);
        }
        fos.close();
        fs.close();
    }

    /**
     * 文件的下载
     */
    @GetMapping("/{id}") //id为时间戳
    public void download(@PathVariable("id") String id, HttpServletRequest request, HttpServletResponse response) {

        try (
                //jdk7新特性，可以直接写到try()括号里面，java会自动关闭
                InputStream inputStream = new FileInputStream(file);
                OutputStream outputStream = response.getOutputStream()
        ) {
            //指明为下载
            response.setContentType("application/x-download");
            String fileName = "test.txt";
            response.addHeader("Content-Disposition", "attachment;fileName=" + fileName);   // 设置文件名


            //把输入流copy到输出流
            IOUtils.copy(inputStream, outputStream);

            outputStream.flush();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
