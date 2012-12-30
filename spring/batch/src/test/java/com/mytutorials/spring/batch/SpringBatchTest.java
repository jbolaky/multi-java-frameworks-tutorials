package com.mytutorials.spring.batch;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.joda.time.LocalDate;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.jdbc.JdbcTestUtils;

import com.mytutorials.spring.batch.acl.api.BookStoreServiceAcl;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"classpath:/default-spring-batch-datasource-context.xml",
		"classpath:/default-spring-batch-test-context.xml" })
public class SpringBatchTest {

	@Autowired
	private JobLauncherTestUtils jobLauncherTestUtils;

	@Autowired
	private BookStoreServiceAcl bookStoreServiceAcl;

	private JdbcOperations jdbcOperations;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcOperations = new JdbcTemplate(dataSource);
	}

	@Before
	public void populateUsername() {

		Authentication authentication = new UsernamePasswordAuthenticationToken(
				"Javaid", "Jav");
		SecurityContextHolder.getContext().setAuthentication(authentication);

		JdbcTestUtils.executeSqlScript((JdbcTemplate) jdbcOperations,
				new ClassPathResource("schema-hsqldb.sql"), false);
	}

	@Test
	public void testSpringBatch() throws Exception {

		LocalDate today = new LocalDate();

		Map<String, JobParameter> parameters = new HashMap<String, JobParameter>();
		parameters.put("date", new JobParameter(today.toDate()));

		jobLauncherTestUtils.launchJob();

		// testing if data was successfully persisted by bookitemwriter
		int count = jdbcOperations
				.queryForInt("SELECT COUNT(ID) FROM BKS_BOOK where ID='1';");
		assertThat(count, is(1));
		count = jdbcOperations
				.queryForInt("SELECT COUNT(BOOK_ID) FROM BKS_AUTHOR where BOOK_ID='1';");
		assertThat(count, is(1));

		// testing if authoritemprocessor was working
		count = jdbcOperations
				.queryForInt("SELECT COUNT(BOOK_ID) FROM BKS_AUTHOR where FIRST_NAME='Javaid';");
		assertThat(count, is(0));
	}
}
