# GitHub Issue 등록 템플릿 — 영화 리뷰 시스템

> 아래 내용을 순서대로 GitHub에 **New issue**로 하나씩 등록하세요.  
> 각 Issue에 **Label**, **Assignees**, **Milestone** 을 함께 설정하세요.

---

## 사전 준비 — Label & Milestone

### Labels (먼저 생성)
| 이름 | 색상 |
|------|------|
| `auth` | `#7F77DD` |
| `movie` | `#1D9E75` |
| `review` | `#D85A30` |
| `search` | `#378ADD` |
| `admin` | `#BA7517` |
| `UI` | `#AFA9EC` |
| `backend` | `#5DCAA5` |

### Milestones (먼저 생성)
| 이름 | 포함 Issue |
|------|-----------|
| `Milestone 1 - 회원 기능` | #1 ~ #4 |
| `Milestone 2 - 영화·리뷰 기능` | #5 ~ #14 |
| `Milestone 3 - 검색·관리자` | #15 ~ #18 |

---

## Issue 목록

---

### Issue #1 — 회원가입

**Label:** `auth` `UI` `backend`  
**Assignees:** A (UI), B (로직)  
**Milestone:** Milestone 1 - 회원 기능

```
## 설명
회원가입 기능 구현

## 작업 내용
- [ ] 아이디, 비밀번호, 닉네임 입력 폼 (JTextField) 구성 — A
- [ ] 중복 아이디 확인 버튼 및 안내 메시지 — A
- [ ] 중복 아이디 DB 검증 로직 — B
- [ ] 비밀번호 SHA-256 해시 저장 — B

## 관련 요구사항
FR-01
```

---

### Issue #2 — 로그인 / 로그아웃

**Label:** `auth` `UI` `backend`  
**Assignees:** A (UI), B (로직)  
**Milestone:** Milestone 1 - 회원 기능

```
## 설명
로그인 및 로그아웃 기능 구현

## 작업 내용
- [ ] 로그인 화면 (LoginFrame) 구성 — A
- [ ] 로그아웃 버튼 및 세션 초기화 — A
- [ ] DB 조회로 자격 증명 확인 로직 — B
- [ ] 로그인 실패 시 안내 메시지 처리 — B

## 관련 요구사항
FR-02
```

---

### Issue #3 — 내 정보 수정

**Label:** `auth` `UI` `backend`  
**Assignees:** A (UI), B (로직)  
**Milestone:** Milestone 1 - 회원 기능

```
## 설명
닉네임, 비밀번호 변경 기능 구현

## 작업 내용
- [ ] 내 정보 수정 다이얼로그 UI — A
- [ ] 현재 비밀번호 확인 입력 폼 — A
- [ ] 변경 전 비밀번호 검증 로직 — B
- [ ] 닉네임·비밀번호 DB 업데이트 — B

## 관련 요구사항
FR-03
```

---

### Issue #4 — 회원 탈퇴

**Label:** `auth` `UI` `backend`  
**Assignees:** A (UI), B (로직)  
**Milestone:** Milestone 1 - 회원 기능

```
## 설명
회원 탈퇴 기능 구현

## 작업 내용
- [ ] 탈퇴 확인 다이얼로그 UI — A
- [ ] 탈퇴 시 연관 리뷰 삭제 트랜잭션 로직 — B
- [ ] 계정 삭제 DB 처리 — B

## 관련 요구사항
FR-04
```

---

### Issue #5 — 영화 목록 조회

**Label:** `movie` `UI` `backend`  
**Assignees:** A (UI), B (로직)  
**Milestone:** Milestone 2 - 영화·리뷰 기능

```
## 설명
전체 영화 목록 조회 화면 구현

## 작업 내용
- [ ] JTable로 영화 목록 표시 (제목·장르·연도·평균별점 컬럼) — A
- [ ] 영화 선택 시 상세 화면으로 이동 — A
- [ ] MovieDAO.findAll() 쿼리 구현 — B

## 관련 요구사항
FR-05
```

