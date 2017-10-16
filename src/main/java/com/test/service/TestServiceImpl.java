package com.test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.dao.TestDao;
import com.test.vo.StatusVO;
import com.test.vo.TestVO;


@Service
public class TestServiceImpl implements TestService {

	
	@Autowired
	private TestDao dao;
	
	public TestVO getData(String name) {
		return dao.getData(name);
	}

	@Override
	public int postData(String name, String content) {
		return dao.postData(name, content);
	}

	@Override
	public int putData(String name, String content) {
		return dao.putData(name, content);
	}

	@Override
	public int patchData(String name, String content) {
		return dao.patchData(name, content);
	}

	@Override
	public int deleteData(String id) {
		return dao.deleteData(id);
	}

}
