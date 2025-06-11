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

import dto.RailDto;
import dto.ReservationDto;

public class ApiRailInformation {
    public static List<RailDto> getApiRailInformation(String startStation, String lastStation, String day, String train) throws Exception {
        StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/1613000/TrainInfoService/getStrtpntAlocFndTrainInfo");
        urlBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8") + "=" + ReservationDto.SERVICEKEY);
        urlBuilder.append("&" + URLEncoder.encode("pageNo", "UTF-8") + "=" + URLEncoder.encode("1", "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("numOfRows", "UTF-8") + "=" + URLEncoder.encode("999", "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("_type", "UTF-8") + "=" + URLEncoder.encode("json", "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("depPlaceId", "UTF-8") + "=" + URLEncoder.encode(startStation, "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("arrPlaceId", "UTF-8") + "=" + URLEncoder.encode(lastStation, "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("depPlandTime", "UTF-8") + "=" + URLEncoder.encode(day, "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("trainGradeCode", "UTF-8") + "=" + URLEncoder.encode(train, "UTF-8"));

        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");

        BufferedReader rd;
        if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
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
        System.out.println(sb.toString());

        List<RailDto> railList = new ArrayList<>();

        JSONObject root = new JSONObject(sb.toString());
        JSONObject response = root.getJSONObject("response");
        JSONObject body = response.getJSONObject("body");
        Object itemsObj = body.get("items");

        if (itemsObj instanceof JSONObject) {
            JSONObject items = (JSONObject) itemsObj;

            if (items.has("item")) {
                JSONArray itemArray = items.getJSONArray("item");

                for (int i = 0; i < itemArray.length(); i++) {
                    JSONObject item = itemArray.getJSONObject(i);

                    RailDto dto = new RailDto();
                    dto.setTrainId(item.getInt("trainno"));
                    dto.setTrainName(item.getString("traingradename"));
                    dto.setStart_station(item.getString("depplacename"));
                    dto.setLast_station(item.getString("arrplacename"));
                    dto.setStart_time(String.valueOf(item.getLong("depplandtime")));
                    dto.setLast_time(String.valueOf(item.getLong("arrplandtime")));
                    dto.setPrice(String.valueOf(item.getInt("adultcharge")));

                    railList.add(dto);
                }
            }
        } else {
            System.out.println("🚫 열차 데이터 없음: items = \"\"");
        }

        return railList;
    }
}
