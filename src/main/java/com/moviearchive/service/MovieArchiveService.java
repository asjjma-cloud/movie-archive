package com.moviearchive.service;

import com.moviearchive.domain.Movie;
import com.moviearchive.domain.Review;
import com.moviearchive.domain.User;
import com.moviearchive.repository.MovieRepository;
import com.moviearchive.repository.ReviewRepository;
import com.moviearchive.repository.UserRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MovieArchiveService {
    private final UserRepository userRepository;
    private final MovieRepository movieRepository;
    private final ReviewRepository reviewRepository;

    public MovieArchiveService(UserRepository userRepository, MovieRepository movieRepository, ReviewRepository reviewRepository) {
        this.userRepository = userRepository;
        this.movieRepository = movieRepository;
        this.reviewRepository = reviewRepository;
    }

    public User registerUser(String username, String email, String password) {
        User user = new User(username, email, password);
        return userRepository.save(user);
    }

    public void follow(UUID followerId, UUID followingId) {
        if (followerId.equals(followingId)) {
            throw new IllegalArgumentException("You cannot follow yourself.");
        }

        User follower = getUserOrThrow(followerId);
        User following = getUserOrThrow(followingId);
        follower.addFollowing(followingId);
        following.addFollower(followerId);
    }

    public void unfollow(UUID followerId, UUID followingId) {
        User follower = getUserOrThrow(followerId);
        User following = getUserOrThrow(followingId);
        follower.removeFollowing(followingId);
        following.removeFollower(followerId);
    }

    public Movie addMovie(UUID userId, String title, String genre, LocalDate watchedDate) {
        getUserOrThrow(userId);
        return movieRepository.save(new Movie(userId, title, genre, watchedDate));
    }

    public Movie updateMovie(UUID movieId, String title, String genre, LocalDate watchedDate) {
        Movie movie = getMovieOrThrow(movieId);
        movie.update(title, genre, watchedDate);
        return movie;
    }

    public void deleteMovie(UUID movieId) {
        movieRepository.findById(movieId).ifPresent(movie -> {
            for (Review review : reviewRepository.findByMovieId(movieId)) {
                reviewRepository.deleteById(review.getId());
            }
            movieRepository.deleteById(movieId);
        });
    }

    public Review addReview(UUID movieId, UUID userId, String content, int rating) {
        getMovieOrThrow(movieId);
        getUserOrThrow(userId);
        return reviewRepository.save(new Review(movieId, userId, content, rating));
    }

    public Review updateReview(UUID reviewId, String content, int rating) {
        Review review = getReviewOrThrow(reviewId);
        review.update(content, rating);
        return review;
    }

    public void deleteReview(UUID reviewId) {
        if (reviewRepository.findById(reviewId).isEmpty()) {
            throw new IllegalArgumentException("Review not found: " + reviewId);
        }
        reviewRepository.deleteById(reviewId);
    }

    public List<Movie> getMoviesByUser(UUID userId) {
        getUserOrThrow(userId);
        return movieRepository.findByUserId(userId);
    }

    public List<Review> getReviewsByMovie(UUID movieId) {
        getMovieOrThrow(movieId);
        return reviewRepository.findByMovieId(movieId);
    }

    public List<MovieFeedItem> getFollowedUsersFeed(UUID userId) {
        User user = getUserOrThrow(userId);
        List<MovieFeedItem> feed = new ArrayList<>();

        for (UUID followingId : user.getFollowingIds()) {
            User followedUser = getUserOrThrow(followingId);
            List<Movie> movies = movieRepository.findByUserId(followingId);
            for (Movie movie : movies) {
                List<Review> reviews = reviewRepository.findByMovieId(movie.getId());
                feed.add(new MovieFeedItem(followedUser.getUsername(), movie, reviews));
            }
        }

        return feed;
    }

    private User getUserOrThrow(UUID userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found: " + userId));
    }

    private Movie getMovieOrThrow(UUID movieId) {
        return movieRepository.findById(movieId)
                .orElseThrow(() -> new IllegalArgumentException("Movie not found: " + movieId));
    }

    private Review getReviewOrThrow(UUID reviewId) {
        return reviewRepository.findById(reviewId)
                .orElseThrow(() -> new IllegalArgumentException("Review not found: " + reviewId));
    }

    public record MovieFeedItem(String username, Movie movie, List<Review> reviews) {
    }
}
