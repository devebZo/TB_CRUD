package com.JoAri.CRUD.board;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BoardServiceImpl implements BoardService{
	@Autowired
	private BoardDao boardDao;
	
	@Override
	public List<Map<String, Object>> getList(){
		return boardDao.getList();
	}
	
	@Override
	public void createBoard(Map<String, Object> param) {
		boardDao.createBoard(param);
	}
	
	@Override
	public Map<String, Object> showBoard(int seq) {
		return boardDao.showBoard(seq);
	}
	@Override
	public void incViewCnt(int seq) {
		boardDao.incViewCnt(seq);
	}
	
	@Override
	public void updateBoard(Map<String, Object> param) {
		boardDao.updateBoard(param);
	}
	
}
