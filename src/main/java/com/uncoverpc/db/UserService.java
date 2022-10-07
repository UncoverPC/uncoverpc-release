package com.uncoverpc.db;

import java.util.concurrent.ExecutionException;

import org.springframework.stereotype.Service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import com.uncoverpc.model.user.User;

@Service
public class UserService {

    private static final String COLLECTION_NAME = "users";

    public String saveUser(User user) throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection(COLLECTION_NAME).document(user.getEmail())
                .set(user);
        return collectionsApiFuture.get().getUpdateTime().toString();
    }

    public User getUserDetails(String email) throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        DocumentReference documentReference = dbFirestore.collection(COLLECTION_NAME).document(email);
        ApiFuture<DocumentSnapshot> future = documentReference.get();

        DocumentSnapshot document = future.get();

        if (document.exists()) {
            User user = document.toObject(User.class);
            return user;
        }
        return null;
    }

    public String updateUser(User user) throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection(COLLECTION_NAME).document(user.getEmail())
                .set(user);
        return collectionsApiFuture.get().getUpdateTime().toString();
    }

    public String deleteUser(User user) throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection(COLLECTION_NAME).document(user.getEmail())
                .delete();
        return "User with email " + user.getEmail() + " has been successfully deleted -- "
                + collectionsApiFuture.get().getUpdateTime().toString();
    }
	
}
