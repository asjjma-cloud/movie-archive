package main.view;

import main.controller.AuthController;
import javax.swing.*;
import java.awt.*;

public class LoginFrame extends JFrame {

    private final AuthController authController = new AuthController();

    private static final Color BG_COLOR = new Color(245, 245, 245);
    private static final Color PANEL_COLOR = new Color(255, 255, 255);
    private static final Color ACCENT_COLOR = new Color(30, 30, 30);
    private static final Color TEXT_COLOR = new Color(30, 30, 30);
    private static final Color HINT_COLOR = new Color(120, 120, 120);
    private static final Color INPUT_BG = new Color(250, 250, 250);
    private static final Color BORDER_COLOR = new Color(220, 220, 220);

    private JTextField emailField;
    private JPasswordField passwordField;

    public LoginFrame() {
        initUI();
    }

    private void initUI() {
        setTitle("Movie Archive");
        setSize(1024, 768);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        getContentPane().setBackground(BG_COLOR);
        setLayout(new GridBagLayout());

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBackground(PANEL_COLOR);
        centerPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(BORDER_COLOR, 1),
                BorderFactory.createEmptyBorder(40, 50, 40, 50)
        ));
        centerPanel.setPreferredSize(new Dimension(400, 480));

        JLabel logoLabel = new JLabel("🎬");
        logoLabel.setFont(new Font("Dialog", Font.PLAIN, 40));
        logoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel titleLabel = new JLabel("Movie Archive");
        titleLabel.setFont(new Font("Dialog", Font.BOLD, 22));
        titleLabel.setForeground(ACCENT_COLOR);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel subLabel = new JLabel("당신의 영화 이야기를 시작하세요");
        subLabel.setFont(new Font("Dialog", Font.PLAIN, 13));
        subLabel.setForeground(HINT_COLOR);
        subLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JSeparator separator = new JSeparator();
        separator.setForeground(BORDER_COLOR);
        separator.setMaximumSize(new Dimension(300, 1));

        JLabel emailLabel = new JLabel("이메일");
        emailLabel.setFont(new Font("Dialog", Font.PLAIN, 12));
        emailLabel.setForeground(HINT_COLOR);
        emailLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        emailField = new JTextField();
        styleInput(emailField);

        JLabel passwordLabel = new JLabel("비밀번호");
        passwordLabel.setFont(new Font("Dialog", Font.PLAIN, 12));
        passwordLabel.setForeground(HINT_COLOR);
        passwordLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        passwordField = new JPasswordField();
        styleInput(passwordField);

        // 엔터키로 로그인
        emailField.addActionListener(e -> handleLogin());
        passwordField.addActionListener(e -> handleLogin());

        JButton loginBtn = new JButton("로그인");
        styleAccentButton(loginBtn);
        loginBtn.addActionListener(e -> handleLogin());

        JButton registerBtn = new JButton("회원가입");
        styleGhostButton(registerBtn);
        registerBtn.addActionListener(e -> openRegisterDialog());

        centerPanel.add(logoLabel);
        centerPanel.add(Box.createVerticalStrut(8));
        centerPanel.add(titleLabel);
        centerPanel.add(Box.createVerticalStrut(6));
        centerPanel.add(subLabel);
        centerPanel.add(Box.createVerticalStrut(20));
        centerPanel.add(separator);
        centerPanel.add(Box.createVerticalStrut(20));
        centerPanel.add(emailLabel);
        centerPanel.add(Box.createVerticalStrut(4));
        centerPanel.add(emailField);
        centerPanel.add(Box.createVerticalStrut(12));
        centerPanel.add(passwordLabel);
        centerPanel.add(Box.createVerticalStrut(4));
        centerPanel.add(passwordField);
        centerPanel.add(Box.createVerticalStrut(24));
        centerPanel.add(loginBtn);
        centerPanel.add(Box.createVerticalStrut(8));
        centerPanel.add(registerBtn);

        add(centerPanel);
        setVisible(true);
    }

    private void handleLogin() {
        String email = emailField.getText().trim();
        String password = new String(passwordField.getPassword());

        if (email.isEmpty() || password.isEmpty()) {
            showError("이메일과 비밀번호를 입력해주세요.");
            return;
        }

        if (authController.login(email, password)) {
            dispose();
            new MainFrame(authController);
        } else {
            showError("이메일 또는 비밀번호가 틀렸습니다.");
        }
    }

    private void openRegisterDialog() {
        new RegisterDialog(this, authController);
    }

    private void showError(String msg) {
        JOptionPane.showMessageDialog(this, msg, "오류", JOptionPane.ERROR_MESSAGE);
    }

    private void styleInput(JTextField field) {
        field.setMaximumSize(new Dimension(300, 36));
        field.setPreferredSize(new Dimension(300, 36));
        field.setBackground(INPUT_BG);
        field.setForeground(TEXT_COLOR);
        field.setCaretColor(ACCENT_COLOR);
        field.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(BORDER_COLOR, 1),
                BorderFactory.createEmptyBorder(4, 10, 4, 10)
        ));
        field.setFont(new Font("Dialog", Font.PLAIN, 13));
        field.setAlignmentX(Component.CENTER_ALIGNMENT);
    }

    private void styleAccentButton(JButton btn) {
        btn.setMaximumSize(new Dimension(300, 38));
        btn.setPreferredSize(new Dimension(300, 38));
        btn.setBackground(ACCENT_COLOR);
        btn.setForeground(Color.WHITE);
        btn.setOpaque(true);
        btn.setFont(new Font("Dialog", Font.BOLD, 13));
        btn.setBorderPainted(false);
        btn.setFocusPainted(false);
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btn.setAlignmentX(Component.CENTER_ALIGNMENT);
    }

    private void styleGhostButton(JButton btn) {
        btn.setMaximumSize(new Dimension(300, 38));
        btn.setPreferredSize(new Dimension(300, 38));
        btn.setBackground(PANEL_COLOR);
        btn.setForeground(HINT_COLOR);
        btn.setOpaque(true);
        btn.setFont(new Font("Dialog", Font.PLAIN, 13));
        btn.setBorder(BorderFactory.createLineBorder(BORDER_COLOR, 1));
        btn.setFocusPainted(false);
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btn.setAlignmentX(Component.CENTER_ALIGNMENT);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(LoginFrame::new);
    }
}