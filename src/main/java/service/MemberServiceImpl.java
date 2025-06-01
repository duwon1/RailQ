package service;

import dao.MemberDao;
import dto.MemberDto;

public class MemberServiceImpl implements MemberService {
	
	private MemberDao dao = new MemberDao();

	
	public MemberDto getLogin() {
		return dao.getLogin("1");
	}

}
