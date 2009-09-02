package com.wrox.beginspring.pix.persistence;

import javax.sql.DataSource;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;


public class JPAPersistenceTest {

	/*
	 * Remove the persistenceContext-test.xml if the test needs to be 
	 * executed against actual local database.
	 * persistenceContext-test.xml overrides the datasource definition in 
	 * persistenceContext.xml to use in memory hsql database.  
	 */
	private static final String[] configLocations = new String[] { "" +
			"persistenceContext.xml",
			"persistenceContext-test.xml"};

	public static void main(String[] args) {

		//Load the Spring Configuration.
		ClassPathXmlApplicationContext context = 
			new ClassPathXmlApplicationContext(
				configLocations);
		
		
		//Create JDBCTemplate from datasource.
		DataSource datsSource = (DataSource)
			context.getBean("dataSource");
		JdbcTemplate jdbcTemplate = new JdbcTemplate(datsSource);
		
				
		jdbcTemplate.execute("select * from pixuser");
		jdbcTemplate.execute("select * from album");
		jdbcTemplate.execute("select * from affiliate");
		jdbcTemplate.execute("select * from picture");
		jdbcTemplate.execute("select * from comment");

		//Association tables
		jdbcTemplate.execute("select * from album_picture");
		jdbcTemplate.execute("select * from picture_comment");	
	}
	
	



}
