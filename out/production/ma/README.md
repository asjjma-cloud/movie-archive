# 🎬 Movie Archive

> 내가 본 영화를 기록하고, 리뷰와 별점을 남기는 영화 아카이브 서비스

**팀명**: Archimoo | **개발 기간**: 2026.04.27 ~ 2026.06.15 | **발표일**: 2026.06.15

---

## 📌 프로젝트 소개

Movie Archive는 영화 감상 기록을 체계적으로 관리할 수 있는 Java 기반 데스크톱 애플리케이션입니다.
회원가입 후 영화를 검색하고, 별점과 리뷰를 남기며, 내 보관함에서 감상 기록을 한눈에 확인할 수 있습니다.

---

## ✨ 구현된 기능

### 🔐 회원 인증
- 회원가입 (아이디, 이메일, 비밀번호, 닉네임)
- 아이디 / 이메일 중복 검증
- 로그인 / 로그아웃
- SHA-256 비밀번호 해시 암호화
- 탈퇴한 계정 로그인 차단
- 로그인 / 회원가입 엔터키 지원

### 👤 마이페이지
- 닉네임 / 비밀번호 수정
- 정보 수정 후 네비게이션 바 닉네임 실시간 갱신
- 회원 탈퇴 (소프트 삭제)
- 내가 작성한 리뷰 모아보기
- 내 리뷰 수정 / 삭제

### 🎥 영화 목록
- 전체 영화 목록 조회
- 제목 / 감독 키워드 검색 (엔터키 지원)
- 장르별 필터링 (액션, 로맨스, 코미디, 공포, SF, 드라마, 애니메이션, 다큐멘터리)
- 컬럼 헤더 클릭으로 정렬 (제목/감독 가나다순, 개봉연도/별점 숫자순)
- 평균 별점 실시간 표시

### 🎬 영화 상세
- 영화 제목, 감독, 장르, 개봉연도, 줄거리 표시
- 평균 별점 실시간 계산 및 갱신
- 해당 영화의 전체 리뷰 목록 조회

### ⭐ 리뷰 & 별점
- 영화별 리뷰 작성 (별점 1~5점, JSlider)
- 영화 1개당 리뷰 1개 제한 (중복 방지)
- 리뷰 작성 후 평균 별점 즉시 갱신
- 리뷰 수정 / 삭제 (본인 리뷰만 가능)

### 🛡 관리자
- 영화 추가 / 삭제
- 전체 회원 목록 조회
- 회원 계정 정지

---

## 🛠 기술 스택

| 분류 | 기술 |
|------|------|
| Language | Java 17 |
| UI | Java Swing |
| Database | SQLite |
| DB 연동 | JDBC |
| 암호화 | SHA-256 (java.security.MessageDigest) |
| 빌드 | IntelliJ IDEA (Maven) |
| 버전 관리 | Git / GitHub |

---

## 🏗 시스템 구조 (4계층 아키텍처)

```
View (Java Swing)
     ↓ 이벤트 / 액션
Controller
     ↓ 비즈니스 로직 위임
Service
     ↓ 데이터 접근
DAO (Repository)
     ↓ JDBC
SQLite DB
```

> View → Controller → Service → DAO → DB 단방향 의존성 유지

---

## 📂 프로젝트 구조

```
movie-archive/
├── src/
│   └── main/
│       ├── controller/
│       │   ├── AuthController.java      # 로그인·회원가입·세션 관리
│       │   ├── MovieController.java     # 영화 조회·검색·필터·CRUD
│       │   └── ReviewController.java    # 리뷰 작성·수정·삭제·조회
│       ├── domain/
│       │   ├── Movie.java               # 영화 도메인 모델
│       │   ├── User.java                # 사용자 도메인 모델
│       │   └── Review.java              # 리뷰 도메인 모델 (별점 검증 포함)
│       ├── repository/
│       │   ├── MovieDAO.java            # 영화 CRUD + 검색 쿼리 + AVG 별점
│       │   ├── UserDAO.java             # 회원 CRUD + 아이디/이메일 조회
│       │   └── ReviewDAO.java           # 리뷰 CRUD + 중복 확인
│       ├── service/
│       │   ├── MovieArchiveService.java # 회원 인증·비밀번호 해시·중복 검증
│       │   ├── MovieService.java        # 영화 조회·검색·필터·평균 별점
│       │   └── ReviewService.java       # 리뷰 CRUD·중복 방지·권한 검증
│       ├── view/
│       │   ├── LoginFrame.java          # 로그인 화면
│       │   ├── RegisterDialog.java      # 회원가입 다이얼로그
│       │   ├── MainFrame.java           # 메인 프레임 + 네비게이션 바
│       │   ├── MovieListPanel.java      # 영화 목록·검색·장르 필터·정렬
│       │   ├── MovieDetailPanel.java    # 영화 상세·평균 별점·리뷰 목록
│       │   ├── ReviewDialog.java        # 리뷰 작성 다이얼로그
│       │   ├── MyPagePanel.java         # 내 보관함·정보 수정·탈퇴
│       │   └── AdminPanel.java          # 관리자 패널 (영화·회원 관리)
│       └── util/
│           ├── DBConnection.java        # SQLite 싱글톤 커넥션 + 절대경로
│           └── PasswordUtil.java        # SHA-256 해시 유틸
└── resources/
    └── db/
        ├── schema.sql                   # DB 초기화 스크립트 (KST 시간대 적용)
        └── movie_archive.db             # SQLite DB 파일 (자동 생성)
```

---

