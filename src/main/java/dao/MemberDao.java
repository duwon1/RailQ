package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.MemberDto;
import utill.DBConn;

public class MemberDao {
	
	private Connection conn;
    private PreparedStatement pstmt;
    private ResultSet rs;
    
	public MemberDto getLogin(String string) {
		MemberDto mDto = new MemberDto();
		
		try {
			conn = DBConn.getConnection();
			String sql = "SELECT * FROM member WHERE num = ?";
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

	public List<MemberDto> getMember() {
		List<MemberDto> mList = new ArrayList<MemberDto>();
		try {
			conn = DBConn.getConnection();
			String sql = "SELECT * FROM member";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				MemberDto mDto = new MemberDto();
				mDto.setNum(rs.getInt("num"));
				mDto.setId(rs.getString("id"));
				mDto.setEmail(rs.getString("email"));
				mDto.setName(rs.getString("name"));
				mDto.setPhone(rs.getString("phone"));
				mDto.setGender(rs.getString("zipcode"));
				mDto.setAddress(rs.getString("address"));
				mDto.setAddress1(rs.getString("address1"));
				mDto.setCreate_time(rs.getString("create_time"));
				mDto.setLast_login(rs.getString("last_login"));
				mDto.setMcheck(rs.getInt("mcheck"));
				mList.add(mDto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConn.close(rs, pstmt, conn);
		}
		
		return mList;
	}

	public void deleteBoard(int num) {
		try {
	        conn = DBConn.getConnection();
	        String sql = "DELETE FROM board WHERE num = ?";
	        pstmt = conn.prepareStatement(sql);
	        pstmt.setInt(1, num);
	        pstmt.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        DBConn.close(rs, pstmt, conn);
	    }
	}
    
	
}
