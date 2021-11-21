package com.genx.god.processor.base.entity.model.mapper.config;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.genx.god.processor.base.entity.model.dao.GodEntity;
import com.genx.god.processor.base.entity.model.dto.GodEntityDTO;

import com.genx.god.processor.base.entity.model.mapper.provider.GodEntityToGodEntityDTOProvider;
import com.genx.god.processor.base.entity.model.mapper.provider.GodEntityDTOToGodEntityProvider;
import javax.annotation.PostConstruct;

@Configuration
public class GodEntityMapperConfig {
	
	@Autowired
	ModelMapper modelMapper;

    @Autowired
    private GodEntityToGodEntityDTOProvider entityToGodEntityDTOProvider;
    
    @Autowired
    private GodEntityDTOToGodEntityProvider entityDTOToGodEntityProvider;
    

	@PostConstruct
    public void modelMapper() {
        // Domain to DTO conversions
        modelMapper.createTypeMap(GodEntity.class, GodEntityDTO.class).setProvider(entityToGodEntityDTOProvider);
        // DTO to Domain conversions
        modelMapper.createTypeMap(GodEntityDTO.class, GodEntity.class).setProvider(entityDTOToGodEntityProvider);
    }

}