---

### Issue #6 — 영화 상세 보기

**Label:** `movie` `UI` `backend`  
**Assignees:** A (UI), B (로직)  
**Milestone:** Milestone 2 - 영화·리뷰 기능

```
## 설명
선택한 영화의 상세 정보 화면 구현

## 작업 내용
- [ ] 영화 상세 패널 (감독, 줄거리, 포스터 이미지, 리뷰 목록) — A
- [ ] 포스터 이미지 로컬 파일 경로 로드 — A
- [ ] MovieDAO.findById() 쿼리 구현 — B

## 관련 요구사항
FR-06
```

---

### Issue #7 — 장르별 필터링

**Label:** `movie` `UI` `backend`  
**Assignees:** A (UI), B (로직)  
**Milestone:** Milestone 2 - 영화·리뷰 기능

```
## 설명
장르 선택으로 영화 목록 필터링 구현

## 작업 내용
- [ ] JComboBox 장르 선택 UI — A
- [ ] 선택 시 목록 즉시 갱신 이벤트 처리 — A
- [ ] MovieDAO.findByGenre() 쿼리 구현 — B

## 관련 요구사항
FR-07
```

---

### Issue #8 — 평균 별점 표시

**Label:** `movie` `UI` `backend`  
**Assignees:** A (UI), B (로직)  
**Milestone:** Milestone 2 - 영화·리뷰 기능

```
## 설명
영화별 평균 별점 자동 집계 및 표시

## 작업 내용
- [ ] 영화 목록·상세 화면에 평균 별점 표시 컴포넌트 — A
- [ ] AVG(rating) SQL 집계 쿼리 구현 — B

## 관련 요구사항
FR-08
```

---

### Issue #9 — 리뷰 작성

**Label:** `review` `UI` `backend`  
**Assignees:** A (UI), B (로직)  
**Milestone:** Milestone 2 - 영화·리뷰 기능

```
## 설명
로그인 사용자의 리뷰 작성 기능 구현

## 작업 내용
- [ ] 리뷰 작성 다이얼로그 (별점 JSlider + JTextArea) — A
- [ ] 로그인 여부 확인 후 작성 버튼 활성화 — A
- [ ] ReviewDAO.insert() 쿼리 구현 — B
- [ ] 로그인 상태 검증 로직 — B

## 관련 요구사항
FR-09
```

---

### Issue #10 — 리뷰 수정

**Label:** `review` `UI` `backend`  
**Assignees:** A (UI), B (로직)  
**Milestone:** Milestone 2 - 영화·리뷰 기능

```
## 설명
본인 리뷰 수정 기능 구현

## 작업 내용
- [ ] 리뷰 수정 다이얼로그 (기존 내용 불러오기) — A
- [ ] ReviewDAO.update() 쿼리 구현 — B
- [ ] 본인 리뷰 여부 검증 로직 — B

## 관련 요구사항
FR-10
```

---

### Issue #11 — 리뷰 삭제

**Label:** `review` `UI` `backend`  
**Assignees:** A (UI), B (로직)  
**Milestone:** Milestone 2 - 영화·리뷰 기능

```
## 설명
리뷰 삭제 기능 구현 (본인 + 관리자)

## 작업 내용
- [ ] 삭제 확인 다이얼로그 UI — A
- [ ] ReviewDAO.delete() 쿼리 구현 — B
- [ ] 본인 / 관리자 권한 분기 로직 — B

## 관련 요구사항
FR-11
```

---

### Issue #12 — 리뷰 목록 조회

**Label:** `review` `UI` `backend`  
**Assignees:** A (UI), B (로직)  
**Milestone:** Milestone 2 - 영화·리뷰 기능

