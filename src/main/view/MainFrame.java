package main.view;

import main.controller.AuthController;
import main.controller.MovieController;
import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    private static final Color BG_COLOR = new Color(245, 245, 245);
    private static final Color PANEL_COLOR = new Color(255, 255, 255);
    private static final Color ACCENT_COLOR = new Color(30, 30, 30);
    private static final Color TEXT_COLOR = new Color(30, 30, 30);
    private static final Color HINT_COLOR = new Color(120, 120, 120);
    private static final Color BORDER_COLOR = new Color(220, 220, 220);

    private final AuthController authController;
    private final MovieController movieController = new MovieController();

    private JPanel contentPanel;
    private JLabel userLabel; // 닉네임 라벨 필드로 분리

    public MainFrame(AuthController authController) {
        this.authController = authController;
        initUI();
    }

    private void initUI() {
        setTitle("Movie Archive");
        setSize(1024, 768);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        getContentPane().setBackground(BG_COLOR);
        setLayout(new BorderLayout());

        JPanel navBar = createNavBar();
        add(navBar, BorderLayout.NORTH);

        contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBackground(BG_COLOR);
        add(contentPanel, BorderLayout.CENTER);

        showMovieList();
        setVisible(true);
    }

    private JPanel createNavBar() {
        JPanel nav = new JPanel(new BorderLayout());
        nav.setBackground(PANEL_COLOR);
        nav.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(0, 0, 1, 0, BORDER_COLOR),
                BorderFactory.createEmptyBorder(12, 20, 12, 20)
        ));

        JLabel logo = new JLabel("🎬 Movie Archive");
        logo.setFont(new Font("Dialog", Font.BOLD, 16));
        logo.setForeground(ACCENT_COLOR);

        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        btnPanel.setBackground(PANEL_COLOR);

        // 닉네임 라벨 필드로 분리
        userLabel = new JLabel(authController.getCurrentUser().getNickname() + " 님");
        userLabel.setFont(new Font("Dialog", Font.PLAIN, 13));
        userLabel.setForeground(HINT_COLOR);

        JButton movieListBtn = createNavButton("영화 목록");
        JButton myPageBtn = createNavButton("내 보관함");
        JButton logoutBtn = createNavButton("로그아웃");

        movieListBtn.addActionListener(e -> showMovieList());
        myPageBtn.addActionListener(e -> showMyPage());
        logoutBtn.addActionListener(e -> handleLogout());

        btnPanel.add(userLabel);
        btnPanel.add(movieListBtn);
        btnPanel.add(myPageBtn);

        if (authController.isAdmin()) {
            JButton adminBtn = createNavButton("관리자");
            adminBtn.setForeground(ACCENT_COLOR);
            adminBtn.addActionListener(e -> showAdminPanel());
            btnPanel.add(adminBtn);
        }

        btnPanel.add(logoutBtn);

        nav.add(logo, BorderLayout.WEST);
        nav.add(btnPanel, BorderLayout.EAST);

        return nav;
    }

    private JButton createNavButton(String text) {
        JButton btn = new JButton(text);
        btn.setBackground(PANEL_COLOR);
        btn.setForeground(TEXT_COLOR);
        btn.setFont(new Font("Dialog", Font.PLAIN, 13));
        btn.setBorder(BorderFactory.createLineBorder(BORDER_COLOR, 1));
        btn.setFocusPainted(false);
        btn.setOpaque(true);
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        return btn;
    }

    // 닉네임 실시간 갱신 메서드
    public void updateUserLabel() {
        userLabel.setText(authController.getCurrentUser().getNickname() + " 님");
        userLabel.revalidate();
        userLabel.repaint();
    }

    public void showMovieList() {
        contentPanel.removeAll();
        contentPanel.add(new MovieListPanel(authController, movieController, this), BorderLayout.CENTER);
        contentPanel.revalidate();
        contentPanel.repaint();
    }

    public void showMyPage() {
        contentPanel.removeAll();
        contentPanel.add(new MyPagePanel(authController, this), BorderLayout.CENTER);
        contentPanel.revalidate();
        contentPanel.repaint();
    }

    private void showAdminPanel() {
        contentPanel.removeAll();
        contentPanel.add(new AdminPanel(authController, movieController, this), BorderLayout.CENTER);
        contentPanel.revalidate();
        contentPanel.repaint();
    }

    private void handleLogout() {
        authController.logout();
        dispose();
        new LoginFrame();
    }
}