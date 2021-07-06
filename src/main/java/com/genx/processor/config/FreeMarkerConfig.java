package com.genx.processor.config;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.genx.processor.generator.GeneratorStarter;
import com.genx.processor.generator.util.LowerAllCharacter;
import com.genx.processor.generator.util.LowerFirstCharacter;
import com.genx.processor.generator.util.UpperFirstCharacter;

import freemarker.template.Configuration;

@Component
public class FreeMarkerConfig {

	@Bean
	public Configuration configuration() {
		Configuration cfg = new Configuration();
		cfg.setClassForTemplateLoading(GeneratorStarter.class, "template");
		cfg.setSharedVariable("upperFC", new UpperFirstCharacter());
		cfg.setSharedVariable("lowerFC", new LowerFirstCharacter());
		cfg.setSharedVariable("lowerAC", new LowerAllCharacter());
		cfg.setSharedVariable("lowerAll", new LowerAllCharacter());
		return cfg;
	}
	
}
