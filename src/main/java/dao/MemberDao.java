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
				mDto.setGender(rs.getString("gender"));
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
				mDto.setGender(rs.getString("gender"));
				mDto.setCreate_time(rs.getString("create_time"));
				mDto.setLast_login(rs.getString("last_login"));
				mDto.setMcheck(rs.getString("mcheck"));
				mList.add(mDto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConn.close(rs, pstmt, conn);
		}
		
		return mList;
	}

	public void joinKakao(MemberDto mDto) {
		String sql = "INSERT INTO member (num, id, name, gender, email, create_time, last_login, mcheck)"
				+ "values (member_seq.NEXTVAL,?,?,?,?,sysdate,sysdate,?)";
		try {
			conn = DBConn.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mDto.getId());
			pstmt.setString(2, mDto.getName());
			pstmt.setString(3, mDto.getGender());
			pstmt.setString(4, mDto.getEmail());
			pstmt.setString(5, "kakao");
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConn.close(rs, pstmt, conn);
		}
		
		
	}

	public MemberDto getKakaoMember(String id) {
		String sql = "SELECT * FROM member WHERE id=?";
		MemberDto mDto = null;
		try {
			conn = DBConn.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				mDto = new MemberDto(); // ← 조회된 경우에만 객체 생성
				mDto.setNum(rs.getInt("num"));
				mDto.setId(rs.getString("id"));
				mDto.setPw(rs.getString("pw"));
				mDto.setEmail("email");
				mDto.setGender(rs.getString("gender"));
				mDto.setName(rs.getString("name"));
				mDto.setCreate_time(rs.getString("create_time"));
				mDto.setLast_login(rs.getString("last_login"));
				mDto.setMcheck(rs.getString("mcheck"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConn.close(rs, pstmt, conn);
		}
		return mDto;
	}

	public void memberDelete(int memberNum) {
		String sql = "DELETE FROM member WHERE num=?";
	    try {
	        conn = DBConn.getConnection();
	        pstmt = conn.prepareStatement(sql);
	        pstmt.setInt(1, memberNum);
	        pstmt.executeUpdate();
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        DBConn.close(rs, pstmt, conn);
	    }
		
	}
    
	
}
