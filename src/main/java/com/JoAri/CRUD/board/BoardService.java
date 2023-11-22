package com.JoAri.CRUD.board;

import java.util.List;
import java.util.Map;

public interface BoardService {
	
	List<Map<String, Object>> getList();
	
	void createBoard(Map<String, Object> param);

}
