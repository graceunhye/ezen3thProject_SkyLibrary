package com.skylibrary.vo;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Setter
@Getter
public class NoticeVO extends SessionVO {
	
	private String userID;
	private int    noticeNo;
	private String noticeTitle; 
	private String noticeBody; 
	private String noticeDate; 
	private int    noticeHit; 
	private String noticeFile; 
	private String fileName;
	private MultipartFile uploadFile;

	
	public void setNoticeDate(String noticeDate) {
		String[] array = noticeDate.split(" ");
		this.noticeDate = array[0];
	}

}
