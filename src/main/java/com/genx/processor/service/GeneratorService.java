package com.genx.processor.service;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;

import com.genx.god.processor.base.entity.model.dao.GodEntity;
import com.genx.god.processor.base.entity.model.dto.SearchQuery;
import com.genx.god.processor.base.entity.service.GodEntityService;
import com.genx.god.processor.base.ext.field.service.FieldServiceExt;
import com.genx.god.processor.base.tenant.model.dao.GodTenant;
import com.genx.god.processor.base.tenant.service.GodTenantService;
import com.genx.processor.config.DomainField;
import com.genx.processor.config.DomainModel;
import com.genx.processor.config.GeneratorConfig;
import com.genx.processor.generator.GeneratorStarter;

@Service
public class GeneratorService {
	@Autowired
	GeneratorStarter generatorStarter;
	
	@Autowired
	GodEntityService entityService;
	
	@Autowired
	GodTenantService tenantService;
	
	@Autowired
	FieldServiceExt fieldServiceExt;
	
	@Autowired
	GeneratorConfig generatorConfig;
	
	
	public void generateByTenant(String uid) {
		
		GodTenant GodTenant = tenantService.findByUid(uid);
		SearchQuery sq = new SearchQuery();
		sq.setQuery(new Document("tenant", uid));
		sq.setPage(0);
		sq.setSize(1000);
		
		Page<GodEntity> entities = entityService.findByPage(sq);
		List<DomainModel> domainModels = new ArrayList<>();
		
		entities.forEach(godEntity -> {
			DomainModel dm = new DomainModel();
			dm.setClassName(godEntity.getName());
			dm.setPackageName(godEntity.getPackageName());
			dm.setId(godEntity.getName());
			dm.setClassName(godEntity.getClassName());
			
			dm.setFields(fieldServiceExt.findByEntityName(
					godEntity.getUid())
					.stream().map(f -> DomainField.builder()
							.id(f.getName())
							.javaType(f.getType())
							.javaSupType(f.getSupType())
							.title(f.getTitle())
							.file(f.getFile())
							.playableVideo(f.getPlayableVideo())
							.reference(f.getReference())
							.referenceModelId(f.getReferenceEntityName())
							.referenceTitleFromRecord(f.getReferenceTitleFromRecord())
							.referenceTitles(f.getReferenceTitles())
							.referenceMultiple(f.getReferenceMultiple())
							.longText(f.getLongText())
							.build()).collect(Collectors.toList()));
			dm.setTenant(GodTenant.getName());
			
			domainModels.add(dm);
			
			generatorStarter.generate(dm);
			generatorStarter.generate(domainModels);
			
			String pathBase = "file:///Users/sdulger/Desktop/github/react-admin-generator";

			
            String fileBasePath = pathBase + "/" + generatorConfig.getOutputDir() + 
            		"/" + dm.getTenant();
			
			try {
				FileSystemUtils.copyRecursively(
						Paths.get(new URI(fileBasePath + "/ui/")), 
						Paths.get(new URI(GodTenant.getUiSrcPath())));
				
				FileSystemUtils.copyRecursively(Paths.get(new URI(fileBasePath + "/com/genx/base/")), 
						Paths.get(new URI(GodTenant.getJavaSrcPath())));
				
				
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (URISyntaxException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		
	}
	
	
	public void generate(String uid) {
		GodEntity godEntity = entityService.findByUid(uid);
		
		DomainModel dm = new DomainModel();
		dm.setClassName(godEntity.getName());
		dm.setPackageName(godEntity.getPackageName());
		dm.setId(godEntity.getName());
		dm.setClassName(godEntity.getClassName());
		
		dm.setFields(fieldServiceExt.findByEntityName(
				godEntity.getUid())
				.stream().map(f -> DomainField.builder()
						.id(f.getName())
						.javaType(f.getType())
						.javaSupType(f.getSupType())
						.title(f.getTitle())
						.file(f.getFile())
						.playableVideo(f.getPlayableVideo())
						.reference(f.getReference())
						.referenceModelId(f.getReferenceEntityName())
						.referenceTitleFromRecord(f.getReferenceTitleFromRecord())
						.referenceTitles(f.getReferenceTitles())
						.referenceMultiple(f.getReferenceMultiple())
						.longText(f.getLongText())
						.build()).collect(Collectors.toList()));
		
		generatorStarter.generate(dm);
		
	}
}
