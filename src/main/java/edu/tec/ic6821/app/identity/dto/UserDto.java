package edu.tec.ic6821.app.identity.dto;

import edu.tec.ic6821.app.identity.model.User;

import java.util.Optional;

public class UserDto {

    private final Optional<Long> id;
    private final String username;

    public static UserDto from(User user) {
        return new UserDto(user.getId(), user.getUsername());
    }

    private UserDto(Optional<Long> id, String username) {
        this.id = id;
        this.username = username;
    }

    public Optional<Long> getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }
}
