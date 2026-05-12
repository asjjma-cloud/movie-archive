# 🎬 Movie Archive — Project Architecture and Schedule

> **작성일**: 2026-05-11 | **작성자**: 최영재

---

## 목차

1. [프로젝트 개요](#1-프로젝트-개요)
2. [시스템 구조도](#2-시스템-구조도)
3. [기능 구성 요소 및 관계](#3-기능-구성-요소-및-관계)
4. [화면 구성 (UI Flow)](#4-화면-구성-ui-flow)
5. [데이터베이스 설계](#5-데이터베이스-설계)
6. [역할 분담](#6-역할-분담)
7. [일정 관리 계획](#7-일정-관리-계획)
8. [협업 및 의사소통 방안](#8-협업-및-의사소통-방안)

---

## 1. 프로젝트 개요

| 항목 | 내용 |
|------|------|
| **프로젝트명** | Movie Archive |
| **목적** | 사용자가 감상한 영화를 체계적으로 기록하고 리뷰를 공유할 수 있는 커뮤니티형 영화 아카이브 서비스 |
| **기술 스택** | Java, Java Swing (UI), MySQL / SQLite (DB), JDBC (DB 연동) |
| **팀원** | 최영재 (백엔드·DB·테스트), 남하경 (프론트엔드·백엔드·테스트) |
| **개발 기간** | 2026-04-27 ~ 2026-06-15 (약 7주) |
| **발표일** | 2026-06-15 또는 2026-06-17 |

---

## 2. 시스템 구조도

아래는 Movie Archive의 계층형 아키텍처 구조도입니다. 2주차 요구사항 분석에서 도출된 기능 요소(F-01 ~ F-18)를 각 계층에 매핑하였습니다.

```
┌─────────────────────────────────────────────────────────────────┐
│                        [ Presentation Layer ]                   │
│                         Java Swing (View)                       │
│                                                                 │
│  LoginFrame  │  MovieListPanel  │  MovieDetailPanel  │  ...     │
│  (F-01,F-02) │  (F-05,F-07,F-15 │  (F-06,F-08,F-12)  │          │
│              │   F-16)          │                    │          │
│  MyPagePanel │  AdminPanel      │  ReviewDialog      │          │
│  (F-03,F-04  │  (F-17, F-18)    │  (F-09,F-10,F-11)  │          │
│   F-13)      │                  │                    │          │
└──────────────────────────┬──────────────────────────────────────┘
                           │  이벤트 / 액션
┌──────────────────────────▼──────────────────────────────────────┐
│                       [ Controller Layer ]                      │
│                                                                 │
│  AuthController   MovieController   ReviewController            │
│  (로그인·인증·세션) (영화 CRUD·검색·필터)  (리뷰 CRUD·중복 방지)           │
│                                                                 │
│  AdminController                                                │
│  (관리자 권한 확인·회원 관리)                                         │
└──────────────────────────┬──────────────────────────────────────┘
                           │  비즈니스 로직 위임
┌──────────────────────────▼──────────────────────────────────────┐
│                        [ Service Layer ]                        │
│                                                                 │
│  UserService    MovieService    ReviewService    AdminService   │
│  (SHA-256 해시  (평균 별점 계산   (중복 리뷰 검증  (권한 검증·            │
│   비밀번호 검증) 장르/제목 필터)   작성자 확인)     계정 정지)              │
└──────────────────────────┬──────────────────────────────────────┘
                           │  데이터 접근
┌──────────────────────────▼──────────────────────────────────────┐
│                      [ Repository Layer ]                       │
│                    (DAO — Data Access Object)                   │
│                                                                 │
│  UserDAO        MovieDAO        ReviewDAO       TagDAO          │
│  (users 테이블) (movies 테이블) (reviews 테이블) (tags 테이블)       │
└──────────────────────────┬──────────────────────────────────────┘
                           │  JDBC
┌──────────────────────────▼──────────────────────────────────────┐
│                       [ Database Layer ]                        │
│                     MySQL / SQLite (JDBC)                       │
│                                                                 │
│   users  │  movies  │  reviews  │  tags  │  archive_tags       │
└─────────────────────────────────────────────────────────────────┘
```

> **설계 원칙 (NF-03)**: View → Controller → Service → DAO → DB 단방향 의존성을 유지하여 계층 간 결합도를 최소화합니다.

---

## 3. 기능 구성 요소 및 관계

### 3.1 패키지 구조

```
movie-archive/
├── src/
│   ├── domain/               # 도메인 모델 (VO/Entity)
│   │   ├── User.java
│   │   ├── Movie.java
│   │   └── Review.java
│   │
│   ├── repository/           # DAO (DB 접근)
│   │   ├── UserDAO.java
│   │   ├── MovieDAO.java
│   │   └── ReviewDAO.java
│   │
│   ├── service/              # 비즈니스 로직
│   │   ├── UserService.java
│   │   ├── MovieService.java
│   │   └── ReviewService.java
│   │
│   ├── controller/           # UI 이벤트 처리
│   │   ├── AuthController.java
│   │   ├── MovieController.java
│   │   └── ReviewController.java
│   │
│   ├── view/                 # Java Swing UI
│   │   ├── LoginFrame.java
│   │   ├── MainFrame.java
│   │   ├── MovieListPanel.java
│   │   ├── MovieDetailPanel.java
│   │   ├── ReviewDialog.java
│   │   ├── MyPagePanel.java
│   │   └── AdminPanel.java
│   │
│   └── util/                 # 공통 유틸
│       ├── DBConnection.java  # JDBC 커넥션 관리
│       └── PasswordUtil.java  # SHA-256 해시
│
└── resources/
    ├── posters/              # 영화 포스터 이미지
    └── db/
        └── schema.sql        # DB 초기화 스크립트
```

### 3.2 주요 기능 요소와 관계 매핑

| 기능 요소 | 요구사항 ID | View | Controller | Service | DAO |
|-----------|------------|------|------------|---------|-----|
| 회원가입·로그인 | F-01, F-02 | LoginFrame | AuthController | UserService | UserDAO |
| 내 정보 수정·탈퇴 | F-03, F-04 | MyPagePanel | AuthController | UserService | UserDAO |
| 영화 목록·상세 조회 | F-05, F-06 | MovieListPanel, MovieDetailPanel | MovieController | MovieService | MovieDAO |
| 장르 필터·제목 검색·감독 검색 | F-07, F-15, F-16 | MovieListPanel | MovieController | MovieService | MovieDAO |
| 평균 별점 표시 | F-08 | MovieListPanel, MovieDetailPanel | MovieController | MovieService | ReviewDAO |
| 리뷰 CRUD | F-09~F-12, F-14 | ReviewDialog | ReviewController | ReviewService | ReviewDAO |
| 내 리뷰 모아보기 | F-13 | MyPagePanel | ReviewController | ReviewService | ReviewDAO |
| 영화·회원 관리 (관리자) | F-17, F-18 | AdminPanel | AdminController | UserService, MovieService | UserDAO, MovieDAO |

---

## 4. 화면 구성 (UI Flow)

### 4.0 화면 와이어프레임 (참고 디자인)

![Movie Archive 와이어프레임](./wireframe.png)

> 위 이미지는 총 3개의 화면으로 구성됩니다: ① 시작 화면 → ② 로그인 화면 → ③ 메인(기능 소개) 화면

### 4.1 화면 흐름도

```
[앱 시작]
    │
    ▼
┌─────────────────────────────┐
│  ① SplashScreen (시작 화면)   │
│                             │
│  Movie Archive              │
│  [클래퍼보드 아이콘]            │
│                             │
│  "화면을 클릭하여 시작하세요"      │
└─────────────┬───────────────┘
              │ 화면 클릭
              ▼
┌─────────────────────────────┐
│  ② LoginFrame (로그인 화면)   │
│                             │
│  영화 기록                    │
│  당신의 영화 이야기를 시작하세요    │
│                             │
│  [ 이메일로 계속하기 ]          │
│  [ 회원가입 ]                 │
└─────────────┬───────────────┘
              │ 로그인 성공
              ▼
┌─────────────────────────────┐
│  ③ MainFrame (메인 화면)      │
│                             │
│  ✏ 새 리뷰 작성                │
│    새로운 영화를 검색하고         │
│    기록을 시작하세요             │
│  ───────────────────────    │
│  ⏱ 작성 중인 리뷰              │
│    임시 저장된 글을             │
│    이어서 작성하세요             │
│  ───────────────────────    │
│  🗂 나의 보관함                │
│    지금까지 작성한 모든          │
│    리뷰를 모아보세요             │
│  ───────────────────────    │
│  👥 피드                     │
│    팔로우한 친구들의            │
│    최신 리뷰를 확인하세요         │
└─────────────┬───────────────┘
              │ 메뉴 선택
              ▼
    ┌─────────────────────────────────────────────┐
    │                                             │
    ▼                 ▼                   ▼       ▼
[MovieListPanel]  [MyPagePanel]     [FeedPanel] [AdminPanel]
 영화 목록·검색    나의 보관함·        피드        (관리자 전용)
 필터·상세보기     내 정보 수정
    │
    ▼
[MovieDetailPanel]
 영화 상세 · 평균 별점 · 리뷰 목록
    │
    ▼
[ReviewDialog]
 리뷰 작성 / 수정 / 삭제
```

### 4.2 주요 화면 설명

| # | 화면 | 구성 요소 | 관련 요구사항 |
|---|------|----------|-------------|
| ① | **SplashScreen** (시작 화면) | 로고(클래퍼보드 아이콘), 서비스명, "화면을 클릭하여 시작하세요" 안내 문구 | - |
| ② | **LoginFrame** (로그인 화면) | 이메일·비밀번호 입력 폼, 로그인 버튼, 회원가입 이동 링크 | F-01, F-02 |
| ③ | **MainFrame** (메인 화면) | 새 리뷰 작성 / 작성 중인 리뷰 / 나의 보관함 / 피드 — 4가지 기능 진입점 | F-09, F-13 |
| - | **MovieListPanel** (영화 목록) | JTable 영화 목록, 장르 JComboBox 필터, 제목·감독 검색 JTextField | F-05, F-07, F-08, F-15, F-16 |
| - | **MovieDetailPanel** (영화 상세) | 포스터 이미지, 감독·줄거리 정보, 평균 별점, 리뷰 목록 | F-06, F-08, F-12 |
| - | **ReviewDialog** (리뷰 작성/수정) | JSlider 별점, JTextArea 본문, 저장·취소 버튼 | F-09, F-10, F-11, F-14 |
| - | **MyPagePanel** (나의 보관함) | 내 리뷰 JTable, 내 정보 수정·탈퇴 버튼 | F-03, F-04, F-13 |
| - | **FeedPanel** (피드) | 팔로우한 사용자 최신 리뷰 목록 | F-12 |
| - | **AdminPanel** (관리자) | 영화 CRUD 폼, 회원 목록·권한 관리 JTable | F-17, F-18 |

---

## 5. 데이터베이스 설계

### 5.1 ERD (테이블 관계)

```
users ──────────┬── reviews ──── movies
  │             │                  │
  │             └── (movie_id FK)  │
  │                                │
  └── (user_id FK)              tags
                              archive_tags ── (movie_id FK)
```

### 5.2 테이블 명세

| 테이블 | 주요 컬럼 | 설명 |
|--------|----------|------|
| **users** | id (PK), username, password (SHA-256), nickname, role (user/admin), is_active, created_at | 회원 정보 (F-01~F-04, F-18) |
| **movies** | id (PK), title, director, genre, release_year, overview, poster_path, created_at | 영화 정보 (F-05~F-08, F-17) |
| **reviews** | id (PK), user_id (FK), movie_id (FK), rating, content, created_at | 리뷰 정보 (F-09~F-14) |
| **tags** | id (PK), name | 태그 (향후 확장) |
| **archive_tags** | id (PK), movie_id (FK), tag_id (FK) | 영화-태그 매핑 |

### 5.3 주요 인덱스 설계 (NF-05)

```sql
-- 검색 성능 최적화
CREATE INDEX idx_movies_title   ON movies(title);
CREATE INDEX idx_movies_genre   ON movies(genre);
CREATE INDEX idx_movies_director ON movies(director);
CREATE INDEX idx_reviews_movie  ON reviews(movie_id);
CREATE INDEX idx_reviews_user   ON reviews(user_id);
```

---

## 6. 역할 분담

### 6.1 역할 분담표

| 구분 | 최영재 | 남하경 |
|------|--------|--------|
| **주요 역할** | 백엔드·DB·테스트 | 프론트엔드·백엔드·테스트 |
| **담당 계층** | Service, DAO, DB 설계, 유틸 | View (Swing UI), Controller, 이벤트 처리 |
| **회원 기능** | DB 검증·SHA-256 해시·트랜잭션 로직 (F-01~F-04) | 로그인/회원가입/마이페이지 UI (F-01~F-04) |
| **영화 기능** | MovieDAO CRUD·검색 쿼리 (F-05~F-08, F-15~F-17) | 영화 목록·상세·필터·검색 UI (F-05~F-08, F-15~F-17) |
| **리뷰 기능** | ReviewDAO CRUD·중복 방지·권한 검증 (F-09~F-14) | 리뷰 작성·수정·삭제 다이얼로그 UI (F-09~F-13) |
| **관리자 기능** | 권한 확인 로직·UserDAO 권한 쿼리 (F-17, F-18) | 관리자 전용 패널 UI (F-17, F-18) |
| **공통** | DB 스키마 설계, JDBC 커넥션 관리, 단위 테스트 | UI 이벤트 연결, 통합 테스트, 문서 작성 |

### 6.2 책임 범위

- **최영재**: 데이터 정합성 및 보안 로직 책임 (NF-01, NF-02), 쿼리 성능 최적화 (NF-05), DB 스키마 확정
- **남하경**: UI/UX 품질 및 사용성 책임, Controller-View 연결, 화면 흐름 관리

---

## 7. 일정 관리 계획

> **전체 기간**: 2026-04-27 (1주차) ~ 2026-06-15 (발표)  
> **실 개발 기간**: 약 7주 (1주차~7주차)

### 7.1 주차별 세부 일정

| 주차 | 목표 | 최영재 | 남하경 |
|------|------|--------|--------|
| **3주차** | 환경 설정 & DB 구축 | DB 스키마 설계·schema.sql 작성 / JDBC 커넥션 유틸 구현 / UserDAO 골격 | 프로젝트 구조 세팅 / LoginFrame·회원가입 UI 구현 |
| **4주차** | 인증 기능 완성 | UserService (SHA-256, 로그인 검증, 탈퇴 트랜잭션) / ReviewDAO 골격 | AuthController 연결 / MyPagePanel UI / 내 정보 수정 다이얼로그 |
| **5주차** | 영화 조회·검색 기능 | MovieDAO (findAll, findById, findByGenre, searchByTitle, searchByDirector) / AVG 쿼리 | MovieListPanel (JTable, 필터, 검색) / MovieDetailPanel (포스터 로드, 평균 별점) |
| **6주차** | 리뷰 기능 & 관리자 | ReviewDAO CRUD / 중복 방지·권한 검증 로직 / AdminDAO 쿼리 | ReviewDialog (JSlider, JTextArea) / AdminPanel UI / 통합 테스트 시작 |
| **7주차** | 최종 완성 & 발표 준비 | 버그 수정 / 전체 단위 테스트 / DB 인덱스 튜닝 | UI 전체 점검·polish / 발표 자료 작성 / 최종 통합 테스트 |

### 7.2 마일스톤

```
05/11 ─── [M1] 프로젝트 세팅 완료 (DB 스키마 확정, 개발 환경 통일)
05/17 ────────────────────────────────────────────────────────┐
                                                              │ 3주차 완료
05/24 ─── [M2] 회원 인증 기능 동작 확인                             │
            └ 로그인·회원가입·로그아웃 E2E 테스트 통과                 │
05/31 ─── [M3] 영화 조회·검색·필터 기능 동작 확인                     │
            └ MovieListPanel↔MovieDAO 연동 완료                 │
06/08 ─── [M4] 리뷰 CRUD + 관리자 기능 동작 확인                    │
            └ 전체 기능 요구사항 (F-01~F-18) 구현 완료               │
06/12 ─── [M5] 중간 점검 (버그 수정, UI 통일, 문서 보완)              │
06/15 ─── [M6] 최종 발표                                        ┘
```

### 7.3 중간 점검 계획

| 점검 시점 | 점검 내용 | 방법 |
|-----|----------|------|
| 매주 화요일 | 주간 진행 상황 공유, 다음 주 작업 조율 | Discord 또는 대면 |
| M3  | DB↔UI 연동 상태, 미구현 항목 파악 | 직접 시연 + GitHub PR 리뷰 |
| M5  | 전 기능 통합 테스트, 발표 자료 초안 점검 | 직접 시연 + 체크리스트 |

---

## 8. 협업 및 의사소통 방안

### 8.1 도구 및 플랫폼

| 용도 | 도구 |
|------|------|
| 소스 코드 관리 | GitHub (브랜치 전략: `main` / `feature/{기능명}`) |
| 실시간 소통 | Discord 또는 대면 |
| 이슈·작업 추적 | GitHub Issues (2주차 Issue 목록 활용) |
| 문서 공유 | GitHub Wiki / 프로젝트 루트 `docs/` 폴더 |

### 8.2 Git 브랜치 전략

```
main
├── feature/auth          # 회원 인증 (F-01~F-04)
├── feature/movie-list    # 영화 목록·검색 (F-05~F-08, F-15~F-16)
├── feature/review        # 리뷰 CRUD (F-09~F-14)
└── feature/admin         # 관리자 기능 (F-17~F-18)
```

- 기능 완성 후 PR(Pull Request) 생성 → 상대방 코드 리뷰 → `main` 머지
- 커밋 메시지 규칙: `[feat] 로그인 기능 구현`, `[fix] 리뷰 삭제 권한 오류 수정`

### 8.3 코딩 컨벤션

- 클래스명: PascalCase (`MovieDAO`, `ReviewService`)
- 메서드·변수명: camelCase (`findByMovieId`, `currentUser`)
- 상수: UPPER_SNAKE_CASE (`MAX_RATING`)
- DAO 메서드 네이밍: `findAll()`, `findById()`, `insert()`, `update()`, `delete()`