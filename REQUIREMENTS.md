# 🎬 Movie Archive Requirements

> 영화 리뷰 시스템 개발 이슈를 GitHub에 일관된 형식으로 등록하기 위한 가이드

---

## 📌 문서 소개

이 문서는 **GitHub Issue 기반 개발 관리 템플릿**입니다.  
아래 정의된 Label/Milestone을 먼저 생성한 뒤, Issue를 번호 순서대로 등록해 사용합니다.

---

## 🧰 사전 준비

### 🏷 Labels (먼저 생성)

| 이름 | 색상 |
|------|------|
| `auth` | `#7F77DD` |
| `movie` | `#1D9E75` |
| `review` | `#D85A30` |
| `search` | `#378ADD` |
| `admin` | `#BA7517` |
| `UI` | `#AFA9EC` |
| `backend` | `#5DCAA5` |

### 🎯 Milestones (먼저 생성)

| 이름 | 포함 Issue |
|------|-----------|
| `Milestone 1 - 회원 기능` | #1 ~ #4 |
| `Milestone 2 - 영화·리뷰 기능` | #5 ~ #14 |
| `Milestone 3 - 검색·관리자` | #15 ~ #18 |

---

## 🗂 이슈 등록 순서

1. Label / Milestone 생성
2. 아래 표 기준으로 Issue 생성
3. 각 Issue에 Label, Assignees, Milestone 지정
4. 본문은 하단 템플릿 형식 유지

---

## ✨ Issue 목록

### 👤 Milestone 1 - 회원 기능

| 번호 | 제목 | Label | Assignees | 요구사항 |
|------|------|-------|-----------|----------|
| #1 | 회원가입 | `auth` `UI` `backend` | A(UI), B(로직) | FR-01 |
| #2 | 로그인 / 로그아웃 | `auth` `UI` `backend` | A(UI), B(로직) | FR-02 |
| #3 | 내 정보 수정 | `auth` `UI` `backend` | A(UI), B(로직) | FR-03 |
| #4 | 회원 탈퇴 | `auth` `UI` `backend` | A(UI), B(로직) | FR-04 |

### 🎥 Milestone 2 - 영화·리뷰 기능

| 번호 | 제목 | Label | Assignees | 요구사항 |
|------|------|-------|-----------|----------|
| #5 | 영화 목록 조회 | `movie` `UI` `backend` | A(UI), B(로직) | FR-05 |
| #6 | 영화 상세 보기 | `movie` `UI` `backend` | A(UI), B(로직) | FR-06 |
| #7 | 장르별 필터링 | `movie` `UI` `backend` | A(UI), B(로직) | FR-07 |
| #8 | 평균 별점 표시 | `movie` `UI` `backend` | A(UI), B(로직) | FR-08 |
| #9 | 리뷰 작성 | `review` `UI` `backend` | A(UI), B(로직) | FR-09 |
| #10 | 리뷰 수정 | `review` `UI` `backend` | A(UI), B(로직) | FR-10 |
| #11 | 리뷰 삭제 | `review` `UI` `backend` | A(UI), B(로직) | FR-11 |
| #12 | 리뷰 목록 조회 | `review` `UI` `backend` | A(UI), B(로직) | FR-12 |
| #13 | 내 리뷰 모아보기 | `review` `UI` `backend` | A(UI), B(로직) | FR-13 |
| #14 | 중복 리뷰 방지 | `review` `backend` | B | FR-14 |

### 🔎 Milestone 3 - 검색·관리자

| 번호 | 제목 | Label | Assignees | 요구사항 |
|------|------|-------|-----------|----------|
| #15 | 영화 제목 검색 | `search` `UI` `backend` | A(UI), B(로직) | FR-15 |
| #16 | 감독 검색 | `search` `UI` `backend` | A(UI), B(로직) | FR-16 |
| #17 | 영화 등록 / 수정 / 삭제 (관리자) | `admin` `UI` `backend` | A(UI), B(로직) | FR-17 |
| #18 | 회원 목록 조회 및 권한 관리 (관리자) | `admin` `UI` `backend` | A(UI), B(로직) | FR-18 |

---

## 🧩 Issue 본문 템플릿

아래 형식을 복사해서 각 Issue의 본문에 사용합니다.

```md
## 설명
기능 요약을 1~2문장으로 작성

## 작업 내용
- [ ] UI 작업 — A
- [ ] 로직/DB 작업 — B

## 관련 요구사항
FR-XX
```

---

## 🧱 Issue 상세 작업 정의

### #1 회원가입
- [ ] 아이디, 비밀번호, 닉네임 입력 폼 (JTextField) 구성 — A
- [ ] 중복 아이디 확인 버튼 및 안내 메시지 — A
- [ ] 중복 아이디 DB 검증 로직 — B
- [ ] 비밀번호 SHA-256 해시 저장 — B

