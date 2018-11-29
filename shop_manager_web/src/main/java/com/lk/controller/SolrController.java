package com.lk.controller;

import com.lk.pojo.YHResult;
import com.lk.service.ItemSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SolrController {
    @Autowired
    private ItemSearchService itemSearchService;
    @RequestMapping("/solr/item/import")
    @ResponseBody
    public YHResult importSearchItem(){
        return itemSearchService.importItem();
    }
}
