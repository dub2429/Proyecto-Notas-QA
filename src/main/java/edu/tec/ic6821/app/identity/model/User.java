package edu.tec.ic6821.app.identity.model;

import java.util.Objects;
import java.util.Optional;

public class User {

    private final Optional<Long> id;
    private final String username;
    private final String password;

    public User(Long id, String username, String password) {
        this.id = Optional.of(id);
        this.username = username;
        this.password = password;
    }

    public User(String username, String password) {
        this.id = Optional.empty();
        this.username = username;
        this.password = password;
    }

    public Optional<Long> getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return Objects.equals(getId(), user.getId()) &&
                Objects.equals(getUsername(), user.getUsername()) &&
                Objects.equals(getPassword(), user.getPassword());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getId(), getUsername(), getPassword());
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}