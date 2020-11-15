package com.baizhi.controller;

import com.baizhi.entity.User;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * Created by HIAPAD on 2019/10/31.
 */
@Controller
@RequestMapping("file")
public class FileController {


    @Autowired
    private User user;

    //处理问价下载操作
    @RequestMapping("down")
    public void download(String fileName, HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println(user);
        //根据相当路径获取绝对路径
        //ServletContext servletContext = request.getSession().getServletContext();
        String realPath = request.getServletContext().getRealPath("/files/download");//2.5规范中不能这么使用
        //获取文件名,根据文件名去指定的目录读取文件
        FileInputStream is = new FileInputStream(new File(realPath, fileName));
        //设置下载时响应头
        response.setHeader("content-disposition","inline;fileName="+ URLEncoder.encode(fileName,"UTF-8"));
        //通过响应流响应即可
        ServletOutputStream outputStream = response.getOutputStream();
        //流的复制
        IOUtils.copy(is,outputStream);
        IOUtils.closeQuietly(is);
        IOUtils.closeQuietly(outputStream);

    }

    //处理文件上传操作
    @RequestMapping("upload")
    public String  upload(MultipartFile aaa, HttpServletRequest request) throws IOException {
        System.out.println("文件名: "+aaa.getOriginalFilename());

        System.out.println("文件类型: "+aaa.getContentType());

        System.out.println("文件大小: "+aaa.getSize());
        //根据相对获取绝对路径
        String realPath = request.getSession().getServletContext().getRealPath("/files");
        //创建时间文件夹
        String format = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        File file = new File(realPath, format);
        if(!file.exists()) file.mkdirs();
        //获取文件后缀
        String extension = FilenameUtils.getExtension(aaa.getOriginalFilename());

        //根据文件后缀动态获取文件类型

        String mimeType = request.getSession().getServletContext().getMimeType("."+extension);
        System.out.println("动态根据后缀获取文件类型: "+mimeType);

        String newFileNamePrefix = UUID.randomUUID().toString().replace("-","")+
                  new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
        String newFileName = newFileNamePrefix+"."+extension;
        //处理上传操作
        aaa.transferTo(new File(file,newFileName));
        return "redirect:/upload.jsp";
    }
}
