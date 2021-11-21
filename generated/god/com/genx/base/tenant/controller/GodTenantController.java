package com.genx.god.processor.base.tenant.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.http.*;

import com.genx.god.processor.base.tenant.model.dto.*;
import com.genx.god.processor.base.tenant.model.dao.*;

import com.genx.god.processor.base.tenant.service.*;

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
@RequestMapping("/god/tenant")
public class GodTenantController {

	@Autowired
	GodTenantService tenantServiceExt;
	
	@Autowired
	ModelMapper modelMapper;
	
    @GetMapping(value="/{uid}")
    public ResponseEntity<?> findByUid(@PathVariable String uid) {
		return ResponseEntity.ok(tenantServiceExt.findByUid(uid));
	}

    @PostMapping
    public ResponseEntity<?> save(@RequestBody GodTenantDTO tenantDTO) {
		GodTenant tenant = modelMapper.map(tenantDTO, GodTenant.class);
		return ResponseEntity.ok(tenantServiceExt.save(tenant));
	}
	
	@PutMapping("/{uid}")
    public ResponseEntity<?> update(@PathVariable String uid, @RequestBody GodTenantDTO tenantDTO) {
		GodTenant tenant = modelMapper.map(tenantDTO, GodTenant.class);
		return ResponseEntity.ok(tenantServiceExt.update(uid, tenant));
	}
	
	@DeleteMapping("/{uid}")
    public ResponseEntity<?> delete(@PathVariable String uid) {
		tenantServiceExt.delete(uid);
		return ResponseEntity.noContent().build();
	}

	@PostMapping("/q")
    public ResponseEntity<?> findByPage(@RequestBody SearchQuery searchQuery) {
		return ResponseEntity.ok(tenantServiceExt.findByPage(searchQuery));
	}
}