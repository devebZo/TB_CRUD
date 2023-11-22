package com.JoAri.CRUD.board;

import java.util.Date;

public class BoardVO {
	
	private Integer boardSeq;
	private String writer;
	private String title;
	private String content;
	private Date regDate;
	private Date uptDate;
	private Integer viewCnt;
	public Integer getBoardSeq() {
		return boardSeq;
	}
	public void setBoardSeq(Integer boardSeq) {
		this.boardSeq = boardSeq;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	public Date getUptDate() {
		return uptDate;
	}
	public void setUptDate(Date uptDate) {
		this.uptDate = uptDate;
	}
	public Integer getViewCnt() {
		return viewCnt;
	}
	public void setViewCnt(Integer viewCnt) {
		this.viewCnt = viewCnt;
	}

}
