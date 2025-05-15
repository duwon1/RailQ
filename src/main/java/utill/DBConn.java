package utill;

import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class DBConn {
    
    private static DataSource ds;

    static {
        try {
            Context initCtx = new InitialContext();
            Context envCtx = (Context)initCtx.lookup("java:comp/env");
            ds = (DataSource)envCtx.lookup("jdbc/oracleDB"); // web.xml & context.xml에서 설정한 이름
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 커넥션 얻기
    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }

    public static void close(AutoCloseable... resources) { // rs, pstmt, conn 순서중요!
        for (AutoCloseable res : resources) {
            try {
                if (res != null) res.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
