package com.lk.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lk.pojo.EasyUIDatagrid;
import com.lk.pojo.YHResult;
import com.lk.service.ItemService;
import com.lk.utils.IdUtils;
import com.yh.dao.ItemCustomMapper;
import com.yh.mapper.TbItemDescMapper;
import com.yh.mapper.TbItemMapper;
import com.yh.mapper.TbItemParamItemMapper;
import com.yh.pojo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {
    @Autowired
    private TbItemParamItemMapper tbItemParamItemMapper;
    @Autowired
    private TbItemDescMapper tbItemDescMapper;
    @Autowired
    private TbItemMapper tbItemMapper;
    @Autowired
    private ItemCustomMapper itemCustomMapper;
    @Override
    public YHResult addItem(TbItem item, String desc, String itemParams) {
        try {
            Long id = IdUtils.genId();
            Date date = new Date();
            item.setId(id);
            item.setCreated(date);
            item.setUpdated(date);
            item.setStatus((byte) 1);
            tbItemMapper.insert(item);

            TbItemDesc tbItemDesc = new TbItemDesc();
            tbItemDesc.setItemId(id);
            tbItemDesc.setCreated(date);
            tbItemDesc.setUpdated(date);
            tbItemDesc.setItemDesc(desc);
            tbItemDescMapper.insert(tbItemDesc);

            TbItemParamItem tbItemParamItem = new TbItemParamItem();
            tbItemParamItem.setCreated(date);
            tbItemParamItem.setUpdated(date);
            tbItemParamItem.setItemId(id);
            tbItemParamItem.setParamData(itemParams);
            tbItemParamItemMapper.insert(tbItemParamItem);
            return YHResult.ok();
        }catch (Exception e){
            return YHResult.build(500,"商品添加失败");
        }
    }

    @Override
    public EasyUIDatagrid getItemsList(Integer page, Integer rows) {
        PageHelper.startPage(page,rows);
        TbItemExample example = new TbItemExample();
        List<TbItem> items = tbItemMapper.selectByExample(example);
        PageInfo<TbItem> pi = new PageInfo<>(items);
        EasyUIDatagrid es = new EasyUIDatagrid();
        es.setTotal(pi.getTotal());
        es.setRows(items);
        return es;
    }

    @Override
    public YHResult getItemDescById(Long id) {
        TbItemDescExample example = new TbItemDescExample();
        TbItemDescExample.Criteria criteria = example.createCriteria();
        criteria.andItemIdEqualTo(id);
        List<TbItemDesc> descs = tbItemDescMapper.selectByExampleWithBLOBs(example);
        String ii = descs.get(0).getItemDesc();
        return YHResult.ok(200,descs.get(0));

    }

    @Override
    public YHResult getItemParamById(Long id) {
        TbItemParamItemExample example = new TbItemParamItemExample();
        TbItemParamItemExample.Criteria criteria = example.createCriteria();
        criteria.andItemIdEqualTo(id);
        List<TbItemParamItem> items = tbItemParamItemMapper.selectByExampleWithBLOBs(example);
        if (items!=null&&items.size()>0){
            return YHResult.ok(200,items.get(0));
        }
        return YHResult.build(500,"未找到商品规格");
    }

    @Override
    public YHResult delItem(Long[] ids) {
        itemCustomMapper.BatchDelItem(ids);
        return YHResult.ok();
    }

    @Override
    public YHResult instockItem(Long[] ids) {
        itemCustomMapper.BatchInstockItem(ids);
        return YHResult.ok();
    }

    @Override
    public YHResult unInstockItem(Long[] ids) {
        itemCustomMapper.BatchUnInstockItem(ids);
        return YHResult.ok();
    }

    @Override
    public YHResult updateItem(TbItem item, String desc, String itemParams) {
        try {
            Date date = new Date();
            item.setUpdated(date);
            tbItemMapper.updateByPrimaryKeySelective(item);

            TbItemDesc tbItemDesc = new TbItemDesc();
            tbItemDesc.setUpdated(date);
            tbItemDesc.setItemDesc(desc);
            TbItemDescExample example = new TbItemDescExample();
            TbItemDescExample.Criteria criteria = example.createCriteria();
            criteria.andItemIdEqualTo(item.getId());
            tbItemDescMapper.updateByExampleSelective(tbItemDesc,example);

            TbItemParamItem tbItemParamItem = new TbItemParamItem();
            tbItemParamItem.setUpdated(date);
            tbItemParamItem.setParamData(itemParams);
            TbItemParamItemExample example1 = new TbItemParamItemExample();
            TbItemParamItemExample.Criteria criteria1 = example1.createCriteria();
            criteria1.andItemIdEqualTo(item.getId());
            tbItemParamItemMapper.updateByExampleSelective(tbItemParamItem,example1);
            return YHResult.ok();
        }catch (Exception e){
            return YHResult.build(500,"商品修改失败");
        }
    }
}
