package com.genx.course.processor.base.musiclesson.model.mapper.config;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.genx.course.processor.base.musiclesson.model.dao.CourseMusicLesson;
import com.genx.course.processor.base.musiclesson.model.dto.CourseMusicLessonDTO;

import com.genx.course.processor.base.musiclesson.model.mapper.provider.CourseMusicLessonToCourseMusicLessonDTOProvider;
import com.genx.course.processor.base.musiclesson.model.mapper.provider.CourseMusicLessonDTOToCourseMusicLessonProvider;
import javax.annotation.PostConstruct;

@Configuration
public class CourseMusicLessonMapperConfig {
	
	@Autowired
	ModelMapper modelMapper;

    @Autowired
    private CourseMusicLessonToCourseMusicLessonDTOProvider musiclessonToCourseMusicLessonDTOProvider;
    
    @Autowired
    private CourseMusicLessonDTOToCourseMusicLessonProvider musiclessonDTOToCourseMusicLessonProvider;
    

	@PostConstruct
    public void modelMapper() {
        // Domain to DTO conversions
        modelMapper.createTypeMap(CourseMusicLesson.class, CourseMusicLessonDTO.class).setProvider(musiclessonToCourseMusicLessonDTOProvider);
        // DTO to Domain conversions
        modelMapper.createTypeMap(CourseMusicLessonDTO.class, CourseMusicLesson.class).setProvider(musiclessonDTOToCourseMusicLessonProvider);
    }

}
