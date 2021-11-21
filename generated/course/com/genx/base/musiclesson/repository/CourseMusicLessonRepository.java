package com.genx.course.processor.base.musiclesson.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.genx.course.processor.base.musiclesson.model.dao.*;
import com.genx.course.processor.base.musiclesson.repository.*;

import java.util.*;

/**
 * @author SD
 * @date 2021/11/21
 */
@Repository
public interface CourseMusicLessonRepository extends PagingAndSortingRepository<CourseMusicLesson, String> {
	Optional<CourseMusicLesson> findByUidIs(String uid);
	
	void deleteByUid(String uid);
}