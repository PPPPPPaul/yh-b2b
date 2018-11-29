package com.lk.service;

import com.lk.customer.pojo.AD1Node;
import com.lk.pojo.TbContent;
import com.lk.pojo.YHResult;

import java.util.List;

public interface ContentService {
    YHResult addContent(TbContent tbContent);
    List<AD1Node> getBigAd(Long cid);
}
