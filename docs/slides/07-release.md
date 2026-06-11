---
layout: default
title: 07. Release
---

# 07. Release

## 실행 환경

| 항목 | 내용 |
|------|------|
| OS | macOS / Windows / Linux |
| JDK | Java 17 이상 |
| IDE | IntelliJ IDEA |
| DB | SQLite (별도 설치 불필요) |

## 실행 방법

### 1. 레포지토리 클론

```bash
git clone https://github.com/asjjma-cloud/movie-archive.git
```

### 2. IntelliJ IDEA에서 프로젝트 열기

File → Open → `movie-archive` 폴더 선택

### 3. SQLite JDBC 라이브러리 추가
### 4. Main.java 실행
> DB 파일은 `resources/db/movie_archive.db`에 자동 생성됩니다.

## 관리자 계정 설정

### 1. 앱에서 회원가입

일반 회원가입으로 관리자 계정 생성

### 2. DB에서 role 변경

```bash
sqlite3 resources/db/movie_archive.db
```

```sql
UPDATE users SET role = 'admin' WHERE email = '관리자이메일';
```

### 3. 앱 재실행

로그인 후 네비게이션 바에 **관리자** 버튼 확인

## 테스트 데이터

영화 데이터 삽입 방법:

```bash
sqlite3 resources/db/movie_archive.db
```

```sql
INSERT INTO movies (title, director, genre, release_year, overview)
VALUES ('인터스텔라', '크리스토퍼 놀란', 'SF', 2014, '우주를 통해 인류의 생존을 찾아 떠나는 이야기');
```

## 버전 히스토리

| 버전 | 날짜 | 내용 |
|------|------|------|
| v1.0 | 2026.05.25 | 초기 개발 환경 세팅, Domain/DAO/Service 구현 |
| v2.0 | 2026.06.01 | Controller, Swing GUI 전체 구현 |
| v3.0 | 2026.06.08 | 버그 수정, UI 개선, 정렬 기능 추가 |
| v4.0 | 2026.06.14 | 통합 테스트 완료, 발표 준비 |

[← 목차로](index.html)
