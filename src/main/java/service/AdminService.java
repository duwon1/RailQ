package service;

import java.util.List;

import dao.BoardDao;
import dao.MemberDao;
import dto.BoardDto;
import dto.MemberDto;

public class AdminService {

	private MemberDao mDao = new MemberDao();
	private BoardDao bDao = new BoardDao();
	
	public List<MemberDto> getMember() {
		
		return mDao.getMember();
	}

	public void registerBoard(BoardDto dto) {
		bDao.insert(dto);
		
	}

	public List<BoardDto> getBoardList() {
		return bDao.selectBoardList();
	}

	public void deleteBoard(int num) {
		mDao.deleteBoard(num);
		
	}

}
