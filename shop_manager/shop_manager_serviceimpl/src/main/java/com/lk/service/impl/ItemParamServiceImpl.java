package com.lk.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lk.pojo.EasyUIDatagrid;
import com.lk.pojo.YHResult;
import com.lk.service.ItemParamService;
import com.yh.dao.ItemParamCustomMapper;
import com.yh.mapper.TbItemParamMapper;
import com.yh.pojo.TbItemParam;
import com.yh.pojo.TbItemParamExample;
import com.yh.custom.TbItemParamList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ItemParamServiceImpl implements ItemParamService {
    @Autowired
    private ItemParamCustomMapper itemParamCustomMapper;
    @Autowired
    private TbItemParamMapper tbItemParamMapper;
    @Override
    public YHResult checkItemParam(Long cid) {
        TbItemParamExample example = new TbItemParamExample();
        TbItemParamExample.Criteria criteria = example.createCriteria();
        criteria.andItemCatIdEqualTo(cid);
        List<TbItemParam> list = tbItemParamMapper.selectByExampleWithBLOBs(example);
        if (list!=null&&list.size()>0){
            return YHResult.ok(200,list.get(0));
        }else {
            return YHResult.ok();
        }
    }

    @Override
    public YHResult addItemParam(Long cid, String paramData) {
        TbItemParam param = new TbItemParam();
        param.setItemCatId(cid);
        param.setParamData(paramData);
        param.setCreated(new Date());
        param.setUpdated(new Date());
        tbItemParamMapper.insert(param);
        return YHResult.ok();
    }

    @Override
    public EasyUIDatagrid getItemParamList(Integer currentPage, Integer pageSize) {
        PageHelper.startPage(currentPage,pageSize);
        List<TbItemParamList> list = itemParamCustomMapper.selectItemParamList();
        PageInfo<TbItemParamList> pi = new PageInfo<>(list);
        long total = pi.getTotal();
        EasyUIDatagrid es = new EasyUIDatagrid();
        es.setRows(list);
        es.setTotal(total);
        return es;
    }

    @Override
    public YHResult delItemParam(int[] ids) {
        itemParamCustomMapper.BatchDelItemParam(ids);
        return YHResult.ok();
    }
}