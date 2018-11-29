package com.lk.controller;

import com.lk.pojo.TbContent;
import com.lk.pojo.YHResult;
import com.lk.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ContentController {
    @Autowired
    private ContentService contentService;
    @RequestMapping("/content/add")
    @ResponseBody
    public YHResult addContent(TbContent tbContent){
       return contentService.addContent(tbContent);
    }
}