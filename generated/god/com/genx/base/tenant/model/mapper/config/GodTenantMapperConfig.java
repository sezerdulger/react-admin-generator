package com.genx.god.processor.base.tenant.model.mapper.config;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.genx.god.processor.base.tenant.model.dao.GodTenant;
import com.genx.god.processor.base.tenant.model.dto.GodTenantDTO;

import com.genx.god.processor.base.tenant.model.mapper.provider.GodTenantToGodTenantDTOProvider;
import com.genx.god.processor.base.tenant.model.mapper.provider.GodTenantDTOToGodTenantProvider;
import javax.annotation.PostConstruct;

@Configuration
public class GodTenantMapperConfig {
	
	@Autowired
	ModelMapper modelMapper;

    @Autowired
    private GodTenantToGodTenantDTOProvider tenantToGodTenantDTOProvider;
    
    @Autowired
    private GodTenantDTOToGodTenantProvider tenantDTOToGodTenantProvider;
    

	@PostConstruct
    public void modelMapper() {
        // Domain to DTO conversions
        modelMapper.createTypeMap(GodTenant.class, GodTenantDTO.class).setProvider(tenantToGodTenantDTOProvider);
        // DTO to Domain conversions
        modelMapper.createTypeMap(GodTenantDTO.class, GodTenant.class).setProvider(tenantDTOToGodTenantProvider);
    }

}
