package dto;

public class RailDto {
	private int trainId;               // 열차 번호
    private String trainName;          // 열차 등급 (KTX 등)
    private String start_station;      // 출발역 이름
    private String last_station;       // 도착역 이름
    private String start_time;         // 출발 시간 (예: 20230403051200)
    private String last_time;          // 도착 시간
    private String price;              // 요금 (성인요금)
    
	public int getTrainId() {
		return trainId;
	}
	public void setTrainId(int trainId) {
		this.trainId = trainId;
	}
	public String getTrainName() {
		return trainName;
	}
	public void setTrainName(String trainName) {
		this.trainName = trainName;
	}
	public String getStart_station() {
		return start_station;
	}
	public void setStart_station(String start_station) {
		this.start_station = start_station;
	}
	public String getLast_station() {
		return last_station;
	}
	public void setLast_station(String last_station) {
		this.last_station = last_station;
	}
	public String getStart_time() {
		return start_time;
	}
	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}
	public String getLast_time() {
		return last_time;
	}
	public void setLast_time(String last_time) {
		this.last_time = last_time;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
    
    
	
    
    
}
