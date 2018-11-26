package com.lk.service;


import com.lk.pojo.EasyUITreeNode;
import com.yh.custom.ItemCatTree;

import java.util.List;

public interface ItemCatService{

	//根据商品分类父id获取商品分类列表信息
	List<EasyUITreeNode> findItemCatsByPid(Long pid);
	//获取商品类别树
	ItemCatTree getItemsCatTree();
}
