package com.JoAri.CRUD.img;

import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

public interface ImgService {
	
	void uploadImg(List<MultipartFile> imgs, int boardSeq, ServletContext servletContext);
	
	List<Map<String, Object>> getImgList(int boardSeq);
	
	int getImgCount(int boardSeq);
	
	void downImg(int fileSeq, ModelAndView mav);

}
