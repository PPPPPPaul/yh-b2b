package com.lk.service;

import com.lk.pojo.EasyUITreeNode;
import com.lk.pojo.TbContentCat;
import com.lk.pojo.YHResult;

import java.util.List;

public interface ContentCatService {
    //获取主页内容类目
    List<EasyUITreeNode> findContentCatByPid(Long pid);
    //添加内容节点
    YHResult addContentCat(TbContentCat tbContentCat);
}
