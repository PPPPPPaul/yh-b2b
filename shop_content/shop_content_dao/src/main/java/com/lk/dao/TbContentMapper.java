package com.lk.dao;

import com.lk.customer.pojo.AD1Node;
import com.lk.pojo.TbContent;

import java.util.List;

public interface TbContentMapper {
    void insert(TbContent tbContent);
    List<AD1Node> selectContentByCatId(Long cid);
}
