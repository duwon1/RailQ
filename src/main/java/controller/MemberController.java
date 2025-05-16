package controller;

import java.io.IOException;

import dto.MemberDto;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.MemberService;
import service.MemberServiceImpl;

@WebServlet({"", "/member/*"})
public class MemberController extends HttpServlet {
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
		
		MemberService service = new MemberServiceImpl();
		switch (command) {
		case "/":
			request.getRequestDispatcher("/WEB-INF/views/main.jsp").forward(request, response);
			break;
		case "/member/loginForm":
			request.getRequestDispatcher("/WEB-INF/views/member/loginForm.jsp").forward(request, response);	
			break;
		case "/member/test":
			MemberDto vo = service.getLogin();
			request.setAttribute("member", vo);
			request.getRequestDispatcher("/WEB-INF/views/member/test.jsp").forward(request, response);
			break;
		case "3":
			break;
		}	
	}

}
