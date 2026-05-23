package main.domain;

public class Movie {
    private int id;
    private String title;
    private String director;
    private String genre;
    private int releaseYear;
    private String overview;
    private String posterPath;
    private String createdAt;
    private double averageRating;

    // 생성자 (DB에서 불러올 때)
    public Movie(int id, String title, String director, String genre,
                 int releaseYear, String overview, String posterPath, String createdAt) {
        this.id = id;
        this.title = title;
        this.director = director;
        this.genre = genre;
        this.releaseYear = releaseYear;
        this.overview = overview;
        this.posterPath = posterPath;
        this.createdAt = createdAt;
    }

    // 생성자 (새 영화 등록할 때)
    public Movie(String title, String director, String genre,
                 int releaseYear, String overview, String posterPath) {
        this.title = title;
        this.director = director;
        this.genre = genre;
        this.releaseYear = releaseYear;
        this.overview = overview;
        this.posterPath = posterPath;
    }

    // Getter / Setter
    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getDirector() { return director; }
    public String getGenre() { return genre; }
    public int getReleaseYear() { return releaseYear; }
    public String getOverview() { return overview; }
    public String getPosterPath() { return posterPath; }
    public String getCreatedAt() { return createdAt; }
    public double getAverageRating() { return averageRating; }

    public void setId(int id) { this.id = id; }
    public void setTitle(String title) { this.title = title; }
    public void setDirector(String director) { this.director = director; }
    public void setGenre(String genre) { this.genre = genre; }
    public void setReleaseYear(int releaseYear) { this.releaseYear = releaseYear; }
    public void setOverview(String overview) { this.overview = overview; }
    public void setPosterPath(String posterPath) { this.posterPath = posterPath; }
    public void setAverageRating(double averageRating) { this.averageRating = averageRating; }

    @Override
    public String toString() {
        return title + " (" + releaseYear + ") - " + director;
    }
}