package com.moviearchive.repository;

import com.moviearchive.domain.Movie;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public class MovieRepository {
    private final Map<UUID, Movie> movies = new LinkedHashMap<>();

    public Movie save(Movie movie) {
        movies.put(movie.getId(), movie);
        return movie;
    }

    public Optional<Movie> findById(UUID movieId) {
        return Optional.ofNullable(movies.get(movieId));
    }

    public void deleteById(UUID movieId) {
        movies.remove(movieId);
    }

    public List<Movie> findByUserId(UUID userId) {
        List<Movie> result = new ArrayList<>();
        for (Movie movie : movies.values()) {
            if (movie.getUserId().equals(userId)) {
                result.add(movie);
            }
        }
        return result;
    }
}
