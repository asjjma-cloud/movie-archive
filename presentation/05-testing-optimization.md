# 5 — 테스트 최적화

## 1 - 테스트 결과

### 1. 회원 관리 (Authentication & User Management)
- [x] 회원가입 → 로그인 성공
- [x] 틀린 비밀번호로 로그인 → 오류 메시지 출력
- [x] 중복 이메일 회원가입 → 오류 메시지 출력
- [x] 로그아웃 → 로그인 화면 이동
---


### 2. 영화 관리 (Movie Management)

- [x] 영화 목록 전체 표시
- [x] 제목 검색 → 결과 표시
- [x] 감독 검색 → 결과 표시
- [x] 장르 필터 → 해당 장르만 표시
- [x] 전체보기 버튼 → 목록 초기화
- [x] 영화 더블클릭 → 상세 화면 이동

---

### 3. 리뷰 관리 (Review Management)

- [x] 리뷰 작성 → 별점과 내용 정상 저장
- [x] 같은 영화 리뷰 중복 작성 시 → 오류 메시지 출력
- [x] 내 보관함 → 작성한 리뷰 목록 표시
- [x] 리뷰 수정 → 변경 내용 정상 반영
- [x] 리뷰 삭제 → 목록에서 제거 확인

---

### 4. 관리자 기능 (Admin Management)

- [x] 관리자 로그인 시 → 관리자 버튼 표시
- [x] 영화 추가 → 목록에 정상 반영
- [x] 영화 삭제 → 목록에서 제거 확인
- [x] 회원 목록 조회 기능 정상 동작

---
## 2 - 테스트 중 발견된 버그
| 버그 | 원인 | 해결 |
|--------|--------|--------|
| DB 두 곳에 생성 | 상대경로 사용 | 절대경로 적용 |
| 평균 별점 미갱신 | 목록 새로고침 누락 | `showMovieList()` 호출 |
| 작성일 시간 오류 | UTC 기준 저장 | KST +9시간 적용 |
| 중복 아이디 가입 | `username` 검증 누락 | `findByUsername()` 추가 |
| 닉네임 미갱신 | 로그아웃 필요 | `updateUserLabel()` 추가 |

---
## 3 - 테스트 후 최적화

| 항목 | 내용 |
|--------|--------|
| DB 인덱스 | `title`, `genre`, `director`, `movie_id`, `user_id` |
| 싱글톤 커넥션 | `DBConnection` 단일 인스턴스 관리 |
| 평균 별점 | `getAllMovies()` 호출 시 `AVG` 쿼리 일괄 처리 |

---

## 4 - 관리자 실행 방법

```
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