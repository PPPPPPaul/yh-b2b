package com.lk.service;

import com.lk.pojo.YHResult;

public interface ItemSearchService {
    /**
     * 向solr服务器添加商品信息，以便查找商品信息
     * @return
     */
    YHResult importItem();
}
