# 🎬 Movie Archive

> 내가 본 영화를 기록하고, 리뷰와 별점을 남기며, 친구들의 영화 취향을 팔로우하는 영화 아카이브 서비스

---

## 📌 프로젝트 소개

**Movie Archive**는 자신이 본 영화를 직접 등록하고, 리뷰와 별점을 작성하며 영화 감상 기록을 관리할 수 있는 Java 기반 애플리케이션입니다.
팔로우 기능을 통해 다른 사용자의 영화 기록과 리뷰를 확인하고, 취향이 비슷한 사람들과 영화 감상을 공유할 수 있습니다.

---

## ✨ 주요 기능

### 🎥 영화 관리
- 본 영화 직접 등록 / 수정 / 삭제
- 영화 제목, 장르, 감상 날짜 등 기본 정보 기록

### ⭐ 리뷰 & 별점
- 등록한 영화에 리뷰 작성
- 별점(1~5점) 평가
- 리뷰 수정 및 삭제

### 👥 팔로우
- 다른 사용자 팔로우 / 언팔로우
- 팔로우한 사용자의 영화 기록 및 리뷰 열람
- 팔로워 / 팔로잉 목록 조회

---

## 🛠 기술 스택

| 분류 | 기술 |
|------|------|
| Language | Java 17 |
| Build Tool | Gradle / Maven |
| Database | MySQL / SQLite |
| ORM | JDBC / JPA |

---

## 📂 프로젝트 구조

```
movie-archive/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/moviearchive/
│   │   │       ├── domain/         # Entity 클래스
│   │   │       ├── repository/     # DB 접근 계층
│   │   │       ├── service/        # 비즈니스 로직
│   │   │       └── Main.java
│   │   └── resources/
│   │       └── application.properties
│   └── test/
├── build.gradle
└── README.md
```

---

## 🗃 ERD

```
USER ──< FOLLOW >── USER
 │
 └──< MOVIE
        │
        └──< REVIEW
```

| 테이블 | 주요 컬럼 |
|--------|-----------|
| USER | id, username, email, password |
| MOVIE | id, user_id, title, genre, watched_date |
| REVIEW | id, movie_id, content, rating, created_at |
| FOLLOW | follower_id, following_id |

---

## 🚀 실행 방법

```bash
# 1. 레포지토리 클론
git clone https://github.com/archimoo/movie-archive.git

# 2. 프로젝트 디렉토리 이동
cd movie-archive

# 3. 빌드
./gradlew build

# 4. 실행
./gradlew run
```

---

## 👥 팀 소개

**팀명: Archimoo**
| Archive와 movie의 합성어

| 이름 | 학번 | 역할 |
|------|------|------|
| 👑 최영재 (팀장) | 20220519 | 백엔드, DB, 테스트 |
| 남하경 | 20240609 | 백엔드, 프론트엔드, 테스트 |
