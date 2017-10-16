package com.test.dao;

import com.test.vo.TestVO;

public interface TestDao {
	
	TestVO getData(String name);
	
	int postData(String name, String content);
	
	int putData(String name, String content);

	int patchData(String name, String content);
	
	int deleteData(String id);

}
