package com.genx.god.processor.base.entity.model.mapper.provider;

import org.modelmapper.Provider;

import com.genx.god.processor.base.entity.model.dao.GodEntity;
import com.genx.god.processor.base.entity.model.dto.GodEntityDTO;
import org.springframework.stereotype.Component;
import lombok.Builder;

@Component
public class GodEntityToGodEntityDTOProvider implements Provider<GodEntityDTO> {

    @Override
    public GodEntityDTO get(ProvisionRequest<GodEntityDTO> request) {
        GodEntity entity = GodEntity.class.cast(request.getSource());
        
         GodEntityDTO entityDTO = GodEntityDTO.builder()
        	.title(entity.getTitle())
        	.className(entity.getClassName())
        	.description(entity.getDescription())
        	.name(entity.getName())
        	.packageName(entity.getPackageName())
        	.tenant(entity.getTenant())
        	.build();

        return entityDTO;
    }
}
