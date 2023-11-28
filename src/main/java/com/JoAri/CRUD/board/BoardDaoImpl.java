package com.JoAri.CRUD.board;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BoardDaoImpl implements BoardDao{
	
	@Autowired
	private SqlSessionTemplate sqlsession;
	
	@Override
	public List<Map<String, Object>> getList(Map<String, Object> param){
		return sqlsession.selectList("BoardMapper.showBoardList", param);
	}
	
	@Override
	public void createBoard(Map<String, Object> param) {
		sqlsession.insert("BoardMapper.createBoard", param);
	}
	
	@Override
	public Map<String, Object> showBoard(int seq) {
		return sqlsession.selectOne("BoardMapper.showBoard", seq);
	}
	@Override
	public void incViewCnt(int seq) {
		sqlsession.update("BoardMapper.incViewCnt", seq);
	}
	
	@Override
	public void updateBoard(Map<String, Object> param) {
		sqlsession.update("BoardMapper.updateBoard", param);
	}
	
	@Override
	public void deleteBoard(Integer[] chk) {
		sqlsession.delete("BoardMapper.deleteBoard", chk);
	}
	
	@Override
	public int boardsNum(Map<String, Object> param) {
		return sqlsession.selectOne("BoardMapper.boardsNum", param);
	}

}
