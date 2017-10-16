package com.test.dao;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.test.vo.TestVO;

@Repository
public class TestDaoImpl implements TestDao{
	
	@Autowired
	private DataSource dataSource;
	
	private JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
	
	public TestVO getData(String name) {
		return (TestVO) jdbcTemplate.query("SELECT ID, CONTENT FROM BOOK WHERE NAME = ? ",
				ps -> {	ps.setString(1, name); }, 
				(rs, rowNum) ->  new TestVO(rs.getLong(1), rs.getString(2)));
	}
	
	@Override
	public int postData(String name, String content) {
		return jdbcTemplate.update("INSERT INTO BOOK(NAME,CONTENT) VALUES(?,?)",
				ps -> {	ps.setString(1, name);ps.setString(1, content); });
	}

	@Override
	public int putData(String name, String content) {
		if(getData(name) != null){
			return jdbcTemplate.update("UPDATE BOOK SET CONTENT = ? WHERE NAME = ?",
					ps -> {	ps.setString(1, content);ps.setString(1, name); });			
		}else{
			return jdbcTemplate.update("INSERT INTO BOOK(NAME,CONTENT) VALUES(?,?)",
					ps -> {	ps.setString(1, name);ps.setString(1, content); });			
		}
	}

	@Override
	public int patchData(String name, String content) {
		return jdbcTemplate.update("UPDATE BOOK SET CONTENT = ? WHERE NAME = ?",
				ps -> {	ps.setString(1, content);ps.setString(1, name); });	
	}

	@Override
	public int deleteData(String id) {
		return jdbcTemplate.update("DELETE FROM BOOK WHERE id = ?",
				ps -> {	ps.setString(1, id); });	
	}
	
	
	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

}
