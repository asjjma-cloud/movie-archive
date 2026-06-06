package main.view;

import main.controller.AuthController;
import javax.swing.*;
import java.awt.*;

public class RegisterDialog extends JDialog {

    private static final Color BG_COLOR = new Color(245, 245, 245);
    private static final Color PANEL_COLOR = new Color(255, 255, 255);
    private static final Color ACCENT_COLOR = new Color(30, 30, 30);
    private static final Color TEXT_COLOR = new Color(30, 30, 30);
    private static final Color HINT_COLOR = new Color(120, 120, 120);
    private static final Color INPUT_BG = new Color(250, 250, 250);
    private static final Color BORDER_COLOR = new Color(220, 220, 220);

    private JTextField usernameField;
    private JTextField emailField;
    private JPasswordField passwordField;
    private JTextField nicknameField;

    public RegisterDialog(JFrame parent, AuthController authController) {
        super(parent, "회원가입", true);
        setSize(400, 480);
        setLocationRelativeTo(parent);
        setResizable(false);
        getContentPane().setBackground(BG_COLOR);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(BG_COLOR);
        panel.setBorder(BorderFactory.createEmptyBorder(30, 40, 30, 40));

        JLabel titleLabel = new JLabel("회원가입");
        titleLabel.setFont(new Font("Dialog", Font.BOLD, 18));
        titleLabel.setForeground(ACCENT_COLOR);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        usernameField = new JTextField();
        emailField = new JTextField();
        passwordField = new JPasswordField();
        nicknameField = new JTextField();

        final JButton registerBtn = new JButton("가입하기");
        registerBtn.setMaximumSize(new Dimension(320, 38));
        registerBtn.setBackground(ACCENT_COLOR);
        registerBtn.setForeground(Color.WHITE);
        registerBtn.setOpaque(true);
        registerBtn.setFont(new Font("Dialog", Font.BOLD, 13));
        registerBtn.setBorderPainted(false);
        registerBtn.setFocusPainted(false);
        registerBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        registerBtn.setAlignmentX(Component.CENTER_ALIGNMENT);

        // 엔터키로 가입
        usernameField.addActionListener(e -> registerBtn.doClick());
        emailField.addActionListener(e -> registerBtn.doClick());
        passwordField.addActionListener(e -> registerBtn.doClick());
        nicknameField.addActionListener(e -> registerBtn.doClick());

        registerBtn.addActionListener(e -> {
            String username = usernameField.getText().trim();
            String email = emailField.getText().trim();
            String password = new String(passwordField.getPassword());
            String nickname = nicknameField.getText().trim();

            if (username.isEmpty() || email.isEmpty() || password.isEmpty() || nickname.isEmpty()) {
                JOptionPane.showMessageDialog(this, "모든 항목을 입력해주세요.", "오류", JOptionPane.ERROR_MESSAGE);
                return;
            }
            boolean success = authController.register(username, email, password, nickname);
            if (success) {
                JOptionPane.showMessageDialog(this, "회원가입이 완료되었습니다!", "성공", JOptionPane.INFORMATION_MESSAGE);
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "이미 사용 중인 아이디 또는 이메일입니다.", "오류", JOptionPane.ERROR_MESSAGE);
            }
        });

        panel.add(titleLabel);
        panel.add(Box.createVerticalStrut(20));
        panel.add(makeLabel("아이디"));
        panel.add(Box.createVerticalStrut(4));
        panel.add(styleInput(usernameField));
        panel.add(Box.createVerticalStrut(10));
        panel.add(makeLabel("이메일"));
        panel.add(Box.createVerticalStrut(4));
        panel.add(styleInput(emailField));
        panel.add(Box.createVerticalStrut(10));
        panel.add(makeLabel("비밀번호"));
        panel.add(Box.createVerticalStrut(4));
        panel.add(styleInput(passwordField));
        panel.add(Box.createVerticalStrut(10));
        panel.add(makeLabel("닉네임"));
        panel.add(Box.createVerticalStrut(4));
        panel.add(styleInput(nicknameField));
        panel.add(Box.createVerticalStrut(24));
        panel.add(registerBtn);

        add(panel);
        setVisible(true);
    }

    private JLabel makeLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Dialog", Font.PLAIN, 12));
        label.setForeground(HINT_COLOR);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        return label;
    }

    private JTextField styleInput(JTextField field) {
        field.setMaximumSize(new Dimension(320, 36));
        field.setBackground(INPUT_BG);
        field.setForeground(TEXT_COLOR);
        field.setCaretColor(ACCENT_COLOR);
        field.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(BORDER_COLOR, 1),
                BorderFactory.createEmptyBorder(4, 10, 4, 10)
        ));
        field.setFont(new Font("Dialog", Font.PLAIN, 13));
        field.setAlignmentX(Component.CENTER_ALIGNMENT);
        return field;
    }
}