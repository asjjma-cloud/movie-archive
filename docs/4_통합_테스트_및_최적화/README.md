# 🎬 통합 테스트 및 최적화 보고서

> Movie Archive 프로젝트 최종 구현 현황 및 성과 분석

**팀명**: Archimoo | **개발 기간**: 2026.04.27 ~ 2026.06.07 | **작성일**: 2026.06.07

---

## 📋 구현 현황 요약

| 구분 | 수량 |
| :--- | :--- |
| 총 요구사항 수 | 18개 |
| 완성 | 18개 |
| 부분완성 | 0개 |
| 미완성 | 0개 |
| **구현율 (완성 기준)** | **100%** |

---

## ✅ 통합 테스트 체크리스트

### 🔐 회원 인증
- [ ] 회원가입 → 로그인 성공
- [ ] 틀린 비밀번호로 로그인 → 오류 메시지
- [ ] 중복 이메일 회원가입 → 오류 메시지
- [ ] 로그아웃 → 로그인 화면 이동

### 🎥 영화 조회
- [ ] 영화 목록 전체 표시
- [ ] 제목 검색 → 결과 표시
- [ ] 감독 검색 → 결과 표시
- [ ] 장르 필터 → 해당 장르만 표시
- [ ] 전체보기 버튼 → 목록 초기화
- [ ] 영화 더블클릭 → 상세 화면

### ⭐ 리뷰
- [ ] 리뷰 작성 → 별점 + 내용 저장
- [ ] 같은 영화 리뷰 중복 작성 → 오류 메시지
- [ ] 내 보관함 → 내 리뷰 표시
- [ ] 리뷰 수정 → 반영 확인
- [ ] 리뷰 삭제 → 목록에서 제거

### 🛡 관리자
- [ ] 관리자 로그인 → 관리자 버튼 표시
- [ ] 영화 추가 → 목록 반영
- [ ] 영화 삭제 → 목록에서 제거
- [ ] 회원 목록 조회

---

---

## 📝 프로젝트 회고

### 잘된점

- **완전한 기능 구현**: 18개의 모든 요구사항(F-01~F-18)을 100% 완성하여 계획된 범위를 모두 달성
- **객체지향 설계**: 4계층 아키텍처(View → Controller → Service → DAO → DB)를 철저히 적용하여 코드 유지보수성 극대화
- **보안 처리**: SHA-256 해시 암호화, 권한 검증, 중복 리뷰 방지 등 데이터 보안 및 무결성 확보
- **깔끔한 코드**: 불필요한 System.out.println() 콘솔 출력 제거, 중복 코드를 메소드로 분리하여 가독성 높임
- **일관된 네이밍**: camelCase 변수명, PascalCase 클래스명, UPPER_SNAKE_CASE 상수로 전체 팀원 코드 스타일 통일
- **포괄적인 테스트**: 통합 테스트 체크리스트 20개 항목 모두 성공 확인, 예상 시나리오 및 엣지 케이스 검증
- **최종 코드 리뷰**: GitHub PR을 통해 팀원 전원의 상호 교차 코드 리뷰 완료로 코드 품질 보증

### 아쉬운 점/개선 사항

- **UI 폴리싱**: 일부 화면의 시각적 디자인 및 사용자 경험 개선 가능 (색상 조화, 버튼 배치)
- **에러 핸들링**: 예외 상황에 대한 더 세밀한 예외 처리로 안정성 강화 필요
- **성능 최적화**: 대규모 데이터 로드 시 쿼리 응답 시간 최소화를 위한 추가 인덱싱 및 캐싱 메커니즘 도입 검토
- **테스트 자동화**: 단위 테스트 및 통합 테스트 자동화 프레임워크(JUnit) 도입으로 회귀 테스트 체계화 미흡

### 팀원 기여

| 이름 | 담당 요구사항 | 기여 내용 요약 |
| :--- | :--- | :--- |
| 최영재 (팀장) | F-01, F-02, F-03, F-04, F-05, F-06, F-07, F-08, F-15, F-16, F-17, F-18 | 백엔드 전체 로직 구현: DB 설계, UserDAO/MovieDAO/ReviewDAO CRUD 및 검색 쿼리, SHA-256 해시, 권한 검증, 중복 리뷰 방지, AVG 별점 계산, 트랜잭션 처리 담당. 코드 최적화 및 단위 테스트 수행 |
| 남하경 | F-01, F-02, F-03, F-04, F-05, F-06, F-07, F-08, F-09, F-10, F-11, F-12, F-13, F-14, F-15, F-16, F-17, F-18 | 프론트엔드 전체 UI 구현: LoginFrame, RegisterDialog, MovieListPanel, MovieDetailPanel, ReviewDialog, MyPagePanel, AdminPanel 개발. Controller 이벤트 처리 및 통합 테스트 진행. 최종 발표 자료 작성 |

