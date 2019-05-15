package com.Hairo.mappers.randomImgMapper;

import com.Hairo.pojo.RandomImg;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Random;

public interface RandomImgMapper {


    /**
     * 删除随机图片byID
     * @param id 随机图片ID
     * @return
     */
    public Integer delectRandomImg(Integer id);

    /**
     *插入随机图片
     * @param randomImg
     * @return
     */
    public Integer insertRandomImg(RandomImg randomImg);


    /**
     * 获取所有随机图片/分页
     * @param page 第page页
     * @param pageSize 显示pageSize条数据
     * @return
     */
    public List<RandomImg> selectAllRandomImg(@Param("page") int page,@Param("pageSize") int pageSize);

    /**
     * 返回随机一张图片 tag0为用户头像图片 1为文章使用
     * @param tag
     * @return
     */
    public String selectRandomImg(@Param("tag") Integer tag);
}
