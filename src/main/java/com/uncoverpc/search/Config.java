package com.uncoverpc.search;

import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;


@SuppressWarnings("deprecation")
@Configuration
@EnableElasticsearchRepositories(basePackages = "com.uncoverpc.search.ArticleRepository") 
@ComponentScan(basePackages = { "com.uncoverpc.search.service" })
public class Config {
	
	private ArticleRepository articleRepository;
	
	@Bean
	public RestHighLevelClient client() {
		ClientConfiguration clientConfiguration 
        = ClientConfiguration.builder()
            .connectedTo("localhost:9200")
            .build();
		return RestClients.create(clientConfiguration).rest();
	}
	
	@Bean
	public ElasticsearchOperations esTemplate() {
		return new ElasticsearchRestTemplate(client());
	}
	
	public Page<Article> searchKeyword(String keyword) {
		Page<Article> query
		  = articleRepository.findByContents(keyword, PageRequest.of(0, 10));
		return query;
	}
	
	public void addArticle(Article article) {
		ElasticsearchOperations esSearch = esTemplate();
		esSearch.indexOps(Article.class).create();
		articleRepository.save(article);
	}
}
