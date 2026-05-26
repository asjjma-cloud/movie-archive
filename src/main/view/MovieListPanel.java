package main.view;

import main.controller.AuthController;
import main.controller.MovieController;
import main.domain.Movie;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.util.List;

public class MovieListPanel extends JPanel {

    private static final Color BG_COLOR = new Color(13, 13, 13);
    private static final Color PANEL_COLOR = new Color(26, 26, 26);
    private static final Color GOLD_COLOR = new Color(255, 215, 0);
    private static final Color TEXT_COLOR = new Color(220, 220, 220);
    private static final Color HINT_COLOR = new Color(120, 120, 120);
    private static final Color INPUT_BG = new Color(40, 40, 40);
    private static final Color BORDER_COLOR = new Color(60, 60, 60);
    private static final Color TABLE_SEL = new Color(50, 50, 20);

    private final AuthController authController;
    private final MovieController movieController;
    private final MainFrame mainFrame;

    private JTable movieTable;
    private DefaultTableModel tableModel;
    private JTextField searchField;
    private JComboBox<String> genreCombo;

    public MovieListPanel(AuthController authController,
                          MovieController movieController,
                          MainFrame mainFrame) {
        this.authController = authController;
        this.movieController = movieController;
        this.mainFrame = mainFrame;
        initUI();
        loadMovies(movieController.getAllMovies());
    }

