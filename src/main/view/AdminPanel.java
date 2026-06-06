package main.view;

import main.controller.AuthController;
import main.controller.MovieController;
import main.domain.Movie;
import main.domain.User;
import main.repository.UserDAO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class AdminPanel extends JPanel {

    private static final Color BG_COLOR = new Color(245, 245, 245);
    private static final Color PANEL_COLOR = new Color(255, 255, 255);
    private static final Color ACCENT_COLOR = new Color(30, 30, 30);
    private static final Color TEXT_COLOR = new Color(30, 30, 30);
    private static final Color HINT_COLOR = new Color(120, 120, 120);
    private static final Color INPUT_BG = new Color(250, 250, 250);
    private static final Color BORDER_COLOR = new Color(220, 220, 220);
    private static final Color TABLE_SEL = new Color(220, 220, 220);

    private final AuthController authController;
    private final MovieController movieController;
    private final MainFrame mainFrame;
    private final UserDAO userDAO = new UserDAO();

    private DefaultTableModel movieTableModel;
    private DefaultTableModel userTableModel;

    public AdminPanel(AuthController authController,
                      MovieController movieController,
                      MainFrame mainFrame) {
        this.authController = authController;
        this.movieController = movieController;
        this.mainFrame = mainFrame;
        initUI();
    }

    private void initUI() {
        setLayout(new BorderLayout());
        setBackground(BG_COLOR);

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.setBackground(PANEL_COLOR);
        tabbedPane.setForeground(TEXT_COLOR);
        tabbedPane.setFont(new Font("Dialog", Font.PLAIN, 13));

        tabbedPane.addTab("영화 관리", createMovieTab());
        tabbedPane.addTab("회원 관리", createUserTab());

        add(tabbedPane, BorderLayout.CENTER);

        loadMovies();
        loadUsers();
    }

    private JPanel createMovieTab() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(BG_COLOR);

        String[] columns = {"ID", "제목", "감독", "장르", "개봉연도"};
        movieTableModel = new DefaultTableModel(columns, 0) {
            public boolean isCellEditable(int r, int c) { return false; }
        };

        JTable movieTable = new JTable(movieTableModel);
        styleTable(movieTable);
        movieTable.getColumnModel().getColumn(0).setMinWidth(0);
        movieTable.getColumnModel().getColumn(0).setMaxWidth(0);

        JScrollPane scrollPane = new JScrollPane(movieTable);
        scrollPane.getViewport().setBackground(new Color(255, 255, 255));
        scrollPane.setBorder(BorderFactory.createEmptyBorder());

        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        btnPanel.setBackground(PANEL_COLOR);
        btnPanel.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, BORDER_COLOR));

        JButton addBtn = createAccentButton("영화 추가");
        JButton deleteBtn = createGhostButton("영화 삭제");

        addBtn.addActionListener(e -> openAddMovieDialog());
        deleteBtn.addActionListener(e -> {
            int row = movieTable.getSelectedRow();
            if (row < 0) {
                JOptionPane.showMessageDialog(this, "삭제할 영화를 선택해주세요.", "알림", JOptionPane.WARNING_MESSAGE);
                return;
            }
            int movieId = (int) movieTableModel.getValueAt(row, 0);
            int confirm = JOptionPane.showConfirmDialog(
                    this, "정말 삭제하시겠습니까?", "영화 삭제", JOptionPane.YES_NO_OPTION
            );
            if (confirm == JOptionPane.YES_OPTION) {
                movieController.deleteMovie(movieId);
                loadMovies();
            }
        });

        btnPanel.add(addBtn);
        btnPanel.add(deleteBtn);

        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(btnPanel, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel createUserTab() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(BG_COLOR);

        String[] columns = {"ID", "아이디", "이메일", "닉네임", "권한", "상태"};
        userTableModel = new DefaultTableModel(columns, 0) {
            public boolean isCellEditable(int r, int c) { return false; }
        };

        JTable userTable = new JTable(userTableModel);
        styleTable(userTable);
        userTable.getColumnModel().getColumn(0).setMinWidth(0);
        userTable.getColumnModel().getColumn(0).setMaxWidth(0);

        JScrollPane scrollPane = new JScrollPane(userTable);
        scrollPane.getViewport().setBackground(new Color(255, 255, 255));
        scrollPane.setBorder(BorderFactory.createEmptyBorder());

        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        btnPanel.setBackground(PANEL_COLOR);
        btnPanel.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, BORDER_COLOR));

        JButton deactivateBtn = createGhostButton("계정 정지");
        deactivateBtn.setForeground(new Color(180, 60, 60));
        deactivateBtn.setBorder(BorderFactory.createLineBorder(new Color(180, 60, 60), 1));

        deactivateBtn.addActionListener(e -> {
            int row = userTable.getSelectedRow();
            if (row < 0) {
                JOptionPane.showMessageDialog(this, "정지할 회원을 선택해주세요.", "알림", JOptionPane.WARNING_MESSAGE);
                return;
            }
            int userId = (int) userTableModel.getValueAt(row, 0);
            int confirm = JOptionPane.showConfirmDialog(
                    this, "해당 계정을 정지하시겠습니까?", "계정 정지", JOptionPane.YES_NO_OPTION
            );
            if (confirm == JOptionPane.YES_OPTION) {
                try {
                    userDAO.deactivate(userId);
                    loadUsers();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "실패: " + ex.getMessage(), "오류", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        btnPanel.add(deactivateBtn);

        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(btnPanel, BorderLayout.SOUTH);

        return panel;
    }

    private void openAddMovieDialog() {
        JTextField titleField = new JTextField();
        JTextField directorField = new JTextField();
        JTextField genreField = new JTextField();
        JTextField yearField = new JTextField();
        JTextArea overviewArea = new JTextArea(3, 20);
        overviewArea.setLineWrap(true);

        JPanel addPanel = new JPanel();
        addPanel.setLayout(new BoxLayout(addPanel, BoxLayout.Y_AXIS));
        addPanel.add(new JLabel("제목 *")); addPanel.add(titleField);
        addPanel.add(Box.createVerticalStrut(6));
        addPanel.add(new JLabel("감독")); addPanel.add(directorField);
        addPanel.add(Box.createVerticalStrut(6));
        addPanel.add(new JLabel("장르")); addPanel.add(genreField);
        addPanel.add(Box.createVerticalStrut(6));
        addPanel.add(new JLabel("개봉연도")); addPanel.add(yearField);
        addPanel.add(Box.createVerticalStrut(6));
        addPanel.add(new JLabel("줄거리")); addPanel.add(new JScrollPane(overviewArea));

        int result = JOptionPane.showConfirmDialog(
                this, addPanel, "영화 추가", JOptionPane.OK_CANCEL_OPTION
        );

        if (result == JOptionPane.OK_OPTION) {
            String title = titleField.getText().trim();
            if (title.isEmpty()) {
                JOptionPane.showMessageDialog(this, "제목을 입력해주세요.", "오류", JOptionPane.ERROR_MESSAGE);
                return;
            }
            int year = 0;
            try { year = Integer.parseInt(yearField.getText().trim()); } catch (Exception ignored) {}

            boolean success = movieController.addMovie(
                    title,
                    directorField.getText().trim(),
                    genreField.getText().trim(),
                    year,
                    overviewArea.getText().trim(),
                    ""
            );
            if (success) {
                JOptionPane.showMessageDialog(this, "영화가 추가되었습니다!", "성공", JOptionPane.INFORMATION_MESSAGE);
                loadMovies();
                mainFrame.showMovieList();
            }
        }
    }

    private void loadMovies() {
        movieTableModel.setRowCount(0);
        List<Movie> movies = movieController.getAllMovies();
        for (Movie m : movies) {
            movieTableModel.addRow(new Object[]{
                    m.getId(), m.getTitle(), m.getDirector(),
                    m.getGenre(), m.getReleaseYear()
            });
        }
    }

    private void loadUsers() {
        userTableModel.setRowCount(0);
        try {
            List<User> users = userDAO.findAll();
            for (User u : users) {
                userTableModel.addRow(new Object[]{
                        u.getId(), u.getUsername(), u.getEmail(),
                        u.getNickname(), u.getRole(),
                        u.isActive() ? "활성" : "정지"
                });
            }
        } catch (Exception e) {
            System.err.println("회원 목록 조회 실패: " + e.getMessage());
        }
    }

    private void styleTable(JTable table) {
        table.setBackground(new Color(255, 255, 255));
        table.setForeground(TEXT_COLOR);
        table.setFont(new Font("Dialog", Font.PLAIN, 13));
        table.setRowHeight(32);
        table.setShowGrid(false);
        table.setSelectionBackground(TABLE_SEL);
        table.setSelectionForeground(TEXT_COLOR);
        table.getTableHeader().setBackground(PANEL_COLOR);
        table.getTableHeader().setForeground(HINT_COLOR);
        table.getTableHeader().setFont(new Font("Dialog", Font.PLAIN, 12));
    }

    private JButton createAccentButton(String text) {
        JButton btn = new JButton(text);
        btn.setBackground(ACCENT_COLOR);
        btn.setForeground(Color.WHITE);
        btn.setFont(new Font("Dialog", Font.BOLD, 13));
        btn.setBorderPainted(false);
        btn.setFocusPainted(false);
        btn.setOpaque(true);
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        return btn;
    }

    private JButton createGhostButton(String text) {
        JButton btn = new JButton(text);
        btn.setBackground(INPUT_BG);
        btn.setForeground(HINT_COLOR);
        btn.setFont(new Font("Dialog", Font.PLAIN, 13));
        btn.setBorder(BorderFactory.createLineBorder(BORDER_COLOR, 1));
        btn.setFocusPainted(false);
        btn.setOpaque(true);
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        return btn;
    }
}