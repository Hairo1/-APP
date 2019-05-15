package com.Hairo.service.impl;

import com.Hairo.mappers.randomImgMapper.RandomImgMapper;
import com.Hairo.pojo.RandomImg;
import com.Hairo.service.RandomImgService;
import com.Hairo.service.UploadService;
import com.Hairo.util.HairoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 项目名称： Hairo
 * 作 者   ： Hairo
 * 创建时间: 2019/5/12 16:50
 * 作用描述:
 */
@Service
public class RandomImgServiceImpl implements RandomImgService {

    @Autowired
    private RandomImgMapper randomImgMapper;
    @Autowired
    private UploadService uploadService;
    @Override
    public List<RandomImg> getAllRandomImg(Integer page, Integer pageSize) {
        if(pageSize == null || pageSize == 0){
            return null;
        }
        return randomImgMapper.selectAllRandomImg((page-1)*HairoUtil.PAGESIZE,pageSize);
    }

    @Override
    public Integer removeRandomImgById(Integer id,String fileUrl) {
        if(id == null || id == 0 || fileUrl == null || "".equals(fileUrl)){
            return 0;
        }
        uploadService.delFile(fileUrl);
        return randomImgMapper.delectRandomImg(id);
    }

    @Override
    public Integer addRandomImg(RandomImg randomImg) {
        if(randomImg==null){
            return 0;
        }
        return randomImgMapper.insertRandomImg(randomImg);
    }

    @Override
    public String selectRandomImg(Integer tag) {
        if(tag == null){
            tag = 0;
        }
        return randomImgMapper.selectRandomImg(tag);
    }
}
