package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dto.MemberDto;
import utill.DBConn;

public class MemberDao {
	
	private MemberDto mDto;
	private Connection conn;
    private PreparedStatement pstmt;
    private ResultSet rs;
    
	public MemberDto getLogin(String string) {
		try {
			conn = DBConn.getConnection();
			String sql = "select * from member where num = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, 2);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				mDto = new MemberDto();
				mDto.setNum(rs.getInt("num"));
				mDto.setId(rs.getString("id"));
				mDto.setPw(rs.getString("pw"));
				mDto.setName(rs.getString("name"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConn.close(rs, pstmt, conn);
		}
		
		
		return mDto;
	}
    
	
}
