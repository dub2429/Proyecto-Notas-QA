package edu.tec.ic6821.app.identity.service;

public interface AuthenticationService {
    String authenticate(String username, String password);
}
