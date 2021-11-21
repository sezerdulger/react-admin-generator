package com.genx.god.processor.base.entity.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.http.*;

import com.genx.god.processor.base.entity.model.dto.*;
import com.genx.god.processor.base.entity.model.dao.*;

import com.genx.god.processor.base.entity.service.*;

import java.util.List;
import java.util.Map;
import org.springframework.data.domain.Pageable;
import org.modelmapper.ModelMapper;

/**
 * @author SD
 * @date 2021/11/21
 */
@RestController
@RequestMapping("/god/entity")
public class GodEntityController {

	@Autowired
	GodEntityService entityServiceExt;
	
	@Autowired
	ModelMapper modelMapper;
	
    @GetMapping(value="/{uid}")
    public ResponseEntity<?> findByUid(@PathVariable String uid) {
		return ResponseEntity.ok(entityServiceExt.findByUid(uid));
	}

    @PostMapping
    public ResponseEntity<?> save(@RequestBody GodEntityDTO entityDTO) {
		GodEntity entity = modelMapper.map(entityDTO, GodEntity.class);
		return ResponseEntity.ok(entityServiceExt.save(entity));
	}
	
	@PutMapping("/{uid}")
    public ResponseEntity<?> update(@PathVariable String uid, @RequestBody GodEntityDTO entityDTO) {
		GodEntity entity = modelMapper.map(entityDTO, GodEntity.class);
		return ResponseEntity.ok(entityServiceExt.update(uid, entity));
	}
	
	@DeleteMapping("/{uid}")
    public ResponseEntity<?> delete(@PathVariable String uid) {
		entityServiceExt.delete(uid);
		return ResponseEntity.noContent().build();
	}

	@PostMapping("/q")
    public ResponseEntity<?> findByPage(@RequestBody SearchQuery searchQuery) {
		return ResponseEntity.ok(entityServiceExt.findByPage(searchQuery));
	}
}