package main.view;

import main.controller.AuthController;
import main.controller.MovieController;
import main.domain.Movie;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.util.List;

public class MovieListPanel extends JPanel {

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

    private JTable movieTable;
    private DefaultTableModel tableModel;
    private TableRowSorter<DefaultTableModel> sorter;
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

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 12));
        topPanel.setBackground(PANEL_COLOR);
        topPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, BORDER_COLOR));

        searchField = new JTextField(20);
        searchField.setBackground(INPUT_BG);
        searchField.setForeground(TEXT_COLOR);
        searchField.setCaretColor(ACCENT_COLOR);
        searchField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(BORDER_COLOR, 1),
                BorderFactory.createEmptyBorder(4, 10, 4, 10)
        ));
        searchField.setFont(new Font("Dialog", Font.PLAIN, 13));

        JComboBox<String> searchTypeCombo = new JComboBox<>(new String[]{"제목", "감독"});
        styleCombo(searchTypeCombo);

        final JButton searchBtn = createAccentButton("검색");

        searchField.addActionListener(e -> searchBtn.doClick());

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

        JButton allBtn = createGhostButton("전체보기");
        allBtn.addActionListener(e -> {
            searchField.setText("");
            genreCombo.setSelectedIndex(0);
            loadMovies(movieController.getAllMovies());
        });

        topPanel.add(makeHintLabel("🔍"));
        topPanel.add(searchField);
        topPanel.add(searchTypeCombo);
        topPanel.add(searchBtn);
        topPanel.add(Box.createHorizontalStrut(10));
        topPanel.add(makeHintLabel("장르:"));
        topPanel.add(genreCombo);
        topPanel.add(allBtn);

        // 테이블 모델
        String[] columns = {"ID", "제목", "감독", "장르", "개봉연도", "평균 별점"};
        tableModel = new DefaultTableModel(columns, 0) {
            public boolean isCellEditable(int row, int col) { return false; }

            // 정렬 타입 지정
            @Override
            public Class<?> getColumnClass(int col) {
                switch (col) {
                    case 0: return Integer.class;  // ID
                    case 4: return Integer.class;  // 개봉연도
                    case 5: return Double.class;   // 평균 별점
                    default: return String.class;
                }
            }
        };

        movieTable = new JTable(tableModel);
        styleTable(movieTable);

        // 정렬 기능 추가
        sorter = new TableRowSorter<>(tableModel);
        movieTable.setRowSorter(sorter);

        movieTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int row = movieTable.getSelectedRow();
                    if (row >= 0) {
                        // 정렬된 행 인덱스를 모델 인덱스로 변환
                        int modelRow = movieTable.convertRowIndexToModel(row);
                        int movieId = (int) tableModel.getValueAt(modelRow, 0);
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
        scrollPane.getViewport().setBackground(new Color(255, 255, 255));
        scrollPane.setBorder(BorderFactory.createEmptyBorder());

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 16, 8));
        bottomPanel.setBackground(PANEL_COLOR);
        bottomPanel.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, BORDER_COLOR));
        bottomPanel.add(makeHintLabel("행을 더블클릭하면 상세 정보를 볼 수 있습니다  |  컬럼 헤더를 클릭하면 정렬됩니다"));

        add(topPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    public void loadMovies(List<Movie> movies) {
        tableModel.setRowCount(0);
        for (Movie m : movies) {
            tableModel.addRow(new Object[]{
                    m.getId(),
                    m.getTitle(),
                    m.getDirector(),
                    m.getGenre(),
                    m.getReleaseYear(),
                    m.getAverageRating()
            });
        }
    }

    private void styleTable(JTable table) {
        table.setBackground(new Color(255, 255, 255));
        table.setForeground(TEXT_COLOR);
        table.setFont(new Font("Dialog", Font.PLAIN, 13));
        table.setRowHeight(36);
        table.setShowGrid(false);
        table.setIntercellSpacing(new Dimension(0, 0));
        table.setSelectionBackground(TABLE_SEL);
        table.setSelectionForeground(TEXT_COLOR);
        table.getTableHeader().setBackground(PANEL_COLOR);
        table.getTableHeader().setForeground(HINT_COLOR);
        table.getTableHeader().setFont(new Font("Dialog", Font.PLAIN, 12));
        table.getTableHeader().setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, BORDER_COLOR));
        table.getTableHeader().setReorderingAllowed(false);

        // ID 컬럼 숨기기
        table.getColumnModel().getColumn(0).setMinWidth(0);
        table.getColumnModel().getColumn(0).setMaxWidth(0);
        table.getColumnModel().getColumn(0).setWidth(0);
        table.getColumnModel().getColumn(1).setPreferredWidth(300);
        table.getColumnModel().getColumn(2).setPreferredWidth(150);
        table.getColumnModel().getColumn(3).setPreferredWidth(100);
        table.getColumnModel().getColumn(4).setPreferredWidth(80);
        table.getColumnModel().getColumn(5).setPreferredWidth(80);

        // 별점 컬럼 렌더러 (소수점 1자리 + ★)
        DefaultTableCellRenderer starRenderer = new DefaultTableCellRenderer() {
            @Override
            public void setValue(Object value) {
                if (value instanceof Double) {
                    setText(String.format("%.1f ★", (Double) value));
                } else {
                    setText(value == null ? "" : value.toString());
                }
            }
        };
        starRenderer.setHorizontalAlignment(JLabel.CENTER);
        starRenderer.setBackground(new Color(255, 255, 255));
        starRenderer.setForeground(TEXT_COLOR);
        table.getColumnModel().getColumn(5).setCellRenderer(starRenderer);
    }

    private void styleCombo(JComboBox<String> combo) {
        combo.setBackground(INPUT_BG);
        combo.setForeground(TEXT_COLOR);
        combo.setFont(new Font("Dialog", Font.PLAIN, 13));
        combo.setBorder(BorderFactory.createLineBorder(BORDER_COLOR, 1));
    }

    private JButton createAccentButton(String text) {
        JButton btn = new JButton(text);
        btn.setBackground(ACCENT_COLOR);
        btn.setForeground(Color.WHITE);
        btn.setOpaque(true);
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
        btn.setOpaque(true);
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