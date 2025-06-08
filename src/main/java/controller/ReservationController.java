package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import dto.CityDto;
import dto.RegionDto;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.ReservationService;

@WebServlet("/reservation/*")
public class ReservationController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, 
                          HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, 
                         HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String path = request.getContextPath();
        String uri = request.getRequestURI();
        String command = uri.substring(path.length());
        System.out.println("요청경로 : " + command);

        ReservationService service = new ReservationService();

        if (command.equals("/reservation")) {

            Map<String, String> pMap = new HashMap<>();
            pMap.put("adult", "0");
            pMap.put("child", "0");
            pMap.put("elderly", "0");
            pMap.put("nice", "1");
            pMap.put("total", "1");

            Map<String, Object> rMap = new HashMap<>();
            rMap.put("start_id", "NAT020040");
            rMap.put("start_name", "상봉");
            rMap.put("last_id", "NAT010032");
            rMap.put("last_name", "용산");

            List<CityDto> cList = service.getCityInformation();

            request.setAttribute("cityList", cList);
            request.setAttribute("pMap", pMap);
            request.setAttribute("rMap", rMap);
            request.setAttribute("date", "2025-06-13");
            request.setAttribute("time", "22");

            request.getRequestDispatcher("/WEB-INF/views/reservation/reservation.jsp").forward(request, response);

        } else if (command.equals("/reservation/getrail")) {
            BufferedReader reader = request.getReader();
            StringBuilder sb = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }

            JSONObject json = new JSONObject(sb.toString());

            String startStation = json.getString("start_station");
            String lastStation = json.getString("last_station");
            String day = json.getString("day");
            String time = json.getString("time");
            String pageNum = json.getString("pageNum");
            try {
            	JSONArray jsonArray = service.getRail(startStation,lastStation, day, pageNum);
            	response.setContentType("application/json");
            	response.setCharacterEncoding("UTF-8");
            	response.getWriter().write(jsonArray.toString());
			} catch (Exception e) {
				e.printStackTrace();
			}
            
            
        } else if (command.equals("/reservation/region")) {
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");

            String citycode = request.getParameter("citycode");
            List<RegionDto> rList = service.getRegionInformation(citycode);

            JSONArray jsonArray = new JSONArray();
            for (RegionDto dto : rList) {
                JSONObject obj = new JSONObject();
                obj.put("nodeid", dto.getNodeid());
                obj.put("nodename", dto.getNodename());
                jsonArray.put(obj);
            }

            response.getWriter().write(jsonArray.toString());

        } else if (command.equals("/reservation/detail")) {
            request.getRequestDispatcher("/WEB-INF/views/reservation/reservation_detail.jsp").forward(request, response);

        } else if (command.equals("/reservation/payment")) {
            request.getRequestDispatcher("/WEB-INF/views/reservation/reservation_payment.jsp").forward(request, response);

        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "요청 경로를 찾을 수 없습니다.");
        }
    }
}
