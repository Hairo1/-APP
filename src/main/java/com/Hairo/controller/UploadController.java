package com.Hairo.controller;

import com.Hairo.pojo.SysUsers;
import com.Hairo.service.PermissionService;
import com.Hairo.service.RandomImgService;
import com.Hairo.service.UploadService;
import com.Hairo.util.HairoUtil;
import com.github.tobato.fastdfs.domain.MateData;
import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.exception.FdfsServerException;
import com.github.tobato.fastdfs.exception.FdfsUnsupportStorePathException;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
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
@Api(value = "文件上下传Controller",tags = "Fastfds分布式文件操作接口")
@RestController
@RequestMapping("/api/upload/")
public class UploadController {


    @Autowired
    private UploadService uploadService;
    @Autowired
    private PermissionService permissionService;
    /**
     * 上传文章内容图片到FastDfs
     * @param file
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    @ApiOperation(value = "上传文章内容中的图片")
    @ApiImplicitParam(name = "file",value = "待上传的图片",required = true,dataType = "MultipartFile")
    @RequestMapping(value = "/uploadArticleImg",method = RequestMethod.POST)
    public Map<String,Object> uploadArticleImg(@RequestParam( required = true)MultipartFile file, HttpServletRequest request, HttpServletResponse response) throws IOException {
        return uploadService.uploadArticlesImg(file);
    }

    /**
     * 上传随机图片(随机头像和文章标题图)
     * @param file 图片
     * @param request
     * @param response
     * @param tag 0->上传头像图片 1-->上传文章标题图片 其他文章标题图片
     * @return
     * @throws IOException
     */
    @ApiOperation(value = "上传随机图片",notes = "随机文章标题图片和随机用户头像图片,只有登陆的管理员才能操作")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "file", value = "待上传的图片", required = true, dataType = "MultipartFile"),
            @ApiImplicitParam(name = "sysUsers", value = "上传图片的管理员", required = true, dataType = "SysUsers")
    })

    @RequestMapping(value = "/uploadRandomImg",method = RequestMethod.POST)
    public Map<String,Object> uploadRandomImg(@RequestParam( required = true)MultipartFile file, HttpServletRequest request,
                                              HttpServletResponse response, @RequestParam( required = true)Integer tag,
                                              SysUsers sysUsers) throws IOException {
        SysUsers user = HairoUtil.verifyUser(sysUsers);
        if(user == null || !user.getU_password().equals(sysUsers.getU_password())){
            return HairoUtil.result(3);
        }
        if(HairoUtil.permissionQuery(user.getU_id())){
            return uploadService.uploadRandowImg(file,tag);
        }else {
            return HairoUtil.result(2);
        }

    }

    /**
     * 删除FastDfs文件系统的文件，在随机图片controller中的删除资源实现了该非法
     * @return

    @RequestMapping(value = "/",method = RequestMethod.DELETE)
    public Map  delFile(@RequestParam( required = true)String fileUrl)  {
        return uploadService.delFile(fileUrl);
    }
     */


}
