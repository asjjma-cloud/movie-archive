---
layout: default
title: 04. Feature Implementation
---

# 04. Feature Implementation

## 🔐 회원 인증

- 회원가입 (아이디, 이메일, 비밀번호, 닉네임)
- 아이디 / 이메일 중복 검증
- 로그인 / 로그아웃
- SHA-256 비밀번호 해시 암호화
- 탈퇴한 계정 로그인 차단
- 로그인 / 회원가입 엔터키 지원

## SHA-256 암호화 구현

```java
public static String hash(String password) {
    MessageDigest md = MessageDigest.getInstance("SHA-256");
    byte[] hashed = md.digest(password.getBytes());
    StringBuilder sb = new StringBuilder();
    for (byte b : hashed) {
        sb.append(String.format("%02x", b));
    }
    return sb.toString();
}
```

## 👤 마이페이지

- 닉네임 / 비밀번호 수정
- 정보 수정 후 네비게이션 바 닉네임 실시간 갱신
- 회원 탈퇴 시 작성한 리뷰 자동 삭제
- 내가 작성한 리뷰 모아보기
- 내 리뷰 수정 / 삭제

## 🎥 영화 목록

- 전체 영화 목록 조회
- 제목 / 감독 키워드 검색 (엔터키 지원)
- 장르별 필터링 (8개 장르)
- 컬럼 헤더 클릭 정렬 (제목/감독 가나다순, 개봉연도/별점 숫자순)
- 평균 별점 실시간 표시

## 정렬 기능 구현

```java
// TableRowSorter로 컬럼 정렬 구현
sorter = new TableRowSorter<>(tableModel);
movieTable.setRowSorter(sorter);

// 정렬 타입 지정
@Override
public Class<?> getColumnClass(int col) {
    switch (col) {
        case 4: return Integer.class;  // 개봉연도
        case 5: return Double.class;   // 평균 별점
        default: return String.class;
    }
}
```

## ⭐ 리뷰 & 별점

- 영화별 리뷰 작성 (JSlider 별점 1~5점)
- 영화 1개당 리뷰 1개 제한 (중복 방지)
- 리뷰 작성 후 평균 별점 즉시 갱신
- 리뷰 수정 / 삭제 (본인 리뷰만 가능)

## 중복 리뷰 방지 구현

```java
// DB 제약조건
UNIQUE (user_id, movie_id)

// exists() 메서드로 이중 검증
public boolean exists(int userId, int movieId) throws SQLException {
    String sql = "SELECT COUNT(*) FROM reviews "
               + "WHERE user_id = ? AND movie_id = ?";
    ...
    return rs.getInt(1) > 0;
}
```

## 🛡 관리자 패널

- 관리자 계정 로그인 시 네비게이션에 관리자 버튼 표시
- 영화 추가 / 삭제
- 전체 회원 목록 조회
- 회원 계정 정지

## 핵심 구현 포인트

| 항목 | 내용 |
|------|------|
| SHA-256 | 비밀번호 해시 처리 |
| 싱글톤 DB | DBConnection 단일 관리 |
| 절대경로 DB | System.getProperty("user.dir") |
| KST 시간대 | datetime('now', '+9 hours') |
| Stream API | 평균 별점 mapToDouble().average() |
| TableRowSorter | 컬럼 헤더 클릭 정렬 |
| 실시간 갱신 | 리뷰·닉네임 즉시 반영 |
| 엔터키 지원 | addActionListener 적용 |

[← 목차로](index.html)
