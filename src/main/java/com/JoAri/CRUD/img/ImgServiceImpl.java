package com.JoAri.CRUD.img;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ImgServiceImpl implements ImgService{
	
	@Autowired
	private ImgDao imgDao;
	
	@Override
	public void uploadImg(List<MultipartFile> imgs, int boardSeq) {
		List<Map<String, Object>> imgList = new ArrayList<Map<String,Object>>();
		
		for(MultipartFile file : imgs) {
			String ariginalFileName = file.getOriginalFilename();
			String extension = ariginalFileName.substring(ariginalFileName.lastIndexOf("."));
			String savedFileName = UUID.randomUUID().toString()+extension;
			
			Map<String, Object> imgMap = new HashMap<String, Object>();
			imgMap.put("saveName", savedFileName);
			imgMap.put("realName", ariginalFileName);
			imgMap.put("boardSeq", boardSeq);
			
			FileUpload fileUpload = new FileUpload();
			fileUpload.fileUpload(file, savedFileName);
			
			imgMap.put("savePath", fileUpload.getUploadPath());
			
			imgList.add(imgMap);
		}
		
		imgDao.uploadImg(imgList);
	}
	
	@Override
	public List<Map<String, Object>> getImgList(int boardSeq){
		return imgDao.getImgList(boardSeq);
	}
	
	@Override
	public int getImgCount(int boardSeq) {
		return imgDao.getImgCount(boardSeq);
	}

}
