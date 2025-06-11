package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

	public List<BoardDto> selectBoardList() {
		List<BoardDto> list = new ArrayList<>();
        String sql = "SELECT * FROM board ORDER BY num DESC";

        try {
        	conn = DBConn.getConnection();
        	pstmt = conn.prepareStatement(sql);
        	rs = pstmt.executeQuery();

            while (rs.next()) {
                BoardDto dto = new BoardDto();
                dto.setNum(rs.getInt("num"));
                dto.setTitle(rs.getString("title"));
                dto.setContent(rs.getString("content"));
                dto.setCreateAt(rs.getDate("create_at"));
                dto.setViewCount(rs.getInt("view_count"));
                dto.setRealFile(rs.getString("real_file"));
                dto.setServerFile(rs.getString("server_file"));

                list.add(dto);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
        

}