```
## 설명
영화 상세 화면에서 전체 리뷰 목록 표시

## 작업 내용
- [ ] 리뷰 목록 패널 (닉네임·별점·내용·날짜) — A
- [ ] ReviewDAO.findByMovieId() 쿼리 구현 — B

## 관련 요구사항
FR-12
```

---

### Issue #13 — 내 리뷰 모아보기

**Label:** `review` `UI` `backend`  
**Assignees:** A (UI), B (로직)  
**Milestone:** Milestone 2 - 영화·리뷰 기능

```
## 설명
마이페이지에서 내가 작성한 리뷰 전체 조회

## 작업 내용
- [ ] 마이페이지 패널 (내 리뷰 목록 JTable) — A
- [ ] ReviewDAO.findByUserId() 쿼리 구현 — B

## 관련 요구사항
FR-13
```

---

### Issue #14 — 중복 리뷰 방지

**Label:** `review` `backend`  
**Assignees:** B  
**Milestone:** Milestone 2 - 영화·리뷰 기능

```
## 설명
동일 사용자의 같은 영화 중복 리뷰 방지

## 작업 내용
- [ ] 리뷰 작성 전 중복 여부 DB 조회 — B
- [ ] 중복 시 안내 메시지 처리 — B

## 관련 요구사항
FR-14
```

---

### Issue #15 — 영화 제목 검색

**Label:** `search` `UI` `backend`  
**Assignees:** A (UI), B (로직)  
**Milestone:** Milestone 3 - 검색·관리자

```
## 설명
영화 제목으로 검색 기능 구현

## 작업 내용
- [ ] 검색 JTextField + 버튼 UI — A
- [ ] 검색 결과 목록 즉시 갱신 — A
- [ ] MovieDAO.searchByTitle() SQL LIKE 쿼리 구현 — B

## 관련 요구사항
FR-15
```

---

### Issue #16 — 감독 검색

**Label:** `search` `UI` `backend`  
**Assignees:** A (UI), B (로직)  
**Milestone:** Milestone 3 - 검색·관리자

```
## 설명
감독 이름으로 영화 검색 기능 구현

## 작업 내용
- [ ] 감독 검색 입력 UI — A
- [ ] MovieDAO.searchByDirector() 쿼리 구현 — B

## 관련 요구사항
FR-16
```

---

### Issue #17 — 영화 등록 / 수정 / 삭제 (관리자)

**Label:** `admin` `UI` `backend`  
**Assignees:** A (UI), B (로직)  
**Milestone:** Milestone 3 - 검색·관리자

```
## 설명
관리자 전용 영화 CRUD 기능 구현

## 작업 내용
- [ ] 관리자 전용 영화 관리 패널 UI — A
- [ ] 등록·수정·삭제 폼 다이얼로그 — A
- [ ] MovieDAO CRUD 쿼리 구현 — B
- [ ] 관리자 권한 확인 로직 — B

## 관련 요구사항
FR-17
```

---

### Issue #18 — 회원 목록 조회 및 권한 관리 (관리자)

**Label:** `admin` `UI` `backend`  
**Assignees:** A (UI), B (로직)  
**Milestone:** Milestone 3 - 검색·관리자

```
## 설명
관리자 전용 회원 관리 기능 구현

## 작업 내용
- [ ] 회원 목록 JTable UI — A
- [ ] 계정 정지·권한 변경 버튼 — A
- [ ] UserDAO.findAll() 및 권한 변경 쿼리 — B

## 관련 요구사항
FR-18
```

---

## 커밋 메시지 규칙

```
feat: 기능 구현       예) feat: 회원가입 폼 구현 closes #1
fix:  버그 수정       예) fix: 로그인 오류 수정 closes #2
ui:   UI 작업        예) ui: 영화 목록 JTable 구성 closes #5
db:   DB/쿼리 작업   예) db: ReviewDAO insert 구현 closes #9
docs: 문서 작업      예) docs: README 업데이트
```
