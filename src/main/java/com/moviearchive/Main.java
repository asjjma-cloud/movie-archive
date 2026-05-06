package com.moviearchive;

import com.moviearchive.domain.Movie;
import com.moviearchive.domain.Review;
import com.moviearchive.domain.User;
import com.moviearchive.repository.MovieRepository;
import com.moviearchive.repository.ReviewRepository;
import com.moviearchive.repository.UserRepository;
import com.moviearchive.service.MovieArchiveService;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        MovieArchiveService service = new MovieArchiveService(
                new UserRepository(),
                new MovieRepository(),
                new ReviewRepository()
        );

        User minsu = service.registerUser("minsu", "minsu@moviearchive.com", "password1");
        User yuna = service.registerUser("yuna", "yuna@moviearchive.com", "password2");

        service.follow(minsu.getId(), yuna.getId());

        Movie yunaMovie = service.addMovie(
                yuna.getId(),
                "Interstellar",
                "Sci-Fi",
                LocalDate.of(2026, 5, 1)
        );

        Review yunaReview = service.addReview(
                yunaMovie.getId(),
                yuna.getId(),
                "압도적인 영상미와 음악이 인상적이었습니다.",
                5
        );

        service.updateReview(yunaReview.getId(), "다시 봐도 최고의 SF 영화입니다.", 5);

        System.out.println("[팔로우 피드]");
        for (MovieArchiveService.MovieFeedItem item : service.getFollowedUsersFeed(minsu.getId())) {
            System.out.printf("- %s님의 영화: %s (%s, %s)%n",
                    item.username(),
                    item.movie().getTitle(),
                    item.movie().getGenre(),
                    item.movie().getWatchedDate());

            for (Review review : item.reviews()) {
                System.out.printf("  리뷰(%d점): %s%n", review.getRating(), review.getContent());
            }
        }
    }
}
