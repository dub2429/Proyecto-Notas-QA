package edu.tec.ic6821.app.identity.service;

import edu.tec.ic6821.app.identity.model.User;

import java.util.Optional;

public interface UserService {
    Optional<User> create(String username, String password);
}
