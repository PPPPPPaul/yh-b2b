package com.lk.controller;

import com.lk.pojo.EasyUITreeNode;
import com.lk.service.ContentCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class ItemContentController {
    @Autowired
    private ContentCatService contentCatService;

    @RequestMapping("/content/category/list")
    @ResponseBody
    public List<EasyUITreeNode> getItemContentTree(@RequestParam(value="id",defaultValue = "0") Long pid){
        return contentCatService.findContentCatByPid(pid);
    }
}
