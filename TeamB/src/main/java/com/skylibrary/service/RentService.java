package com.skylibrary.service;

import java.util.List;
import java.util.Map;


import com.skylibrary.vo.BookVO;
import com.skylibrary.vo.RentVO;
import com.skylibrary.vo.SessionVO;

public interface RentService {

	public List<RentVO> Rentlist(RentVO vo) throws Exception;
	
	public List<Map<String,String>> userRentInfo(SessionVO vo) throws Exception;
	
	public int extensionBook(RentVO vo) throws Exception;
	
	public RentVO extenstionSelect(RentVO vo) throws Exception;
	
	public int updateBook(BookVO vo) throws Exception;
	
	public int deleteRent(RentVO vo) throws Exception;
}
