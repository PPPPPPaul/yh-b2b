package com.lk.pojo;

import java.io.Serializable;
import java.util.List;

public class SearchResult implements Serializable {
    private List<ItemSearch> itemList;
    private String query;
    private Long totalRes;
    private Integer totalPages;
    private Integer page;

    public List<ItemSearch> getItemList() {
        return itemList;
    }

    public void setItemList(List<ItemSearch> itemList) {
        this.itemList = itemList;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public Long getTotalRes() {
        return totalRes;
    }

    public void setTotalRes(Long totalRes) {
        this.totalRes = totalRes;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }
}