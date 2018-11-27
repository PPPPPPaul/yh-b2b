package com.lk.service.impl;

import com.lk.pojo.EasyUITreeNode;
import com.lk.redisclient.JedisClient;
import com.lk.service.ItemCatService;
import com.lk.utils.JsonUtils;
import com.yh.custom.ItemCatTree;
import com.yh.custom.ItemCatTreeNode;
import com.yh.mapper.TbItemCatMapper;
import com.yh.pojo.TbItemCat;
import com.yh.pojo.TbItemCatExample;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ItemCatServiceImpl implements ItemCatService {
    @Autowired
    private JedisClient jedisClient;
    @Autowired
    private TbItemCatMapper tbItemCatMapper;
    @Value("${redis.itemcattemp}")
    private String itemCatTemp;
    @Override
    public List<EasyUITreeNode> findItemCatsByPid(Long pid) {
        TbItemCatExample example = new TbItemCatExample();
        TbItemCatExample.Criteria criteria = example.createCriteria();
        criteria.andParentIdEqualTo(pid);
        List<TbItemCat> list = tbItemCatMapper.selectByExample(example);
        List<EasyUITreeNode> res = new ArrayList<>();
        for (TbItemCat cat : list){
            EasyUITreeNode node = new EasyUITreeNode();
            node.setId(cat.getId());
            node.setText(cat.getName());
            node.setState(cat.getIsParent()==true?"closed":"open");
            res.add(node);
        }
        return res;
    }

    @Override
    public ItemCatTree getItemsCatTree() {
        try {
            String json = jedisClient.get("itemCatTemp");
            if(StringUtils.isNotBlank(json)){
                ItemCatTree tree = JsonUtils.jsonToPojo(json, ItemCatTree.class);
                return tree;
            }
        }catch (Exception e){}
        List list = getItemCatsByPid(0L);
        ItemCatTree tree = new ItemCatTree();
        tree.setData(list);
        try {
            jedisClient.set("itemCatTemp",JsonUtils.objectToJson(tree));
        }catch (Exception e){}
        return tree;
    }
    private List getItemCatsByPid(Long pid){
        List res = new ArrayList();
        TbItemCatExample example = new TbItemCatExample();
        TbItemCatExample.Criteria criteria = example.createCriteria();
        criteria.andParentIdEqualTo(pid);
        //执行查询
        List<TbItemCat> list = tbItemCatMapper.selectByExample(example);
        for (TbItemCat cat : list) {
            //判断该分类是否是父节点
            if(cat.getIsParent()){
                ItemCatTreeNode node = new ItemCatTreeNode();
                node.setUrl("/products/"+cat.getId()+".html");// /products/1.html
                if(cat.getParentId()==0){//判断该分类是否是一级类目
                    node.setName("<a href='/products/"+cat.getId()+".html'>"+cat.getName()+"</a>"); // <a href='/products/1.html'>图书、音像、电子书刊</a>
                }else{
                    node.setName(cat.getName());//电子书刊
                }
                node.setItems(getItemCatsByPid(cat.getId()));
                res.add(node);
            }else{
                //该节点是叶子节点. /products/3.html|电子书
                res.add("/products/"+cat.getId()+".html|"+cat.getName());
            }

        }
        return res;
    }
}
