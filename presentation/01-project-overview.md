---
marp: true
theme: default
paginate: true
backgroundColor: #ffffff
style: |
  section {
    font-family: -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, "Helvetica Neue", Arial, sans-serif;
    padding: 40px 60px;
    color: #333333;
  }
  h1 {
    font-size: 32px;
    font-weight: bold;
    color: #0f172a;
    border-bottom: none;
    margin-bottom: 30px;
  }
  h2 {
    font-size: 22px;
    font-weight: bold;
    color: #0f172a;
    margin-top: 24px;
    margin-bottom: 12px;
  }
  p, li {
    font-size: 16px;
    line-height: 1.6;
    color: #334155;
  }
  ul {
    margin-left: 20px;
    margin-bottom: 16px;
  }
  blockquote {
    background: transparent;
    border-left: 4px solid #cbd5e1;
    padding-left: 16px;
    margin: 16px 0;
    color: #64748b;
  }

---

# 1 — 프로젝트 소개

## 🎬 Movie Archive

> 내가 본 영화를 기록하고, 리뷰와 별점을 남기는, 영화 아카이브 서비스

## 프로젝트 목적

- 세대 제한 없는 유니버설 디자인 기반의 전 연령층 유저
- 감상 이력의 영속적 보존을 위한 맞춤형 히스토리 관리
- JAVA 핵심 메커니즘을 활용한 시네마틱 라이프 아카이빙 플랫폼 개발

## 기대 효과

- JAVA의 객체 지향 프로그래밍(OOP) 핵심 개념(상속, 다형성, 캡슐화 등)을 실제 비즈니스 로직에 적용함으로써 이론적 지식을 실무 지향적 개발 역량으로 승화.
- 데이터의 생성, 조회, 수정, 삭제(CRUD) 프로세스를 직접 설계하며 안정적인 백엔드 아키텍처 및 데이터 흐름 제어 능력 확보.
- 영화, 유저, 리뷰 등 서비스의 핵심 엔티티를 독립된 객체로 설계하고 인터페이스를 통해 모듈화함으로써, 코드의 재사용성과 유지보수성을 극대화하는 객체 지향적 소프트웨어 디자인 체득. (**클래스 설계, 상속, 다형성, 캡슐화, 인터페이스와 연계**)

---

# 2 — 프로젝트 배경 및 범위

## 프로젝트 배경

- OTT 플랫폼의 폭발적인 성장으로 인류 역사상 그 어느 때보다 많은 영상 미디어를 소비하고 있습니다. 그러나 역설적으로 콘텐츠 소비 속도가 빨라지면서, 영화를 본 후 느낀 깊은 영감과 정서적 감동이 기록되지 못한 채 빠르게 휘발되는 문제가 발생하고 있습니다.
- 남녀노소 누구나 자신의 영화적 경험을 쉽고 직관적으로 기록하고, 오랫동안 아카이빙할 수 있는 공간의 필요성을 절감했습니다. 

## 프로젝트 범위

| 포함         | 제외        |
|------------|-----------| 
| 회원가입 & 로그인 | 고급 보안 처리  |
| 영화 목록      | 외부 API 연동 |
| 리뷰 & 별점    | 다국어 지원    |
| 관리자        | 팔로우 기능    |

## 사용 기술 스택

| 분류           | 기술 및 도구 (Tech / Tool)              |
|:-------------|:-----------------------------------|
| **Backend**  | **Java 17**                        | 
| **UI**       | **Java Swing**                     | 
| **Database** | **SQLite**                         | 
| **Frontend** | **HTML5 / CSS3**<br>**JavaScript** | 
| **VCS**      | **Git & GitHub**                   | 

---

# 3 — 팀 구성 및 Github 저장소

## 팀 구성원

| 이름      | 학번       | 역할 |
|---------|----------|-----------|
| 최영재     | 20220519 | 백엔드, DB 설계, Domain/DAO/Service 구현, 테스트|
| 남하경     | 20240609 | 프론트엔드, Controller, Swing GUI 구현, 통합 테스트|

## 저장소 URL 공유

> https://github.com/asjjma-cloud/movie-archive.git
