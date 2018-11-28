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
        for (TbContentCat cc : list) {
            EasyUITreeNode node = new EasyUITreeNode();
            node.setText(cc.getName());
            node.setId(cc.getId());
            cc.getIsParent();
            node.setState(cc.getIsParent() == true ? "closed" : "open");
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
        tbContentCat.setIsParent(false);
        if(tbContentCat.getParentId()>0) {
            TbContentCat parentCat = new TbContentCat();
            parentCat.setId(tbContentCat.getParentId());
            parentCat.setIsParent(true);
            tbContentCatMapper.updatecontentcatbykey(parentCat);
        }
        tbContentCatMapper.insertcontentcat(tbContentCat);
        return YHResult.ok(200,tbContentCat.getId());
    }

    @Override
    public YHResult delContentCatByid(Long id) {
        try {
            TbContentCat tbContentCat = tbContentCatMapper.selectbyid(id);
            delContentCatClid(id);
            updatedelcontentcatparent(tbContentCat.getParentId());
            return YHResult.build(200,"删除成功");
        }catch (Exception e){
            return YHResult.build(500,"删除失败");
        }
    }

    /**
     * 递归删除该节点的所有子节点
     * @param id
     */
    private void delContentCatClid(Long id) {
        TbContentCat tbContentCat = tbContentCatMapper.selectbyid(id);
        if (tbContentCat.getIsParent()) {
            List<TbContentCat> cats = tbContentCatMapper.selectbypid(id);
            tbContentCatMapper.delcontentcatbyid(id);
            for (TbContentCat cat : cats) {
                delContentCatClid(cat.getId());
            }
        }else {
            tbContentCatMapper.delcontentcatbyid(id);
        }
    }

    /**
     * 删除子节点后需要更新父节点的IsParent信息，此时需要先查询父节点下是否还有其他子节点，没有就可以将IsParent设置为false，如果还有其他子节点，则父节点还不是没有儿子
     * @param pid
     */
    private void updatedelcontentcatparent(Long pid){
        List<TbContentCat> list = tbContentCatMapper.selectbypid(pid);
        if (list==null || list.size()<=0) {
            TbContentCat tbContentCat = new TbContentCat();
            tbContentCat.setId(pid);
            tbContentCat.setIsParent(false);
            tbContentCatMapper.updatecontentcatbykey(tbContentCat);
        }
    }
}
