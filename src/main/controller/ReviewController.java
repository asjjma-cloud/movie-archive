package main.controller;

import main.domain.Review;
import main.service.ReviewService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReviewController {

    private final ReviewService reviewService = new ReviewService();

    // 리뷰 작성
    public boolean addReview(int userId, int movieId, int rating, String content) {
        try {
            reviewService.addReview(userId, movieId, rating, content);
            return true;
        } catch (IllegalArgumentException | SQLException e) {
            System.err.println("리뷰 작성 실패: " + e.getMessage());
            return false;
        }
    }

    // 영화별 리뷰 조회
    public List<Review> getReviewsByMovie(int movieId) {
        try {
            return reviewService.getReviewsByMovie(movieId);
        } catch (SQLException e) {
            System.err.println("리뷰 조회 실패: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    // 내 리뷰 조회
    public List<Review> getMyReviews(int userId) {
        try {
            return reviewService.getMyReviews(userId);
        } catch (SQLException e) {
            System.err.println("내 리뷰 조회 실패: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    // 리뷰 수정
    public boolean updateReview(int reviewId, int userId, int rating, String content) {
        try {
            reviewService.updateReview(reviewId, userId, rating, content);
            return true;
        } catch (IllegalArgumentException | SQLException e) {
            System.err.println("리뷰 수정 실패: " + e.getMessage());
            return false;
        }
    }

    // 리뷰 삭제
    public boolean deleteReview(int reviewId, int userId, boolean isAdmin) {
        try {
            reviewService.deleteReview(reviewId, userId, isAdmin);
            return true;
        } catch (IllegalArgumentException | SQLException e) {
            System.err.println("리뷰 삭제 실패: " + e.getMessage());
            return false;
        }
    }
}