    private void initUI() {
        setLayout(new BorderLayout());
        setBackground(BG_COLOR);

        // 상단 검색/필터 영역
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 12));
        topPanel.setBackground(PANEL_COLOR);
        topPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, BORDER_COLOR));

        // 검색창
        searchField = new JTextField(20);
        searchField.setBackground(INPUT_BG);
        searchField.setForeground(TEXT_COLOR);
        searchField.setCaretColor(GOLD_COLOR);
        searchField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(BORDER_COLOR, 1),
                BorderFactory.createEmptyBorder(4, 10, 4, 10)
        ));
        searchField.setFont(new Font("Dialog", Font.PLAIN, 13));

        // 검색 타입
        JComboBox<String> searchTypeCombo = new JComboBox<>(new String[]{"제목", "감독"});
        styleCombo(searchTypeCombo);

        // 검색 버튼
        JButton searchBtn = createGoldButton("검색");
        searchBtn.addActionListener(e -> {
            String keyword = searchField.getText().trim();
            String type = (String) searchTypeCombo.getSelectedItem();
            if (keyword.isEmpty()) {
                loadMovies(movieController.getAllMovies());
                return;
            }
            if ("제목".equals(type)) {
                loadMovies(movieController.searchByTitle(keyword));
            } else {
                loadMovies(movieController.searchByDirector(keyword));
            }
        });

        // 장르 필터
        genreCombo = new JComboBox<>(new String[]{
                "전체", "액션", "로맨스", "코미디", "공포", "SF", "드라마", "애니메이션", "다큐멘터리"
        });
        styleCombo(genreCombo);
        genreCombo.addActionListener(e -> {
            String genre = (String) genreCombo.getSelectedItem();
            if ("전체".equals(genre)) {
                loadMovies(movieController.getAllMovies());
            } else {
                loadMovies(movieController.filterByGenre(genre));
            }
        });

        // 전체보기 버튼
        JButton allBtn = createGhostButton("전체보기");
        allBtn.addActionListener(e -> {
            searchField.setText("");
            genreCombo.setSelectedIndex(0);
            loadMovies(movieController.getAllMovies());
        });

        topPanel.add(new JLabel("") {{
            setText("🔍"); setFont(new Font("Dialog", Font.PLAIN, 14));
        }});
        topPanel.add(searchField);
        topPanel.add(searchTypeCombo);
        topPanel.add(searchBtn);
        topPanel.add(Box.createHorizontalStrut(10));
        topPanel.add(makeHintLabel("장르:"));
        topPanel.add(genreCombo);
        topPanel.add(allBtn);

        // 테이블
        String[] columns = {"ID", "제목", "감독", "장르", "개봉연도", "평균 별점"};
        tableModel = new DefaultTableModel(columns, 0) {
            public boolean isCellEditable(int row, int col) { return false; }
        };

        movieTable = new JTable(tableModel);
        styleTable(movieTable);

        // 더블클릭 → 상세보기
        movieTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int row = movieTable.getSelectedRow();
                    if (row >= 0) {
                        int movieId = (int) tableModel.getValueAt(row, 0);
                        Movie movie = movieController.getMovieDetail(movieId);
                        if (movie != null) {
                            new MovieDetailPanel(movie, authController, movieController, mainFrame);
                        }
                    }
                }
            }
        });

        JScrollPane scrollPane = new JScrollPane(movieTable);
        scrollPane.setBackground(BG_COLOR);
        scrollPane.getViewport().setBackground(BG_COLOR);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());

        // 하단 안내
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 16, 8));
        bottomPanel.setBackground(PANEL_COLOR);
        bottomPanel.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, BORDER_COLOR));
        bottomPanel.add(makeHintLabel("행을 더블클릭하면 상세 정보를 볼 수 있습니다"));

        add(topPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    // 영화 목록 테이블에 로드
    public void loadMovies(List<Movie> movies) {
        tableModel.setRowCount(0);
        for (Movie m : movies) {
            tableModel.addRow(new Object[]{
                    m.getId(),
                    m.getTitle(),
                    m.getDirector(),
                    m.getGenre(),
                    m.getReleaseYear(),
                    String.format("%.1f ★", m.getAverageRating())
            });
        }
    }

    private void styleTable(JTable table) {
        table.setBackground(new Color(20, 20, 20));
        table.setForeground(TEXT_COLOR);
        table.setFont(new Font("Dialog", Font.PLAIN, 13));
        table.setRowHeight(36);
        table.setShowGrid(false);
        table.setIntercellSpacing(new Dimension(0, 0));
        table.setSelectionBackground(TABLE_SEL);
        table.setSelectionForeground(GOLD_COLOR);
        table.getTableHeader().setBackground(PANEL_COLOR);
        table.getTableHeader().setForeground(HINT_COLOR);
        table.getTableHeader().setFont(new Font("Dialog", Font.PLAIN, 12));
        table.getTableHeader().setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, BORDER_COLOR));

        // ID 컬럼 숨기기
        table.getColumnModel().getColumn(0).setMinWidth(0);
        table.getColumnModel().getColumn(0).setMaxWidth(0);
        table.getColumnModel().getColumn(0).setWidth(0);

        // 컬럼 너비
        table.getColumnModel().getColumn(1).setPreferredWidth(300);
        table.getColumnModel().getColumn(2).setPreferredWidth(150);
        table.getColumnModel().getColumn(3).setPreferredWidth(100);
        table.getColumnModel().getColumn(4).setPreferredWidth(80);
        table.getColumnModel().getColumn(5).setPreferredWidth(80);

        // 별점 컬럼 가운데 정렬
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        centerRenderer.setBackground(new Color(20, 20, 20));
        centerRenderer.setForeground(GOLD_COLOR);
        table.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);
    }

    private void styleCombo(JComboBox<String> combo) {
        combo.setBackground(INPUT_BG);
        combo.setForeground(TEXT_COLOR);
        combo.setFont(new Font("Dialog", Font.PLAIN, 13));
        combo.setBorder(BorderFactory.createLineBorder(BORDER_COLOR, 1));
    }

    private JButton createGoldButton(String text) {
        JButton btn = new JButton(text);
        btn.setBackground(GOLD_COLOR);
        btn.setForeground(Color.BLACK);
        btn.setFont(new Font("Dialog", Font.BOLD, 13));
        btn.setBorderPainted(false);
        btn.setFocusPainted(false);
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
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        return btn;
    }

    private JLabel makeHintLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Dialog", Font.PLAIN, 12));
        label.setForeground(HINT_COLOR);
        return label;
    }
}