package com.lk.portal.controller;

import com.lk.service.ItemCatService;
import com.yh.custom.ItemCatTree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ItemCatTreeController {
    @Autowired
    private ItemCatService itemCatService;
    @RequestMapping("/item/cat/all")
    public @ResponseBody ItemCatTree getItemTree(){
   return itemCatService.getItemsCatTree();
    }
}
