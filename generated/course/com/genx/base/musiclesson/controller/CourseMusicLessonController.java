package com.genx.course.processor.base.musiclesson.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.http.*;

import com.genx.course.processor.base.musiclesson.model.dto.*;
import com.genx.course.processor.base.musiclesson.model.dao.*;

import com.genx.course.processor.base.musiclesson.service.*;

import java.util.List;
import java.util.Map;
import org.springframework.data.domain.Pageable;
import org.modelmapper.ModelMapper;

/**
 * @author SD
 * @date 2021/11/21
 */
 @CrossOrigin("*")
@RestController
@RequestMapping("/course/musiclesson")
public class CourseMusicLessonController {

	@Autowired
	CourseMusicLessonService musiclessonServiceExt;
	
	@Autowired
	ModelMapper modelMapper;
	
    @GetMapping(value="/{uid}")
    public ResponseEntity<?> findByUid(@PathVariable String uid) {
		return ResponseEntity.ok(musiclessonServiceExt.findByUid(uid));
	}

    @PostMapping
    public ResponseEntity<?> save(@RequestBody CourseMusicLessonDTO musiclessonDTO) {
		CourseMusicLesson musiclesson = modelMapper.map(musiclessonDTO, CourseMusicLesson.class);
		return ResponseEntity.ok(musiclessonServiceExt.save(musiclesson));
	}
	
	@PutMapping("/{uid}")
    public ResponseEntity<?> update(@PathVariable String uid, @RequestBody CourseMusicLessonDTO musiclessonDTO) {
		CourseMusicLesson musiclesson = modelMapper.map(musiclessonDTO, CourseMusicLesson.class);
		return ResponseEntity.ok(musiclessonServiceExt.update(uid, musiclesson));
	}
	
	@DeleteMapping("/{uid}")
    public ResponseEntity<?> delete(@PathVariable String uid) {
		musiclessonServiceExt.delete(uid);
		return ResponseEntity.noContent().build();
	}

	@PostMapping("/q")
    public ResponseEntity<?> findByPage(@RequestBody SearchQuery searchQuery) {
		return ResponseEntity.ok(musiclessonServiceExt.findByPage(searchQuery));
	}
}