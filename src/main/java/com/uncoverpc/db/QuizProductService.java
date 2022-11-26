package com.uncoverpc.db;

import java.util.HashMap;
import java.util.concurrent.ExecutionException;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import com.uncoverpc.product.Product;

@Service
public interface QuizProductService extends MongoRepository<Product, String>{
	
}
