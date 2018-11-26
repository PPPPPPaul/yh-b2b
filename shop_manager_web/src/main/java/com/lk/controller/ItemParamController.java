package com.lk.controller;

import com.lk.pojo.EasyUIDatagrid;
import com.lk.pojo.YHResult;
import com.lk.service.ItemParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
public class ItemParamController {
    @Autowired
    private ItemParamService itemParamService;

    @RequestMapping("/item/param/query/itemcatid/{cid}")
    @ResponseBody
    public YHResult checkItemParam(@PathVariable Long cid){
        return itemParamService.checkItemParam(cid);
    }
    @RequestMapping("/item/param/save/{cid}")
    @ResponseBody
    public YHResult addItemParm(@PathVariable Long cid,String paramData){
        return itemParamService.addItemParam(cid,paramData);
    }
    @RequestMapping("/item/param/list")
    @ResponseBody
    public EasyUIDatagrid getItemParm(Integer page, Integer rows){
        return itemParamService.getItemParamList(page, rows);
    }
    @RequestMapping("/item/param/delete")
    @ResponseBody
    public YHResult delItemParam(int[] ids){
        return itemParamService.delItemParam(ids);
    }
}
