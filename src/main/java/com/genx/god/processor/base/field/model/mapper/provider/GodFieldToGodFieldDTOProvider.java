package com.genx.god.processor.base.field.model.mapper.provider;

import org.modelmapper.Provider;

import com.genx.god.processor.base.field.model.dao.GodField;
import com.genx.god.processor.base.field.model.dto.GodFieldDTO;
import org.springframework.stereotype.Component;
import lombok.Builder;

@Component
public class GodFieldToGodFieldDTOProvider implements Provider<GodFieldDTO> {

    @Override
    public GodFieldDTO get(ProvisionRequest<GodFieldDTO> request) {
        GodField field = GodField.class.cast(request.getSource());
        
         GodFieldDTO fieldDTO = GodFieldDTO.builder()
        	.name(field.getName())
        	.type(field.getType())
        	.supType(field.getSupType())
        	.title(field.getTitle())
        	.description(field.getDescription())
        	.longText(field.getLongText())
        	.file(field.getFile())
        	.playableVideo(field.getPlayableVideo())
        	.reference(field.getReference())
        	.referenceMultiple(field.getReferenceMultiple())
        	.referenceTabbed(field.getReferenceTabbed())
        	.referenceAsForm(field.getReferenceAsForm())
        	.referenceEntityName(field.getReferenceEntityName())
        	.referenceTitleFromRecord(field.getReferenceTitleFromRecord())
        	.referenceTitles(field.getReferenceTitles())
        	.entity(field.getEntity())
        	.build();

        return fieldDTO;
    }
}
