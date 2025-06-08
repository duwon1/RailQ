package dto;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ChatDto {
    private int num;
    private int mnum;
    private String sender_type;
    private String msg;
    private String create_time; // "yyyy-MM-dd HH:mm:ss" 형식 가정

    // Getter / Setter
    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getMnum() {
        return mnum;
    }

    public void setMnum(int mnum) {
        this.mnum = mnum;
    }

    public String getSender_type() {
        return sender_type;
    }

    public void setSender_type(String sender_type) {
        this.sender_type = sender_type;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    // ✅ 화면 출력용 시간 포맷 (예: "오후 07:51")
    public String getDisplayTime() {
        try {
            SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            SimpleDateFormat outputFormat = new SimpleDateFormat("a hh:mm", Locale.KOREAN);
            Date date = inputFormat.parse(create_time);
            return outputFormat.format(date);
        } catch (Exception e) {
            return create_time; // 파싱 실패 시 원본 반환
        }
    }
}