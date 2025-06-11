package service;

import dao.MemberDao;
import dto.MemberDto;

public class MemberService {
	
	private MemberDao dao = new MemberDao();

	
	public MemberDto getLogin() {
		return dao.getLogin("1");
	}


	public MemberDto getMember(String id) {
		return dao.getKakaoMember(id);
	}


	public void joinKakao(MemberDto mDto) {
		dao.joinKakao(mDto);
		
	}


	public void memberDelete(int memberNum) {
		dao.memberDelete(memberNum);
		
	}


}
