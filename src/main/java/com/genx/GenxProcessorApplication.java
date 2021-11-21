package com.genx;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.FileSystemUtils;

import com.genx.processor.service.GeneratorService;

@SpringBootApplication
@ComponentScan("com.genx")
public class GenxProcessorApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(GenxProcessorApplication.class, args);
	}
	
//	@Autowired
//	GeneratorService generator;

	@Override
	public void run(String... args) throws Exception {
		
//		Map<String, String> entities = new HashMap<>();
//		
//		entities.put("user", "9db2ee60-3f2d-485a-bf8b-ad6c62f4b9e7");
//		entities.put("customer", "313d697f-72de-4dc5-9b5b-f23bb3f365d7");
//		entities.put("ticket", "9a6e10cb-25ee-420b-a73c-a3644a9b826b");
//
//		String pathBase = "file:///Users/sdulger/Desktop/github/react-admin-generator/generated/com/genx/base/";
//		String targetPath = "file:///Users/sdulger/Desktop/github/partek/src/main/java/com/genx/processor/base/";
//		
//		String reactAdminTargetPathBase = "file:///Users/sdulger/Desktop/github/partek-client/src/react-admin/";
//		
//		entities.keySet().forEach(e -> {
//			generator.generate(entities.get(e));
//			
//			try {
//				FileSystemUtils.copyRecursively(Paths.get(new URI(pathBase + "ui/" + e)), 
//						Paths.get(new URI(reactAdminTargetPathBase + e)));
//				
//				FileSystemUtils.copyRecursively(Paths.get(new URI(pathBase + e)), 
//						Paths.get(new URI(targetPath + e)));
//				
//			} catch (IOException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			} catch (URISyntaxException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
//		});
	}

}
