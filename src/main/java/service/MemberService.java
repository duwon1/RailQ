package service;

import dao.MemberDao;
import dto.MemberDto;

public class MemberService {
	
	private MemberDao dao = new MemberDao();

	
	public MemberDto getLogin() {
		return dao.getLogin("1");
	}

}
