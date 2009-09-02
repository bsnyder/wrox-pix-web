package com.wrox.beginspring.pix.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("HOLIDAY")
public class HolidayAlbum extends Album {
	

	public HolidayAlbum() {
	}

	public HolidayAlbum(String name) {
		super(name);
	}

}
