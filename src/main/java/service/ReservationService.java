package service;

import java.util.List;

import dto.CityDto;
import dto.RegionDto;

public interface ReservationService {

	List<CityDto> getCityInformation();

	List<RegionDto> getRegionInformation(String cityid);

}
