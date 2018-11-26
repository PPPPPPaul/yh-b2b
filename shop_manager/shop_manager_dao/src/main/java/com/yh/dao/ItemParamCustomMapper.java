package com.yh.dao;

import com.yh.custom.TbItemParamList;

import java.util.List;

public interface ItemParamCustomMapper {
    List<TbItemParamList> selectItemParamList();
    void BatchDelItemParam(int[] ids);
}
