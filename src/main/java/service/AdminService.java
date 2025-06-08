package service;

import java.util.List;

import dao.MemberDao;
import dto.MemberDto;

public class AdminService {

	private MemberDao mDao = new MemberDao();
	
	public List<MemberDto> getMember() {
		
		return mDao.getMember();
	}

}
