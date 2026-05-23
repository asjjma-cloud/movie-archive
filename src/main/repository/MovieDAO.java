package main.repository;

import main.domain.Movie;
import main.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MovieDAO {

    // 영화 등록
    public void insert(Movie movie) throws SQLException {
        String sql = "INSERT INTO movies (title, director, genre, release_year, overview, poster_path) " +
                "VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, movie.getTitle());
            pstmt.setString(2, movie.getDirector());
            pstmt.setString(3, movie.getGenre());
            pstmt.setInt(4, movie.getReleaseYear());
            pstmt.setString(5, movie.getOverview());
            pstmt.setString(6, movie.getPosterPath());
            pstmt.executeUpdate();
        }
    }

    // 전체 영화 조회
    public List<Movie> findAll() throws SQLException {
        String sql = "SELECT * FROM movies ORDER BY created_at DESC";
        List<Movie> movies = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) movies.add(mapRow(rs));
        }
        return movies;
    }

    // ID로 조회
    public Movie findById(int id) throws SQLException {
        String sql = "SELECT * FROM movies WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) return mapRow(rs);
        }
        return null;
    }

    // 장르로 필터링
    public List<Movie> findByGenre(String genre) throws SQLException {
        String sql = "SELECT * FROM movies WHERE genre = ? ORDER BY created_at DESC";
        List<Movie> movies = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, genre);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) movies.add(mapRow(rs));
        }
        return movies;
    }

    // 제목으로 검색
    public List<Movie> searchByTitle(String keyword) throws SQLException {
        String sql = "SELECT * FROM movies WHERE title LIKE ? ORDER BY created_at DESC";
        List<Movie> movies = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, "%" + keyword + "%");
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) movies.add(mapRow(rs));
        }
        return movies;
    }

    // 감독으로 검색
    public List<Movie> searchByDirector(String keyword) throws SQLException {
        String sql = "SELECT * FROM movies WHERE director LIKE ? ORDER BY created_at DESC";
        List<Movie> movies = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, "%" + keyword + "%");
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) movies.add(mapRow(rs));
        }
        return movies;
    }

    // 평균 별점 조회
    public double getAverageRating(int movieId) throws SQLException {
        String sql = "SELECT AVG(rating) FROM reviews WHERE movie_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, movieId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) return rs.getDouble(1);
        }
        return 0.0;
    }

    // 영화 수정
    public void update(Movie movie) throws SQLException {
        String sql = "UPDATE movies SET title = ?, director = ?, genre = ?, " +
                "release_year = ?, overview = ?, poster_path = ? WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, movie.getTitle());
            pstmt.setString(2, movie.getDirector());
            pstmt.setString(3, movie.getGenre());
            pstmt.setInt(4, movie.getReleaseYear());
            pstmt.setString(5, movie.getOverview());
            pstmt.setString(6, movie.getPosterPath());
            pstmt.setInt(7, movie.getId());
            pstmt.executeUpdate();
        }
    }

    // 영화 삭제
    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM movies WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        }
    }

    // ResultSet → Movie 객체 변환
    private Movie mapRow(ResultSet rs) throws SQLException {
        return new Movie(
                rs.getInt("id"),
                rs.getString("title"),
                rs.getString("director"),
                rs.getString("genre"),
                rs.getInt("release_year"),
                rs.getString("overview"),
                rs.getString("poster_path"),
                rs.getString("created_at")
        );
    }
}