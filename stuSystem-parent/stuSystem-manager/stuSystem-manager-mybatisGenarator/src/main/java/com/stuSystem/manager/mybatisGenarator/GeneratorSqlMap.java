package com.stuSystem.manager.mybatisGenarator;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author ASUS
 *ʹ使用java程序生成mapper.xml、mapper、pojo
 */
public class GeneratorSqlMap {
	
	public void generator()throws Exception{
		
		   List<String> warnings = new ArrayList<String>();
		   boolean overwrite = true;
		 /*  File configFile = new File("resources/generatorConfig.xml");*/
		   ConfigurationParser cp = new ConfigurationParser(warnings);
		   //使用类加载器直接获取资源
		   Configuration config = cp.parseConfiguration(this.getClass().getClassLoader().getResourceAsStream("generatorConfig.xml"));
		   DefaultShellCallback callback = new DefaultShellCallback(overwrite);
		   MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
		   myBatisGenerator.generate(null);
	}
	public static void main(String args[])throws Exception{
		try {
			GeneratorSqlMap generatorSqlMap = new GeneratorSqlMap();
			generatorSqlMap.generator();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
