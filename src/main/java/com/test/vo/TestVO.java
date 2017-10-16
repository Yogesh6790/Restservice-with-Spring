package com.test.vo;

public class TestVO {
	private final long id;
	private final String content;

	public TestVO(long id, String content) {
		this.id = id;
		this.content = content;
	}

	public long getId() {
		return id;
	}

	public String getContent() {
		return content;
	}
}
