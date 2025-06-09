package controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;

import dto.BoardDto;
import dto.MemberDto;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import service.AdminService;

@WebServlet({"/admin/*"})
@MultipartConfig(
	fileSizeThreshold = 1024 * 1024,  // 1MB
	maxFileSize = 1024 * 1024 * 10,   // 10MB
	maxRequestSize = 1024 * 1024 * 50 // 50MB
)
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
            request.getRequestDispatcher("/views/admin/header.jsp").forward(request, response);

        } else if (command.equals("/admin/member")) {
            List<MemberDto> mList = service.getMember();
            request.setAttribute("memberList", mList);
            request.getRequestDispatcher("/views/admin/member.jsp").forward(request, response);

        } else if (command.equals("/admin/board")) {
            request.getRequestDispatcher("/views/admin/board.jsp").forward(request, response);

        } else if (command.equals("/admin/boardForm")) {
            request.getRequestDispatcher("/views/admin/boardForm.jsp").forward(request, response);

        } else if (command.equals("/admin/chat")) {
            request.getRequestDispatcher("/views/admin/chat.jsp").forward(request, response);

        } else if (command.equals("/admin/setboard")) {
        	String title = request.getParameter("title");
            String content = request.getParameter("content");

            Part filePart = request.getPart("file");
            String realFile = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
            String ext = realFile.substring(realFile.lastIndexOf("."));
            String serverFile = System.currentTimeMillis() + ext;

            String uploadPath = getServletContext().getRealPath("/upload");
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) uploadDir.mkdirs();

            filePart.write(uploadPath + File.separator + serverFile);

            BoardDto dto = new BoardDto();
            dto.setTitle(title);
            dto.setContent(content);
            dto.setRealFile(realFile);
            dto.setServerFile(serverFile);
            dto.setCreateAt(new Date());
            dto.setViewCount(0);
            service.registerBoard(dto);

            response.sendRedirect("/admin/board"); // 게시판 목록 페이지로 이동

        	
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "요청 경로를 찾을 수 없습니다.");
        }

    }

}
