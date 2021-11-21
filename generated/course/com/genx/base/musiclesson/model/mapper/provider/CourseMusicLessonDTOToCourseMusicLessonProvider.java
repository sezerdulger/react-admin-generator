package com.genx.course.processor.base.musiclesson.model.mapper.provider;

import org.modelmapper.Provider;

import com.genx.course.processor.base.musiclesson.model.dao.CourseMusicLesson;
import com.genx.course.processor.base.musiclesson.model.dto.CourseMusicLessonDTO;
import org.springframework.stereotype.Component;
import lombok.Builder;

@Component
public class CourseMusicLessonDTOToCourseMusicLessonProvider implements Provider<CourseMusicLesson> {

    @Override
    public CourseMusicLesson get(ProvisionRequest<CourseMusicLesson> request) {
        CourseMusicLessonDTO musiclessonDTO = CourseMusicLessonDTO.class.cast(request.getSource());
        
        
        CourseMusicLesson musiclesson = CourseMusicLesson.builder()
        	.title(musiclessonDTO.getTitle())
        	.record(musiclessonDTO.getRecord())
        	.note(musiclessonDTO.getNote())
        	.build();

        return musiclesson;
    }
}
