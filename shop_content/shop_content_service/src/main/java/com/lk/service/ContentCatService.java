package com.lk.service;

import com.lk.pojo.EasyUITreeNode;

import java.util.List;

public interface ContentCatService {
    List<EasyUITreeNode> findContentCatByPid(Long pid);
}
