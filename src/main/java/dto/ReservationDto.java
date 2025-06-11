package dto;

public class ReservationDto {
	
	public final static String SERVICEKEY = "H5P66sxp6BnNWukmmYbToCrdFD4OB3GT0ynRUpfXlBmm5L9scMtyAtVAsShHgWk1qiAUEdE%2BQTd9D7i00bi%2Fug%3D%3D";
	
	private int num;
    private int mnum;
    private String trainId;
    private String resvDate;
    private String startStation;
    private String lastStation;
    private String startTime;
    private String lastTime;
    private int price;
    private java.sql.Date createdAt;
    
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public int getMnum() {
		return mnum;
	}
	public void setMnum(int mnum) {
		this.mnum = mnum;
	}
	public String getTrainId() {
		return trainId;
	}
	public void setTrainId(String trainId) {
		this.trainId = trainId;
	}
	public String getResvDate() {
		return resvDate;
	}
	public void setResvDate(String resvDate) {
		this.resvDate = resvDate;
	}
	public String getStartStation() {
		return startStation;
	}
	public void setStartStation(String startStation) {
		this.startStation = startStation;
	}
	public String getLastStation() {
		return lastStation;
	}
	public void setLastStation(String lastStation) {
		this.lastStation = lastStation;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getLastTime() {
		return lastTime;
	}
	public void setLastTime(String lastTime) {
		this.lastTime = lastTime;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public java.sql.Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(java.sql.Date createdAt) {
		this.createdAt = createdAt;
	}
	public static String getServicekey() {
		return SERVICEKEY;
	}
    
    
	
}
