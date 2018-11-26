package com.yh.dao;

public interface ItemCustomMapper {
    //批量删除
    void BatchDelItem(Long[] ids);
    //批量下架
    void BatchInstockItem(Long[] ids);
    //批量上架
    void BatchUnInstockItem(Long[] ids);
}
