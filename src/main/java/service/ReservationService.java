package service;

import java.util.ArrayList;
import java.util.List;

import api.ApiCityInformation;
import api.ApiRegionInformation;
import dto.CityDto;
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

}
