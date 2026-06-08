package main.service;

import main.domain.User;
import main.repository.ReviewDAO;
import main.repository.UserDAO;
import main.util.PasswordUtil;

import java.sql.SQLException;

public class MovieArchiveService {

    private final UserDAO userDAO = new UserDAO();
    private final ReviewDAO reviewDAO = new ReviewDAO();

    // 회원가입
    public void register(String username, String email, String password, String nickname) throws SQLException {
        // 아이디 중복 확인
        if (userDAO.findByUsername(username) != null) {
            throw new IllegalArgumentException("이미 사용 중인 아이디입니다.");
        }
        // 이메일 중복 확인
        if (userDAO.findByEmail(email) != null) {
            throw new IllegalArgumentException("이미 사용 중인 이메일입니다.");
        }
        String hashedPassword = PasswordUtil.hash(password);
        User user = new User(username, email, hashedPassword, nickname);
        userDAO.insert(user);
    }

    // 로그인
    public User login(String email, String password) throws SQLException {
        User user = userDAO.findByEmail(email);
        if (user == null) {
            throw new IllegalArgumentException("존재하지 않는 이메일입니다.");
        }
        if (!user.isActive()) {
            throw new IllegalArgumentException("탈퇴한 계정입니다.");
        }
        if (!PasswordUtil.hash(password).equals(user.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 틀렸습니다.");
        }
        return user;
    }

    // 내 정보 수정
    public void updateProfile(User user, String newNickname, String newPassword) throws SQLException {
        user.setNickname(newNickname);
        if (newPassword != null && !newPassword.isEmpty()) {
            user.setPassword(PasswordUtil.hash(newPassword));
        }
        userDAO.update(user);
    }

    // 회원 탈퇴
    public void deactivate(int userId) throws SQLException {
        // 유저 리뷰 먼저 삭제
        reviewDAO.deleteByUserId(userId);
        // 회원 탈퇴 (소프트 삭제)
        userDAO.deactivate(userId);
    }
}