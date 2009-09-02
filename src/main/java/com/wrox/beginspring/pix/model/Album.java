package com.wrox.beginspring.pix.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.commons.lang.builder.ToStringBuilder;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorValue("ALBUM")
@DiscriminatorColumn(name = "DTYPE")
@NamedQueries( {
		@NamedQuery(name = "userAlbums", query = "select a from Album a where a.user.userName = ?1"),
		@NamedQuery(name = "allAlbums", query = "select a from Album a") })
public class Album implements Serializable{

	@Id
	@GeneratedValue
	private Integer id;

    // eagar fetching for web services and other remoting such as DWR
	// default is lasy loading
	
	@ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
	private PixUser user;

	private String name;

	private String description;

	@Temporal(TemporalType.TIMESTAMP)
	private Date creationDate = new Date();
	
	private String[] labels;

	
	// eagar fetching for web services and other remoting such as DWR
	// default is lasy loading
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Picture> pictures = new ArrayList<Picture>();

	public Album() {
	}

	public Album(String name) {
		this.name = name;
	}

	public void addNewPicture(Picture picture) {
		picture.setAlbum(this);
		pictures.add(picture);
	}

	public void addUser(PixUser userToAdd) {
		userToAdd.addAlbum(this);
		setUser(userToAdd);
	}

	/**
	 * @return  the creationDate
	 * @uml.property  name="creationDate"
	 */
	public Date getCreationDate() {
		return creationDate;
	}

	/**
	 * @param creationDate  the creationDate to set
	 * @uml.property  name="creationDate"
	 */
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	/**
	 * @return  the description
	 * @uml.property  name="description"
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description  the description to set
	 * @uml.property  name="description"
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return  the id
	 * @uml.property  name="id"
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id  the id to set
	 * @uml.property  name="id"
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return  the name
	 * @uml.property  name="name"
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name  the name to set
	 * @uml.property  name="name"
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return  the pictures
	 * @uml.property  name="pictures"
	 */
	public List<Picture> getPictures() {
		return pictures;
	}

	/**
	 * @return  the user
	 * @uml.property  name="user"
	 */
	public PixUser getUser() {
		return user;
	}

	/**
	 * @param user  the user to set
	 * @uml.property  name="user"
	 */
	public void setUser(PixUser user) {
		this.user = user;
	}

	public boolean getIsNew() {
		return getId() == null;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	@Override
	public int hashCode() {
		final int PRIME = 31;
		int result = super.hashCode();
		result = PRIME * result + ((creationDate == null) ? 0 : creationDate.hashCode());
		result = PRIME * result + ((description == null) ? 0 : description.hashCode());
		result = PRIME * result + ((id == null) ? 0 : id.hashCode());
		result = PRIME * result + Arrays.hashCode(labels);
		result = PRIME * result + ((name == null) ? 0 : name.hashCode());
		result = PRIME * result + ((user == null) ? 0 : user.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		final Album other = (Album) obj;
		if (creationDate == null) {
			if (other.creationDate != null)
				return false;
		} else if (!creationDate.equals(other.creationDate))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (!Arrays.equals(labels, other.labels))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}

	public String[] getLabels() {
		return labels;
	}

	public void setLabels(String[] labels) {
		this.labels = labels;
	}

}
