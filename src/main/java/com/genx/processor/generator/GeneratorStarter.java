package com.genx.processor.generator;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.genx.processor.config.GeneratorConfig;

import freemarker.template.Configuration;
import freemarker.template.Template;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class GeneratorStarter {

	@Autowired
	GeneratorConfig generatorConfig;
	
	@Autowired
	ControllerGenerator restControllerGenerator;
	
	@Autowired
	ServiceGenerator serviceGenerator;
	
	@Autowired
	Configuration cfg;
	
	@PostConstruct
	public void init() {
		log.debug("generatorConfig: " + generatorConfig);
		generatorConfig.getDomainModels().forEach(domainModel -> {
			generatorConfig.getProcessors().forEach(processor -> {
			
				try {
		            Template template = cfg.getTemplate(processor.getTemplate());

		            String fileBasePath = generatorConfig.getOutputDir() + 
		            		"/com/genx/base/" + domainModel.getId().toLowerCase() + "/" + 
		            		processor.getPackageName() + "/"; 
		            		;
		            String filePath = fileBasePath + domainModel.getClassName() + processor.getSuffix() + ".java";
		            new File(fileBasePath).mkdirs();
		            
		            FileOutputStream fos = new FileOutputStream(filePath);
		            
		            Map data = new HashMap();	
		            data.put("domainModel", domainModel);
		            data.put("processor", processor);
		            data.put("generatorInfo", generatorConfig);
		            
		            template.process(data, new OutputStreamWriter(fos, "utf-8"));

		            fos.flush();
		            fos.close();
		        } catch (Exception e) {
		            e.printStackTrace();
		        }
				
				
			});
			try {
	            Template template = cfg.getTemplate("SearchQuery.html");

	            String fileBasePath = generatorConfig.getOutputDir() + 
	            		"/com/genx/base/" + domainModel.getPackageName()+ "/" + 
	            		generatorConfig.getModelPackage() + "/"; 
	            		;
	            String filePath = fileBasePath + "SearchQuery.java";
	            new File(fileBasePath).mkdirs();
	            
	            FileOutputStream fos = new FileOutputStream(filePath);
	            
	            Map data = new HashMap();	
	            data.put("domainModel", domainModel);
	            
	            template.process(data, new OutputStreamWriter(fos, "utf-8"));

	            fos.flush();
	            fos.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
		});
		
		
	}

}
