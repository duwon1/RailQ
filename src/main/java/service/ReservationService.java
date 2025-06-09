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
import dto.CityDto;
import dto.RailDto;
import dto.RegionDto;

public class ReservationService {


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

	public JSONArray getRail(String startStation, String lastStation, String day) throws Exception {
		List<RailDto> rList = ApiRailInformation.getApiRailInformation(startStation, lastStation, day);
		JSONArray jsonArray = new JSONArray();
        for (RailDto dto : rList) {
            JSONObject obj = new JSONObject();
            obj.put("trainId", dto.getTrainId());
            obj.put("trainName", dto.getTrainName());
            obj.put("start_station", dto.getStart_station());
            obj.put("last_station", dto.getLast_station());
            obj.put("start_time", dto.getStart_time());
            obj.put("last_time", dto.getLast_time());
            obj.put("price", dto.getPrice());
            jsonArray.put(obj);
        }
        return jsonArray;
		
	}

	public List<RailDto> getRailArray(String startStation, String lastStation, String day) throws Exception {
	    List<RailDto> rList = ApiRailInformation.getApiRailInformation(startStation, lastStation, day);
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





	
}
