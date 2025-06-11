package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.ReservationDto;
import utill.DBConn;

public class ReservationDao {
	
	private Connection conn;
    private PreparedStatement pstmt;
    private ResultSet rs;

	public Boolean insertReservation(ReservationDto dto) {
		String sql = "INSERT INTO reservation "
                + "(num, mnum, train_id, resv_date, start_station, last_station, start_time, last_time, price) "
                + "VALUES (reservation_seq.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?)";

	     try (Connection conn = DBConn.getConnection(); 
	          PreparedStatement pstmt = conn.prepareStatement(sql)) {
	
	         pstmt.setInt(1, dto.getMnum());
	         pstmt.setString(2, dto.getTrainId());
	         pstmt.setString(3, dto.getResvDate());
	         pstmt.setString(4, dto.getStartStation());
	         pstmt.setString(5, dto.getLastStation());
	         pstmt.setString(6, dto.getStartTime());
	         pstmt.setString(7, dto.getLastTime());
	         pstmt.setInt(8, dto.getPrice());
	
	         int result = pstmt.executeUpdate();
	         return result > 0;
	
	     } catch (SQLException e) {
	         e.printStackTrace();
	         return false;
	     } finally {
	    	 DBConn.close(rs, pstmt, rs);
		}
	}

	public List<ReservationDto> getMemberReservation(int mnum) {
		List<ReservationDto> list = new ArrayList<>();
        String sql = "SELECT * FROM reservation WHERE mnum = ? ORDER BY created_at DESC";

        try (Connection conn = DBConn.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, mnum);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                ReservationDto dto = new ReservationDto();
                dto.setNum(rs.getInt("num"));
                dto.setMnum(rs.getInt("mnum"));
                dto.setTrainId(rs.getString("train_id"));
                dto.setResvDate(rs.getString("resv_date"));
                dto.setStartStation(rs.getString("start_station"));
                dto.setLastStation(rs.getString("last_station"));
                dto.setStartTime(rs.getString("start_time"));
                dto.setLastTime(rs.getString("last_time"));
                dto.setPrice(rs.getInt("price"));
                dto.setCreatedAt(rs.getDate("created_at"));
                list.add(dto);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
			DBConn.close(rs, pstmt, conn);
		}
        return list;
	}
}
