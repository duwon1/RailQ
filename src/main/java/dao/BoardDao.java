package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dto.BoardDto;
import utill.DBConn;

public class BoardDao {
	
	private Connection conn;
    private PreparedStatement pstmt;
    private ResultSet rs;
    
	public void insert(BoardDto dto) {
		String sql = """
	            INSERT INTO board (num, title, content, create_at, view_count, real_file, server_file)
	            VALUES (board_seq.NEXTVAL, ?, ?, SYSDATE, 0, ?, ?)
	        """;
		
		try {
			conn = DBConn.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getTitle());
            pstmt.setString(2, dto.getContent());
            pstmt.setString(3, dto.getRealFile());
            pstmt.setString(4, dto.getServerFile());
            pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConn.close(rs, pstmt, conn);
		}
    }
        

}
