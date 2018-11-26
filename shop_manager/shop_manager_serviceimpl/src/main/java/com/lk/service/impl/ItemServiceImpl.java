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
import com.yh.pojo.TbItem;
import com.yh.pojo.TbItemDesc;
import com.yh.pojo.TbItemExample;
import com.yh.pojo.TbItemParamItem;
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
}
