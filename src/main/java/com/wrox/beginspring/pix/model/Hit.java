package com.wrox.beginspring.pix.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Hit implements Serializable {
	@Id
	private long id;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateOfHit = new Date();
	
	private boolean resultedInNewRegistration;
	
	

	public Hit(Date dateOfHit, boolean resultedInNewRegistration) {
		super();
		this.dateOfHit = dateOfHit;
		this.resultedInNewRegistration = resultedInNewRegistration;
	}

	public Date getDateOfHit() {
		return dateOfHit;
	}

	public void setDateOfHit(Date dateOfHit) {
		this.dateOfHit = dateOfHit;
	}

	public boolean isResultedInNewRegistration() {
		return resultedInNewRegistration;
	}

	public void setResultedInNewRegistration(boolean resultedInNewRegistration) {
		this.resultedInNewRegistration = resultedInNewRegistration;
	}
	
	
	
	
}
