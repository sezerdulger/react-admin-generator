package com.genx.god.processor.base.tenant.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.*;

import com.genx.god.processor.base.tenant.model.dao.*;
import com.genx.god.processor.base.tenant.model.dto.*;

import com.genx.god.processor.base.tenant.repository.*;

import java.util.*;
import java.util.stream.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.bson.Document;
import org.springframework.util.StringUtils;
import com.genx.god.processor.base.tenant.model.dto.SearchQuery;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.support.PageableExecutionUtils;

/**
 * @author SD
 * @date 2021/11/21
 */
@Service
public class GodTenantService {

	@Autowired
	GodTenantRepository tenantRepositoryExt;
	
	@Autowired
	ObjectMapper mapper;
	
	@Autowired
	MongoTemplate mongoTemplate;
	
    public GodTenant findByUid(String uid) {
		return tenantRepositoryExt.findByUidIs(uid).orElse(null);
	}
	
	public GodTenant save(GodTenant tenant) {
		tenant.setUid(UUID.randomUUID().toString());
		return tenantRepositoryExt.save(tenant);
	}
	
	public GodTenant update(String uid, GodTenant tenant) {
		GodTenant tenantDAO = tenantRepositoryExt.findByUidIs(uid).orElse(null);
		if (tenantDAO != null) {
			tenant.setId(tenantDAO.getId());
		}
		return tenantRepositoryExt.save(tenant);
	}
	
	public void delete(String uid) {
		tenantRepositoryExt.deleteByUid(uid);
	}
	
	public Page<GodTenant> findByPage(SearchQuery searchQuery) {
		String tableName = StringUtils.uncapitalize(GodTenant.class.getSimpleName());
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
				.map(d -> mapper.convertValue(d, GodTenant.class))
				.collect(Collectors.toList()), 
				p, () -> new Long(countResult.getInteger("n")));
		
	}
}