package com.lk.controller;

import java.util.List;

import com.lk.pojo.EasyUITreeNode;
import com.lk.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class ItemCatController {
	@Autowired
	private ItemCatService itemCatService;

	@RequestMapping("/item/cat/list")
	@ResponseBody
	public List<EasyUITreeNode> findItemCatByPid(@RequestParam(value="id",defaultValue="0") Long pid){
		return itemCatService.findItemCatsByPid(pid);
	}
}
