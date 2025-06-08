package service;

import java.util.ArrayList;
import java.util.List;

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

	public JSONArray getRail(String startStation, String lastStation, String day, String pageNum) throws Exception {
		List<RailDto> rList = ApiRailInformation.getApiRailInformation(pageNum, startStation, lastStation, day);
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



	
}
