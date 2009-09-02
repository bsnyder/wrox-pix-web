package com.wrox.beginspring.pix.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @author  wsemp2
 */
@Entity
public class Comment {
	
	@Id
	@GeneratedValue
	private Integer id;
	
	private String comment;

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
	 * @return  the comment
	 * @uml.property  name="comment"
	 */
	public String getComment() {
		return comment;
	}

	/**
	 * @param comment  the comment to set
	 * @uml.property  name="comment"
	 */
	public void setComment(String comment) {
		this.comment = comment;
	}
	

	@Override
	public int hashCode() {
		final int PRIME = 31;
		int result = 1;
		result = PRIME * result + ((comment == null) ? 0 : comment.hashCode());
		result = PRIME * result + ((id == null) ? 0 : id.hashCode());
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
		final Comment other = (Comment) obj;
		if (comment == null) {
			if (other.comment != null)
				return false;
		} else if (!comment.equals(other.comment))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
