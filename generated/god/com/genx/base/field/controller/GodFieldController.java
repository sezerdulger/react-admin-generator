package com.genx.god.processor.base.field.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.http.*;

import com.genx.god.processor.base.field.model.dto.*;
import com.genx.god.processor.base.field.model.dao.*;

import com.genx.god.processor.base.field.service.*;

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
@RequestMapping("/god/field")
public class GodFieldController {

	@Autowired
	GodFieldService fieldServiceExt;
	
	@Autowired
	ModelMapper modelMapper;
	
    @GetMapping(value="/{uid}")
    public ResponseEntity<?> findByUid(@PathVariable String uid) {
		return ResponseEntity.ok(fieldServiceExt.findByUid(uid));
	}

    @PostMapping
    public ResponseEntity<?> save(@RequestBody GodFieldDTO fieldDTO) {
		GodField field = modelMapper.map(fieldDTO, GodField.class);
		return ResponseEntity.ok(fieldServiceExt.save(field));
	}
	
	@PutMapping("/{uid}")
    public ResponseEntity<?> update(@PathVariable String uid, @RequestBody GodFieldDTO fieldDTO) {
		GodField field = modelMapper.map(fieldDTO, GodField.class);
		return ResponseEntity.ok(fieldServiceExt.update(uid, field));
	}
	
	@DeleteMapping("/{uid}")
    public ResponseEntity<?> delete(@PathVariable String uid) {
		fieldServiceExt.delete(uid);
		return ResponseEntity.noContent().build();
	}

	@PostMapping("/q")
    public ResponseEntity<?> findByPage(@RequestBody SearchQuery searchQuery) {
		return ResponseEntity.ok(fieldServiceExt.findByPage(searchQuery));
	}
}