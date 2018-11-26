package com.lk.service;

import com.lk.pojo.EasyUIDatagrid;
import com.lk.pojo.YHResult;
import com.yh.pojo.TbItem;


public interface ItemService {
    //添加商品
    YHResult addItem(TbItem item,String desc,String itemParams);
    //获取商品列表
    EasyUIDatagrid getItemsList(Integer page, Integer rows);
    //删除商品
    YHResult delItem(Long[] ids);
    //商品下架
    YHResult instockItem(Long[] ids);
    //商品上架
    YHResult unInstockItem(Long[] ids);
}
