package com.uncoverpc.db;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import com.uncoverpc.model.quiz.Questions;
import com.uncoverpc.model.quiz.Quiz;
import com.uncoverpc.model.user.User;
import com.uncoverpc.product.Product;

@Service
public interface QuizService extends MongoRepository<Quiz, String> {

	Quiz findByQuizTitle(String quizTitle);

}
