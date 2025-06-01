package api;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import dto.RegionDto;
import dto.ReservationDto;

public class ApiRegionInformation {
	public List<RegionDto> getRegionInformation(String citycode) throws Exception {
		StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/1613000/TrainInfoService/getCtyAcctoTrainSttnList"); /*URL*/
        urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=" + ReservationDto.SERVICEKEY); /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*페이지번호*/
        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("999", "UTF-8")); /*한 페이지 결과 수*/
        urlBuilder.append("&" + URLEncoder.encode("_type","UTF-8") + "=" + URLEncoder.encode("json", "UTF-8")); /*데이터 타입(xml, json)*/
        urlBuilder.append("&" + URLEncoder.encode("cityCode","UTF-8") + "=" + URLEncoder.encode(citycode, "UTF-8")); /*시/도 ID*/
        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        BufferedReader rd;
        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        rd.close();
        conn.disconnect();
        
	    // JSON 문자열을 JSONObject로 파싱
        List<RegionDto> rList = new ArrayList<>();
	    JSONObject root = new JSONObject(sb.toString()); // sb는 JSON 문자열이 저장된 StringBuilder
	    // item 배열 추출
	    JSONArray items = root
	    		.getJSONObject("response")
	    		.getJSONObject("body")
	    		.getJSONObject("items")
	    		.getJSONArray("item");

	    // 배열 순회하며 RegionDto 리스트로 변환
	    for (int i = 0; i < items.length(); i++) {
	    	JSONObject item = items.getJSONObject(i);
	    	RegionDto dto = new RegionDto();
	    	dto.setNodeid(item.getString("nodeid"));     // 문자열 그대로 설정
	    	dto.setNodename(item.getString("nodename"));
	    	rList.add(dto);
	    }
		
		return rList;
    }
	
}
