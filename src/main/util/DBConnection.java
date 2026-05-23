package main.util;

import java.sql.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class DBConnection {

    private static final String DB_URL = "jdbc:sqlite:resources/db/movie_archive.db";
    private static Connection conn = null;

    public static Connection getConnection() throws SQLException {
        if (conn == null || conn.isClosed()) {
            conn = DriverManager.getConnection(DB_URL);
            initDB();
        }
        return conn;
    }

    private static void initDB() {
        String sql;
        try {
            sql = new String(Files.readAllBytes(
                    Paths.get("resources/db/schema.sql")
            ));
            try (Statement stmt = conn.createStatement()) {
                stmt.executeUpdate(sql);
            }
        } catch (IOException | SQLException e) {
            System.err.println("DB 초기화 실패: " + e.getMessage());
        }
    }

    public static void close() {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        } catch (SQLException e) {
            System.err.println("DB 연결 종료 실패: " + e.getMessage());
        }
    }
}