package com.skylibrary.vo;

<<<<<<< HEAD
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class NoticeVO extends SessionVO {
	private int noticeNo; 
	private String userID;
	private String noticeTitle; 
	private String noticeBody; 
	private String noticeDate;
	private int noticeHit;
	private String noticeFile;
	
//	
//	public int getNoticeNo() {
//		return noticeNo;
//	}
//	public void setNoticeNo(int noticeNo) {
//		this.noticeNo = noticeNo;
//	}
//	public String getManagerID() {
//		return managerID;
//	}
//	public void setManagerID(String managerID) {
//		this.managerID = managerID;
//	}
//	public String getNoticeTitle() {
//		return noticeTitle;
//	}
//	public void setNoticeTitle(String noticeTitle) {
//		this.noticeTitle = noticeTitle;
//	}
//	public String getNoticeBody() {
//		return noticeBody;
//	}
//	public void setNoticeBody(String noticeBody) {
//		this.noticeBody = noticeBody;
//	}
//	public String getNoticeDate() {
//		return noticeDate;
//	}
=======
import org.springframework.web.multipart.MultipartFile;

public class NoticeVO extends SessionVO {
	private int noticeNo; //������ȣ
	private String noticeTitle; //��������
	private String noticeBody; //��������
	private String noticeDate; //������¥
	private int noticeHit; //���� ��ȸ��
	private String noticeFile; //÷������
	
	private String userID;        //ȸ�� ���̵�
	
	//���� ���ε�
	private String fileName;
	private MultipartFile uploadFile;
	
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public MultipartFile getUploadFile() {
		return uploadFile;
	}
	public void setUploadFile(MultipartFile uploadFile) {
		this.uploadFile = uploadFile;
	}
	public int getNoticeNo() {
		return noticeNo;
	}
	public void setNoticeNo(int noticeNo) {
		this.noticeNo = noticeNo;
	}
	public String getNoticeTitle() {
		return noticeTitle;
	}
	public void setNoticeTitle(String noticeTitle) {
		this.noticeTitle = noticeTitle;
	}
	public String getNoticeBody() {
		return noticeBody;
	}
	public void setNoticeBody(String noticeBody) {
		this.noticeBody = noticeBody;
	}
	public int getNoticeHit() {
		return noticeHit;
	}
	public void setNoticeHit(int noticeHit) {
		this.noticeHit = noticeHit;
	}
	public String getNoticeFile() {
		return noticeFile;
	}
	public void setNoticeFile(String noticeFile) {
		this.noticeFile = noticeFile;
	}
	public String getNoticeDate() {
		return noticeDate;
	}
>>>>>>> da08ed998bdb39dcf4778bf7528d74a6046dfc40
	public void setNoticeDate(String noticeDate) {
		//noticeDate �� timestamp �����̶�
		//yyyy-MM-dd hh:mm:ss ���·� ���� �޾ƿ�.
		//"����"�� �������� �߶� �迭�� �־��ش�.
		//array[0] = yyyy-MM-dd
		//array[1] = hh:mm:ss
		//array[0] = yyyy-MM-dd �� this.noticeDate�� �����Ѵ�.
		String[] array = noticeDate.split(" ");
		this.noticeDate = array[0];
	}
<<<<<<< HEAD
//	public int getNoticeHit() {
//		return noticeHit;
//	}
//	public void setNoticeHit(int noticeHit) {
//		this.noticeHit = noticeHit;
//	}
//	public String getNoticeFile() {
//		return noticeFile;
//	}
//	public void setNoticeFile(String noticeFile) {
//		this.noticeFile = noticeFile;
//	}
=======
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	
	@Override
	public String toString() {
		return "NoticeVO [noticeNo=" + noticeNo + ", noticeTitle=" + noticeTitle + ", noticeBody=" + noticeBody
				+ ", noticeDate=" + noticeDate + ", noticeHit=" + noticeHit + ", noticeFile=" + noticeFile
				+ ", getNoticeNo()=" + getNoticeNo() + ", getNoticeTitle()=" + getNoticeTitle() + ", getNoticeBody()="
				+ getNoticeBody() + ", getNoticeHit()=" + getNoticeHit() + ", getNoticeFile()=" + getNoticeFile()
				+ ", getNoticeDate()=" + getNoticeDate() + ", getUserID()=" + getUserID() + ", getUserPW()="
				+ getUserPW() + ", getCheck()=" + getCheck() + ", getClass()=" + getClass() + ", hashCode()="
				+ hashCode() + ", toString()=" + super.toString() + "]";
	}
>>>>>>> da08ed998bdb39dcf4778bf7528d74a6046dfc40
	
	
}
