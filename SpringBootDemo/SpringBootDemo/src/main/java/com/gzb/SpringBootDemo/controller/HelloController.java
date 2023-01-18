package com.gzb.SpringBootDemo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demo/")
public class HelloController {

	@RequestMapping("demo1")
	@ResponseBody
	public String Demo1() {
		return "Helloworld";
	}
	
	
}
