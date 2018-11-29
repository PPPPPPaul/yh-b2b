package com.lk.service.impl;

import com.lk.mapper.ItemSearchMapper;
import com.lk.pojo.ItemSearch;
import com.lk.pojo.YHResult;
import com.lk.service.ItemSearchService;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemSearchServiceImpl  implements ItemSearchService {
    @Autowired
    private SolrServer solrServer;
    @Autowired
    private ItemSearchMapper itemSearchMapper;

    @Override
    public YHResult importItem() {
        List<ItemSearch> list = itemSearchMapper.getSearchItem();
        for (ItemSearch is:list){
            SolrInputDocument document = new SolrInputDocument();
            document.addField("id",is.getId());
            document.addField("item_title",is.getTitle());
            document.addField("item_sell_point",is.getSellPoint());
            document.addField("item_price",is.getPrice());
            document.addField("item_image",is.getImage());
            document.addField("item_category_name",is.getCategory_name());
            document.addField("item_desc",is.getItem_desc());
            try {
                solrServer.add(document);
                solrServer.commit();
            } catch (Exception e) {
                e.printStackTrace();
                return YHResult.build(500,"添加失败");
            }
        }
        return YHResult.ok();
    }
}
