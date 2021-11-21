package com.genx.god.processor.base.ext.field.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.genx.god.processor.base.ext.field.repository.FieldRepositoryExt;
import com.genx.god.processor.base.field.model.dao.GodField;
import com.genx.god.processor.base.field.service.GodFieldService;

@Component
public class FieldServiceExt extends GodFieldService {
	@Autowired
	FieldRepositoryExt fieldRepo;
	
	public List<GodField> findByEntityName(String entityName) {
		return fieldRepo.findByEntity(entityName);
	}
}
