package com.lk.service.impl;

import com.lk.dao.TbContentCatMapper;
import com.lk.pojo.EasyUITreeNode;
import com.lk.pojo.TbContentCat;
import com.lk.pojo.YHResult;
import com.lk.service.ContentCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ContentCatServiceImpl implements ContentCatService {
    @Autowired
    private TbContentCatMapper tbContentCatMapper;
    @Override
    public List<EasyUITreeNode> findContentCatByPid(Long pid) {
        List<TbContentCat> list = tbContentCatMapper.selectbypid(pid);
        List<EasyUITreeNode> res = new ArrayList<>();
        for (TbContentCat cc : list){
            EasyUITreeNode node = new EasyUITreeNode();
            node.setText(cc.getName());
            node.setId(cc.getId());
            cc.getIsParent();
            node.setState(cc.getIsParent()==true?"closed":"open");
            res.add(node);
        }
        return res;
    }

    @Override
    public YHResult addContentCat(TbContentCat tbContentCat) {
        Date date = new Date();
        tbContentCat.setCreated(date);
        tbContentCat.setUpdated(date);
        tbContentCat.setStatus(1);
        tbContentCat.setSortOrder(1);
        if(tbContentCat.getParentId()>0){
            TbContentCat parentCat = new TbContentCat();
            parentCat.setId(tbContentCat.getParentId());
            parentCat.setIsParent(true);
            tbContentCatMapper.updatecontentcatbykey(parentCat);
            tbContentCat.setIsParent(false);
        }else{
            tbContentCat.setIsParent(true);
        }
        tbContentCatMapper.insertcontentcat(tbContentCat);
        return YHResult.ok();
    }

    @Override
    public YHResult delContentCat(TbContentCat tbContentCat) {
        tbContentCat.setStatus(0);
        Long pid = tbContentCat.getParentId();
        TbContentCat parentContentCat = tbContentCatMapper.selectbyid(pid);
        parentContentCat.setIsParent(false);
        tbContentCatMapper.updatecontentcatbykey(parentContentCat);
        tbContentCatMapper.updatecontentcatbykey(tbContentCat);
        return null;
    }
}
