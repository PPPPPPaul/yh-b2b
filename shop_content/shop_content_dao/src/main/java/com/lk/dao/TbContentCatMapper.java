package com.lk.dao;

import com.lk.pojo.TbContentCat;

import java.util.List;

public interface TbContentCatMapper {
    List<TbContentCat> selectbypid(Long pid);
    TbContentCat selectbyid(Long id);
    Long insertcontentcat(TbContentCat tbContentCat);
    void updatecontentcatbykey(TbContentCat tbContentCat);
    void delcontentcatbyid(Long id);
}
