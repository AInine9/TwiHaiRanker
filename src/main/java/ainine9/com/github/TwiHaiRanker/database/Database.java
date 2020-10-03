package ainine9.com.github.TwiHaiRanker.database;

import java.sql.*;

public class Database {
    public static void connect(String[] args) {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rset = null;

        String url = "postgres://oonduonaxbyruw:23bef676139a6643fb3bf472298fddd97f4b086b9868931ab21592869d34ec6a@ec2-54-160-161-214.compute-1.amazonaws.com:5432/d2v8qohv0f6egp";
        String user = "oonduonaxbyruw";
        String password = "23bef676139a6643fb3bf472298fddd97f4b086b9868931ab21592869d34ec6a";

        try {
            //PostgreSQLへ接続
            conn = DriverManager.getConnection(url, user, password);

            //自動コミットOFF
            conn.setAutoCommit(false);

            //SELECT文の実行
            stmt = conn.createStatement();
            String sql = "SELECT 1";
            rset = stmt.executeQuery(sql);

            //SELECT結果の受け取り
            while (rset.next()) {
                String col = rset.getString(1);
                System.out.println(col);
            }

            //INSERT文の実行
            sql = "INSERT INTO jdbc_test VALUES (1, 'AAA')";
            stmt.executeUpdate(sql);
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rset != null) rset.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
    }
}
