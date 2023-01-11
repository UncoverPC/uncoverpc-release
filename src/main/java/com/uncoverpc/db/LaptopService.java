package com.uncoverpc.db;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.uncoverpc.product.Laptop;

public interface LaptopService extends MongoRepository<Laptop, String>{
	
	@Query("{'specs.use' : ?0}")
	List<Laptop> findbyLaptopUse(String use);
}
