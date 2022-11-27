package com.uncoverpc.db;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;

import com.uncoverpc.model.user.EarlyUser;

@Service
public interface EarlyUserService extends MongoRepository<EarlyUser, String> {
	EarlyUser findByEmail(String email);
	EarlyUser findByVerificationCode(String code);
}
