package com.gzb.SpringBootDemo.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/demo/")
public class HelloController {

	@RequestMapping("demo1")
	@ResponseBody
	public String Demo1() {
		return "Helloworld";
	}
	
	
	@RequestMapping("/index")
	public ModelAndView  index(Model map) {
		map.addAttribute("msg", "后台数据。。。");
		//return "index";
		return new ModelAndView("index");
		
	}
	
	@RequestMapping("center")
	public ModelAndView center() {
		return new ModelAndView("center");
		//return "freemaker/center/center";
		//return new ModelAndView("freemaker/center/center");
	}
}
