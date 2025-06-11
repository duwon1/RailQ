package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.json.JSONArray;
import org.json.JSONObject;

import dto.CityDto;
import dto.MemberDto;
import dto.RailDto;
import dto.RegionDto;
import dto.ReservationDto;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
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
        	String start_id = request.getParameter("start_id") != null ? request.getParameter("start_id") : "NAT011668";
        	String last_id = request.getParameter("last_id") != null ? request.getParameter("last_id") : "NAT010000";
        	String start_name = request.getParameter("start_name") != null ? request.getParameter("start_name") : "대전";
        	String last_name = request.getParameter("last_name") != null ? request.getParameter("last_name") : "서울";
        	String date = request.getParameter("date") != null ? request.getParameter("date") : "2025-06-14";
        	String time = request.getParameter("time") != null ? request.getParameter("time") : "04";

        	Map<String, String> pMap = new HashMap<>();
        	pMap.put("total", "1");

        	Map<String, Object> rMap = new HashMap<>();
        	rMap.put("start_id", start_id);
        	rMap.put("start_name", start_name);
        	rMap.put("last_id", last_id);
        	rMap.put("last_name", last_name);

        	List<CityDto> cList = service.getCityInformation();

        	List<String> vehicleTypes = Arrays.asList("00", "07", "10", "16", "17", "08", "09", "18"); // 차량 종류
        	Set<String> seenTrainKeys = new HashSet<>();
        	List<RailDto> rList = new ArrayList<>();

        	for (String type : vehicleTypes) {
        	    try {
        	        List<RailDto> tempList = service.getRailArray(start_id, last_id, date.replace("-", ""), type);
        	        if (tempList != null) {
        	            for (RailDto dto : tempList) {
        	                if (dto != null && dto.getStart_time() != null) {
        	                    String uniqueKey = dto.getTrainId() + dto.getStart_time();
        	                    if (!seenTrainKeys.contains(uniqueKey)) {
        	                        // ✅ KTX일 때만 뒤에 붙은 이름 제거
        	                        String fullTrainName = dto.getTrainName();
        	                        if (fullTrainName != null && fullTrainName.startsWith("KTX-")) {
        	                            dto.setTrainName("KTX");
        	                        }

        	                        seenTrainKeys.add(uniqueKey);
        	                        rList.add(dto);
        	                    }
        	                }
        	            }
        	        }
        	    } catch (Exception e) {
        	        e.printStackTrace();
        	    }
        	}

        	// 필터: 전달받은 시간 이후 열차만
        	int baseHour = Integer.parseInt(time);
        	rList = rList.stream()
        	    .filter(r -> {
        	        String st = r.getStart_time();
        	        if (st == null || st.length() < 2) return false;
        	        try {
        	            int hour = Integer.parseInt(st.substring(0, 2));
        	            return hour >= baseHour;
        	        } catch (NumberFormatException e) {
        	            return false;
        	        }
        	    })
        	    .sorted(Comparator.comparing(RailDto::getStart_time))
        	    .collect(Collectors.toList());

        	request.setAttribute("rList", rList);
        	request.setAttribute("cityList", cList);
        	request.setAttribute("pMap", pMap);
        	request.setAttribute("rMap", rMap);
        	request.setAttribute("date", date);
        	request.setAttribute("time", time);

        	request.getRequestDispatcher("/views/reservation/reservation.jsp").forward(request, response);

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
        	String trainId = request.getParameter("trainId");
        	String date = request.getParameter("date"); // yyyyMMdd 또는 yyyy-MM-dd
        	String startStation = request.getParameter("start_station");
        	String lastStation = request.getParameter("last_station");
        	String startTime = request.getParameter("start_time");
        	String lastTime = request.getParameter("last_time");
        	String trainName = request.getParameter("trainName"); // 예: KTX, ITX-청춘 등
        	String price = request.getParameter("price");

        	// 날짜 포맷 변경: yyyyMMdd 또는 yyyy-MM-dd → yyyy년 MM월 dd일
        	String formattedDate = "";
        	String dayOfWeek = "";
        	LocalDate localDate = null;
        	try {
        	    if (date != null) {
        	        if (date.contains("-")) {
        	            localDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        	        } else {
        	            localDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyyMMdd"));
        	        }
        	        formattedDate = localDate.format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일"));
        	        DayOfWeek dow = localDate.getDayOfWeek();
        	        dayOfWeek = dow.getDisplayName(java.time.format.TextStyle.SHORT, Locale.KOREAN); // 예: 월, 화
        	    }
        	} catch (Exception e) {
        	    e.printStackTrace();
        	}

        	// 결제 마감시간 임의 생성 (예: 예약일 하루 전 16시)
        	String deadline = "";
        	try {
        	    if (localDate != null) {
        	        LocalDate deadlineDate = localDate.minusDays(1);
        	        deadline = deadlineDate.format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일")) + " 16:00";
        	    }
        	} catch (Exception e) {
        	    e.printStackTrace();
        	}

        	// 값 넘기기
        	request.setAttribute("trainId", trainId);
        	request.setAttribute("date", formattedDate);
        	request.setAttribute("dayOfWeek", dayOfWeek);
        	request.setAttribute("startStation", startStation);
        	request.setAttribute("lastStation", lastStation);
        	request.setAttribute("startTime", startTime);
        	request.setAttribute("lastTime", lastTime);
        	request.setAttribute("trainName", trainName);
        	request.setAttribute("deadline", deadline);
        	request.setAttribute("price", price);

        	request.getRequestDispatcher("/views/reservation/reservation_detail.jsp").forward(request, response);


        } else if (command.equals("/reservation/pay")) {
        	String trainId = request.getParameter("trainId");
            String date = request.getParameter("date");
            String startStation = request.getParameter("startStation");
            String lastStation = request.getParameter("lastStation");
            String startTime = request.getParameter("startTime");
            String lastTime = request.getParameter("lastTime");
            int price = Integer.parseInt(request.getParameter("price"));

            // 유저 정보 가져오기
            HttpSession session = request.getSession();
            MemberDto mDto = (MemberDto) session.getAttribute("loginUser");
            if (mDto == null) {
                response.sendRedirect("/member/loginForm");
                return;
            }

            int mNum = mDto.getNum();

            ReservationDto dto = new ReservationDto();
            dto.setTrainId(trainId);
            dto.setResvDate(date);
            dto.setStartStation(startStation);
            dto.setLastStation(lastStation);
            dto.setStartTime(startTime);
            dto.setLastTime(lastTime);
            dto.setPrice(price);
            dto.setMnum(mNum);

            Boolean success = service.insertReservation(dto);

            response.setContentType("text/html; charset=UTF-8");
            PrintWriter out = response.getWriter();

            if (success) {
                out.println("<script>");
                out.println("alert('예약이 성공적으로 완료되었습니다.');");
                out.println("location.href='/'");
                out.println("</script>");
            } else {
                out.println("<script>");
                out.println("alert('예약에 실패했습니다. 다시 시도해주세요.');");
                out.println("location.href='/'");
                out.println("</script>");
            }
            
        } else if (command.equals("/resrvation/getMemberReservation")) {
        	HttpSession session = request.getSession();
            MemberDto loginUser = (MemberDto) session.getAttribute("loginUser");

            if (loginUser != null) {
                int mnum = loginUser.getNum();
                List<ReservationDto> resvList = service.getMemberReservation(mnum);

                request.setAttribute("resvList", resvList);
                request.getRequestDispatcher("/views/reservation/reservation_list.jsp").forward(request, response);
            } else {
                response.sendRedirect("/login");
            }
        }
    }
}
