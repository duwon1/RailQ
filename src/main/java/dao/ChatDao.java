package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dto.ChatDto;
import utill.DBConn;

public class ChatDao {
	
	private Connection conn;
    private PreparedStatement pstmt;
    private ResultSet rs;
    
	public void insertMessage(ChatDto dto) {
		try {
			conn = DBConn.getConnection();
			String sql = "INSERT INTO chat (num, mnum, sender_type, msg, create_time) " +
                    "VALUES (chat_seq.NEXTVAL, ?, ?, ?, SYSDATE)";

	       pstmt = conn.prepareStatement(sql);
	       pstmt.setInt(1, dto.getMnum());
	       pstmt.setString(2, dto.getSender_type());
	       pstmt.setString(3, dto.getMsg());
	       pstmt.executeUpdate(); // 실제 DB에 반영하는 코드
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConn.close(rs, pstmt, conn);
		}

	}

    
    

}
