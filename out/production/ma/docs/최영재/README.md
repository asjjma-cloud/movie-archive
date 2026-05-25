# 🎬 Movie Archive

> **작성자**: 최영재 | **작성일**: 2026-05-23

---

## 프로젝트 개요

| 항목 | 내용 |
|------|------|
| **프로젝트명** | Movie Archive |
| **목적** | 사용자가 감상한 영화를 기록하고 리뷰를 공유할 수 있는 커뮤니티형 영화 아카이브 서비스 |
| **기술 스택** | Java, Java Swing (UI), SQLite (DB), JDBC (DB 연동) |
| **팀원** | 최영재 (백엔드·DB·테스트), 남하경 (프론트엔드·백엔드·테스트) |
| **개발 기간** | 2026-04-27 ~ 2026-06-15 (약 7주) |
| **발표일** | 2026-06-15 또는 2026-06-17 |

---

## 패키지 구조

```
src/
└── main/
    ├── domain/         # 도메인 모델 (Entity)
    ├── repository/     # DAO (DB 접근)
    ├── service/        # 비즈니스 로직
    ├── controller/     # UI 이벤트 처리
    ├── view/           # Java Swing UI
    └── util/           # 공통 유틸

resources/
└── db/
    ├── schema.sql      # DB 초기화 스크립트
    └── movie_archive.db  # SQLite DB 파일 (자동 생성)
```

---

## 시스템 구조 (4계층 아키텍처)

```
View (Java Swing)
     ↓ 이벤트
Controller
     ↓ 비즈니스 로직 위임
Service
     ↓ 데이터 접근
DAO (Repository)
     ↓ JDBC
SQLite DB
```

---

## 오늘 구현한 내용 (2026-05-23)

### 1. 프로젝트 초기 세팅
- IntelliJ 프로젝트 생성
- 패키지 구조 세팅 (`domain`, `repository`, `service`, `controller`, `view`, `util`)
- SQLite JDBC 라이브러리 추가 (`org.xerial:sqlite-jdbc:3.45.3.0`)

### 2. DB 설계 및 연결

**`resources/db/schema.sql`**
- `users` 테이블 — 회원 정보 (SHA-256 해시 비밀번호, role, is_active)
- `movies` 테이블 — 영화 정보 (title, director, genre, release_year, overview, poster_path)
- `reviews` 테이블 — 리뷰 정보 (rating 1~5 검증, 유저당 영화 1개 리뷰 제한)
- 검색 성능 최적화 인덱스 5개

**`util/DBConnection.java`**
- SQLite 싱글톤 커넥션 관리
- 앱 시작 시 `schema.sql` 자동 실행으로 테이블 초기화
- DB 연결 테스트 통과 ✅

### 3. Domain 클래스

| 클래스 | 주요 필드 |
|--------|----------|
| `Movie.java` | id, title, director, genre, releaseYear, overview, posterPath |
| `User.java` | id, username, email, password, nickname, role, isActive |
| `Review.java` | id, userId, movieId, rating, content, createdAt, updatedAt |

- `Review` — 별점 범위 검증 (`validateRating`), 리뷰 수정 (`update`) 메서드 포함

### 4. DAO (Repository) 클래스

**`UserDAO.java`**
- `insert()` — 회원가입
- `findById()` — ID로 조회
- `findByEmail()` — 이메일로 조회 (로그인)
- `findAll()` — 전체 회원 조회 (관리자)
- `update()` — 회원 정보 수정
- `deactivate()` — 회원 탈퇴 (소프트 삭제)

**`MovieDAO.java`**
- `insert()` / `update()` / `delete()` — 영화 CRUD
- `findAll()` / `findById()` — 전체·단건 조회
- `findByGenre()` — 장르 필터
- `searchByTitle()` / `searchByDirector()` — 키워드 검색 (`LIKE`)
- `getAverageRating()` — 평균 별점 계산 (`AVG` 쿼리)

**`ReviewDAO.java`**
- `insert()` / `update()` / `delete()` — 리뷰 CRUD
- `findByMovieId()` — 영화별 리뷰 조회
- `findByUserId()` — 내 리뷰 조회
- `exists()` — 중복 리뷰 방지 확인

### 5. Service 클래스

**`MovieArchiveService.java`**
- `register()` — 이메일 중복 확인 + SHA-256 해시 후 회원가입
- `login()` — 이메일·비밀번호 검증, 탈퇴 계정 차단
- `updateProfile()` — 닉네임·비밀번호 수정
- `deactivate()` — 회원 탈퇴

**`util/PasswordUtil.java`**
- SHA-256 해시 암호화 유틸

---

## 다음 구현 예정

- [ ] `MovieService.java` — 영화 조회·검색·필터·평균 별점
- [ ] `ReviewService.java` — 리뷰 CRUD·중복 방지·권한 검증
- [ ] `AuthController`, `MovieController`, `ReviewController`
- [ ] Java Swing GUI (`LoginFrame`, `MainFrame`, `MovieListPanel` 등)
- [ ] 통합 테스트 및 버그 수정

---

## 진행률

```
[=======···············] 30% 완료
```

| 단계 | 상태 |
|------|------|
| 프로젝트 세팅 | ✅ 완료 |
| DB 설계 및 연결 | ✅ 완료 |
| Domain 클래스 | ✅ 완료 |
| DAO 클래스 | ✅ 완료 |
| Service 클래스 | 🔄 진행 중 |
| Controller | ⬜ 예정 |
| View (Swing GUI) | ⬜ 예정 |
| 테스트 및 버그 수정 | ⬜ 예정 |