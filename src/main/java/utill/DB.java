package utill;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

// 싱글턴 방식(객체하나를 미리 만들어놓고 재사용하는 방식)
public class DB {
	
	private static DB instance = new DB();
	
	// DB 연결 정보
	private Connection conn;
	private final String url = "jdbc:oracle:thin:@34.10.182.38:1521:XE";
	private final String id = "scott";
	private final String pw = "tiger";
	
	// 직접 객체를 만들지 못하게 생성자 접근제어
	private DB() {
		try {	        
	        Class.forName("oracle.jdbc.driver.OracleDriver");
	        conn = DriverManager.getConnection(url, id, pw);
	        System.out.println("DB연결성공");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static void close(Connection conn, PreparedStatement pstmt, ResultSet rs ) {
		try {
//			if(conn!=null) conn.close();
			if(pstmt!=null) pstmt.close();
			if(rs!=null) rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// 외부에서 호출할떄 get으로 객체를 리턴함
	public static DB getInstace() {
		return instance;
	}
	
	public Connection getConnection() {
        return conn;
    }

}
