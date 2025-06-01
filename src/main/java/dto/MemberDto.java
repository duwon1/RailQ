package dto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MemberDto {
	private int num;
	private String id;
	private String pw;
	private String email;
	private String name;
	private String phone;
	private String gender;
	private String zipcode;
	private String address;
	private String address1;
	private String create_time;
	private String last_login;
	private int mcheck;
	
	 private static final DateTimeFormatter FORMATTER = 
			 DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 HH시 mm분 ss초");
	
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getAddress1() {
		return address1;
	}
	public void setAddress1(String address1) {
		this.address1 = address1;
	}
	public String getCreate_time() {
		return create_time;
	}
	public void setCreate_time(String create_time) {
		this.create_time = formatIfTimestamp(create_time);;
	}
	public String getLast_login() {
		return last_login;
	}
	
	private String formatIfTimestamp(String raw) {
        try {
            LocalDateTime ldt = LocalDateTime.parse(raw); // ISO-8601 기준 파싱
            return ldt.format(FORMATTER);
        } catch (Exception e) {
            // 파싱 안 되면 원본 그대로 반환
            return raw;
        }
    }
	
	public void setLast_login(String last_login) {
		this.last_login = formatIfTimestamp(last_login);
	}
	public int getMcheck() {
		return mcheck;
	}
	public void setMcheck(int mcheck) {
		this.mcheck = mcheck;
	}
	
	
	
	
}
