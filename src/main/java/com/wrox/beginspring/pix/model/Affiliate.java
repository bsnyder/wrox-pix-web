package com.wrox.beginspring.pix.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;


@Entity
public class Affiliate extends PixUser {

	private String companyName;

	private String faxNumber;

	private String websiteURL;

	
	
	public Affiliate(){
		
	}
	
	public Affiliate(String userName, String firstName, String lastName,
			String email, String password, String companyName,String faxNumber,
			String websiteURL) {
		super(userName,firstName,lastName,email,password);
		setCompanyName(companyName);
		setFaxNumber(faxNumber);
		setWebsiteURL(websiteURL);
		
	}
	
	
	
	/**
	 * @return  the companyName
	 * @uml.property  name="companyName"
	 */
	public String getCompanyName() {
		return companyName;
	}

	/**
	 * @param companyName  the companyName to set
	 * @uml.property  name="companyName"
	 */
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	/**
	 * @return  the faxNumber
	 * @uml.property  name="faxNumber"
	 */
	public String getFaxNumber() {
		return faxNumber;
	}

	/**
	 * @param faxNumber  the faxNumber to set
	 * @uml.property  name="faxNumber"
	 */
	public void setFaxNumber(String faxNumber) {
		this.faxNumber = faxNumber;
	}

	/**
	 * @return  the websiteURL
	 * @uml.property  name="websiteURL"
	 */
	public String getWebsiteURL() {
		return websiteURL;
	}

	
	/**
	 * @param websiteURL  the websiteURL to set
	 * @uml.property  name="websiteURL"
	 */
	public void setWebsiteURL(String websiteURL) {
		this.websiteURL = websiteURL;
	}

	

}
