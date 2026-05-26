package main.view;

import main.controller.AuthController;
import main.controller.ReviewController;
import main.domain.Movie;
import javax.swing.*;
import java.awt.*;

public class ReviewDialog extends JDialog {

    private static final Color BG_COLOR = new Color(26, 26, 26);
    private static final Color GOLD_COLOR = new Color(255, 215, 0);
    private static final Color TEXT_COLOR = new Color(220, 220, 220);
    private static final Color HINT_COLOR = new Color(120, 120, 120);
    private static final Color INPUT_BG = new Color(40, 40, 40);
    private static final Color BORDER_COLOR = new Color(60, 60, 60);

    public ReviewDialog(JDialog parent, Movie movie,
                        AuthController authController,
                        ReviewController reviewController) {
        super(parent, "리뷰 작성 — " + movie.getTitle(), true);
        setSize(460, 320);
        setLocationRelativeTo(parent);
        setResizable(false);
        getContentPane().setBackground(BG_COLOR);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(BG_COLOR);
        panel.setBorder(BorderFactory.createEmptyBorder(24, 32, 24, 32));

        // 제목
        JLabel titleLabel = new JLabel("리뷰 작성");
        titleLabel.setFont(new Font("Dialog", Font.BOLD, 16));
        titleLabel.setForeground(GOLD_COLOR);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // 별점 슬라이더
        JLabel ratingLabel = new JLabel("별점: 3 ★");
        ratingLabel.setFont(new Font("Dialog", Font.PLAIN, 13));
        ratingLabel.setForeground(TEXT_COLOR);
        ratingLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JSlider ratingSlider = new JSlider(1, 5, 3);
        ratingSlider.setBackground(BG_COLOR);
        ratingSlider.setForeground(GOLD_COLOR);
        ratingSlider.setMajorTickSpacing(1);
        ratingSlider.setPaintTicks(true);
        ratingSlider.setPaintLabels(true);
        ratingSlider.setSnapToTicks(true);
        ratingSlider.setMaximumSize(new Dimension(380, 50));
        ratingSlider.setAlignmentX(Component.CENTER_ALIGNMENT);
        ratingSlider.addChangeListener(e ->
                ratingLabel.setText("별점: " + ratingSlider.getValue() + " ★")
        );

        // 리뷰 내용
        JLabel contentLabel = new JLabel("내용");
        contentLabel.setFont(new Font("Dialog", Font.PLAIN, 12));
        contentLabel.setForeground(HINT_COLOR);
        contentLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JTextArea contentArea = new JTextArea(4, 30);
        contentArea.setBackground(INPUT_BG);
        contentArea.setForeground(TEXT_COLOR);
        contentArea.setCaretColor(GOLD_COLOR);
        contentArea.setFont(new Font("Dialog", Font.PLAIN, 13));
        contentArea.setLineWrap(true);
        contentArea.setWrapStyleWord(true);
        contentArea.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(BORDER_COLOR, 1),
                BorderFactory.createEmptyBorder(6, 10, 6, 10)
        ));

        JScrollPane contentScroll = new JScrollPane(contentArea);
        contentScroll.setMaximumSize(new Dimension(380, 80));
        contentScroll.setAlignmentX(Component.CENTER_ALIGNMENT);
        contentScroll.setBorder(BorderFactory.createEmptyBorder());

        // 저장 버튼
        JButton saveBtn = new JButton("저장");
        saveBtn.setMaximumSize(new Dimension(380, 38));
        saveBtn.setBackground(GOLD_COLOR);
        saveBtn.setForeground(Color.BLACK);
        saveBtn.setFont(new Font("Dialog", Font.BOLD, 13));
        saveBtn.setBorderPainted(false);
        saveBtn.setFocusPainted(false);
        saveBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        saveBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        saveBtn.addActionListener(e -> {
            int rating = ratingSlider.getValue();
            String content = contentArea.getText().trim();
            if (content.isEmpty()) {
                JOptionPane.showMessageDialog(this, "리뷰 내용을 입력해주세요.", "오류", JOptionPane.ERROR_MESSAGE);
                return;
            }
            int userId = authController.getCurrentUser().getId();
            boolean success = reviewController.addReview(userId, movie.getId(), rating, content);
            if (success) {
                JOptionPane.showMessageDialog(this, "리뷰가 등록되었습니다!", "성공", JOptionPane.INFORMATION_MESSAGE);
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "리뷰 등록에 실패했습니다.\n이미 작성한 리뷰가 있을 수 있습니다.", "오류", JOptionPane.ERROR_MESSAGE);
            }
        });

        panel.add(titleLabel);
        panel.add(Box.createVerticalStrut(16));
        panel.add(ratingLabel);
        panel.add(Box.createVerticalStrut(4));
        panel.add(ratingSlider);
        panel.add(Box.createVerticalStrut(12));
        panel.add(contentLabel);
        panel.add(Box.createVerticalStrut(4));
        panel.add(contentScroll);
        panel.add(Box.createVerticalStrut(16));
        panel.add(saveBtn);

        add(panel);
        setVisible(true);
    }
}