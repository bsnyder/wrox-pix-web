package com.wrox.beginspring.pix.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

@Entity
@NamedQueries( { @NamedQuery(name = "deletePicture", query = "delete from Picture p where p.id = ?1") })
public class Picture implements Serializable {

	private static final long serialVersionUID = -4491601142208084597L;

	@Id
	@GeneratedValue
	private Integer id;

	private String name;

	private String description;

	private long size;

	// eagar fetching for web services and other remoting such as DWR
	// default is lasy loading, optimized for web app access

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<Comment> comments = new HashSet<Comment>();

	private Album album;

	private String fileName;

	private String path;

	public Picture() {
	}

	public Picture(String name, String location) {
		this.name = name;
		this.path = location;
	}

	/**
	 * @return the id
	 * @uml.property name="id"
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 * @uml.property name="id"
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the name
	 * @uml.property name="name"
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 * @uml.property name="name"
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the size
	 * @uml.property name="size"
	 */
	public long getSize() {
		return size;
	}

	/**
	 * @param size
	 *            the size to set
	 * @uml.property name="size"
	 */
	public void setSize(long size) {
		this.size = size;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<Comment> getComments() {
		return comments;
	}

	public void setComments(Set<Comment> comments) {
		this.comments = comments;
	}

	public Album getAlbum() {
		return album;
	}

	public void setAlbum(Album album) {
		this.album = album;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String location) {
		this.path = location;
	}

	@Override
	public int hashCode() {
		final int PRIME = 31;
		int result = 1;
		result = PRIME * result
				+ ((description == null) ? 0 : description.hashCode());
		result = PRIME * result + ((id == null) ? 0 : id.hashCode());
		result = PRIME * result + ((name == null) ? 0 : name.hashCode());
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
		final Picture other = (Picture) obj;
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
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getLocation() {
		String location = this.path + this.fileName;
		return location.replace('\\', '/');
	}

	public String getFileLocation() {
		return System.getProperty("webapp.root") + getLocation();
	}

}
