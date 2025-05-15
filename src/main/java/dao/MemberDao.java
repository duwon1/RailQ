package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dto.MemberVO;
import utill.DBConn;

public class MemberDao {
	
	private MemberVO vo;
	private Connection conn;
    private PreparedStatement pstmt;
    private ResultSet rs;
    
	public MemberVO getLogin(String string) {
		try {
			conn = DBConn.getConnection();
			String sql = "select * from member where num = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, 2);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				vo = new MemberVO();
	            vo.setNum(rs.getInt("num"));
	            vo.setId(rs.getString("id"));
	            vo.setPw(rs.getString("pw"));
	            vo.setName(rs.getString("name"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConn.close(rs, pstmt, conn);
		}
		
		
		return vo;
	}
    
	
}
