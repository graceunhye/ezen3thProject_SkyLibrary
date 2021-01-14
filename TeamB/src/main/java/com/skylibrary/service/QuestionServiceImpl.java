package com.skylibrary.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.skylibrary.dao.QuestionDAO;
import com.skylibrary.vo.QuestionVO;
import com.skylibrary.vo.SearchVO;

@Service
public class QuestionServiceImpl implements QuestionService {
	@Inject
	QuestionDAO dao;
	
	@Override
	public List<QuestionVO> list(SearchVO vo) throws Exception {
		return dao.list(vo);
	}

	@Override
	public QuestionVO qView(QuestionVO vo) throws Exception {
		return dao.qView(vo);
	}
	
	@Override
	public int countList() throws Exception {
		return dao.countList();
	}
}