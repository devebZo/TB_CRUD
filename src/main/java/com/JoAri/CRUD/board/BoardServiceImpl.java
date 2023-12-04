package com.JoAri.CRUD.board;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.swing.plaf.multi.MultiFileChooserUI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.JoAri.CRUD.img.FileUpload;
import com.JoAri.CRUD.img.ImgService;

@Service
public class BoardServiceImpl implements BoardService{
	
	@Autowired
	private BoardDao boardDao;
	
	@Autowired
	private ImgService imgSer;
	
	@Override
	public List<Map<String, Object>> getList(Map<String, Object> param){
		return boardDao.getList(param);
	}
	
	@Override
	public void createBoard(Map<String, Object> param) {
		Map<String, Object> boardMap = new HashMap<String, Object>();
		
		boardMap.put("writer", param.get("writer"));
		boardMap.put("title", param.get("title"));
		boardMap.put("content", param.get("content"));
		
		int boardSeq = boardDao.getMaxSeq();
		
		boardDao.createBoard(param);
		
		@SuppressWarnings("unchecked")
		List<MultipartFile> imgFiles = (List<MultipartFile>) param.get("imgs");
		imgSer.uploadImg(imgFiles, boardSeq);
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
	
	@Override
	public void deleteBoard(Integer[] chk) {
		boardDao.deleteBoard(chk);
	}
	
	@Override
	public int boardsNum(Map<String, Object> param) {
		return boardDao.boardsNum(param);
	}
	
}
