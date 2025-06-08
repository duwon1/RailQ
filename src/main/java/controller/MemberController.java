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

        MemberService service = new MemberService();

        if (command.equals("/")) {
            request.getRequestDispatcher("/WEB-INF/views/main.jsp").forward(request, response);

        } else if (command.equals("/member/loginForm")) {
            request.getRequestDispatcher("/WEB-INF/views/member/loginForm.jsp").forward(request, response);

        } else if (command.equals("/member/test")) {
            MemberDto mDto = service.getLogin();
            request.setAttribute("member", mDto);
            request.getRequestDispatcher("/WEB-INF/views/member/test.jsp").forward(request, response);

        } else if (command.equals("/member/login")) {
            request.getRequestDispatcher("/WEB-INF/views/member/login.jsp").forward(request, response);

        } else if (command.equals("/member/join")) {
            request.getRequestDispatcher("/WEB-INF/views/member/join.jsp").forward(request, response);

        } else if (command.equals("/member/terms")) {
            request.getRequestDispatcher("/WEB-INF/views/member/terms.jsp").forward(request, response);

        } else if (command.equals("/member/find")) {
            request.getRequestDispatcher("/WEB-INF/views/member/find.jsp").forward(request, response);

        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "해당 요청 경로가 존재하지 않습니다.");
        }
    }
}
