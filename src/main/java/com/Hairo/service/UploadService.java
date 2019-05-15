package com.Hairo.service;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@Transactional(rollbackFor = Exception.class)//统一事务
public interface UploadService {


    public Map uploadArticlesImg(MultipartFile file);

    public Map uploadRandowImg(MultipartFile file,Integer tag);

    public Map delFile(String fileUrl);
}
