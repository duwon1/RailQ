package utill;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.json.JSONObject;

import dao.ChatDao;
import dto.ChatDto;
import jakarta.websocket.OnClose;
import jakarta.websocket.OnError;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.PathParam;
import jakarta.websocket.server.ServerEndpoint;

@ServerEndpoint("/chatSocket/{mnum}")
public class ChatSocket {

    // 유저별 세션 저장: mnum -> 세션
    private static Map<Integer, Session> userSessions = new ConcurrentHashMap<>();

    // 상담원 세션 (1명만 연결된다고 가정)
    private static Session agentSession = null;

    // DAO 생성
    private ChatDao cDao = new ChatDao();

    // 연결 시
    @OnOpen
    public void onOpen(Session session, @PathParam("mnum") String mnumParam) {
        int mnum = Integer.parseInt(mnumParam);

        if (mnum == 0) {
            agentSession = session;
            System.out.println("✅ 상담원 접속");
        } else {
            userSessions.put(mnum, session);
            System.out.println("✅ 유저 접속: " + mnum);
        }
    }

    // 메시지 수신 시
    @OnMessage
    public void onMessage(String message, Session session) {
    	System.out.println("📨 받은 원본 메시지(JSON): " + message);
        try {
            JSONObject json = new JSONObject(message);
            String sender = json.getString("sender"); // "m" or "a"
            int mnum = json.getInt("mnum");
            String msg = json.getString("msg");

            // ✅ DTO 객체에 값 설정
            ChatDto dto = new ChatDto();
            dto.setMnum(mnum);
            dto.setSender_type(sender);
            dto.setMsg(msg);
            
            // ✅ DB 저장
            cDao.insertMessage(dto);

            // ✅ 상대방 및 본인에게 메시지 전송
            String jsonMessage = json.toString();

            if ("m".equals(sender)) {
                if (agentSession != null && agentSession.isOpen()) {
                    agentSession.getBasicRemote().sendText(jsonMessage);
                }
                Session userSession = userSessions.get(mnum);
                if (userSession != null && userSession.isOpen()) {
                    userSession.getBasicRemote().sendText(jsonMessage);
                }
            } else if ("a".equals(sender)) {
                Session userSession = userSessions.get(mnum);
                if (userSession != null && userSession.isOpen()) {
                    userSession.getBasicRemote().sendText(jsonMessage);
                }
                if (agentSession != null && agentSession.isOpen()) {
                    agentSession.getBasicRemote().sendText(jsonMessage);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    // 연결 종료
    @OnClose
    public void onClose(Session session, @PathParam("mnum") String mnumParam) {
        int mnum = Integer.parseInt(mnumParam);
        if (session == agentSession) {
            System.out.println("❌ 상담원 연결 종료");
            agentSession = null;
        } else {
            System.out.println("❌ 유저 연결 종료: " + mnum);
            userSessions.remove(mnum);
        }
    }

    // 오류 발생
    @OnError
    public void onError(Session session, Throwable e) {
        e.printStackTrace();
    }
}
