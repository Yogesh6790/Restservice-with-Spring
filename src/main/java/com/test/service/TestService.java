package com.test.service;

import com.test.vo.StatusVO;
import com.test.vo.TestVO;

public interface TestService {
	
	TestVO getData(String name);
	
	int postData(String name, String content);
	
	int putData(String name, String content);

	int patchData(String name, String content);
	
	int deleteData(String id);
}
