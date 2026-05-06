package com.moviearchive.domain;

import java.time.LocalDate;
import java.util.UUID;

public class Movie {
    private final UUID id;
    private final UUID userId;
    private String title;
    private String genre;
    private LocalDate watchedDate;

    public Movie(UUID userId, String title, String genre, LocalDate watchedDate) {
        this.id = UUID.randomUUID();
        this.userId = userId;
        this.title = title;
        this.genre = genre;
        this.watchedDate = watchedDate;
    }

    public UUID getId() {
        return id;
    }

    public UUID getUserId() {
        return userId;
    }

    public String getTitle() {
        return title;
    }

    public String getGenre() {
        return genre;
    }

    public LocalDate getWatchedDate() {
        return watchedDate;
    }

    public void update(String title, String genre, LocalDate watchedDate) {
        this.title = title;
        this.genre = genre;
        this.watchedDate = watchedDate;
    }
}
