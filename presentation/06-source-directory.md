# 6 — 소스 디렉토리

## 1 - 프로젝트 패키지
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

## 2 - 패키지 역할

| 폴더           | 내용                        |
|--------------|---------------------------|
| `controller` | UI 이벤트 처리                 |
| `domain`     | User, Movie, Review 같은 객체 |
| `repository` | DB CRUD(SQL) 담당           |
| `service`    | 비즈니스 로직                   |
| `util`       | 공통 기능(DB 연결, 암호화 등)       |
| `Main.java`  | 프로그램 시작점                  |


---