package com.wrox.beginspring.pix.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;


@Entity
@DiscriminatorValue("PERSONAL")

public class PersonalAlbum extends Album {
	
	public PersonalAlbum() {
	}

	public PersonalAlbum(String name) {
		super(name);
	}

}
