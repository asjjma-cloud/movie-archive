package main.controller;

import main.domain.Movie;
import main.service.MovieService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MovieController {

    private final MovieService movieService = new MovieService();

    // 전체 영화 조회
    public List<Movie> getAllMovies() {
        try {
            return movieService.getAllMovies();
        } catch (SQLException e) {
            System.err.println("영화 목록 조회 실패: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    // 영화 상세 조회
    public Movie getMovieDetail(int id) {
        try {
            return movieService.getMovieDetail(id);
        } catch (IllegalArgumentException | SQLException e) {
            System.err.println("영화 상세 조회 실패: " + e.getMessage());
            return null;
        }
    }

    // 장르 필터
    public List<Movie> filterByGenre(String genre) {
        try {
            return movieService.filterByGenre(genre);
        } catch (SQLException e) {
            System.err.println("장르 필터 실패: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    // 제목 검색
    public List<Movie> searchByTitle(String keyword) {
        try {
            return movieService.searchByTitle(keyword);
        } catch (IllegalArgumentException | SQLException e) {
            System.err.println("제목 검색 실패: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    // 감독 검색
    public List<Movie> searchByDirector(String keyword) {
        try {
            return movieService.searchByDirector(keyword);
        } catch (IllegalArgumentException | SQLException e) {
            System.err.println("감독 검색 실패: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    // 평점 높은 순 TOP 5
    public List<Movie> getTopRatedMovies() {
        try {
            return movieService.getTopRatedMovies();
        } catch (SQLException e) {
            System.err.println("TOP 5 조회 실패: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    // 영화 등록 (관리자)
    public boolean addMovie(String title, String director, String genre,
                            int releaseYear, String overview, String posterPath) {
        try {
            movieService.addMovie(title, director, genre, releaseYear, overview, posterPath);
            return true;
        } catch (IllegalArgumentException | SQLException e) {
            System.err.println("영화 등록 실패: " + e.getMessage());
            return false;
        }
    }

    // 영화 수정 (관리자)
    public boolean updateMovie(Movie movie) {
        try {
            movieService.updateMovie(movie);
            return true;
        } catch (SQLException e) {
            System.err.println("영화 수정 실패: " + e.getMessage());
            return false;
        }
    }

    // 영화 삭제 (관리자)
    public boolean deleteMovie(int id) {
        try {
            movieService.deleteMovie(id);
            return true;
        } catch (SQLException e) {
            System.err.println("영화 삭제 실패: " + e.getMessage());
            return false;
        }
    }
}