### #2 로그인 / 로그아웃
- [ ] 로그인 화면 (LoginFrame) 구성 — A
- [ ] 로그아웃 버튼 및 세션 초기화 — A
- [ ] DB 조회로 자격 증명 확인 로직 — B
- [ ] 로그인 실패 시 안내 메시지 처리 — B

### #3 내 정보 수정
- [ ] 내 정보 수정 다이얼로그 UI — A
- [ ] 현재 비밀번호 확인 입력 폼 — A
- [ ] 변경 전 비밀번호 검증 로직 — B
- [ ] 닉네임·비밀번호 DB 업데이트 — B

### #4 회원 탈퇴
- [ ] 탈퇴 확인 다이얼로그 UI — A
- [ ] 탈퇴 시 연관 리뷰 삭제 트랜잭션 로직 — B
- [ ] 계정 삭제 DB 처리 — B

### #5 영화 목록 조회
- [ ] JTable로 영화 목록 표시 (제목·장르·연도·평균별점 컬럼) — A
- [ ] 영화 선택 시 상세 화면으로 이동 — A
- [ ] MovieDAO.findAll() 쿼리 구현 — B

### #6 영화 상세 보기
- [ ] 영화 상세 패널 (감독, 줄거리, 포스터 이미지, 리뷰 목록) — A
- [ ] 포스터 이미지 로컬 파일 경로 로드 — A
- [ ] MovieDAO.findById() 쿼리 구현 — B

### #7 장르별 필터링
- [ ] JComboBox 장르 선택 UI — A
- [ ] 선택 시 목록 즉시 갱신 이벤트 처리 — A
- [ ] MovieDAO.findByGenre() 쿼리 구현 — B

### #8 평균 별점 표시
- [ ] 영화 목록·상세 화면에 평균 별점 표시 컴포넌트 — A
- [ ] AVG(rating) SQL 집계 쿼리 구현 — B

### #9 리뷰 작성
- [ ] 리뷰 작성 다이얼로그 (별점 JSlider + JTextArea) — A
- [ ] 로그인 여부 확인 후 작성 버튼 활성화 — A
- [ ] ReviewDAO.insert() 쿼리 구현 — B
- [ ] 로그인 상태 검증 로직 — B

### #10 리뷰 수정
- [ ] 리뷰 수정 다이얼로그 (기존 내용 불러오기) — A
- [ ] ReviewDAO.update() 쿼리 구현 — B
- [ ] 본인 리뷰 여부 검증 로직 — B

### #11 리뷰 삭제
- [ ] 삭제 확인 다이얼로그 UI — A
- [ ] ReviewDAO.delete() 쿼리 구현 — B
- [ ] 본인 / 관리자 권한 분기 로직 — B

### #12 리뷰 목록 조회
- [ ] 리뷰 목록 패널 (닉네임·별점·내용·날짜) — A
- [ ] ReviewDAO.findByMovieId() 쿼리 구현 — B

### #13 내 리뷰 모아보기
- [ ] 마이페이지 패널 (내 리뷰 목록 JTable) — A
- [ ] ReviewDAO.findByUserId() 쿼리 구현 — B

### #14 중복 리뷰 방지
- [ ] 리뷰 작성 전 중복 여부 DB 조회 — B
- [ ] 중복 시 안내 메시지 처리 — B

### #15 영화 제목 검색
- [ ] 검색 JTextField + 버튼 UI — A
- [ ] 검색 결과 목록 즉시 갱신 — A
- [ ] MovieDAO.searchByTitle() SQL LIKE 쿼리 구현 — B

### #16 감독 검색
- [ ] 감독 검색 입력 UI — A
- [ ] MovieDAO.searchByDirector() 쿼리 구현 — B

### #17 영화 등록 / 수정 / 삭제 (관리자)
- [ ] 관리자 전용 영화 관리 패널 UI — A
- [ ] 등록·수정·삭제 폼 다이얼로그 — A
- [ ] MovieDAO CRUD 쿼리 구현 — B
- [ ] 관리자 권한 확인 로직 — B

### #18 회원 목록 조회 및 권한 관리 (관리자)
- [ ] 회원 목록 JTable UI — A
- [ ] 계정 정지·권한 변경 버튼 — A
- [ ] UserDAO.findAll() 및 권한 변경 쿼리 — B

---

## 📝 커밋 메시지 규칙

```bash
feat: 기능 구현       예) feat: 회원가입 폼 구현 closes #1
fix:  버그 수정       예) fix: 로그인 오류 수정 closes #2
ui:   UI 작업         예) ui: 영화 목록 JTable 구성 closes #5
db:   DB/쿼리 작업    예) db: ReviewDAO insert 구현 closes #9
docs: 문서 작업       예) docs: README 업데이트
```
