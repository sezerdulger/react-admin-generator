package com.genx.course.processor.base.musiclesson.model.mapper.provider;

import org.modelmapper.Provider;

import com.genx.course.processor.base.musiclesson.model.dao.CourseMusicLesson;
import com.genx.course.processor.base.musiclesson.model.dto.CourseMusicLessonDTO;
import org.springframework.stereotype.Component;
import lombok.Builder;

@Component
public class CourseMusicLessonToCourseMusicLessonDTOProvider implements Provider<CourseMusicLessonDTO> {

    @Override
    public CourseMusicLessonDTO get(ProvisionRequest<CourseMusicLessonDTO> request) {
        CourseMusicLesson musiclesson = CourseMusicLesson.class.cast(request.getSource());
        
         CourseMusicLessonDTO musiclessonDTO = CourseMusicLessonDTO.builder()
        	.title(musiclesson.getTitle())
        	.record(musiclesson.getRecord())
        	.note(musiclesson.getNote())
        	.build();

        return musiclessonDTO;
    }
}
