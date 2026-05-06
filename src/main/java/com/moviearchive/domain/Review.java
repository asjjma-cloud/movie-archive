package com.moviearchive.domain;

import java.time.LocalDateTime;
import java.util.UUID;

public class Review {
    private final UUID id;
    private final UUID movieId;
    private final UUID userId;
    private String content;
    private int rating;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Review(UUID movieId, UUID userId, String content, int rating) {
        validateRating(rating);
        this.id = UUID.randomUUID();
        this.movieId = movieId;
        this.userId = userId;
        this.content = content;
        this.rating = rating;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = createdAt;
    }

    public UUID getId() {
        return id;
    }

    public UUID getMovieId() {
        return movieId;
    }

    public UUID getUserId() {
        return userId;
    }

    public String getContent() {
        return content;
    }

    public int getRating() {
        return rating;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void update(String content, int rating) {
        validateRating(rating);
        this.content = content;
        this.rating = rating;
        this.updatedAt = LocalDateTime.now();
    }

    private static void validateRating(int rating) {
        if (rating < 1 || rating > 5) {
            throw new IllegalArgumentException("Rating must be between 1 and 5.");
        }
    }
}
