package com.genx.processor.generator;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.genx.processor.config.GeneratorConfig;

import freemarker.template.Configuration;
import freemarker.template.Template;

@Component
public class ServiceGenerator {

	@Autowired
	GeneratorConfig generatorConfig;
	
	@Autowired
	Configuration cfg;
	
	public void generate() {
		generatorConfig.getDomainModels().forEach(domainModel -> {
			try {
	            Template template = cfg.getTemplate("javaserviceclass.html");

	            String fileBasePath = generatorConfig.getOutputDir() + 
	            		"/com/genx/base/" + domainModel.getId().toLowerCase() + "/"  + "/"; 
	            		;
	            String filePath = fileBasePath + StringUtils.capitalize(domainModel.getId()) + "Service.java";
	            new File(fileBasePath).mkdirs();
	            
	            FileOutputStream fos = new FileOutputStream(filePath);
	            
	            Map data = new HashMap();	
	            data.put("domainModel", domainModel);
	            data.put("generatorInfo", generatorConfig);
	            
	            template.process(data, new OutputStreamWriter(fos, "utf-8"));

	            fos.flush();
	            fos.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
		});
	}

}
