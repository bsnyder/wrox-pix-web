package com.wrox.beginspring.pix.dao;

import org.springframework.test.jpa.AbstractJpaTests;

import com.wrox.beginspring.pix.model.Affiliate;
import com.wrox.beginspring.pix.model.PixUser;

public class UserJpaRepositoryTest extends AbstractJpaTests {

	private UserRepository userRepo;

	private static final String[] configLocations = new String[] {
			"persistenceContext.xml",
			"persistenceContext-test.xml" };

	protected String[] getConfigLocations() {

		return configLocations;
	}

	private PixUser testUser1 = new PixUser("user1", "firstname1", "lastName1",
			"email1", "password1");

	private PixUser testUser2 = new PixUser("user2", "firstname2", "lastName2",
			"email2", "password2");

	private Affiliate affiliate1 = new Affiliate("affiliate1", "affname1",
			"afflname1", "email3", "password3", "mysurf", "569-008-0909",
			"http://mysurf.com");

	@Override
	protected void onSetUpInTransaction() throws Exception {

		populateUsers();
		populateAffiliates();

	}

	public void populateUsers() {
		userRepo.persistUser(testUser1);
		userRepo.persistUser(testUser2);
	}

	public void populateAffiliates() {
		userRepo.persistUser(affiliate1);

	}

	public void testRetrieveUserByUserName() {

		assertEquals("user1", userRepo.retreiveUserByUserName("user1")
				.getUserName());
		assertEquals("user2", userRepo.retreiveUserByUserName("user2")
				.getUserName());

	}

	public void testAffiliateUserByUserName() {
		assertEquals("affiliate1", userRepo.retreiveUserByUserName("affiliate1")
				.getUserName());

	}

	public void setUserDAO(UserRepository userDAO) {
		this.userRepo = userDAO;
	}

}
