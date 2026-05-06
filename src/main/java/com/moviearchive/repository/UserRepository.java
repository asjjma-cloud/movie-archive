package com.moviearchive.repository;

import com.moviearchive.domain.User;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public class UserRepository {
    private final Map<UUID, User> users = new LinkedHashMap<>();

    public User save(User user) {
        users.put(user.getId(), user);
        return user;
    }

    public Optional<User> findById(UUID id) {
        return Optional.ofNullable(users.get(id));
    }
}
