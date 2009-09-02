package com.wrox.beginspring.pix.model;

import java.util.ArrayList;

import junit.framework.TestCase;

public class PixUserTest extends TestCase {

       private PixUser [] testUsers;
		
		public void setUp() throws Exception {
			super.setUp();
			testUsers = new PixUser[2];
		    testUsers[0] =
		    	new PixUser("joes", "joe", "smoe",
		    		"joes@natat.com", "pass1"	
		    			);
		    testUsers[1] =
		    	new PixUser("johnm", "john", "mola",
		    		"johnm@dist.com", "pass2"
		    			)
		    	;
		
		}

		public void testFields() {
		 PixUser testUser = testUsers[0];
		 assert(testUser.getUserName().equals("joes"));
		 assert(testUser.getFirstName().equals("joe"));
		 assert(testUser.getLastName().equals("smoe"));
		 assert(testUser.getEmail().equals("joes@natat.com"));
		 assert(testUser.getPassword().equals("pass1"));
		 testUser = testUsers[1];
		 assert(testUser.getUserName().equals("johnm"));
		 assert(testUser.getFirstName().equals("john"));
		 assert(testUser.getLastName().equals("mola"));
		 assert(testUser.getEmail().equals("johnm@dist.com"));
		 assert(testUser.getPassword().equals("pass2"));
		}
		public void testEqual() {
		 PixUser newUser = new PixUser("joes", "joe", "smoe",
		    		"joes@natat.com", "pass1"	
			);
		 assert(newUser.equals(testUsers[0]));
			
		 newUser = new PixUser("johnm", "john", "mola",
		    		"johnm@dist.com", "pass2");
		 
		 assert(newUser.equals(testUsers[1]));
		
		}
		
		public void testAlbums() {
			Album a1 = new Album("2008 Trip");
			testUsers[0].addAlbum(a1);
			Album a2 = new Album("Junkaroo");
			Album a3 = new Album("ScubaDive");
			testUsers[1].addAlbum(a2);
			testUsers[1].addAlbum(a3);
			
			// albums can be retreived
			assert(testUsers[0].getAlbums().size() == 1);
			assert(testUsers[1].getAlbums().size() == 2);
			
			// albums does not affect user identify
			PixUser newUser = new PixUser("joes", "joe", "smoe",
			    		"joes@natat.com", "pass1");
			 newUser.addAlbum(new Album("Does not match"));
			 assert(newUser.equals(testUsers[0]));
			
			 
			
		}
		
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

