package controller;

import java.io.IOException;
import java.util.List;

import dto.MemberDto;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.AdminService;

@WebServlet({"/admin/*"})
public class AdminController extends HttpServlet {

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

        AdminService service = new AdminService();

        if (command.equals("/admin")) {
            request.getRequestDispatcher("/WEB-INF/views/admin/header.jsp").forward(request, response);

        } else if (command.equals("/admin/member")) {
            List<MemberDto> mList = service.getMember();
            request.setAttribute("memberList", mList);
            request.getRequestDispatcher("/WEB-INF/views/admin/member.jsp").forward(request, response);

        } else if (command.equals("/admin/board")) {
            request.getRequestDispatcher("/WEB-INF/views/admin/board.jsp").forward(request, response);

        } else if (command.equals("/admin/chat")) {
            request.getRequestDispatcher("/WEB-INF/views/admin/chat.jsp").forward(request, response);

        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "요청 경로를 찾을 수 없습니다.");
        }
    }
}
