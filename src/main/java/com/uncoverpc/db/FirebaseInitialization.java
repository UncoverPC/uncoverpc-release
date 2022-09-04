package com.uncoverpc.db;

import java.io.FileInputStream;
import java.io.InputStream;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

@Service
public class FirebaseInitialization {

    @PostConstruct
    public void initialization() {
        InputStream serviceAccount;
        try {
            serviceAccount = this.getClass().getClassLoader().getResourceAsStream("serviceAccountKey.json");
            
            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount)).build();
            FirebaseApp.initializeApp(options);
        } catch (Exception e) {
            System.err.println("FirebaseApp Error -- " + e.getLocalizedMessage());
        }
    }
	
}
