---
marp: true
theme: default
paginate: true
---

# 03. Project Structure

## 4계층 아키텍처

> View → Controller → Service → DAO → DB 단방향 의존성 유지

---

## View 계층

| 파일 | 역할 |
|------|------|
| LoginFrame | 로그인 화면 |
| RegisterDialog | 회원가입 다이얼로그 |
| MainFrame | 메인 프레임 + 네비게이션 바 |
| MovieListPanel | 영화 목록·검색·장르 필터·정렬 |
| MovieDetailPanel | 영화 상세·평균 별점·리뷰 목록 |
| ReviewDialog | 리뷰 작성 다이얼로그 |
| MyPagePanel | 내 보관함·정보 수정·탈퇴 |
| AdminPanel | 관리자 패널 (영화·회원 관리) |

---

## Controller / Service 계층

| 파일 | 역할 |
|------|------|
| AuthController | 로그인·회원가입·세션 관리 |
| MovieController | 영화 조회·검색·필터·CRUD |
| ReviewController | 리뷰 작성·수정·삭제·조회 |
| MovieArchiveService | 회원 인증·비밀번호 해시·중복 검증 |
| MovieService | 영화 조회·검색·필터·평균 별점 |
| ReviewService | 리뷰 CRUD·중복 방지·권한 검증 |

---

## DAO / Util 계층

| 파일 | 역할 |
|------|------|
| UserDAO | 회원 CRUD·아이디/이메일 조회 |
| MovieDAO | 영화 CRUD·검색 쿼리·AVG 별점 |
| ReviewDAO | 리뷰 CRUD·중복 확인·유저별 삭제 |
| DBConnection | SQLite 싱글톤 커넥션·절대경로 |
| PasswordUtil | SHA-256 해시 유틸 |

---

## DB 설계 — ERD

---

## DB 설계 — 테이블 명세

| 테이블 | 주요 컬럼 |
|--------|-----------|
| users | id(PK), username(UNIQUE), email(UNIQUE), password(SHA-256), nickname, role, is_active |
| movies | id(PK), title, director, genre, release_year, overview, poster_path |
| reviews | id(PK), user_id(FK), movie_id(FK), rating(1~5), content, created_at, updated_at |

---

## DB 설계 — 인덱스

| 인덱스 | 대상 컬럼 |
|--------|-----------|
| idx_movies_title | movies(title) |
| idx_movies_genre | movies(genre) |
| idx_movies_director | movies(director) |
| idx_reviews_movie | reviews(movie_id) |
| idx_reviews_user | reviews(user_id) |
