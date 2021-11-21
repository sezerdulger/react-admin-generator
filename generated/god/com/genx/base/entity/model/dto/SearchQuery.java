package com.genx.god.processor.base.entity.model.dto;

import org.bson.Document;

import lombok.Data;

@Data
public class SearchQuery {
	private int page;
	private int size;
	
	private Document query;
}