-- 사용자 테이블
CREATE TABLE IF NOT EXISTS users (
                                     id          INTEGER PRIMARY KEY AUTOINCREMENT,
                                     username    TEXT    NOT NULL UNIQUE,
                                     email       TEXT    NOT NULL UNIQUE,
                                     password    TEXT    NOT NULL,
                                     nickname    TEXT    NOT NULL,
                                     role        TEXT    NOT NULL DEFAULT 'user',
                                     is_active   INTEGER NOT NULL DEFAULT 1,
                                     created_at  TEXT    NOT NULL DEFAULT (datetime('now'))
    );

-- 영화 테이블
CREATE TABLE IF NOT EXISTS movies (
                                      id           INTEGER PRIMARY KEY AUTOINCREMENT,
                                      title        TEXT    NOT NULL,
                                      director     TEXT,
                                      genre        TEXT,
                                      release_year INTEGER,
                                      overview     TEXT,
                                      poster_path  TEXT,
                                      created_at   TEXT    NOT NULL DEFAULT (datetime('now'))
    );

-- 리뷰 테이블
CREATE TABLE IF NOT EXISTS reviews (
                                       id         INTEGER PRIMARY KEY AUTOINCREMENT,
                                       user_id    INTEGER NOT NULL,
                                       movie_id   INTEGER NOT NULL,
                                       rating     INTEGER NOT NULL CHECK(rating BETWEEN 1 AND 5),
    content    TEXT,
    created_at TEXT NOT NULL DEFAULT (datetime('now', '+9 hours')),
    updated_at TEXT NOT NULL DEFAULT (datetime('now', '+9 hours')),
    FOREIGN KEY (user_id)  REFERENCES users(id),
    FOREIGN KEY (movie_id) REFERENCES movies(id),
    UNIQUE (user_id, movie_id)
    );

-- 검색 성능 최적화 인덱스
CREATE INDEX IF NOT EXISTS idx_movies_title    ON movies(title);
CREATE INDEX IF NOT EXISTS idx_movies_genre    ON movies(genre);
CREATE INDEX IF NOT EXISTS idx_movies_director ON movies(director);
CREATE INDEX IF NOT EXISTS idx_reviews_movie   ON reviews(movie_id);
CREATE INDEX IF NOT EXISTS idx_reviews_user    ON reviews(user_id);