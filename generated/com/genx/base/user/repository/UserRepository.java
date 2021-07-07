package com.genx.base.user.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.genx.base.user.model.*;
import com.genx.base.user.repository.*;

import java.util.*;

/**
 * @author SD
 * @date 2021/07/06
 */
@Repository
public interface UserRepository extends PagingAndSortingRepository<User, String> {
	Optional<User> findByUidIs(String uid);
	
	void deleteByUid(String uid);
}