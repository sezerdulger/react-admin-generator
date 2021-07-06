package com.genx.processor.config;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Configuration
@Data
@ConfigurationProperties(prefix = "generator")
public class GeneratorConfig {
	private String modelPackage;
	private String controllerPackage;
	private String servicePackage;
	private String utilPackage;
	private String constantPackage;
	private String repositoryPackage;
	
	private List<Processor> processors;
	private String  outputDir;
	
	private List<DomainModel> domainModels;
}
