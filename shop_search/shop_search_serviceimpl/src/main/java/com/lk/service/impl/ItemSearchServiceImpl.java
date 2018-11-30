package com.lk.service.impl;

import com.lk.mapper.ItemSearchMapper;
import com.lk.pojo.ItemSearch;
import com.lk.pojo.SearchResult;
import com.lk.pojo.YHResult;
import com.lk.service.ItemSearchService;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ItemSearchServiceImpl implements ItemSearchService {
    @Autowired
    private SolrServer solrServer;
    @Autowired
    private ItemSearchMapper itemSearchMapper;

    @Override
    public YHResult importItem() {
        List<ItemSearch> list = itemSearchMapper.getSearchItem();
        List<SolrInputDocument> docs = new ArrayList<SolrInputDocument>();
        for (ItemSearch is : list) {
            SolrInputDocument document = new SolrInputDocument();
            document.addField("id", is.getId());
            document.addField("item_title", is.getTitle());
            document.addField("item_sell_point", is.getSellPoint());
            document.addField("item_price", is.getPrice());
            document.addField("item_image", is.getImage());
            document.addField("item_category_name", is.getCategory_name());
            document.addField("item_desc", is.getItem_desc());
            docs.add(document);
        }
        try {
            solrServer.add(docs);
            solrServer.commit();
        } catch (Exception e) {
            e.printStackTrace();
            return YHResult.build(500, "添加失败");
        }
        return YHResult.ok();
    }

    @Override
    public SearchResult itemSearch(String keyWord) {
        try {
            SolrQuery query = new SolrQuery();
            query.setQuery(keyWord);
            query.set("df", "item_keywords");
            query.setHighlight(true);
            QueryResponse response = solrServer.query(query);
            SolrDocumentList results = response.getResults();
            List<ItemSearch> lis = new ArrayList<>();
            for (SolrDocument sd : results){
                ItemSearch is = new ItemSearch();
                is.setId((Long) sd.getFieldValue("item_id"));
                is.setTitle((String) sd.getFieldValue("item_title"));
                is.setSellPoint((String) sd.getFieldValue("item_sell_point"));
                is.setPrice((Long) sd.getFieldValue("item_price"));
                String image = (String) sd.getFieldValue("item_image");
                String s = image.substring(0, image.lastIndexOf(","));
                is.setImage(s);
                is.setCategory_name((String) sd.getFieldValue("item_category_name"));
                is.setItem_desc((String) sd.getFieldValue("item_desc"));
                lis.add(is);
            }
            SearchResult sr = new SearchResult();
            sr.setItemList(lis);
            sr.setQuery(keyWord);
            sr.setTotalRes(results.getNumFound());
            return sr;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

}
