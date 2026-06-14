# 3 — 프로젝트 구조 및 역할

---

## 1 - 디렉토리 구조

```
movie-archive/
│ 
├──README.md
├──docs
│   └── 0_project_overview
│       └── README.md
│   └── 1_requirment_analysis
│       └── README.md
│       └── UI_design.jpg
│   └── 2_project_structure
│       └── README.md
│   └── 3_feature_implementation
│       └── README.md
│   └── 4_summary
│       └── README.md
├── src/
│   └── main/
│       ├── controller/
│       │   ├── AuthController.java      
│       │   ├── MovieController.java   
│       │   └── ReviewController.java   
│       ├── domain/
│       │   ├── Movie.java               
│       │   ├── User.java             
│       │   └── Review.java            
│       ├── repository/
│       │   ├── MovieDAO.java      
│       │   ├── UserDAO.java             
│       │   └── ReviewDAO.java          
│       ├── service/
│       │   ├── MovieArchiveService.java
│       │   ├── MovieService.java      
│       │   └── ReviewService.java       
│       ├── view/
│       │   ├── LoginFrame.java      
│       │   ├── RegisterDialog.java     
│       │   ├── MainFrame.java          
│       │   ├── MovieListPanel.java    
│       │   ├── MovieDetailPanel.java    
│       │   ├── ReviewDialog.java        
│       │   ├── MyPagePanel.java         
│       │   └── AdminPanel.java         
│       └── util/
│           ├── DBConnection.java       
│           └── PasswordUtil.java        
└── resources/
    └── db/
        ├── schema.sql                  
        └── movie_archive.db             
```



## 2 - 팀 역할 분담

| 구분 | 최영재 | 남하경 |
|------|--------|--------|
| **주요 역할** | 백엔드·DB·테스트 | 프론트엔드·백엔드·테스트 |
| **담당 계층** | Service, DAO, DB 설계, 유틸 | View (Swing UI), Controller, 이벤트 처리 |
| **회원 기능** | DB 검증·SHA-256 해시·트랜잭션 로직 (F-01~F-04) | 로그인/회원가입/마이페이지 UI (F-01~F-04) |
| **영화 기능** | MovieDAO CRUD·검색 쿼리 (F-05~F-08, F-15~F-17) | 영화 목록·상세·필터·검색 UI (F-05~F-08, F-15~F-17) |
| **리뷰 기능** | ReviewDAO CRUD·중복 방지·권한 검증 (F-09~F-14) | 리뷰 작성·수정·삭제 다이얼로그 UI (F-09~F-13) |
| **관리자 기능** | 권한 확인 로직·UserDAO 권한 쿼리 (F-17, F-18) | 관리자 전용 패널 UI (F-17, F-18) |
| **공통** | DB 스키마 설계, JDBC 커넥션 관리, 단위 테스트 | UI 이벤트 연결, 통합 테스트, 문서 작성 |

## 3 - 일정 관리


| 주차 | 기간 | 내용 |
|------|------|------|
| 1~2주차 | 04/27 ~ 05/10 | 요구사항 분석, 시스템 설계, GitHub 세팅 |
| 3주차 | 05/11 ~ 05/17 | DB 스키마 설계, DBConnection, UserDAO 골격 |
| 4주차 | 05/18 ~ 05/24 | Domain 클래스, DAO, Service 구현 |
| 5~6주차 | 05/25 ~ 06/07 | Controller, Swing GUI 전체 구현 |
| 7주차 | 06/08 ~ 06/14 | 버그 수정, UI 개선, 발표 준비 |
| 발표 | 06/15 | 최종 발표 |

