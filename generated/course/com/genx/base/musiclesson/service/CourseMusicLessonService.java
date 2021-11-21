package com.genx.course.processor.base.musiclesson.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.*;

import com.genx.course.processor.base.musiclesson.model.dao.*;
import com.genx.course.processor.base.musiclesson.model.dto.*;

import com.genx.course.processor.base.musiclesson.repository.*;

import java.util.*;
import java.util.stream.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.bson.Document;
import org.springframework.util.StringUtils;
import com.genx.course.processor.base.musiclesson.model.dto.SearchQuery;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.support.PageableExecutionUtils;

/**
 * @author SD
 * @date 2021/11/21
 */
@Service
public class CourseMusicLessonService {

	@Autowired
	CourseMusicLessonRepository musiclessonRepositoryExt;
	
	@Autowired
	ObjectMapper mapper;
	
	@Autowired
	MongoTemplate mongoTemplate;
	
    public CourseMusicLesson findByUid(String uid) {
		return musiclessonRepositoryExt.findByUidIs(uid).orElse(null);
	}
	
	public CourseMusicLesson save(CourseMusicLesson musiclesson) {
		musiclesson.setUid(UUID.randomUUID().toString());
		return musiclessonRepositoryExt.save(musiclesson);
	}
	
	public CourseMusicLesson update(String uid, CourseMusicLesson musiclesson) {
		CourseMusicLesson musiclessonDAO = musiclessonRepositoryExt.findByUidIs(uid).orElse(null);
		if (musiclessonDAO != null) {
			musiclesson.setId(musiclessonDAO.getId());
		}
		return musiclessonRepositoryExt.save(musiclesson);
	}
	
	public void delete(String uid) {
		musiclessonRepositoryExt.deleteByUid(uid);
	}
	
	public Page<CourseMusicLesson> findByPage(SearchQuery searchQuery) {
		String tableName = StringUtils.uncapitalize(CourseMusicLesson.class.getSimpleName());
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
				.map(d -> mapper.convertValue(d, CourseMusicLesson.class))
				.collect(Collectors.toList()), 
				p, () -> new Long(countResult.getInteger("n")));
		
	}
}