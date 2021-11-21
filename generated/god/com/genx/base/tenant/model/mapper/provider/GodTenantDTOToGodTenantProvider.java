package com.genx.god.processor.base.tenant.model.mapper.provider;

import org.modelmapper.Provider;

import com.genx.god.processor.base.tenant.model.dao.GodTenant;
import com.genx.god.processor.base.tenant.model.dto.GodTenantDTO;
import org.springframework.stereotype.Component;
import lombok.Builder;

@Component
public class GodTenantDTOToGodTenantProvider implements Provider<GodTenant> {

    @Override
    public GodTenant get(ProvisionRequest<GodTenant> request) {
        GodTenantDTO tenantDTO = GodTenantDTO.class.cast(request.getSource());
        
        
        GodTenant tenant = GodTenant.builder()
        	.title(tenantDTO.getTitle())
        	.className(tenantDTO.getClassName())
        	.description(tenantDTO.getDescription())
        	.name(tenantDTO.getName())
        	.packageName(tenantDTO.getPackageName())
        	.uiSrcPath(tenantDTO.getUiSrcPath())
        	.javaSrcPath(tenantDTO.getJavaSrcPath())
        	.build();

        return tenant;
    }
}
