package com.genx.god.processor.base.field.model.mapper.provider;

import org.modelmapper.Provider;

import com.genx.god.processor.base.field.model.dao.GodField;
import com.genx.god.processor.base.field.model.dto.GodFieldDTO;
import org.springframework.stereotype.Component;
import lombok.Builder;

@Component
public class GodFieldDTOToGodFieldProvider implements Provider<GodField> {

    @Override
    public GodField get(ProvisionRequest<GodField> request) {
        GodFieldDTO fieldDTO = GodFieldDTO.class.cast(request.getSource());
        
        
        GodField field = GodField.builder()
        	.name(fieldDTO.getName())
        	.type(fieldDTO.getType())
        	.supType(fieldDTO.getSupType())
        	.title(fieldDTO.getTitle())
        	.description(fieldDTO.getDescription())
        	.longText(fieldDTO.getLongText())
        	.file(fieldDTO.getFile())
        	.playableVideo(fieldDTO.getPlayableVideo())
        	.reference(fieldDTO.getReference())
        	.referenceMultiple(fieldDTO.getReferenceMultiple())
        	.referenceTabbed(fieldDTO.getReferenceTabbed())
        	.referenceAsForm(fieldDTO.getReferenceAsForm())
        	.referenceEntityName(fieldDTO.getReferenceEntityName())
        	.referenceTitleFromRecord(fieldDTO.getReferenceTitleFromRecord())
        	.referenceTitles(fieldDTO.getReferenceTitles())
        	.entity(fieldDTO.getEntity())
        	.build();

        return field;
    }
}
