package com.Hairo.pojo;

/**
 * 项目名称： Hairo
 * 作 者   ： Hairo
 * 创建时间: 2019/5/12 16:34
 * 作用描述:
 * 文章标题图片+回复头像=随机
 */

public class RandomImg {

    private Integer id ;

    private Integer tag;
    private String imgUrl;
    private String imgDescribe;//图片描述


    @Override
    public String toString() {
        return "RandomImg{" +
                "id=" + id +
                ", tag=" + tag +
                ", imgUrl='" + imgUrl + '\'' +
                ", imgDescribe='" + imgDescribe + '\'' +
                '}';
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setTag(Integer tag) {
        this.tag = tag;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public void setImgDescribe(String imgDescribe) {
        this.imgDescribe = imgDescribe;
    }

    public Integer getId() {
        return id;
    }

    public Integer getTag() {
        return tag;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public String getImgDescribe() {
        return imgDescribe;
    }
}
