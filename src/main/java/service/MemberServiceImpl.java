package service;

import dao.MemberDao;
import dto.MemberVO;

public class MemberServiceImpl implements MemberService {
	
	private MemberDao dao = new MemberDao();

	@Override
	public MemberVO getLogin() {
		return dao.getLogin("1");
	}

}
