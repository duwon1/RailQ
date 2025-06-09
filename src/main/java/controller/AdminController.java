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
        	List<BoardDto> boardList = service.getBoardList();  // 게시글 리스트 불러오기
            request.setAttribute("boardList", boardList);       // JSP로 전달
            request.getRequestDispatcher("/views/admin/board.jsp").forward(request, response);
        } else if (command.equals("/admin/boardForm")) {
            request.getRequestDispatcher("/views/admin/boardForm.jsp").forward(request, response);

        } else if (command.equals("/admin/chat")) {
            request.getRequestDispatcher("/views/admin/chat.jsp").forward(request, response);

        } else if (command.equals("/admin/setboard")) {
        	String title = request.getParameter("title");
        	String content = request.getParameter("content");

        	String realFile = null;
        	String serverFile = null;

        	Part filePart = request.getPart("file");

        	// 파일이 있을 경우에만 처리
        	if (filePart != null && filePart.getSubmittedFileName() != null && !filePart.getSubmittedFileName().isBlank()) {
        	    realFile = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
        	    String ext = realFile.substring(realFile.lastIndexOf("."));
        	    serverFile = System.currentTimeMillis() + ext;

        	    String uploadPath = getServletContext().getRealPath("/upload");
        	    File uploadDir = new File(uploadPath);
        	    if (!uploadDir.exists()) uploadDir.mkdirs();

        	    filePart.write(uploadPath + File.separator + serverFile);
        	}

        	BoardDto dto = new BoardDto();
        	dto.setTitle(title);
        	dto.setContent(content);
        	dto.setRealFile(realFile);     // null이 될 수도 있음
        	dto.setServerFile(serverFile); // null이 될 수도 있음
        	dto.setCreateAt(new Date());
        	dto.setViewCount(0);

        	service.registerBoard(dto);

        	response.sendRedirect("/admin/board?header=3");


        	
        } else if (command.equals("/admin/deleteBoard")) {
            int num = Integer.parseInt(request.getParameter("num"));

            // 파일도 같이 삭제하고 싶으면 파일명 조회 후 실제 파일 삭제 추가
            service.deleteBoard(num);

            response.sendRedirect("/admin/board?header=3");
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "요청 경로를 찾을 수 없습니다.");
        }

    }

}
