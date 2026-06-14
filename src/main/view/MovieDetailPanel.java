package main.view;

import main.controller.AuthController;
import main.controller.MovieController;
import main.controller.ReviewController;
import main.domain.Movie;
import main.domain.Review;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class MovieDetailPanel extends JDialog {

    private static final Color BG_COLOR = new Color(245, 245, 245);
    private static final Color PANEL_COLOR = new Color(255, 255, 255);
    private static final Color ACCENT_COLOR = new Color(30, 30, 30);
    private static final Color TEXT_COLOR = new Color(30, 30, 30);
    private static final Color HINT_COLOR = new Color(120, 120, 120);
    private static final Color BORDER_COLOR = new Color(220, 220, 220);
    private static final Color TABLE_SEL = new Color(220, 220, 220);

    private final Movie movie;
    private final AuthController authController;
    private final MovieController movieController;
    private final ReviewController reviewController = new ReviewController();
    private final MainFrame mainFrame;

    private DefaultTableModel reviewTableModel;
    private JLabel ratingLabel;

    public MovieDetailPanel(Movie movie, AuthController authController,
                            MovieController movieController, MainFrame mainFrame) {
        this.movie = movie;
        this.authController = authController;
        this.movieController = movieController;
        this.mainFrame = mainFrame;
        initUI();
    }

    private void initUI() {
        setTitle(movie.getTitle());
        setSize(700, 600);
        setLocationRelativeTo(mainFrame);
        setModal(true);
        getContentPane().setBackground(BG_COLOR);
        setLayout(new BorderLayout());

        add(createInfoPanel(), BorderLayout.NORTH);
        add(createReviewPanel(), BorderLayout.CENTER);
        add(createBottomPanel(), BorderLayout.SOUTH);

        loadReviews();
        setVisible(true);
    }

    private JPanel createInfoPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(PANEL_COLOR);
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(0, 0, 1, 0, BORDER_COLOR),
                BorderFactory.createEmptyBorder(20, 24, 20, 24)
        ));

        JLabel titleLabel = new JLabel(movie.getTitle());
        titleLabel.setFont(new Font("Dialog", Font.BOLD, 20));
        titleLabel.setForeground(ACCENT_COLOR);
        titleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel metaLabel = new JLabel(
                movie.getDirector() + "  ·  " + movie.getGenre() + "  ·  " + movie.getReleaseYear()
        );
        metaLabel.setFont(new Font("Dialog", Font.PLAIN, 13));
        metaLabel.setForeground(HINT_COLOR);
        metaLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        ratingLabel = new JLabel(String.format("★ %.1f / 5.0", movie.getAverageRating()));
        ratingLabel.setFont(new Font("Dialog", Font.BOLD, 15));
        ratingLabel.setForeground(ACCENT_COLOR);
        ratingLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JTextArea overviewArea = new JTextArea(
                movie.getOverview() != null ? movie.getOverview() : "줄거리 정보 없음"
        );
        overviewArea.setFont(new Font("Dialog", Font.PLAIN, 13));
        overviewArea.setForeground(TEXT_COLOR);
        overviewArea.setBackground(PANEL_COLOR);
        overviewArea.setLineWrap(true);
        overviewArea.setWrapStyleWord(true);
        overviewArea.setEditable(false);
        overviewArea.setMaximumSize(new Dimension(650, 60));
        overviewArea.setAlignmentX(Component.LEFT_ALIGNMENT);

        panel.add(titleLabel);
        panel.add(Box.createVerticalStrut(6));
        panel.add(metaLabel);
        panel.add(Box.createVerticalStrut(8));
        panel.add(ratingLabel);
        panel.add(Box.createVerticalStrut(10));
        panel.add(overviewArea);

        return panel;
    }

    private JPanel createReviewPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(BG_COLOR);

        JPanel headerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 16, 10));
        headerPanel.setBackground(BG_COLOR);
        JLabel reviewTitle = new JLabel("리뷰");
        reviewTitle.setFont(new Font("Dialog", Font.BOLD, 15));
        reviewTitle.setForeground(TEXT_COLOR);
        headerPanel.add(reviewTitle);

        String[] columns = {"ID", "닉네임", "별점", "내용", "작성일"};
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

        // ID 컬럼 숨기기
        reviewTable.getColumnModel().getColumn(0).setMinWidth(0);
        reviewTable.getColumnModel().getColumn(0).setMaxWidth(0);
        reviewTable.getColumnModel().getColumn(1).setPreferredWidth(100);
        reviewTable.getColumnModel().getColumn(2).setPreferredWidth(60);
        reviewTable.getColumnModel().getColumn(3).setPreferredWidth(300);
        reviewTable.getColumnModel().getColumn(4).setPreferredWidth(120);

        JScrollPane scrollPane = new JScrollPane(reviewTable);
        scrollPane.getViewport().setBackground(new Color(255, 255, 255));
        scrollPane.setBorder(BorderFactory.createEmptyBorder());

        panel.add(headerPanel, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);

        return panel;
    }

    private JPanel createBottomPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 12, 10));
        panel.setBackground(PANEL_COLOR);
        panel.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, BORDER_COLOR));

        JButton writeReviewBtn = new JButton("리뷰 작성");
        writeReviewBtn.setBackground(ACCENT_COLOR);
        writeReviewBtn.setForeground(Color.WHITE);
        writeReviewBtn.setOpaque(true);
        writeReviewBtn.setFont(new Font("Dialog", Font.BOLD, 13));
        writeReviewBtn.setBorderPainted(false);
        writeReviewBtn.setFocusPainted(false);
        writeReviewBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        writeReviewBtn.addActionListener(e -> {
            new ReviewDialog(this, movie, authController, reviewController);
            loadReviews();
            Movie updated = movieController.getMovieDetail(movie.getId());
            if (updated != null) {
                movie.setAverageRating(updated.getAverageRating());
                ratingLabel.setText(String.format("★ %.1f / 5.0", movie.getAverageRating()));
            }
        });

        JButton closeBtn = new JButton("닫기");
        closeBtn.setBackground(new Color(245, 245, 245));
        closeBtn.setForeground(HINT_COLOR);
        closeBtn.setOpaque(true);
        closeBtn.setFont(new Font("Dialog", Font.PLAIN, 13));
        closeBtn.setBorder(BorderFactory.createLineBorder(BORDER_COLOR, 1));
        closeBtn.setFocusPainted(false);
        closeBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        closeBtn.addActionListener(e -> {
            dispose();
            mainFrame.showMovieList();
        });

        panel.add(writeReviewBtn);
        panel.add(closeBtn);

        return panel;
    }

    private void loadReviews() {
        reviewTableModel.setRowCount(0);
        List<Review> reviews = reviewController.getReviewsByMovie(movie.getId());
        for (Review r : reviews) {
            reviewTableModel.addRow(new Object[]{
                    r.getId(),
                    r.getNickname() != null ? r.getNickname() : "알 수 없음",
                    r.getRating() + " ★",
                    r.getContent(),
                    r.getCreatedAt()
            });
        }
    }
}