package com.lk.dao;

import com.lk.pojo.TbContentCat;

import java.util.List;

public interface TbContentCatMapper {
    List<TbContentCat> selectbypid(Long pid);
}
