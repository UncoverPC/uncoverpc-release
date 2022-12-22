package com.uncoverpc.search;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;


public interface ArticleRepository extends ElasticsearchRepository<Article, String>{
	
	Page<Article> findbyContents(String content, Pageable pageable);
	
	//@Query("{\"bool\": {\"con\": [{\"match\": {\"authors.name\": \"?0\"}}]}}")
    Page<Article> findByContentsUsingCustomQuery(String name, Pageable pageable);

	Page<Article> findByContents(String keyword, PageRequest of);
	
}
