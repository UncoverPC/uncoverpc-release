package com.uncoverpc.db;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import org.springframework.stereotype.Service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import com.uncoverpc.product.Product;
import com.uncoverpc.quiz.Questions;
import com.uncoverpc.quiz.Quiz;

@Service
public class QuizService {

	private static final String COLLECTION_NAME = "quizzes";

	/**
	 * Retrieve quiz
	 * 
	 * @param quizName
	 * @return quiz
	 */
	public Object getQuiz(String quizName) {
		try {
			Firestore dbFirestore = FirestoreClient.getFirestore();
			DocumentReference documentReference = dbFirestore.collection(COLLECTION_NAME).document(quizName);
			ApiFuture<DocumentSnapshot> future = documentReference.get();

			DocumentSnapshot document = future.get();
			return document;
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public String createQuiz(Quiz quiz) throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        System.out.println(quiz);
        Questions questions = new Questions(quiz.getQuestions());
        ApiFuture<WriteResult> collectionsApiFuture = 
        		dbFirestore.collection(COLLECTION_NAME).document(quiz.getTitle()).set(questions);
        return collectionsApiFuture.get().getUpdateTime().toString();
	}

}
