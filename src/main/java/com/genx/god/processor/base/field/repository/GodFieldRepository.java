package com.genx.god.processor.base.field.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.genx.god.processor.base.field.model.dao.*;
import com.genx.god.processor.base.field.repository.*;

import java.util.*;

/**
 * @author SD
 * @date 2021/11/21
 */
@Repository
public interface GodFieldRepository extends PagingAndSortingRepository<GodField, String> {
	Optional<GodField> findByUidIs(String uid);
	
	void deleteByUid(String uid);
}