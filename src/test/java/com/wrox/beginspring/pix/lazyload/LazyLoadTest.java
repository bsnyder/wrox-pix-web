package com.wrox.beginspring.pix.lazyload;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.springframework.orm.jpa.EntityManagerHolder;
import org.springframework.test.jpa.AbstractJpaTests;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import com.wrox.beginspring.pix.dao.UserRepository;
import com.wrox.beginspring.pix.model.Album;
import com.wrox.beginspring.pix.model.Picture;
import com.wrox.beginspring.pix.model.PixUser;

/*
 * This class tests out the lazy loading feature for Hibernate JPA Product.
 * This Test might not work will other JPA product as different
 * JPA product imply different lazy loading strategy.
 */
public class LazyLoadTest extends AbstractJpaTests {

	private UserRepository userDAO;

	private static boolean skipSetupBeforeTransaction = true;

	private PixUser testUser1 = new PixUser("user1", "firstname1", "lastName1",
			"email1", "password1");

	private static final String[] configLocations = new String[] {
			"persistenceContext.xml", "persistenceContext-test.xml" };

	@Override
	protected String[] getConfigLocations() {
		return configLocations;
	}

	protected void onSetUpBeforeTransaction() throws Exception {

		if (!skipSetupBeforeTransaction) {

			entityManagerFactory = (EntityManagerFactory) applicationContext
					.getBean("entityManagerFactory");

			EntityManager manager = entityManagerFactory.createEntityManager();

			TransactionSynchronizationManager.bindResource(
					entityManagerFactory, new EntityManagerHolder(manager));
		}

	}

	protected void onTearDownAfterTransaction() throws Exception {

		if (!skipSetupBeforeTransaction) {
			entityManagerFactory = (EntityManagerFactory) applicationContext
					.getBean("entityManagerFactory");

			EntityManagerHolder emHolder = (EntityManagerHolder) TransactionSynchronizationManager
					.unbindResource(entityManagerFactory);
			emHolder.getEntityManager().close();
		}
		skipSetupBeforeTransaction = false;

	}

	private void populateAlbumObject() {
		Album album = new Album();
		album.setDescription("This is my album");
		Picture pic = new Picture();
		album.addNewPicture(pic);
		testUser1.addAlbum(album);

	}

	public void testLazyLoadingFailuretRetrieveUserByUserName() {

		// Populate user and album object
		populateAlbumObject();

		// Persist User.
		userDAO.persistUser(testUser1);

		// Commit the transaction and records.
		setComplete();

		// End transaction.
		endTransaction();

		// Start new transaction.
		startNewTransaction();

		//Retreive user and album information inserted earlier
		PixUser ps = userDAO.retreiveUserByUserName(testUser1.getUserName());
		endTransaction();

		try {

			// This will throw lazy load exception in case of hibernate.
			ps.getAlbums().get(0).getPictures().get(0).getName();

		} catch (Exception e) {

			if (checkForHibernateJPA()) {
				assertEquals(e.getClass().getName(),
						"org.hibernate.LazyInitializationException");
			}

		}

		startNewTransaction();

		//clean up records.
		cleanUpDatabaseTables();

		// Commit the transaction and records.
		setComplete();

		// End transaction.
		endTransaction();

	}

	public void testLazyLoadingRetrieveUserByUserName() {

		// Populate user and album object
		populateAlbumObject();

		// Persist User.
		userDAO.persistUser(testUser1);

		// Commit the transaction and records.
		setComplete();

		// End transaction.
		endTransaction();

		// Start new transaction.
		startNewTransaction();

		PixUser ps = userDAO.retreiveUserByUserName(testUser1.getUserName());
		endTransaction();

		ps.getAlbums().get(0).getPictures();

		startNewTransaction();

		cleanUpDatabaseTables();

		// Commit the transaction and records.
		setComplete();

		// End transaction.
		endTransaction();

	}

	private void cleanUpDatabaseTables() {
		PixUser user = userDAO.retreiveUserByUserName(testUser1.getUserName());
		userDAO.deleteUser(user);

	}

	public UserRepository getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(UserRepository userDAO) {
		this.userDAO = userDAO;
	}

	private boolean checkForHibernateJPA() {

		int hib = sharedEntityManager.getDelegate().getClass().getName()
				.indexOf("hibernate");
		if (hib > 0) {
			return true;
		}

		return false;

	}

}
