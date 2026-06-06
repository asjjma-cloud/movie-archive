package main.view;

import main.controller.AuthController;
import main.controller.ReviewController;
import main.domain.Review;
import main.domain.User;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class MyPagePanel extends JPanel {

    private static final Color BG_COLOR = new Color(245, 245, 245);
    private static final Color PANEL_COLOR = new Color(255, 255, 255);
    private static final Color ACCENT_COLOR = new Color(30, 30, 30);
    private static final Color TEXT_COLOR = new Color(30, 30, 30);
    private static final Color HINT_COLOR = new Color(120, 120, 120);
    private static final Color INPUT_BG = new Color(250, 250, 250);
    private static final Color BORDER_COLOR = new Color(220, 220, 220);
    private static final Color TABLE_SEL = new Color(220, 220, 220);

    private final AuthController authController;
    private final MainFrame mainFrame;
    private final ReviewController reviewController = new ReviewController();

    private DefaultTableModel reviewTableModel;

    public MyPagePanel(AuthController authController, MainFrame mainFrame) {
        this.authController = authController;
        this.mainFrame = mainFrame;
        initUI();
        loadMyReviews();
    }

    private void initUI() {
        setLayout(new BorderLayout());
        setBackground(BG_COLOR);
        add(createProfilePanel(), BorderLayout.NORTH);
        add(createReviewPanel(), BorderLayout.CENTER);
    }

    private JPanel createProfilePanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(PANEL_COLOR);
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(0, 0, 1, 0, BORDER_COLOR),
                BorderFactory.createEmptyBorder(20, 24, 20, 24)
        ));

        User user = authController.getCurrentUser();

        JLabel nicknameLabel = new JLabel(user.getNickname());
        nicknameLabel.setFont(new Font("Dialog", Font.BOLD, 18));
        nicknameLabel.setForeground(ACCENT_COLOR);
        nicknameLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel emailLabel = new JLabel(user.getEmail());
        emailLabel.setFont(new Font("Dialog", Font.PLAIN, 13));
        emailLabel.setForeground(HINT_COLOR);
        emailLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 8));
        btnPanel.setBackground(PANEL_COLOR);
        btnPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JButton editBtn = createGhostButton("정보 수정");
        JButton deactivateBtn = createGhostButton("회원 탈퇴");
        deactivateBtn.setForeground(new Color(180, 60, 60));
        deactivateBtn.setBorder(BorderFactory.createLineBorder(new Color(180, 60, 60), 1));

        editBtn.addActionListener(e -> openEditDialog());
        deactivateBtn.addActionListener(e -> handleDeactivate());

        btnPanel.add(editBtn);
        btnPanel.add(Box.createHorizontalStrut(8));
        btnPanel.add(deactivateBtn);

        panel.add(nicknameLabel);
        panel.add(Box.createVerticalStrut(4));
        panel.add(emailLabel);
        panel.add(btnPanel);

        return panel;
    }

    private JPanel createReviewPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(BG_COLOR);

        JPanel headerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 16, 12));
        headerPanel.setBackground(BG_COLOR);
        JLabel title = new JLabel("내가 작성한 리뷰");
        title.setFont(new Font("Dialog", Font.BOLD, 15));
        title.setForeground(TEXT_COLOR);
        headerPanel.add(title);

        String[] columns = {"ID", "영화 ID", "별점", "내용", "작성일"};
        reviewTableModel = new DefaultTableModel(columns, 0) {
            public boolean isCellEditable(int r, int c) { return false; }
        };

        JTable reviewTable = new JTable(reviewTableModel);
        reviewTable.setBackground(new Color(255, 255, 255));
        reviewTable.setForeground(TEXT_COLOR);
        reviewTable.setFont(new Font("Dialog", Font.PLAIN, 13));
        reviewTable.setRowHeight(32);
        reviewTable.setShowGrid(false);
        reviewTable.setSelectionBackground(TABLE_SEL);
        reviewTable.setSelectionForeground(TEXT_COLOR);
        reviewTable.getTableHeader().setBackground(PANEL_COLOR);
        reviewTable.getTableHeader().setForeground(HINT_COLOR);
        reviewTable.getTableHeader().setFont(new Font("Dialog", Font.PLAIN, 12));

        reviewTable.getColumnModel().getColumn(0).setMinWidth(0);
        reviewTable.getColumnModel().getColumn(0).setMaxWidth(0);
        reviewTable.getColumnModel().getColumn(1).setMinWidth(0);
        reviewTable.getColumnModel().getColumn(1).setMaxWidth(0);
        reviewTable.getColumnModel().getColumn(2).setPreferredWidth(60);
        reviewTable.getColumnModel().getColumn(3).setPreferredWidth(400);
        reviewTable.getColumnModel().getColumn(4).setPreferredWidth(120);

        reviewTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int row = reviewTable.getSelectedRow();
                    if (row >= 0) {
                        int reviewId = (int) reviewTableModel.getValueAt(row, 0);
                        int rating = Integer.parseInt(
                                reviewTableModel.getValueAt(row, 2).toString().replace(" ★", "")
                        );
                        String content = (String) reviewTableModel.getValueAt(row, 3);
                        openEditReviewDialog(reviewId, rating, content);
                    }
                }
            }
        });

        JScrollPane scrollPane = new JScrollPane(reviewTable);
        scrollPane.getViewport().setBackground(new Color(255, 255, 255));
        scrollPane.setBorder(BorderFactory.createEmptyBorder());

        panel.add(headerPanel, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);

        return panel;
    }

    private void loadMyReviews() {
        reviewTableModel.setRowCount(0);
        int userId = authController.getCurrentUser().getId();
        List<Review> reviews = reviewController.getMyReviews(userId);
        for (Review r : reviews) {
            reviewTableModel.addRow(new Object[]{
                    r.getId(),
                    r.getMovieId(),
                    r.getRating() + " ★",
                    r.getContent(),
                    r.getCreatedAt()
            });
        }
    }

    private void openEditDialog() {
        User user = authController.getCurrentUser();

        JTextField nicknameField = new JTextField(user.getNickname());
        JPasswordField passwordField = new JPasswordField();

        JPanel editPanel = new JPanel();
        editPanel.setLayout(new BoxLayout(editPanel, BoxLayout.Y_AXIS));
        editPanel.add(new JLabel("닉네임"));
        editPanel.add(Box.createVerticalStrut(4));
        editPanel.add(nicknameField);
        editPanel.add(Box.createVerticalStrut(10));
        editPanel.add(new JLabel("새 비밀번호 (변경 시만 입력)"));
        editPanel.add(Box.createVerticalStrut(4));
        editPanel.add(passwordField);

        int result = JOptionPane.showConfirmDialog(
                this, editPanel, "정보 수정", JOptionPane.OK_CANCEL_OPTION
        );

        if (result == JOptionPane.OK_OPTION) {
            String newNickname = nicknameField.getText().trim();
            String newPassword = new String(passwordField.getPassword());
            if (!newNickname.isEmpty()) {
                try {
                    new main.service.MovieArchiveService().updateProfile(user, newNickname, newPassword);
                    mainFrame.updateUserLabel(); // 네비게이션 닉네임 실시간 갱신
                    JOptionPane.showMessageDialog(this, "수정되었습니다!", "성공", JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "수정 실패: " + ex.getMessage(), "오류", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    private void openEditReviewDialog(int reviewId, int rating, String content) {
        JSlider ratingSlider = new JSlider(1, 5, rating);
        ratingSlider.setMajorTickSpacing(1);
        ratingSlider.setPaintTicks(true);
        ratingSlider.setPaintLabels(true);
        ratingSlider.setSnapToTicks(true);

        JTextArea contentArea = new JTextArea(content, 4, 30);
        contentArea.setLineWrap(true);
        contentArea.setWrapStyleWord(true);

        JPanel editPanel = new JPanel();
        editPanel.setLayout(new BoxLayout(editPanel, BoxLayout.Y_AXIS));
        editPanel.add(new JLabel("별점"));
        editPanel.add(ratingSlider);
        editPanel.add(Box.createVerticalStrut(8));
        editPanel.add(new JLabel("내용"));
        editPanel.add(new JScrollPane(contentArea));

        String[] options = {"수정", "삭제", "취소"};
        int result = JOptionPane.showOptionDialog(
                this, editPanel, "리뷰 수정/삭제",
                JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
                null, options, options[0]
        );

        int userId = authController.getCurrentUser().getId();
        if (result == 0) {
            reviewController.updateReview(reviewId, userId, ratingSlider.getValue(), contentArea.getText().trim());
            loadMyReviews();
        } else if (result == 1) {
            reviewController.deleteReview(reviewId, userId, authController.isAdmin());
            loadMyReviews();
        }
    }

    private void handleDeactivate() {
        int confirm = JOptionPane.showConfirmDialog(
                this, "정말 탈퇴하시겠습니까?", "회원 탈퇴", JOptionPane.YES_NO_OPTION
        );
        if (confirm == JOptionPane.YES_OPTION) {
            authController.deactivate();
            mainFrame.dispose();
            new LoginFrame();
        }
    }

    private JButton createGhostButton(String text) {
        JButton btn = new JButton(text);
        btn.setBackground(INPUT_BG);
        btn.setForeground(HINT_COLOR);
        btn.setFont(new Font("Dialog", Font.PLAIN, 13));
        btn.setBorder(BorderFactory.createLineBorder(BORDER_COLOR, 1));
        btn.setFocusPainted(false);
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        return btn;
    }
}