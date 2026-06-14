# 8 — Collaborators

## Archimoo

> Archive + movie 의 합성어

## 최영재 담당 파트

### 백엔드 · DB · 테스트

- DB 스키마 설계 (`schema.sql`)
- `DBConnection.java` 구현
- Domain 클래스 (`Movie`, `User`, `Review`)
- DAO 3개 (`UserDAO`, `MovieDAO`, `ReviewDAO`)
- Service 3개 (`MovieArchiveService`, `MovieService`, `ReviewService`)
- SHA-256 암호화 (`PasswordUtil`)
- 단위 테스트 및 버그 수정

## 남하경 담당 파트

### 프론트엔드 · Controller · 테스트

- Controller 3개
    - `AuthController`
    - `MovieController`
    - `ReviewController`

- Swing GUI 전체 구현
    - `LoginFrame`, `RegisterDialog`
    - `MainFrame`, `MovieListPanel`, `MovieDetailPanel`
    - `ReviewDialog`, `MyPagePanel`, `AdminPanel`

- 통합 테스트
- 문서 작성

## 협업 진행 방식
| 용도 | 도구 |
|------|------|
| 소스 코드 관리 | GitHub (브랜치 전략: `main` / `feature/{기능명}`) |
| 실시간 소통 | Discord 또는 대면 |
| 이슈·작업 추적 | GitHub Issues (2주차 Issue 목록 활용) |
| 문서 공유 | GitHub Wiki / 프로젝트 루트 `docs/` 폴더 |