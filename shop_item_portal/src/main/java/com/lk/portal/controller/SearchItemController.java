package com.lk.portal.controller;

import com.lk.pojo.ItemSearch;
import com.lk.pojo.SearchResult;
import com.lk.service.ItemSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.UnsupportedEncodingException;
import java.util.List;

@Controller
public class SearchItemController {
    @Autowired
    private ItemSearchService itemSearchService;
    @RequestMapping("/search")
    public String searchItem(Model model,String keyword) throws UnsupportedEncodingException {
        keyword = new String(keyword.getBytes("ISO-8859-1"),"UTF-8");
        SearchResult result = itemSearchService.itemSearch(keyword);
        model.addAttribute("itemList",result.getItemList());
        model.addAttribute("query",result.getQuery());
        return "search";
    }
}
