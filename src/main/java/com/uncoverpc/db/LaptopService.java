package com.uncoverpc.db;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Service;

import com.uncoverpc.product.Laptop;

@Service
public interface LaptopService extends MongoRepository<Laptop, String>{
	
	@Query("{'QuizResponses.Main use?' : ?0}")
	List<Laptop> findbyLaptopUse(String use);
}
