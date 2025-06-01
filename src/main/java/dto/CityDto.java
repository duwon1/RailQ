package dto;

public class CityDto {
	private int citycode;
	private String cityname;
	
	public int getCitycode() {
		return citycode;
	}
	public void setCitycode(int citycode) {
		this.citycode = citycode;
	}
	public String getCityname() {
		return cityname;
	}
	public void setCityname(String cityname) {
		this.cityname = cityname;
	}
	
	@Override
	public String toString() {
	    return "CityDto { citycode = " + citycode + ", cityname = '" + cityname + "' }";
	}
	

}
