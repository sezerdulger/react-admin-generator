package com.genx.god.processor.base.tenant.model.mapper.provider;

import org.modelmapper.Provider;

import com.genx.god.processor.base.tenant.model.dao.GodTenant;
import com.genx.god.processor.base.tenant.model.dto.GodTenantDTO;
import org.springframework.stereotype.Component;
import lombok.Builder;

@Component
public class GodTenantToGodTenantDTOProvider implements Provider<GodTenantDTO> {

    @Override
    public GodTenantDTO get(ProvisionRequest<GodTenantDTO> request) {
        GodTenant tenant = GodTenant.class.cast(request.getSource());
        
         GodTenantDTO tenantDTO = GodTenantDTO.builder()
        	.title(tenant.getTitle())
        	.className(tenant.getClassName())
        	.description(tenant.getDescription())
        	.name(tenant.getName())
        	.packageName(tenant.getPackageName())
        	.uiSrcPath(tenant.getUiSrcPath())
        	.javaSrcPath(tenant.getJavaSrcPath())
        	.build();

        return tenantDTO;
    }
}
