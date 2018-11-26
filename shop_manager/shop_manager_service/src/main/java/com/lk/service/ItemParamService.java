package com.lk.service;

import com.lk.pojo.EasyUIDatagrid;
import com.lk.pojo.YHResult;

import java.util.Arrays;

public interface ItemParamService {
    YHResult checkItemParam(Long cid);
    YHResult addItemParam(Long cid,String paramData);
    EasyUIDatagrid getItemParamList(Integer currentPage, Integer pageSize);
    YHResult delItemParam(int[] ids);
}
