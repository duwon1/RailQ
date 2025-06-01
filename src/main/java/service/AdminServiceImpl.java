package service;

import java.util.List;

import dao.MemberDao;
import dto.MemberDto;

public class AdminServiceImpl implements AdminService {

	MemberDao mDao = new MemberDao();
	
	@Override
	public List<MemberDto> getMember() {
		
		return mDao.getMember();
	}

}
