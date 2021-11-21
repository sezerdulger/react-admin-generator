package com.genx.god.processor.base.entity.model.mapper.provider;

import org.modelmapper.Provider;

import com.genx.god.processor.base.entity.model.dao.GodEntity;
import com.genx.god.processor.base.entity.model.dto.GodEntityDTO;
import org.springframework.stereotype.Component;
import lombok.Builder;

@Component
public class GodEntityDTOToGodEntityProvider implements Provider<GodEntity> {

    @Override
    public GodEntity get(ProvisionRequest<GodEntity> request) {
        GodEntityDTO entityDTO = GodEntityDTO.class.cast(request.getSource());
        
        
        GodEntity entity = GodEntity.builder()
        	.title(entityDTO.getTitle())
        	.className(entityDTO.getClassName())
        	.description(entityDTO.getDescription())
        	.name(entityDTO.getName())
        	.packageName(entityDTO.getPackageName())
        	.tenant(entityDTO.getTenant())
        	.build();

        return entity;
    }
}
