---
marp: true
theme: default
paginate: true
---

# 09. Readme & Gitignore

## README.md 구성

README.md는 프로젝트의 첫 인상입니다.
GitHub 레포 접속 시 자동으로 표시됩니다.

---

## README.md 주요 항목

| 항목 | 내용 |
|------|------|
| 프로젝트 소개 | 목적 및 개요 |
| 구현된 기능 | 전체 기능 목록 |
| 기술 스택 | 사용 기술 정리 |
| 시스템 구조 | 4계층 아키텍처 |
| 프로젝트 구조 | 폴더·파일 구조 |
| DB 설계 | ERD 및 테이블 명세 |
| 실행 방법 | 설치 및 실행 가이드 |
| 개발 일정 | 주차별 일정 |
| 팀 소개 | 팀원 및 역할 |

---

## README.md 링크

[📄 README.md 보기](https://github.com/asjjma-cloud/movie-archive/blob/main/README.md)

---

## .gitignore 구성

.gitignore는 Git 추적에서 제외할 파일을 지정합니다.

---

## .gitignore 적용 이유

| 항목 | 이유 |
|------|------|
| `.idea/` | IntelliJ 설정 파일 (개인 환경 의존) |
| `out/` | 컴파일된 결과물 (재생성 가능) |
| `*.class` | 컴파일된 클래스 파일 |
| `movie_archive.db` | 로컬 DB 데이터 (개인 데이터 포함) |
| `.DS_Store` | macOS 시스템 파일 |

---

## GitHub 레포지토리

| 항목 | 내용 |
|------|------|
| 레포 주소 | asjjma-cloud/movie-archive |
| 브랜치 | main |
| Issues | F-01 ~ F-19 전부 Closed |
| Pages | asjjma-cloud.github.io/movie-archive |
