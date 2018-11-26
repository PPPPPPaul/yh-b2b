package com.lk.controller;

import com.lk.pojo.PictureResult;
import com.lk.utils.PictureUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class PictrueController {
    @Value("${IMG_URL}")
    private String img_addr;
    @RequestMapping("/pic/upload")

    public  @ResponseBody PictureResult picUpload(MultipartFile uploadFile){
        return PictureUtil.uploadFile(uploadFile,img_addr);
    }
}
