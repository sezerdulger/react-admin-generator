package com.genx.processor.config;

import java.util.List;

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
	private Boolean reference = false;
	
	@Builder.Default
	private Boolean referenceMultiple = false;
	
	@Builder.Default
	private Boolean longText = false;
	
	@Builder.Default
	private Boolean file = false;
	
	@Builder.Default
	private Boolean playableVideo = false;

	private String referenceModelId;
	private String referenceTitleFromRecord;
	private List<String> referenceTitles;

}
