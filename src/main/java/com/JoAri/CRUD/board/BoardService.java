package com.JoAri.CRUD.board;

import java.util.List;
import java.util.Map;

public interface BoardService {
	
	List<Map<String, Object>> getList(Map<String, Object> param);
	
	void createBoard(Map<String, Object> param);
	
	Map<String, Object> showBoard(int seq);
	void incViewCnt(int seq);
	
	void updateBoard(Map<String, Object> param);
	
	void deleteBoard(Integer[] chk);
	
	int boardsNum(Map<String, Object> param);

}
