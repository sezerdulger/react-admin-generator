package com.genx.processor.generator;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.FileSystemUtils;
import org.springframework.util.StringUtils;

import com.genx.processor.config.DomainField;
import com.genx.processor.config.DomainModel;
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
			domainModel.setTenant("god");
			generate(domainModel);
		});
		
		String pathBase = "file:///Users/sdulger/Desktop/github/react-admin-generator/generated/god";
		String targetPath = "file:///Users/sdulger/Desktop/github/react-admin-generator/src/main/java/com/genx/god/processor/base/";
		
		String reactAdminTargetPathBase = "file:///Users/sdulger/Desktop/github/react-admin-client/src/react-admin/";
		
		try {
			FileSystemUtils.copyRecursively(Paths.get(new URI(pathBase + "/"+"ui"+"/")), 
					Paths.get(new URI(reactAdminTargetPathBase)));
			
//			FileSystemUtils.copyRecursively(Paths.get(new URI(pathBase + "/" +"com"
//					+"/"+"genx"+"/"+"base"+"/")), 
//					Paths.get(new URI(targetPath)));
			
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (URISyntaxException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}
	
	
	public void generate(DomainModel domainModel) {
		generatorConfig.getProcessors().forEach(processor -> {
			
			try {
	            Template template = cfg.getTemplate(processor.getTemplate());

	            String fileBasePath = generatorConfig.getOutputDir() + 
	            		"/" + domainModel.getTenant() + "/"+"com"
	            		+"/"+"genx"+"/"+"base"+"/" + domainModel.getId().toLowerCase() 
	            		+ "/" + 
	            		processor.getPackageName().replace(".", "/") + "/"; 

	            String filePath = fileBasePath + StringUtils.capitalize(domainModel.getTenant()) + domainModel.getClassName() + processor.getSuffix() + ".java";
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
		      
		// ui model
		try {
            Template template = cfg.getTemplate("ui/reactmodel.html");

            String fileBasePath = generatorConfig.getOutputDir() + 
            		"/" + domainModel.getTenant() + "/" +"ui"+"/" + domainModel.getId() + "/";
            
            String filePath = fileBasePath +"/" + domainModel.getId() +  ".js";
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
		
		// ext model
		try {
			
            Template template = cfg.getTemplate("ui/modelExt.html");

            String fileBasePath = generatorConfig.getOutputDir() + 
            		"/" + domainModel.getTenant() + "/"+"uiext"+
            		"/" + domainModel.getId() + "/"+"ext";
            
            String filePath = fileBasePath + "/" + domainModel.getId() +  "Ext.js";
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
		
		// ui customDataProvider
		try {
            Template template = cfg.getTemplate("ui/customDataProvider.html");

            String fileBasePath = generatorConfig.getOutputDir() + 
            		"/" + domainModel.getTenant() + "/"+"ui"+"/" + domainModel.getId() + "/"+"provider"+"/";
            
            String filePath = fileBasePath + domainModel.getId() +  "DataProvider.js";
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
		
		// ui showColumnSlicer
		try {
            Template template = cfg.getTemplate("ui/showColumnSlicer.html");

            String fileBasePath = generatorConfig.getOutputDir() + 
            		"/" + domainModel.getTenant() + "/" + "ui" +
            		"/"+ domainModel.getId() + "/" + "slicer" + "/";
            
            String filePath = fileBasePath + domainModel.getId() +  "ShowColumnSlicer.js";
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
		
		// ui editFieldSlicer
		try {
            Template template = cfg.getTemplate("ui/editFieldSlicer.html");

            String fileBasePath = generatorConfig.getOutputDir() + 
            		"/" + domainModel.getTenant() + "/"+"ui" 
            		+"/"+ domainModel.getId() + "/"+"slicer"+"/";
            
            String filePath = fileBasePath + domainModel.getId() +  "FieldEditedSlicer.js";
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
		
		// ui edit fields
		domainModel.getFields().forEach(field -> {
			try {
	            Template template = null;
	            if (field.getReference() != null && field.getReference() && 
	            		field.getReferenceMultiple() != null && field.getReferenceMultiple()) {
	            	template = cfg.getTemplate("ui/edit/reactreferencearrayfield.html");
	            }
	            else if (field.getReference() != null && field.getReference()) {
	            	template = cfg.getTemplate("ui/edit/reactreferencefield.html");
	            }
	            else if (field.getJavaSupType() != null && field.getJavaSupType().equals("List")){
	            	template = cfg.getTemplate("ui/edit/reactlistfield.html");
	            	
	            	if (field.getFile() != null && field.getFile()) {
	            		System.out.println("field.getPlayableVideo(): " + field.getPlayableVideo());
	            		if (field.getPlayableVideo() != null && field.getPlayableVideo()) {
		            		template = cfg.getTemplate("ui/edit/reactvideofield.html");
		            		generateFieldComponent(domainModel, template, field, true);
	            		}
	            		template = cfg.getTemplate("ui/edit/reactfilefield.html");
	            	}
	            }
	            else if (field.getJavaType().equals("LocalDate")){
	            	template = cfg.getTemplate("ui/edit/reactdatefield.html");
	            }
	            else if (field.getJavaType().equals("LocalDateTime")){
	            	template = cfg.getTemplate("ui/edit/reactdatetimefield.html");
	            }
	            else if (field.getJavaType().equals("String")){
	            	if (field.getLongText() != null && field.getLongText()) {
	            		template = cfg.getTemplate("ui/edit/reacttextfield.html");
	            	}
	            	else if (field.getFile() != null && field.getFile()) {
	            		System.out.println("field.getPlayableVideo(): " + field.getPlayableVideo());
	            		if (field.getPlayableVideo() != null && field.getPlayableVideo()) {
		            		template = cfg.getTemplate("ui/edit/reactvideofield.html");
		            		generateFieldComponent(domainModel, template, field, true);
	            		}
	            		
	            		template = cfg.getTemplate("ui/edit/reactfilefield.html");
	            	}
	            	else {
	            		template = cfg.getTemplate("ui/edit/reacttextfield.html");
	            	}
	            }
	            else if (field.getJavaType().equals("Boolean")){
	            	template = cfg.getTemplate("ui/edit/reactbooleanfield.html");
	            }
	            else if (field.getJavaType().equals("Long")){
	            	template = cfg.getTemplate("ui/edit/reacttextfield.html");
	            }
	            else if (field.getJavaType().equals("Double")){
	            	template = cfg.getTemplate("ui/edit/reactdoublefield.html");
	            }
	            
	            generateFieldComponent(domainModel, template, field, false);

	           
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
		});
		
//		try {
//            Template template = cfg.getTemplate("semantic-react-ui/semanticlist.html");
//
//            String fileBasePath = generatorConfig.getOutputDir() + 
//            		"/com/genx/base/semantic-ui/" + domainModel.getId() + "/";
//            
//            String filePath = fileBasePath + "/" + domainModel.getClassName() +  "List.js";
//            new File(fileBasePath).mkdirs();
//            
//            FileOutputStream fos = new FileOutputStream(filePath);
//            
//            Map data = new HashMap();	
//            data.put("domainModel", domainModel);
//            
//            template.process(data, new OutputStreamWriter(fos, "utf-8"));
//
//            fos.flush();
//            fos.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
		
		// Searchquery
		try {
            Template template = cfg.getTemplate("SearchQuery.html");

            String fileBasePath = generatorConfig.getOutputDir() + 
            		"/" + domainModel.getTenant() + "/" +"com"+"/"+"genx"
            		+"/" +"base" +"/" + domainModel.getPackageName()+ "/" + 
            		generatorConfig.getModelPackage() + "/"+"dto"+"/"; 
            		
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
		
		// ModelMapper
		try {
            Template template = cfg.getTemplate("ModelMapperConfig.html");

            String fileBasePath = generatorConfig.getOutputDir() + 
            		"/" + domainModel.getTenant() + "/"+"com"+"/"+"genx"
            		+"/"+"base" +"/"+ domainModel.getPackageName()+ "/" + 
            		generatorConfig.getModelPackage() + "/"+"mapper"+"/"+"config"+"/"; 
            		
            String filePath = fileBasePath + StringUtils.capitalize(domainModel.getTenant()) + domainModel.getClassName() + "MapperConfig.java";
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
		
		// DomainToDTOProvider
		try {
            Template template = cfg.getTemplate("DomainToDTOProvider.html");

            String fileBasePath = generatorConfig.getOutputDir() + 
            		"/" + domainModel.getTenant() + "/"+"com"+"/"+"genx"+"/"+
            		"base" + "/"+domainModel.getPackageName()+ "/" + 
            		generatorConfig.getModelPackage() + "/"+"mapper"+"/"+"provider"+"/"; 
            		
            String filePath = fileBasePath + StringUtils.capitalize(domainModel.getTenant()) + domainModel.getClassName() 
            	+ "To" + StringUtils.capitalize(domainModel.getTenant()) + domainModel.getClassName() + "DTOProvider.java";
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
		
		// DTOToDomainProvider
		try {
            Template template = cfg.getTemplate("DTOToDomainProvider.html");

            String fileBasePath = generatorConfig.getOutputDir() + 
            		"/" + domainModel.getTenant() + "/"+"com"+"/"+"genx"+"/"+"base"
            		+"/"+ domainModel.getPackageName()+ "/" + 
            		generatorConfig.getModelPackage() + "/"+"mapper"+"/"+"provider"+"/"; 
            		
            String filePath = fileBasePath +StringUtils.capitalize(domainModel.getTenant()) + domainModel.getClassName() + 
            		"DTOTo" + StringUtils.capitalize(domainModel.getTenant()) +domainModel.getClassName() + "Provider.java";
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
		
		log.debug("Finished: {}" + domainModel.getId());
	}
	
	private void generateFieldComponent(DomainModel domainModel, Template template, DomainField field, boolean isVideoPlayer) {
		try {
			String fileBasePath = generatorConfig.getOutputDir() + 
	         		"/" + domainModel.getTenant() + "/"+"ui" + 
	         		"/" + domainModel.getId() + "/"+"edit"
	         		+"/" +"fields" +"/";
	         
	         String filePath = fileBasePath + StringUtils.capitalize(field.getId()) + ".js";
	         if (isVideoPlayer) {
	        	 System.out.println("Player generating");
	        	 filePath = fileBasePath + StringUtils.capitalize(field.getId()) + "Player.js";
	         }
	         new File(fileBasePath).mkdirs();
	         
	         FileOutputStream fos = new FileOutputStream(filePath);
	         
	         Map data = new HashMap();	
	         data.put("domainModel", domainModel);
	         data.put("field", field);
	         
	         template.process(data, new OutputStreamWriter(fos, "utf-8"));

	         fos.flush();
	         fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		 
	}
	
	public void generate(List<DomainModel> domainModels) {
		// appjs
		try {
            Template template = cfg.getTemplate("ui/sideBar.html");

            String fileBasePath = generatorConfig.getOutputDir() + 
            		"/" + domainModels.get(0).getTenant() + "/"+"ui"+"/";
            
            String filePath = fileBasePath + "/"+"Processor.js";
            new File(fileBasePath).mkdirs();
            
            FileOutputStream fos = new FileOutputStream(filePath);
            
            Map data = new HashMap();	
            data.put("domainModels", domainModels);
            
            template.process(data, new OutputStreamWriter(fos, "utf-8"));

            fos.flush();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
		
		// multipleRoute
		try {
            Template template = cfg.getTemplate("ui/multipleDataProvider.html");

            String fileBasePath = generatorConfig.getOutputDir() + 
            		"/" + domainModels.get(0).getTenant() + "/"+"ui"+"/";
            
            String filePath = fileBasePath + "/"+"multipleDataProvider.js";
            new File(fileBasePath).mkdirs();
            
            FileOutputStream fos = new FileOutputStream(filePath);
            
            Map data = new HashMap();	
            data.put("domainModels", domainModels);
            
            template.process(data, new OutputStreamWriter(fos, "utf-8"));

            fos.flush();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
	}


}
