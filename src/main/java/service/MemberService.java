package service;

import dao.MemberDao;
import dto.MemberDto;

public class MemberService {
	
	private MemberDao dao = new MemberDao();

	
	public MemberDto getLogin() {
		return dao.getLogin("1");
	}
	
	public int sessionMember() {
		
		return 1;
	}


	public void memberMessage(int memberNum, String msg) {
		
		
	}

}
