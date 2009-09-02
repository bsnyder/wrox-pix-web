package com.wrox.beginspring.pix.dao;

import java.io.IOException;

import javax.servlet.ServletContext;

import junit.framework.TestCase;

import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import com.wrox.beginspring.pix.dao.PictureRepository.UploadStatus;
import com.wrox.beginspring.pix.model.Picture;
import com.wrox.beginspring.pix.web.PictureUpload;

public class PictureFileRepositoryTest extends TestCase {

	private PictureFileRepository pictureRepo;

	@Override
	protected void setUp() throws Exception {
		pictureRepo = new PictureFileRepository();
		pictureRepo.setRepositoryRoot(System.getProperty("java.io.tmpdir"));
	}

	public void testUploadPicture() throws IOException {
		PictureUpload upload = new PictureUpload();
		upload.setAlbumId(1);
		MultipartFile file = new MockMultipartFile("spring.jpg",
				new DefaultResourceLoader().getResource("spring.jpg")
						.getInputStream());
		upload.setUpload(file);

		UploadStatus status = pictureRepo.storePicture(upload);
		assertTrue("Upload succeeded.", status.equals(UploadStatus.SUCCESS));
	}

	public void testUploadInvalidFile() throws IOException {
		PictureUpload upload = new PictureUpload();
		upload.setAlbumId(1);
		MultipartFile file = new MockMultipartFile("log4j.properties",
				new DefaultResourceLoader().getResource("log4j.properties")
						.getInputStream());
		upload.setUpload(file);

		UploadStatus status = pictureRepo.storePicture(upload);
		assertTrue("Upload succeeded.", status.equals(UploadStatus.INVALID));
	}
	
	public void testRotateImageLeft() throws IOException {
		PictureUpload upload = new PictureUpload();
		upload.setAlbumId(1);
        DefaultResourceLoader loader = new DefaultResourceLoader();
        ClassPathResource resource = (ClassPathResource) loader.getResource("spring2.jpg");
        System.out.println("++++++++ resource: " + resource.getURL()); 
		MultipartFile file = new MockMultipartFile("spring2.jpg",
				new DefaultResourceLoader().getResource("spring2.jpg")
						.getInputStream());
		upload.setUpload(file);

		pictureRepo.storePicture(upload);
		Picture p = upload.getPicture();
		pictureRepo.rotatePictureLeft(p);
		assertTrue("Rotated without execptions",true);
	}

}
