package main.domain;

public class Review {
    private int id;
    private int userId;
    private int movieId;
    private int rating;
    private String content;
    private String createdAt;
    private String updatedAt;
    private String nickname;

    // 생성자 (DB에서 불러올 때)
    public Review(int id, int userId, int movieId,
                  int rating, String content, String createdAt, String updatedAt) {
        this.id = id;
        this.userId = userId;
        this.movieId = movieId;
        this.rating = rating;
        this.content = content;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    // 생성자 (새 리뷰 작성할 때)
    public Review(int userId, int movieId, int rating, String content) {
        validateRating(rating);
        this.userId = userId;
        this.movieId = movieId;
        this.rating = rating;
        this.content = content;
    }

    // Getter
    public int getId() { return id; }
    public int getUserId() { return userId; }
    public int getMovieId() { return movieId; }
    public int getRating() { return rating; }
    public String getContent() { return content; }
    public String getCreatedAt() { return createdAt; }
    public String getUpdatedAt() { return updatedAt; }
    public String getNickname() { return nickname; }

    // Setter
    public void setId(int id) { this.id = id; }
    public void setRating(int rating) { this.rating = rating; }
    public void setContent(String content) { this.content = content; }
    public void setNickname(String nickname) { this.nickname = nickname; }

    // 리뷰 수정
    public void update(int rating, String content) {
        validateRating(rating);
        this.rating = rating;
        this.content = content;
    }

    // 별점 검증
    private static void validateRating(int rating) {
        if (rating < 1 || rating > 5) {
            throw new IllegalArgumentException("별점은 1~5 사이여야 합니다.");
        }
    }

    @Override
    public String toString() {
        return "별점: " + rating + "/5 - " + content;
    }
}