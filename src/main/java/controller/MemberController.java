package controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import com.google.gson.Gson;

import dto.KakaoProfile;
import dto.KakaoProfile.KakaoAccount;
import dto.KakaoProfile.KakaoAccount.Profile;
import dto.MemberDto;
import dto.OAuthToken;
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
            request.getRequestDispatcher("/views/main.jsp").forward(request, response);

        } else if (command.equals("/member/loginForm")) {
            request.getRequestDispatcher("/views/member/loginForm.jsp").forward(request, response);

        } else if (command.equals("/member/chat")) {
        	if (sessionCheck(request, response)) return;
        	HttpSession session = request.getSession();
        	request.setAttribute("loginUser",session.getAttribute("loginUser"));
            request.getRequestDispatcher("/views/member/chat.jsp").forward(request, response);

        } else if (command.equals("/member/kakaostart")) {
        	String kakaoUrl = "https://kauth.kakao.com/oauth/authorize?"
                    + "client_id=a5bb6b551dc5cb1e59e5f5a9d3b4dd31"
                    + "&redirect_uri=http://localhost:8080/member/kakaologin"
                    + "&response_type=code"
                    + "&prompt=login";
        	response.sendRedirect(kakaoUrl);

        }  else if (command.equals("/member/kakaologin")) {
        	// 카카오 아이디, 비번 인증 + 아이디 이메일 제공 동의 후 전송되는 암호화 코드
    		String code = request.getParameter("code");
    		// 전송된 암호화코드를 이용해서 토큰을 요청
    		// 토큰 요청 주소 url 설정 및 파라미터
    		String endpoint = "https://kauth.kakao.com/oauth/token";
    		URL url = new URL(endpoint); // import java.net.URL;
    		String bodyData = "grant_type=authorization_code&";
    		bodyData += "client_id=a5bb6b551dc5cb1e59e5f5a9d3b4dd31";
    		bodyData += "&redirect_uri=http://localhost:8080/member/kakaologin&";
    		bodyData += "code=" + code;
    		// Stream 연결 및 토큰 수신
    		HttpURLConnection conn = (HttpURLConnection) url.openConnection(); // import java.net.HttpURLConnection;
    		conn.setRequestMethod("POST");
    		conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
    		conn.setDoOutput(true);
    		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream(), "UTF-8"));
    		bw.write(bodyData);
    		bw.flush();
    		BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
    		String input = "";
    		StringBuilder sb = new StringBuilder(); // 조각난 String 을 조립하기위한 객체
    		while ((input = br.readLine()) != null) {
    			sb.append(input);
    			System.out.println(input); // 수신된 토큰을 콘솔에 출력합니다
    		}

    		// 위 토큰내용을 본따 만든 클래스에 gson 파싱으로 옮겨 담음
    		Gson gson = new Gson();
    		OAuthToken oAuthToken = gson.fromJson(sb.toString(), OAuthToken.class);

    		// oAuthToken을 이용해서 사용자 정보를 요청 수신
    		String endpoint2 = "https://kapi.kakao.com/v2/user/me";
    		URL url2 = new URL(endpoint2);
    		// import java.net.HttpURLConnection;
    		HttpsURLConnection conn2 = (HttpsURLConnection) url2.openConnection();
    		conn2.setRequestProperty("Authorization", "Bearer " + oAuthToken.getAccess_token());
    		conn2.setDoOutput(true);
    		BufferedReader br2 = new BufferedReader(new InputStreamReader(conn2.getInputStream(), "UTF-8"));
    		String input2 = "";
    		StringBuilder sb2 = new StringBuilder();
    		while ((input2 = br2.readLine()) != null) {
    			sb2.append(input2);
    			System.out.println(input2);
    		}

    		// 전달받은 회원정보를 kakaoProfile 객체에 담음
    		Gson gson2 = new Gson();
    		KakaoProfile kakaoProfile = gson2.fromJson(sb2.toString(), KakaoProfile.class);

    		KakaoAccount ac = kakaoProfile.getAccount();
    		Profile pf = ac.getProfile();

    		MemberDto mDto = service.getMember(kakaoProfile.getId());
    		if (mDto == null) {
    			mDto = new MemberDto();
    			mDto.setId(kakaoProfile.getId());
    			mDto.setEmail(ac.getEmail());
    			mDto.setName(pf.getNickname());
    			mDto.setGender(ac.getGender());
    			mDto.setMcheck("kakao");
    			service.joinKakao(mDto);
    			HttpSession session = request.getSession();
    			session.setAttribute("loginUser", mDto);
    			System.out.println("카카오 회원가입중");
    		} else {
    			HttpSession session = request.getSession();
    			session.setAttribute("loginUser", mDto);
    		}
    		
    		response.sendRedirect("/");
    		
        } else if (command.equals("/member/logout")) {
            HttpSession session = request.getSession(false);
            if (session != null) {
                MemberDto loginUser = (MemberDto) session.getAttribute("loginUser");

                if (loginUser != null && "kakao".equals(loginUser.getMcheck())) {
                    // 카카오 로그인 사용자면 /member/kakaologout 으로 재요청
                    response.sendRedirect("/member/kakaologout");
                    return;
                }

                // 일반 로그인 사용자: 세션만 무효화
                session.invalidate();
                PrintWriter out = response.getWriter();
                out.println("<script>");
                out.println("alert('회원탈퇴에 성공했습니다.');");
                out.println("location.href='/';");
                out.println("</script>");
                out.close();
                return;
            }
        } else if (command.equals("/member/kakaologout")) {
        	HttpSession session = request.getSession();
        	MemberDto mDto = (MemberDto) session.getAttribute("loginUser");

            String kakaoId = mDto.getId();        // 카카오 고유 ID (String 또는 long)
            int memberNum = mDto.getNum();        // 회원 고유 번호
            String adminKey = "d11ce67e5716aabc3a20e46a302965ea";

            try {
                URL url = new URL("https://kapi.kakao.com/v1/user/unlink");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Authorization", "KakaoAK " + adminKey);
                conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                conn.setDoOutput(true);

                String body = "target_id_type=user_id&target_id=" + kakaoId;
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream(), "UTF-8"));
                bw.write(body);
                bw.flush();
                bw.close();

                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
                StringBuilder sb = new StringBuilder();
                String line;
                while ((line = br.readLine()) != null) {
                    sb.append(line);
                }
                br.close();

                System.out.println("카카오 연결 해제 응답: " + sb.toString());

            } catch (Exception e) {
                e.printStackTrace();
                response.setContentType("text/html; charset=UTF-8");
                PrintWriter out = response.getWriter();
                out.println("<script>");
                out.println("alert('카카오 연결 해제 중 오류가 발생했습니다.');");
                out.println("location.href='/';");
                out.println("</script>");
                out.close();
                return;
            }

            // DB에서 회원 삭제
            service.memberDelete(memberNum);

            // 세션 무효화
            session.invalidate();

            // 알림 후 메인 이동
            response.setContentType("text/html; charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<script>");
            out.println("alert('회원 탈퇴 및 카카오 연결이 해제되었습니다.');");
            out.println("location.href='/';");
            out.println("</script>");
            out.close();
            
        } 

    } 
        
        
    
    
    public boolean sessionCheck(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(false);
        Object loginUser = (session != null) ? session.getAttribute("loginUser") : null;
        if (loginUser == null) {
            response.setContentType("text/html; charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<script>");
            out.println("alert('로그인하세요');");
            out.println("location.href='/member/loginForm'");
            out.println("</script>");
            out.close();
            return true;
        }

        return false;
    }
    
}
