package com.gzb.SpringBootDemo.utils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;
import org.springframework.context.annotation.Configuration;

public class GeneratorDisplay {

	public void generator() throws Exception {
		List<String> warnings = new ArrayList<String>();
		boolean overwrite = true;
		
		//获取 逆向工程配置文件
		File configFile = new File("GeneratorConfig.xml");
		
		ConfigurationParser cp = new ConfigurationParser(warnings);
		
		org.mybatis.generator.config.Configuration config = cp.parseConfiguration(configFile);
		
		DefaultShellCallback callback = new DefaultShellCallback(overwrite);
		
		MyBatisGenerator myBatisGenerator = new MyBatisGenerator((org.mybatis.generator.config.Configuration) config, callback, warnings);
		
		myBatisGenerator.generate(null);
	}
	
	
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			GeneratorDisplay generatorDisplay = new GeneratorDisplay();
			generatorDisplay.generator();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}
