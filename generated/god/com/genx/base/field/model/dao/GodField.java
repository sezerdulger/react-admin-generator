package com.genx.god.processor.base.field.model.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.*;
import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Map;
import org.springframework.data.annotation.Id;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import java.time.LocalDateTime;
import java.time.LocalDate;

/**
 * @author SD
 * @date 2021/11/21
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class GodField {

	@Id
	private String id;
	
	private String uid;
	

    private String name;


    private String type;


    private String supType;


    private String title;


    private String description;


    private Boolean longText;


    private Boolean file;


    private Boolean playableVideo;


    private Boolean reference;


    private Boolean referenceMultiple;


    private Boolean referenceTabbed;


    private Boolean referenceAsForm;


    private String referenceEntityName;


    private String referenceTitleFromRecord;


    private List<String> referenceTitles;


    private String entity;

	
}