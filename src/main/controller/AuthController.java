package main.controller;

import main.domain.User;
import main.service.MovieArchiveService;

import java.sql.SQLException;

public class AuthController {

    private final MovieArchiveService userService = new MovieArchiveService();
    private User currentUser = null;

    // 회원가입
    public void register(String username, String email, String password, String nickname) {
        try {
            userService.register(username, email, password, nickname);
            System.out.println("회원가입 성공!");
        } catch (IllegalArgumentException | SQLException e) {
            System.err.println("회원가입 실패: " + e.getMessage());
        }
    }

    // 로그인
    public boolean login(String email, String password) {
        try {
            currentUser = userService.login(email, password);
            System.out.println("로그인 성공: " + currentUser.getNickname());
            return true;
        } catch (IllegalArgumentException | SQLException e) {
            System.err.println("로그인 실패: " + e.getMessage());
            return false;
        }
    }

    // 로그아웃
    public void logout() {
        currentUser = null;
        System.out.println("로그아웃 되었습니다.");
    }

    // 현재 로그인한 유저 반환
    public User getCurrentUser() {
        return currentUser;
    }

    // 로그인 여부 확인
    public boolean isLoggedIn() {
        return currentUser != null;
    }

    // 관리자 여부 확인
    public boolean isAdmin() {
        return currentUser != null && currentUser.isAdmin();
    }

    // 회원 탈퇴
    public void deactivate() {
        if (currentUser == null) {
            System.err.println("로그인이 필요합니다.");
            return;
        }
        try {
            userService.deactivate(currentUser.getId());
            currentUser = null;
            System.out.println("회원 탈퇴 완료.");
        } catch (SQLException e) {
            System.err.println("탈퇴 실패: " + e.getMessage());
        }
    }
}