package com.genx.processor.config;

import lombok.Data;

@Data
public class Processor {
	private String id;
	private String template;
	private String packageName;
	private String suffix;
}
