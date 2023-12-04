package com.JoAri.CRUD.img;

import java.util.List;
import java.util.Map;

public interface ImgDao {
	
	void uploadImg(List<Map<String, Object>> imgList);
	
	List<Map<String, Object>> getImgList(int boardSeq);
	
	int getImgCount(int boardSeq);

}
