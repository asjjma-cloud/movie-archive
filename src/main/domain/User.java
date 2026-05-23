package main.domain;

public class User {
    private int id;
    private String username;
    private String email;
    private String password;
    private String nickname;
    private String role;
    private boolean isActive;
    private String createdAt;

    // 생성자 (DB에서 불러올 때)
    public User(int id, String username, String email, String password,
                String nickname, String role, boolean isActive, String createdAt) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.role = role;
        this.isActive = isActive;
        this.createdAt = createdAt;
    }

    // 생성자 (새 회원 등록할 때)
    public User(String username, String email, String password, String nickname) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.role = "user";
        this.isActive = true;
    }

    // Getter
    public int getId() { return id; }
    public String getUsername() { return username; }
    public String getEmail() { return email; }
    public String getPassword() { return password; }
    public String getNickname() { return nickname; }
    public String getRole() { return role; }
    public boolean isActive() { return isActive; }
    public String getCreatedAt() { return createdAt; }

    // Setter
    public void setId(int id) { this.id = id; }
    public void setPassword(String password) { this.password = password; }
    public void setNickname(String nickname) { this.nickname = nickname; }
    public void setRole(String role) { this.role = role; }
    public void setActive(boolean active) { isActive = active; }

    public boolean isAdmin() { return "admin".equals(role); }

    @Override
    public String toString() {
        return nickname + " (" + username + ")";
    }
}