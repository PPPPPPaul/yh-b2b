package com.lk.controller;

import com.lk.pojo.EasyUIDatagrid;
import com.lk.pojo.YHResult;
import com.lk.service.ItemService;
import com.yh.pojo.TbItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class ItemController {
    @Value("${IMG_URL}")
    private String img_addr;
    @Autowired
    private ItemService itemService;
    @RequestMapping("/item/save")
    @ResponseBody
    public YHResult addItem(TbItem item, String desc, String itemParams){
        return itemService.addItem(item,desc,itemParams);
    }
    @RequestMapping("/item/list")
    @ResponseBody
    public EasyUIDatagrid getItemList(Integer page, Integer rows){
        return itemService.getItemsList(page,rows);
    }
    @RequestMapping("/item/delete")
    @ResponseBody
    public YHResult delItem(Long[] ids){
        return itemService.delItem(ids);
    }
    @RequestMapping("/item/instock")
    @ResponseBody
    public YHResult instockItem(Long[] ids){
        return itemService.instockItem(ids);
    }
    @RequestMapping("/item/reshelf")
    @ResponseBody
    public YHResult unInstockItem(Long[] ids){
        return itemService.unInstockItem(ids);
    }
}