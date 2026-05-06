package com.moviearchive.domain;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;

public class User {
    private final UUID id;
    private final String username;
    private final String email;
    private final String password;
    private final Set<UUID> followerIds = new LinkedHashSet<>();
    private final Set<UUID> followingIds = new LinkedHashSet<>();

    public User(String username, String email, String password) {
        this.id = UUID.randomUUID();
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public UUID getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Set<UUID> getFollowerIds() {
        return Collections.unmodifiableSet(followerIds);
    }

    public Set<UUID> getFollowingIds() {
        return Collections.unmodifiableSet(followingIds);
    }

    public void addFollower(UUID userId) {
        followerIds.add(userId);
    }

    public void removeFollower(UUID userId) {
        followerIds.remove(userId);
    }

    public void addFollowing(UUID userId) {
        followingIds.add(userId);
    }

    public void removeFollowing(UUID userId) {
        followingIds.remove(userId);
    }
}
