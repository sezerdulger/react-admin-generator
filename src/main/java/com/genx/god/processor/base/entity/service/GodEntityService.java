package com.genx.god.processor.base.entity.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.*;

import com.genx.god.processor.base.entity.model.dao.*;
import com.genx.god.processor.base.entity.model.dto.*;

import com.genx.god.processor.base.entity.repository.*;

import java.util.*;
import java.util.stream.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.bson.Document;
import org.springframework.util.StringUtils;
import com.genx.god.processor.base.entity.model.dto.SearchQuery;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.support.PageableExecutionUtils;

/**
 * @author SD
 * @date 2021/11/21
 */
@Service
public class GodEntityService {

	@Autowired
	GodEntityRepository entityRepositoryExt;
	
	@Autowired
	ObjectMapper mapper;
	
	@Autowired
	MongoTemplate mongoTemplate;
	
    public GodEntity findByUid(String uid) {
		return entityRepositoryExt.findByUidIs(uid).orElse(null);
	}
	
	public GodEntity save(GodEntity entity) {
		entity.setUid(UUID.randomUUID().toString());
		return entityRepositoryExt.save(entity);
	}
	
	public GodEntity update(String uid, GodEntity entity) {
		GodEntity entityDAO = entityRepositoryExt.findByUidIs(uid).orElse(null);
		if (entityDAO != null) {
			entity.setId(entityDAO.getId());
		}
		return entityRepositoryExt.save(entity);
	}
	
	public void delete(String uid) {
		entityRepositoryExt.deleteByUid(uid);
	}
	
	public Page<GodEntity> findByPage(SearchQuery searchQuery) {
		String tableName = StringUtils.uncapitalize(GodEntity.class.getSimpleName());
		Document ob = new Document("find", tableName);
		ob.append("filter", searchQuery.getQuery());
		ob.append("skip", searchQuery.getPage() * searchQuery.getSize());
		ob.append("limit", searchQuery.getSize());
		Document result = mongoTemplate.executeCommand(ob);
		
		List<Document> documents = (List<Document>)(((Document)result.get("cursor")).get("firstBatch"));
		
		Document c = new Document();
		c.put("count", tableName);
		c.put("query", searchQuery.getQuery());
		
		Document countResult = mongoTemplate.getDb().runCommand(c);
		PageRequest p = PageRequest.of(searchQuery.getPage(), searchQuery.getSize());
		return PageableExecutionUtils.getPage(documents.stream()
				.map(d -> mapper.convertValue(d, GodEntity.class))
				.collect(Collectors.toList()), 
				p, () -> new Long(countResult.getInteger("n")));
		
	}
}