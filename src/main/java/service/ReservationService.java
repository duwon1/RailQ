package service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.json.JSONArray;
import org.json.JSONObject;

import api.ApiCityInformation;
import api.ApiRailInformation;
import api.ApiRegionInformation;
import dao.ReservationDao;
import dto.CityDto;
import dto.RailDto;
import dto.RegionDto;
import dto.ReservationDto;

public class ReservationService {
	
	private ReservationDao rDao = new ReservationDao();

	public List<CityDto> getCityInformation() {
		ApiCityInformation api = new ApiCityInformation();
		List<CityDto> cList = new ArrayList<CityDto>();
		try {
			cList = api.getCityInformation();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return cList;
	}

	public List<RegionDto> getRegionInformation(String citycode) {
		ApiRegionInformation api = new ApiRegionInformation();
		List<RegionDto> cList = new ArrayList<RegionDto>();
		
		try {
			cList = api.getRegionInformation(citycode);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cList;
	}

	public List<RailDto> getRailArray(String startStation, String lastStation, String day, String train) throws Exception {
	    List<RailDto> rList = ApiRailInformation.getApiRailInformation(startStation, lastStation, day, train);
	    // 시간 포맷터 정의
	    SimpleDateFormat originalFormat = new SimpleDateFormat("yyyyMMddHHmmss");
	    SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");

	    for (RailDto dto : rList) {
	        // 출도착 원본시간
	        String startRaw = dto.getStart_time();
	        String lastRaw = dto.getLast_time();

	        // Date 파싱
	        Date startDate = originalFormat.parse(startRaw);
	        Date lastDate = originalFormat.parse(lastRaw);

	        // 포맷된 시간 저장
	        dto.setStart_time(timeFormat.format(startDate));
	        dto.setLast_time(timeFormat.format(lastDate));

	        // 소요시간 계산
	        long diffMillis = lastDate.getTime() - startDate.getTime();
	        long diffMinutes = TimeUnit.MILLISECONDS.toMinutes(diffMillis);
	        long hours = diffMinutes / 60;
	        long minutes = diffMinutes % 60;
	        String duration = hours + "시간 " + minutes + "분";

	        // DTO에 소요시간 저장 (필드 추가 필요)
	        dto.setDuration(duration);
	    }
	    
	    return rList;
	}

	public Boolean insertReservation(ReservationDto dto) {
		
		return rDao.insertReservation(dto);
	}

	public List<ReservationDto> getMemberReservation(int mnum) {
		return rDao.getMemberReservation(mnum);
	}





	
}
