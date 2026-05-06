package com.moviearchive.repository;

import com.moviearchive.domain.Review;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public class ReviewRepository {
    private final Map<UUID, Review> reviews = new LinkedHashMap<>();

    public Review save(Review review) {
        reviews.put(review.getId(), review);
        return review;
    }

    public Optional<Review> findById(UUID reviewId) {
        return Optional.ofNullable(reviews.get(reviewId));
    }

    public void deleteById(UUID reviewId) {
        reviews.remove(reviewId);
    }

    public List<Review> findByMovieId(UUID movieId) {
        List<Review> result = new ArrayList<>();
        for (Review review : reviews.values()) {
            if (review.getMovieId().equals(movieId)) {
                result.add(review);
            }
        }
        return result;
    }
}
