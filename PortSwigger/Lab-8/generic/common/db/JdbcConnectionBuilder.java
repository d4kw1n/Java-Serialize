package common.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcConnectionBuilder {
    // Đây là mẫu đơn giản để bạn hình dung
    public static JdbcConnectionBuilder from(String driver, String type, String host, int port, String db, String user, String pass) {
        return new JdbcConnectionBuilder();
    }

    public JdbcConnectionBuilder withAutoCommit() {
        return this;
    }

    public Connection connect(int timeout) throws SQLException {
        // Thực tế sẽ dùng DriverManager.getConnection(...) ở đây
        return null;
    }
}