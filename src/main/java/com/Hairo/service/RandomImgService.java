package com.Hairo.service;


import com.Hairo.pojo.RandomImg;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(rollbackFor = Exception.class)//统一事务
public interface RandomImgService {

    public List<RandomImg> getAllRandomImg(Integer page, Integer pageSize);

    public Integer removeRandomImgById(Integer id,String fileUrl);

    public Integer addRandomImg(RandomImg randomImg);

    public String selectRandomImg( Integer tag);
}
