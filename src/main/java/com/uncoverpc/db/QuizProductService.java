package com.uncoverpc.db;

import java.util.HashMap;
import java.util.concurrent.ExecutionException;

import org.springframework.stereotype.Service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import com.uncoverpc.product.Product;

@Service
public class QuizProductService {
	
	public String addProduct(String quizName, Product product) throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionsApiFuture = 
        		dbFirestore.collection(getCollectionName(quizName)).document().set(product);
        return collectionsApiFuture.get().getUpdateTime().toString();
	}
	
	public String getCollectionName(String quizName) {
		return quizName.split("\\s+")[0] + " Answer Bank";
	}

	
}
