package com.uncoverpc.db;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;

import com.uncoverpc.model.user.User;

@Service
public interface UserService extends MongoRepository<User, String> {

	User findByEmail(String email);
}
