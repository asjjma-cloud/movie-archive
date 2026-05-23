package main.repository;

import main.domain.Review;
import main.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReviewDAO {

    // 리뷰 등록
    public void insert(Review review) throws SQLException {
        String sql = "INSERT INTO reviews (user_id, movie_id, rating, content) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, review.getUserId());
            pstmt.setInt(2, review.getMovieId());
            pstmt.setInt(3, review.getRating());
            pstmt.setString(4, review.getContent());
            pstmt.executeUpdate();
        }
    }

    // 영화별 리뷰 전체 조회
    public List<Review> findByMovieId(int movieId) throws SQLException {
        String sql = "SELECT * FROM reviews WHERE movie_id = ? ORDER BY created_at DESC";
        List<Review> reviews = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, movieId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) reviews.add(mapRow(rs));
        }
        return reviews;
    }

    // 내 리뷰 전체 조회
    public List<Review> findByUserId(int userId) throws SQLException {
        String sql = "SELECT * FROM reviews WHERE user_id = ? ORDER BY created_at DESC";
        List<Review> reviews = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, userId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) reviews.add(mapRow(rs));
        }
        return reviews;
    }

    // 중복 리뷰 확인 (한 유저가 같은 영화에 리뷰 하나만)
    public boolean exists(int userId, int movieId) throws SQLException {
        String sql = "SELECT COUNT(*) FROM reviews WHERE user_id = ? AND movie_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, userId);
            pstmt.setInt(2, movieId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) return rs.getInt(1) > 0;
        }
        return false;
    }

    // 리뷰 수정
    public void update(Review review) throws SQLException {
        String sql = "UPDATE reviews SET rating = ?, content = ?, " +
                "updated_at = datetime('now') WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, review.getRating());
            pstmt.setString(2, review.getContent());
            pstmt.setInt(3, review.getId());
            pstmt.executeUpdate();
        }
    }

    // 리뷰 삭제
    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM reviews WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        }
    }

    // ResultSet → Review 객체 변환
    private Review mapRow(ResultSet rs) throws SQLException {
        return new Review(
                rs.getInt("id"),
                rs.getInt("user_id"),
                rs.getInt("movie_id"),
                rs.getInt("rating"),
                rs.getString("content"),
                rs.getString("created_at"),
                rs.getString("updated_at")
        );
    }
}