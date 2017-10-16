package com.test.controller;

import java.io.IOException;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.service.TestService;
import com.test.vo.StatusVO;
import com.test.vo.TestVO;

@RestController
public class TestController {

	@Autowired
	private TestService service;

	@RequestMapping(value = "/getData/{name}", method = RequestMethod.GET, produces = "application/json")
	public TestVO getData(@PathVariable("name") String name) {
		System.out.println(name);
		return service.getData(name);
	}

	@RequestMapping(value = "/postData", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public StatusVO postData(HttpEntity<String> httpEntity) {
		StatusVO vo;
		try {
			String json = httpEntity.getBody();
			HashMap<String, String> result;
			result = new ObjectMapper().readValue(json, HashMap.class);
			String name = result.get("name");
			String content = result.get("content");
			if (service.postData(name, content) == 1) {
				vo = new StatusVO(true, "");
			} else {
				vo = new StatusVO(false, "Error in posting data");
			}
		} catch (Exception e) {
			vo = new StatusVO(false, e.getMessage());
		}
		return vo;
	}

	@RequestMapping(value = "/putData", method = RequestMethod.PUT, produces = "application/json", consumes = "application/json")
	public StatusVO putData(HttpEntity<String> httpEntity) {
		StatusVO vo;
		try {
			String json = httpEntity.getBody();
			HashMap<String, String> result;
			result = new ObjectMapper().readValue(json, HashMap.class);
			String name = result.get("name");
			String content = result.get("content");
			if (service.putData(name, content) == 1) {
				vo = new StatusVO(true, "");
			} else {
				vo = new StatusVO(false, "Error in putting data");
			}
		} catch (Exception e) {
			vo = new StatusVO(false, e.getMessage());
		}
		return vo;
	}

	@RequestMapping(value = "/patchData", method = RequestMethod.PATCH, produces = "application/json", consumes = "application/json")
	public StatusVO patchData(HttpEntity<String> httpEntity) {
		StatusVO vo;
		try {
			String json = httpEntity.getBody();
			HashMap<String, String> result;
			result = new ObjectMapper().readValue(json, HashMap.class);
			String name = result.get("name");
			String content = result.get("content");
			if (service.patchData(name, content) == 1) {
				vo = new StatusVO(true, "");
			} else {
				vo = new StatusVO(false, "Error in patching data");
			}
		} catch (IOException e) {
			vo = new StatusVO(false, e.getMessage());
		}
		return vo;
	}

	@RequestMapping(value = "/deleteData/{id}", method = RequestMethod.DELETE, produces = "application/json", consumes = "application/json")
	public StatusVO deleteData(@PathVariable("id") String id) {
		System.out.println(id);
		StatusVO vo;
		if (service.deleteData(id) == 1) {
			vo = new StatusVO(true, "");
		} else {
			vo = new StatusVO(false, "Error in deleting data");
		}
		return vo;
	}

}
