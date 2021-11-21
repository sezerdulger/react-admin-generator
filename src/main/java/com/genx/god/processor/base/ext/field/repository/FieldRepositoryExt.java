package com.genx.god.processor.base.ext.field.repository;

import java.util.List;

import com.genx.god.processor.base.field.model.dao.GodField;
import com.genx.god.processor.base.field.repository.GodFieldRepository;

public interface FieldRepositoryExt extends GodFieldRepository {
	List<GodField> findByEntity(String entityId);
}