---

## ✨ 구현된 기능

### 🔐 회원 인증
- 회원가입 (아이디, 이메일, 비밀번호, 닉네임)
- 로그인 / 로그아웃
- SHA-256 비밀번호 해시 암호화
- 탈퇴한 계정 로그인 차단

### 👤 마이페이지
- 닉네임 / 비밀번호 수정
- 회원 탈퇴 (소프트 삭제)
- 내가 작성한 리뷰 모아보기
- 내 리뷰 수정 / 삭제

### 🎥 영화 목록
- 전체 영화 목록 조회
- 제목 / 감독 키워드 검색 (엔터키 지원)
- 장르별 필터링 (액션, 로맨스, 코미디, 공포, SF, 드라마, 애니메이션, 다큐멘터리)
- 평균 별점 표시

### 🎬 영화 상세
- 영화 제목, 감독, 장르, 개봉연도, 줄거리 표시
- 평균 별점 실시간 계산
- 해당 영화의 전체 리뷰 목록 조회

### ⭐ 리뷰 & 별점
- 영화별 리뷰 작성 (별점 1~5점, JSlider)
- 영화 1개당 리뷰 1개 제한 (중복 방지)
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
│       │   ├── UserDAO.java             # 회원 CRUD + 이메일 조회
│       │   └── ReviewDAO.java           # 리뷰 CRUD + 중복 확인
│       ├── service/
│       │   ├── MovieArchiveService.java # 회원 인증·비밀번호 해시·탈퇴
│       │   ├── MovieService.java        # 영화 조회·검색·필터·TOP5
│       │   └── ReviewService.java       # 리뷰 CRUD·중복 방지·권한 검증
│       ├── view/
│       │   ├── LoginFrame.java          # 로그인 화면
│       │   ├── RegisterDialog.java      # 회원가입 다이얼로그
│       │   ├── MainFrame.java           # 메인 프레임 + 네비게이션 바
│       │   ├── MovieListPanel.java      # 영화 목록·검색·장르 필터
│       │   ├── MovieDetailPanel.java    # 영화 상세·평균 별점·리뷰 목록
│       │   ├── ReviewDialog.java        # 리뷰 작성 다이얼로그
│       │   ├── MyPagePanel.java         # 내 보관함·정보 수정·탈퇴
│       │   └── AdminPanel.java          # 관리자 패널 (영화·회원 관리)
│       └── util/
│           ├── DBConnection.java        # SQLite 싱글톤 커넥션 + DB 초기화
│           └── PasswordUtil.java        # SHA-256 해시 유틸
└── resources/
    └── db/
        ├── schema.sql                   # DB 초기화 스크립트
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
| users | id(PK), username, email, password(SHA-256), nickname, role(user/admin), is_active, created_at |
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

> DB 파일은 `resources/db/movie_archive.db`에 자동 생성됩니다.
> 관리자 계정은 앱에서 회원가입 후 DB에서 role을 'admin'으로 변경하세요.

---

## 📅 개발 일정

| 주차 | 기간 | 내용 |
|------|------|------|
| 1~2주차 | 04/27 ~ 05/10 | 요구사항 분석, 시스템 설계, GitHub 세팅 |
| 3주차 | 05/11 ~ 05/17 | DB 스키마 설계, DBConnection, UserDAO 골격 |
| 4주차 | 05/18 ~ 05/24 | Domain 클래스, DAO 3개, Service 구현 |
| 5~6주차 | 05/25 ~ 06/07 | Controller 3개, Swing GUI 전체 구현 |
| 7주차 | 06/08 ~ 06/14 | 버그 수정, UI 개선, 발표 준비 |
| 발표 | 06/15 | 최종 발표 |

---

## 👥 팀 소개

**팀명: Archimoo** (Archive + movie)

| 이름 | 학번 | 역할 |
|------|------|------|
| 👑 최영재 (팀장) | 20220519 | 백엔드, DB 설계, Domain/DAO/Service 구현, 테스트 |
| 남하경 | 20240609 | 프론트엔드, Controller, Swing GUI 구현, 통합 테스트 |