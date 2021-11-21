package com.genx.processor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.genx.processor.service.GeneratorService;

/**
 * @author SD
 * @date 2021/07/23
 */
@RestController
@RequestMapping("/generate")
public class GeneratorController {

	@Autowired
	GeneratorService generatorService;
	
    @PostMapping(value="/entity/{uid}")
    public void generate(@PathVariable String uid) {
    	generatorService.generate(uid);
	}
    
    @PostMapping(value="/tenant/{uid}")
    public void generateTenant(@PathVariable String uid) {
    	generatorService.generateByTenant(uid);
	}
}