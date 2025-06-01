package controller;

import java.io.IOException;
import java.util.List;

import javax.print.attribute.HashPrintRequestAttributeSet;

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
import service.ReservationServiceImpl;

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
		
		ReservationService service = new ReservationServiceImpl();
		switch (command) {
			case "/reservation":
				List<CityDto> cList = service.getCityInformation();
				request.setAttribute("cityList", cList);
				request.setAttribute("start", "서울");
				request.setAttribute("last", "미국");
				request.getRequestDispatcher("/WEB-INF/views/reservation/reservation.jsp").forward(request, response);
				break;
			case "/reservation/region":
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
				break;
			case "/reservation/detail":
				request.getRequestDispatcher("/WEB-INF/views/reservation/reservation_detail.jsp").forward(request, response);
				break;
			case "/reservation/payment":
				request.getRequestDispatcher("/WEB-INF/views/reservation/reservation_payment.jsp").forward(request, response);
				break;
		}	
	}

}
