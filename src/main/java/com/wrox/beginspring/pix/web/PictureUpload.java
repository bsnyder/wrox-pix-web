package com.wrox.beginspring.pix.web;

import org.springframework.web.multipart.MultipartFile;

import com.wrox.beginspring.pix.model.Picture;

public class PictureUpload {

	private Integer albumId;

	private MultipartFile upload;

	private Picture picture;

	public Integer getAlbumId() {
		return albumId;
	}

	public void setAlbumId(Integer albumId) {
		this.albumId = albumId;
	}

	public MultipartFile getUpload() {
		return upload;
	}

	public void setUpload(MultipartFile pictureUpload) {
		this.upload = pictureUpload;
	}

	public Picture getPicture() {
		return picture;
	}

	public void setPicture(Picture picture) {
		this.picture = picture;
	}

}
