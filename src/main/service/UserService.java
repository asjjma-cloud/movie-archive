package main.service;

import main.domain.User;
import main.repository.UserDAO;
import main.util.PasswordUtil;

import java.sql.SQLException;

public class UserService {

    private final UserDAO userDAO = new UserDAO();

    // 회원가입
    public void register(String username, String email, String password, String nickname) throws SQLException {
        // 이메일 중복 확인
        if (userDAO.findByEmail(email) != null) {
            throw new IllegalArgumentException("이미 사용 중인 이메일입니다.");
        }
        // 비밀번호 해시화
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
        userDAO.deactivate(userId);
    }
}