package main.service;

import main.domain.Review;
import main.repository.ReviewDAO;

import java.sql.SQLException;
import java.util.List;

public class ReviewService {

    private final ReviewDAO reviewDAO = new ReviewDAO();

    // 리뷰 작성
    public void addReview(int userId, int movieId, int rating, String content) throws SQLException {
        // 중복 리뷰 확인
        if (reviewDAO.exists(userId, movieId)) {
            throw new IllegalArgumentException("이미 리뷰를 작성한 영화입니다.");
        }
        // 별점 검증은 Review 생성자에서 처리
        Review review = new Review(userId, movieId, rating, content);
        reviewDAO.insert(review);
    }

    // 영화별 리뷰 조회
    public List<Review> getReviewsByMovie(int movieId) throws SQLException {
        return reviewDAO.findByMovieId(movieId);
    }

    // 내 리뷰 조회
    public List<Review> getMyReviews(int userId) throws SQLException {
        return reviewDAO.findByUserId(userId);
    }

    // 리뷰 수정
    public void updateReview(int reviewId, int userId, int rating, String content) throws SQLException {
        Review review = reviewDAO.findById(reviewId);
        if (review == null) {
            throw new IllegalArgumentException("존재하지 않는 리뷰입니다.");
        }
        // 작성자 본인 확인
        if (review.getUserId() != userId) {
            throw new IllegalArgumentException("본인의 리뷰만 수정할 수 있습니다.");
        }
        review.update(rating, content);
        reviewDAO.update(review);
    }

    // 리뷰 삭제
    public void deleteReview(int reviewId, int userId, boolean isAdmin) throws SQLException {
        Review review = reviewDAO.findById(reviewId);
        if (review == null) {
            throw new IllegalArgumentException("존재하지 않는 리뷰입니다.");
        }
        // 관리자이거나 본인 리뷰만 삭제 가능
        if (!isAdmin && review.getUserId() != userId) {
            throw new IllegalArgumentException("본인의 리뷰만 삭제할 수 있습니다.");
        }
        reviewDAO.delete(reviewId);
    }
}