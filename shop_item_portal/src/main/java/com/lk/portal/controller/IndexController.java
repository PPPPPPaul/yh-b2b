package com.lk.portal.controller;


import com.lk.customer.pojo.AD1Node;
import com.lk.service.ContentService;
import com.lk.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class IndexController {
	@Value("${CONTENT_CAT_ID}")
	private Long cid;
	@Autowired
	private ContentService contentService;
	@RequestMapping("/index")
	public String toIndex(Model model){
		List<AD1Node> an = contentService.getBigAd(cid);
		String json = JsonUtils.objectToJson(an);
		model.addAttribute("ad1",json);
		return "index";
	}
}