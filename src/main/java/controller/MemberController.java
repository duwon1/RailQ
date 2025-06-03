package controller;

import java.io.IOException;

import dto.MemberDto;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import service.MemberService;

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
		HttpSession session = request.getSession();
		MemberService service = new MemberService();
		switch (command) {
			case "/":
				
				session.setAttribute("member", 1);
				request.getRequestDispatcher("/WEB-INF/views/main.jsp").forward(request, response);
				break;
			case "/member/loginForm":
				sessionCheck(request, response);
				request.getRequestDispatcher("/WEB-INF/views/member/loginForm.jsp").forward(request, response);	
				break;
			case "/member/test":
				MemberDto mDto = service.getLogin();
				request.setAttribute("member", mDto);
				request.getRequestDispatcher("/WEB-INF/views/member/test.jsp").forward(request, response);
				break;
			case "/member/login":
				request.getRequestDispatcher("/WEB-INF/views/member/login.jsp").forward(request, response);
				break;
			case "/member/join":
				request.getRequestDispatcher("/WEB-INF/views/member/join.jsp").forward(request, response);
				break;
			case "/member/terms":
				request.getRequestDispatcher("/WEB-INF/views/member/terms.jsp").forward(request, response);
				break;
			case "/member/find":
				request.getRequestDispatcher("/WEB-INF/views/member/find.jsp").forward(request, response);
				break;
			case "/member/insertmessage":
				sessionCheck(request, response);
				int memberNum = (Integer) session.getAttribute("member");
				String msg = request.getParameter("msg");
				
				service.memberMessage(memberNum, msg);
				break;
		}	
	}
	
	protected void sessionCheck(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession(false);
		if (session == null) {
		    response.sendRedirect("/member/loginForm");
		} 

			
	}

}