## 🗃 데이터베이스 설계

### ERD

```
users ──────────── reviews ──── movies
  (1)               (N)  (N)     (1)
  user_id(PK)       user_id(FK)
                    movie_id(FK)
```

### 테이블 명세

| 테이블 | 주요 컬럼 |
|--------|-----------|
| users | id(PK), username(UNIQUE), email(UNIQUE), password(SHA-256), nickname, role(user/admin), is_active, created_at |
| movies | id(PK), title, director, genre, release_year, overview, poster_path, created_at |
| reviews | id(PK), user_id(FK), movie_id(FK), rating(1~5), content, created_at, updated_at |

### 인덱스

```sql
CREATE INDEX idx_movies_title    ON movies(title);
CREATE INDEX idx_movies_genre    ON movies(genre);
CREATE INDEX idx_movies_director ON movies(director);
CREATE INDEX idx_reviews_movie   ON reviews(movie_id);
CREATE INDEX idx_reviews_user    ON reviews(user_id);
```

---

## 💡 핵심 구현 포인트

| 항목 | 내용 |
|------|------|
| SHA-256 암호화 | java.security.MessageDigest로 비밀번호 해시 처리 |
| 싱글톤 DB 커넥션 | DBConnection.getConnection()으로 SQLite 연결 단일 관리 |
| 절대경로 DB | System.getProperty("user.dir")로 DB 경로 고정 |
| KST 시간대 | datetime('now', '+9 hours')로 한국 시간 저장 |
| Stream API | 평균 별점 계산에 mapToDouble().average() 적용 |
| 중복 방지 | UNIQUE 제약 + exists() 메서드로 이중 검증 |
| 정렬 기능 | TableRowSorter로 컬럼 헤더 클릭 정렬 구현 |
| 실시간 갱신 | 리뷰 작성/정보 수정 후 UI 즉시 반영 |
| 엔터키 지원 | addActionListener로 로그인·검색·회원가입 엔터키 지원 |

---

## ✅ 통합 테스트 체크리스트

### 🔐 회원 인증
- [ ] 회원가입 → 로그인 성공
- [ ] 중복 아이디 회원가입 → 오류 메시지
- [ ] 중복 이메일 회원가입 → 오류 메시지
- [ ] 틀린 비밀번호로 로그인 → 오류 메시지
- [ ] 로그아웃 → 로그인 화면 이동

### 🎥 영화 조회
- [ ] 영화 목록 전체 표시
- [ ] 제목 검색 → 결과 표시
- [ ] 감독 검색 → 결과 표시
- [ ] 장르 필터 → 해당 장르만 표시
- [ ] 컬럼 헤더 클릭 → 정렬 동작
- [ ] 전체보기 버튼 → 목록 초기화
- [ ] 영화 더블클릭 → 상세 화면

### ⭐ 리뷰
- [ ] 리뷰 작성 → 별점 + 내용 저장
- [ ] 리뷰 작성 후 평균 별점 즉시 갱신
- [ ] 같은 영화 리뷰 중복 작성 → 오류 메시지
- [ ] 내 보관함 → 내 리뷰 표시
- [ ] 리뷰 수정 → 반영 확인
- [ ] 리뷰 삭제 → 목록에서 제거

### 👤 마이페이지
- [ ] 닉네임 수정 → 네비게이션 바 즉시 반영
- [ ] 비밀번호 수정 → 변경된 비밀번호로 로그인
- [ ] 회원 탈퇴 → 로그인 화면 이동

### 🛡 관리자
- [ ] 관리자 로그인 → 관리자 버튼 표시
- [ ] 영화 추가 → 목록 반영
- [ ] 영화 삭제 → 목록에서 제거
- [ ] 회원 목록 조회

---

## 🚀 실행 방법

```bash
# 1. 레포지토리 클론
git clone https://github.com/asjjma-cloud/movie-archive.git

# 2. IntelliJ IDEA에서 프로젝트 열기

# 3. SQLite JDBC 라이브러리 추가
#    File → Project Structure → Libraries → + → From Maven
#    org.xerial:sqlite-jdbc:3.45.3.0

# 4. Main.java 실행 (DB 및 테이블 자동 생성)
```

> 관리자 계정은 앱에서 회원가입 후 DB에서 role을 'admin'으로 변경하세요.
> ```sql
> UPDATE users SET role = 'admin' WHERE email = '관리자이메일';
> ```

---

## 📅 개발 일정

| 주차 | 기간 | 내용 |
|------|------|------|
| 1~2주차 | 04/27 ~ 05/10 | 요구사항 분석, 시스템 설계, GitHub 세팅 |
| 3주차 | 05/11 ~ 05/17 | DB 스키마 설계, DBConnection, UserDAO 골격 |
| 4주차 | 05/18 ~ 05/24 | Domain 클래스, DAO 3개, Service 구현 |
| 5~6주차 | 05/25 ~ 06/07 | Controller 3개, Swing GUI 전체 구현 |
| 7주차 | 06/08 ~ 06/14 | 버그 수정, UI 개선, 통합 테스트, 발표 준비 |
| 발표 | 06/15 | 최종 발표 |

---

## 👥 팀 소개

**팀명: Archimoo** (Archive + movie)

| 이름 | 학번 | 역할 |
|------|------|------|
| 👑 최영재 (팀장) | 20220519 | 백엔드, DB 설계, Domain/DAO/Service 구현, 테스트 |
| 남하경 | 20240609 | 프론트엔드, Controller, Swing GUI 구현, 통합 테스트 |
