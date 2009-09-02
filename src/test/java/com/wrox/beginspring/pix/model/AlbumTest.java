package com.wrox.beginspring.pix.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import junit.framework.TestCase;

public class AlbumTest extends TestCase {
	private PixUser [] testUsers;
    private Album [] testAlbums;
    
	protected void setUp() throws Exception {
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
		
		testAlbums = new Album[2];
		testAlbums[0] =
			new Album("first album");
		testAlbums[1] =
			new Album("second album");
		testAlbums[0].addUser(testUsers[0]);
		testAlbums[1].addUser(testUsers[1]);
		
		Calendar cal = Calendar.getInstance();
		cal.set(2008,8,8,12,0,0);
		testAlbums[0].setCreationDate(cal.getTime());
		cal.set(2008,8,11,4,0,0);
		testAlbums[1].setCreationDate(cal.getTime());

	}
	public void testDummy() {
		
	}
	
}
