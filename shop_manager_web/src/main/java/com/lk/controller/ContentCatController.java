package com.lk.controller;

import com.lk.pojo.EasyUITreeNode;
import com.lk.pojo.TbContentCat;
import com.lk.pojo.YHResult;
import com.lk.service.ContentCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class ContentCatController {
    @Autowired
    private ContentCatService contentCatService;

    @RequestMapping("/content/category/list")
    @ResponseBody
    public List<EasyUITreeNode> getItemContentTree(@RequestParam(value="id",defaultValue = "0") Long pid){
        return contentCatService.findContentCatByPid(pid);
    }
    @RequestMapping("/content/category/create")
    @ResponseBody
    public YHResult addContentCat(TbContentCat tbContentCat){
        return contentCatService.addContentCat(tbContentCat);
    }
    @RequestMapping("/content/category/delete")
    @ResponseBody
    public YHResult delContentCat(Long id){
        return contentCatService.delContentCatByid(id);
    }
}
