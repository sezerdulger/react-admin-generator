package com.genx.processor.config;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class DomainField {
	private String id;
	private String javaSupType;
	private String javaType;
	private String title;
	
	@Builder.Default
	private boolean reference = false;
	
	private String referenceModelId;
	private String referenceFieldId;
}
