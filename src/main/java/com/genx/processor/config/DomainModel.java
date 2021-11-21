package com.genx.processor.config;

import java.util.List;

import lombok.Data;

@Data
public class DomainModel {
	private String tenant;
	private String id;
	private String packageName;
	private String className;
	private List<DomainField> fields;
}
