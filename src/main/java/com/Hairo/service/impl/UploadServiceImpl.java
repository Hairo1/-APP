package com.Hairo.service.impl;

import com.Hairo.pojo.RandomImg;
import com.Hairo.service.RandomImgService;
import com.Hairo.service.UploadService;
import com.Hairo.util.HairoUtil;
import com.github.tobato.fastdfs.domain.MateData;
import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.exception.FdfsServerException;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 项目名称： Hairo
 * 作 者   ： Hairo
 * 创建时间: 2019/5/12 17:14
 * 作用描述:
 */
@Service
public class UploadServiceImpl implements UploadService {

    @Autowired
    private RandomImgService randomImgService;
    @Autowired
    private FastFileStorageClient fastFileStorageClient;


    @Override
    public Map uploadArticlesImg(MultipartFile file) {
        Map<String,Object> map = new HashMap<>();
        if(file == null){
            return HairoUtil.result(-1);
        }
        if( file.getSize()>1048576*19){
            return HairoUtil.result(0);
        }
        String url = uploadFileUtil(file);
        map.put("success",1);
        map.put("message","");
        map.put("url","http://www.hailuo888.com/"+url);
        return map;
    }

    @Override
    public Map uploadRandowImg(MultipartFile file, Integer tag) {

        Map<String,Object> map = new HashMap<>();
        if(file == null){
            return HairoUtil.result(-1);
        }
        if( file.getSize()>1048576*19){
            return HairoUtil.result(0);
        }
        String url = uploadFileUtil(file);
        if(url == null){
            return HairoUtil.result(0);
        }
        RandomImg randomImg = new RandomImg();
        randomImg.setImgUrl(url);
        randomImg.setTag(tag);
        if(randomImgService.addRandomImg(randomImg)>0){
            return HairoUtil.result(1);
        }else {
            return HairoUtil.result(0);
        }
    }

    @Override
    public Map delFile(String fileUrl) {
        Map map = new HashMap();
        try {
            fastFileStorageClient.deleteFile(fileUrl);
            return HairoUtil.result(1);
        }catch (FdfsServerException exception){
            map.put("success",0);
            map.put("message","资源删除失败,资源不存在或已经被删除!");
            return map;
        }
    }


    private String uploadFileUtil(MultipartFile file){
        if(file == null){
            return null;
        }

        Set<MateData> mataData = new HashSet<MateData>();
        mataData.add(new MateData("author", "Hairo"));
        mataData.add(new MateData("description", file.getName()));

        StorePath storePath = null;
        try {
            storePath = fastFileStorageClient.uploadFile(file.getInputStream(), file.getSize(), FilenameUtils.getExtension(file.getOriginalFilename()), mataData);
        } catch (IOException e) {

        }

        return storePath.getFullPath();
    }
}
