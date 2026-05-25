package main.service;

import main.domain.Movie;
import main.repository.MovieDAO;

import java.sql.SQLException;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class MovieService {

    private final MovieDAO movieDAO = new MovieDAO();

    // 전체 영화 조회
    public List<Movie> getAllMovies() throws SQLException {
        return movieDAO.findAll();
    }

    // 영화 상세 조회
    public Movie getMovieDetail(int id) throws SQLException {
        Movie movie = movieDAO.findById(id);
        if (movie == null) {
            throw new IllegalArgumentException("존재하지 않는 영화입니다.");
        }
        // 평균 별점 세팅
        double avg = movieDAO.getAverageRating(id);
        movie.setAverageRating(avg);
        return movie;
    }

    // 장르 필터
    public List<Movie> filterByGenre(String genre) throws SQLException {
        return movieDAO.findByGenre(genre);
    }

    // 제목 검색
    public List<Movie> searchByTitle(String keyword) throws SQLException {
        if (keyword == null || keyword.trim().isEmpty()) {
            throw new IllegalArgumentException("검색어를 입력해주세요.");
        }
        return movieDAO.searchByTitle(keyword.trim());
    }

    // 감독 검색
    public List<Movie> searchByDirector(String keyword) throws SQLException {
        if (keyword == null || keyword.trim().isEmpty()) {
            throw new IllegalArgumentException("검색어를 입력해주세요.");
        }
        return movieDAO.searchByDirector(keyword.trim());
    }

    // 평점 높은 순 TOP 5
    public List<Movie> getTopRatedMovies() throws SQLException {
        List<Movie> movies = movieDAO.findAll();
        for (Movie m : movies) {
            m.setAverageRating(movieDAO.getAverageRating(m.getId()));
        }
        return movies.stream()
                .sorted(Comparator.comparingDouble(Movie::getAverageRating).reversed())
                .limit(5)
                .collect(Collectors.toList());
    }

    // 영화 등록 (관리자)
    public void addMovie(String title, String director, String genre,
                         int releaseYear, String overview, String posterPath) throws SQLException {
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("영화 제목을 입력해주세요.");
        }
        Movie movie = new Movie(title, director, genre, releaseYear, overview, posterPath);
        movieDAO.insert(movie);
    }

    // 영화 수정 (관리자)
    public void updateMovie(Movie movie) throws SQLException {
        movieDAO.update(movie);
    }

    // 영화 삭제 (관리자)
    public void deleteMovie(int id) throws SQLException {
        movieDAO.delete(id);
    }
}