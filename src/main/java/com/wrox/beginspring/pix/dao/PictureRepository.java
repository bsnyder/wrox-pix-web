package com.wrox.beginspring.pix.dao;

import com.wrox.beginspring.pix.model.Picture;
import com.wrox.beginspring.pix.web.PictureUpload;

public interface PictureRepository {

	public enum UploadStatus {
		SUCCESS, EXISTS, INVALID, FAILED
	}

	public void deletePicture(Picture pictureToDelete);

	public UploadStatus storePicture(PictureUpload picture);

	public void rotatePictureLeft(Picture picture);

	public void rotatePictureRight(Picture picture);

}
