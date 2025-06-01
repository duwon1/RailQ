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

import dto.CityDto;
import dto.ReservationDto;

public class ApiCityInformation {
	public List<CityDto> getCityInformation() throws Exception {
		StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/1613000/TrainInfoService/getCtyCodeList"); /*URL*/
        urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=" + ReservationDto.SERVICEKEY); /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("_type","UTF-8") + "=" + URLEncoder.encode("json", "UTF-8")); /*데이터 타입(xml, json)*/
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
        
        // ✅ JSON 파싱
        List<CityDto> cList = new ArrayList<>();
        JSONObject root = new JSONObject(sb.toString());
        JSONArray items = root
            .getJSONObject("response")
            .getJSONObject("body")
            .getJSONObject("items")
            .getJSONArray("item");

        for (int i = 0; i < items.length(); i++) {
            JSONObject item = items.getJSONObject(i);
            CityDto dto = new CityDto();
            dto.setCitycode(item.getInt("citycode"));
            dto.setCityname(item.getString("cityname"));
            cList.add(dto);
        }
        
        return cList;
	}
}
