package com.JoAri.CRUD.img;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ImgDaoImpl implements ImgDao{

	@Autowired
	private SqlSessionTemplate sqlSession;
	
	@Override
	public void uploadImg(List<Map<String, Object>> imgList) {
		for(Map<String, Object> imgMap : imgList) {
			sqlSession.insert("ImgMapper.uploadImg", imgMap);
		}
	}
	
	@Override
	public List<Map<String, Object>> getImgList(int boardSeq){
		return sqlSession.selectList("ImgMapper.getImgList", boardSeq);
	}
	
	@Override
	public int getImgCount(int boardSeq) {
		return sqlSession.selectOne("ImgMapper.getImgCount", boardSeq);
	}
	
}
