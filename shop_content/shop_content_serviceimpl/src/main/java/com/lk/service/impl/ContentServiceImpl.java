package com.lk.service.impl;

import com.lk.customer.pojo.AD1Node;
import com.lk.dao.TbContentMapper;
import com.lk.pojo.TbContent;
import com.lk.pojo.YHResult;
import com.lk.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ContentServiceImpl implements ContentService {

    @Autowired
    private TbContentMapper tbContentMapper;
    @Override
    public YHResult addContent(TbContent tbContent) {
        try {
            Date date = new Date();
            tbContent.setCreated(date);
            tbContent.setUpdated(date);
            tbContentMapper.insert(tbContent);
            return YHResult.build(200,"成功");
        }catch (Exception e){
            return YHResult.build(500,"失败");
        }
    }

    @Override
    public List<AD1Node> getBigAd(Long cid) {
        return tbContentMapper.selectContentByCatId(cid);
    }
}
