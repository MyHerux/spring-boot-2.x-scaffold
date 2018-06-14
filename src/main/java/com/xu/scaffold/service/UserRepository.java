package com.xu.scaffold.service;

import com.xu.scaffold.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;

public interface UserRepository extends MongoRepository<User, String> {
}
