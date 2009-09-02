package com.wrox.beginspring.pix.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class PixUser implements Serializable {

	@Id
	private String userName;

	private String firstName;

	private String lastName;

	private String email;

	private String password;

	@OneToMany(fetch=FetchType.LAZY,cascade = CascadeType.ALL,mappedBy="user")
	private List<Album> albums = new ArrayList<Album>();

	@OneToMany(fetch=FetchType.LAZY,cascade = CascadeType.ALL)
	private List<Comment> comments = new ArrayList<Comment>();

	public PixUser() {

	}

	public PixUser(String userName, String firstName, String lastName,
			String email, String password) {
		super();
		this.userName = userName;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
	}

	@Transient
	public void addAlbum(Album albumToAdd) {
		albumToAdd.setUser(this);
		getAlbums().add(albumToAdd);
	}

	/**
	 * @return the albums
	 * @uml.property name="albums"
	 */
	public List<Album> getAlbums() {
		return albums;
	}

	/**
	 * @param albums
	 *            the albums to set
	 * @uml.property name="albums"
	 */
	public void setAlbums(List<Album> albums) {
		this.albums = albums;
	}

	/**
	 * @return the email
	 * @uml.property name="email"
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            the email to set
	 * @uml.property name="email"
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the firstName
	 * @uml.property name="firstName"
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName
	 *            the firstName to set
	 * @uml.property name="firstName"
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 * @uml.property name="lastName"
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName
	 *            the lastName to set
	 * @uml.property name="lastName"
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the password
	 * @uml.property name="password"
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            the password to set
	 * @uml.property name="password"
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the userName
	 * @uml.property name="userName"
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName
	 *            the userName to set
	 * @uml.property name="userName"
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return the comments
	 * @uml.property name="comments"
	 */
	public List<Comment> getComments() {
		return comments;
	}

	public void addComment(Comment comment) {
		comments.add(comment);
	}

	@Override
	public int hashCode() {
		final int PRIME = 31;
		int result = 1;
		result = PRIME * result + ((email == null) ? 0 : email.hashCode());
		result = PRIME * result
				+ ((firstName == null) ? 0 : firstName.hashCode());
		result = PRIME * result
				+ ((lastName == null) ? 0 : lastName.hashCode());
		result = PRIME * result
				+ ((password == null) ? 0 : password.hashCode());
		result = PRIME * result
				+ ((userName == null) ? 0 : userName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final PixUser other = (PixUser) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (userName == null) {
			if (other.userName != null)
				return false;
		} else if (!userName.equals(other.userName))
			return false;
		return true;
	}
	
	public String toString() {
	   return ToStringBuilder.reflectionToString( this, ToStringStyle.MULTI_LINE_STYLE );
	}
}
