package com.wrox.beginspring.pix.dao;

import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.io.FileSystemResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.util.FileCopyUtils;

import com.wrox.beginspring.pix.model.Picture;
import com.wrox.beginspring.pix.web.PictureUpload;

public class PictureFileRepository implements PictureRepository {

	private Log log = LogFactory.getLog(PictureFileRepository.class);

	private String repositoryRoot = System.getProperty("webapp.root");

	private boolean deleteOnShutdown = true;

	private enum Rotation {

		LEFT(Math.toRadians(-90)), RIGHT(Math.toRadians(90));

		private final double angle;

		Rotation(double angle) {
			this.angle = angle;
		}

		public double getAngle() {
			return angle;
		}

	}

	public void deletePicture(Picture pictureToDelete) {
		File fileToDelete = new File(this.repositoryRoot
				+ pictureToDelete.getPath());
		fileToDelete.delete();
	}

	public void rotatePictureLeft(Picture picture) {
		rotatePicture(picture, Rotation.LEFT);
	}

	public void rotatePictureRight(Picture picture) {
		rotatePicture(picture, Rotation.RIGHT);
	}

	private void rotatePicture(Picture picture, Rotation rotation) {
		ResourceLoader loader = new FileSystemResourceLoader();
		Resource resource = loader.getResource(repositoryRoot
				+ picture.getLocation());

		BufferedImage img;
		try {
			img = ImageIO.read(resource.getFile());
			BufferedImage result = tilt(img, rotation);
			String fileName = picture.getFileName();
			ImageIO.write(result, fileName.substring(fileName.indexOf('.')+1),
					new File(resource.getFile().toURI()));
		} catch (IOException e) {
			throw new RuntimeException("Failed to rotate image.", e);
		}

	}
	
	private BufferedImage createThumb( BufferedImage image) {
		return image;
	}

    public static BufferedImage tilt(BufferedImage image, Rotation rotation) {
        double sin = Math.abs(Math.sin(rotation.angle)), cos = Math.abs(Math.cos(rotation.angle));
        int w = image.getWidth(), h = image.getHeight();
        int neww = (int)Math.floor(w*cos+h*sin), newh = (int)Math.floor(h*cos+w*sin);
        GraphicsConfiguration gc = getDefaultConfiguration();
        BufferedImage rotated = gc.createCompatibleImage(neww, newh);
        Graphics2D g = rotated.createGraphics();
        g.translate((neww-w)/2, (newh-h)/2);
        g.rotate(rotation.angle, w/2, h/2);
        g.drawRenderedImage(image, null);
        g.dispose();
        return rotated;
    }
 
    public static GraphicsConfiguration getDefaultConfiguration() {
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gd = ge.getDefaultScreenDevice();
        return gd.getDefaultConfiguration();
    }

	public PictureRepository.UploadStatus storePicture(PictureUpload upload) {

		// Check if all minimum requirements are met.
		if (upload.getAlbumId() == null || upload.getUpload() == null
				|| repositoryRoot == null) {
			throw new IllegalArgumentException(
					"A picture cannot be stored if the album ID or file is not provided.");
		}

		StringBuffer target = new StringBuffer();
		target.append("albums");
		target.append(File.separator);
		target.append(upload.getAlbumId());
		target.append(File.separator);

		// Set the relative file location here.
		String path = target.toString();

		target.insert(0, this.repositoryRoot);

		// Create album folder if it doesn't exist.
		File dir = new File(target.toString());
		if (!dir.exists()) {
			dir.mkdirs();
		}

		String fileName = upload.getUpload().getOriginalFilename();
		if (fileName == null || "".equals(fileName)) {
			fileName = upload.getUpload().getName();
		}
		target.append(fileName);

		// Check if the picture exists.
		File targetFile = new File(target.toString());
		if (targetFile.exists()) {
			if (log.isInfoEnabled()) {
				log.info(targetFile + " already exists.");
			}
			return UploadStatus.EXISTS;
		}

		// Mark for automatic deletion on JVM shutdown.
		if (deleteOnShutdown) {
			targetFile.deleteOnExit();
		}

		// Check if this is a valid image.
		try {
			if (ImageIO.read(upload.getUpload().getInputStream()) == null) {
				return UploadStatus.INVALID;
			}
		} catch (IOException e1) {
			e1.printStackTrace();
			return UploadStatus.FAILED;
		}

		// Write the file to the picture repository.
		try {
			if (log.isInfoEnabled()) {
				log.info("Copying file to " + targetFile.toURL());
			}
			FileCopyUtils.copy(upload.getUpload().getBytes(), targetFile);
		} catch (IOException e) {
			e.printStackTrace();
			return UploadStatus.FAILED;
		}

		// Get a reference to the picture object to populate with data coming
		// from the file.
		Picture picture = upload.getPicture();
		if (upload.getPicture() == null) {
			picture = new Picture();
			upload.setPicture(picture);
		}
		picture.setFileName(fileName);
		picture.setPath(path);
		picture.setSize(upload.getUpload().getSize());

		return UploadStatus.SUCCESS;
	}

	public void setRepositoryRoot(String repositoryRoot) {
		this.repositoryRoot = repositoryRoot;
	}

	public void setDeleteOnShutdown(boolean deleteOnShutdown) {
		this.deleteOnShutdown = deleteOnShutdown;
	}
}
