package com.linagora.coding.exercise.model;

import java.util.UUID;

import com.google.common.base.Objects;


public class User {
    private UUID id;
    private String name;
    private String email;

    public User() {}

    public User(UUID id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equal(id, user.id) &&
            Objects.equal(name, user.name) &&
            Objects.equal(email, user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id, name, email);
    }
}
