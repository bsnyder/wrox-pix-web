package com.wrox.beginspring.pix.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("OFFICE")
public class OfficeAlbum extends Album {
	
	public OfficeAlbum() {
	}

	public OfficeAlbum(String name) {
		super(name);
	}

}
