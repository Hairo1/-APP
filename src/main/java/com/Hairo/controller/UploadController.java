package com.Hairo.controller;

import com.github.tobato.fastdfs.domain.MateData;
import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 项目名称： Hairo
 * 作 者   ： Hairo
 * 创建时间: 2019/4/14 18:54
 * 作用描述:
 * 文件上传下载删除....
 */
@RestController
@RequestMapping("/api/upload/")
public class UploadController {


    @Autowired
    public FastFileStorageClient fastFileStorageClient;
    //上传图片
    @RequestMapping(value = "/uploadImg",method = RequestMethod.POST)
    public Map<String,Object> saveImg(@RequestParam( required = true)MultipartFile file, HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 设置文件信息
        Set<MateData> mataData = new HashSet<MateData>();
        mataData.add(new MateData("author", "Hairo"));
        mataData.add(new MateData("description", file.getName()));
        StorePath storePath = fastFileStorageClient.uploadFile(file.getInputStream(), file.getSize(), FilenameUtils.getExtension(file.getOriginalFilename()), mataData);
        Map<String,Object> map = new HashMap<>();
        map.put("success",1);
        map.put("message","");
        map.put("url","http://192.168.153.132/"+storePath.getFullPath());

        return map;
    }
}
