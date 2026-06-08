
---

# 06. 팀 소개

🎬 Movie Archive

---

## 팀 Archimoo

> **Archive** + **movie** 의 합성어

영화 감상 기록을 아카이빙한다는 의미를 담았습니다.

---

## 팀원 소개

| 이름 | 학번 | 역할 |
|------|------|------|
| 👑 최영재 (팀장) | 20220519 | 백엔드·DB 설계·테스트 |
| 남하경 | 20240609 | 프론트엔드·Controller·테스트 |

---

## 최영재 담당 파트

**백엔드 · DB · 테스트**

- DB 스키마 설계 (`schema.sql`)
- `DBConnection.java` 구현
- Domain 클래스 (`Movie`, `User`, `Review`)
- DAO 3개 (`UserDAO`, `MovieDAO`, `ReviewDAO`)
- Service 3개 (`MovieArchiveService`, `MovieService`, `ReviewService`)
- SHA-256 암호화 (`PasswordUtil`)
- 단위 테스트 및 버그 수정

---

## 남하경 담당 파트

**프론트엔드 · Controller · 테스트**

- Controller 3개 (`AuthController`, `MovieController`, `ReviewController`)
- Swing GUI 전체 구현
    - `LoginFrame`, `RegisterDialog`
    - `MainFrame`, `MovieListPanel`, `MovieDetailPanel`
    - `ReviewDialog`, `MyPagePanel`, `AdminPanel`
- 통합 테스트
- 문서 작성

---

## 협업 방식

| 항목 | 내용 |
|------|------|
| 소스 관리 | GitHub (`asjjma-cloud/movie-archive`) |
| 브랜치 전략 | `main` / `feature/{기능명}` |
| 커밋 규칙 | `[feat]`, `[fix]`, `[docs]` 접두사 사용 |
| 소통 | Discord 또는 대면 |
| 이슈 관리 | GitHub Issues (F-01 ~ F-19) |

---

## GitHub Issues 완료 현황

✅ F-01 ~ F-19 **19개 이슈 전부 Closed**

---

## 프로젝트 회고

**잘 된 점 ✅**
- 계획한 기능 전부 구현 완료
- 4계층 아키텍처로 역할 분리 명확
- 버그 발견 및 수정 체계적으로 진행

**아쉬운 점 📌**
- 팔로우·피드 기능 미구현 (향후 개발 예정)
- 로컬 DB라 실시간 다중 사용자 연동 불가

---

## 향후 개발 계획

- 팔로우 / 피드 기능 구현
- MySQL 서버 전환 (실시간 연동)
- Spring Boot REST API 마이그레이션

---

## 감사합니다 🎬

**팀 Archimoo**
최영재 · 남하경

[← 목차로](index.html)