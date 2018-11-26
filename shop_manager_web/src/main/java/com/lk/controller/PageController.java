package com.lk.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {

	@RequestMapping("/")
	public String toIndex(){
		return "index";
	}
	
	@RequestMapping("/{page}")
	public String toItemList(@PathVariable String page){
		return page;
	}
	/*@RequestMapping("/item-add")
	public String toItemAdd(){
		return "item-add";
	}
	@RequestMapping("/item-list")
	public String toItemList(){
		return "item-list";
	}
	@RequestMapping("/item-param-list")
	public String toItemParamList(){
		return "item-param-list";
	}*/
}
