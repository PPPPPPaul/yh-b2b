package com.lk.service;

import com.lk.pojo.ItemSearch;
import com.lk.pojo.SearchResult;
import com.lk.pojo.YHResult;

import java.util.List;

public interface ItemSearchService {
    /**
     * 向solr服务器添加商品信息，以便查找商品信息
     * @return
     */
    YHResult importItem();
    SearchResult itemSearch(String keyWord);
}
