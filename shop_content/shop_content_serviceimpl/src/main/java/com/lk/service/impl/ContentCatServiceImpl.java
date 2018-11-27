package com.lk.service.impl;

import com.lk.dao.TbContentCatMapper;
import com.lk.pojo.EasyUITreeNode;
import com.lk.pojo.TbContentCat;
import com.lk.service.ContentCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
}
