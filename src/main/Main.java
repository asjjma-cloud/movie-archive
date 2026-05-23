package main;

import main.util.DBConnection;
import java.sql.Connection;

public class Main {
    public static void main(String[] args) {
        try {
            Connection conn = DBConnection.getConnection();
            System.out.println("DB 연결 성공!");
            DBConnection.close();
        } catch (Exception e) {
            System.err.println("DB 연결 실패: " + e.getMessage());
        }
    }
}