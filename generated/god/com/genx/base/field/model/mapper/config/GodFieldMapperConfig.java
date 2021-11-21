package com.genx.god.processor.base.field.model.mapper.config;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.genx.god.processor.base.field.model.dao.GodField;
import com.genx.god.processor.base.field.model.dto.GodFieldDTO;

import com.genx.god.processor.base.field.model.mapper.provider.GodFieldToGodFieldDTOProvider;
import com.genx.god.processor.base.field.model.mapper.provider.GodFieldDTOToGodFieldProvider;
import javax.annotation.PostConstruct;

@Configuration
public class GodFieldMapperConfig {
	
	@Autowired
	ModelMapper modelMapper;

    @Autowired
    private GodFieldToGodFieldDTOProvider fieldToGodFieldDTOProvider;
    
    @Autowired
    private GodFieldDTOToGodFieldProvider fieldDTOToGodFieldProvider;
    

	@PostConstruct
    public void modelMapper() {
        // Domain to DTO conversions
        modelMapper.createTypeMap(GodField.class, GodFieldDTO.class).setProvider(fieldToGodFieldDTOProvider);
        // DTO to Domain conversions
        modelMapper.createTypeMap(GodFieldDTO.class, GodField.class).setProvider(fieldDTOToGodFieldProvider);
    }

}
