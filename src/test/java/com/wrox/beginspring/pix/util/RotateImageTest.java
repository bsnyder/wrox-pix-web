package com.wrox.beginspring.pix.util;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Transparency;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.MalformedURLException;

import javax.imageio.ImageIO;

import junit.framework.TestCase;

import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageDecoder;

/**
 * 
 * Code from http://www.nsftools.com/tips/JpgImage.java
 * 
 * @author Thomas.Van.De.Velde
 *
 */
public class RotateImageTest extends TestCase {

	public RenderingHints rh;

	public void testRotateImage() throws MalformedURLException, IOException {

		Resource resource = new DefaultResourceLoader()
				.getResource("football.jpg");
		
		JPEGImageDecoder decoder = JPEGCodec.createJPEGDecoder(resource.getInputStream());

		BufferedImage bi = decoder.decodeAsBufferedImage();

		OutputStream fos = new FileOutputStream("test.jpg");
		ImageIO.write(rotate(bi,45, Color.WHITE), "jpg", fos);
		fos.close();
	}
	

	public BufferedImage rotate(BufferedImage bi, double degrees,
			Color backgroundColor) {
		/*
		 * Okay, this required some strange geometry. Before an image is
		 * rotated, the origin is at the top left corner of the rectangle that
		 * contains the image. After an image is rotated, you want the origin to
		 * get moved to a spot that will allow the entire rotated image to be
		 * framed within a rectangle. Unfortunately, this does not happen
		 * automatically.
		 * 
		 * That's where the strange geometry comes in. We essentially need to
		 * rotate the image, and then determine what the width and height of the
		 * new image is, and then determine where the new origin should be. The
		 * width and height is easy (you can also use the AffineTransform
		 * getWidth and getHeight methods), but the new origin...well...not so
		 * easy. Unfortunately, my trigonometry skills aren't sharp enough to be
		 * able to give you a good explanation of what's going on with this
		 * method without drawing everything out for you. If you want to figure
		 * it out for yourself, just draw an axis on a sheet of paper, place a
		 * smaller rectangular piece of paper on the axis, and start rotating it
		 * along the axis to see what's going on. Then pull out your old trig
		 * books and start calculating.
		 * 
		 * BTW, if there's an easier way to do this, I'd love to know about it.
		 */

		// adjust the angle that was passed so it's between 0 and 360 degrees
		double positiveDegrees = (degrees % 360) + ((degrees < 0) ? 360 : 0);
		double degreesMod90 = positiveDegrees % 90;
		double radians = Math.toRadians(positiveDegrees);
		double radiansMod90 = Math.toRadians(degreesMod90);

		// don't bother with any of the rest of this if we're not really
		// rotating
		if (positiveDegrees == 0)
			return bi;

		// figure out which quadrant we're in (we'll want to know this later)
		int quadrant = 0;
		if (positiveDegrees < 90)
			quadrant = 1;
		else if ((positiveDegrees >= 90) && (positiveDegrees < 180))
			quadrant = 2;
		else if ((positiveDegrees >= 180) && (positiveDegrees < 270))
			quadrant = 3;
		else if (positiveDegrees >= 270)
			quadrant = 4;

		// get the height and width of the rotated image (you can also do this
		// by applying a rotational AffineTransform to the image and calling
		// getWidth and getHeight against the transform, but this should be a
		// faster calculation)
		int height = bi.getHeight();
		int width = bi.getWidth();
		double side1 = (Math.sin(radiansMod90) * height)
				+ (Math.cos(radiansMod90) * width);
		double side2 = (Math.cos(radiansMod90) * height)
				+ (Math.sin(radiansMod90) * width);

		double h = 0;
		int newWidth = 0, newHeight = 0;
		if ((quadrant == 1) || (quadrant == 3)) {
			h = (Math.sin(radiansMod90) * height);
			newWidth = (int) side1;
			newHeight = (int) side2;
		} else {
			h = (Math.sin(radiansMod90) * width);
			newWidth = (int) side2;
			newHeight = (int) side1;
		}

		// figure out how much we need to shift the image around in order to
		// get the origin where we want it
		int shiftX = (int) (Math.cos(radians) * h)
				- ((quadrant == 3) || (quadrant == 4) ? width : 0);
		int shiftY = (int) (Math.sin(radians) * h)
				+ ((quadrant == 2) || (quadrant == 3) ? height : 0);

		// create a new BufferedImage of the appropriate height and width and
		// rotate the old image into it, using the shift values that we
		// calculated
		// earlier in order to make sure the new origin is correct
		BufferedImage newbi = new BufferedImage(newWidth, newHeight, bi
				.getType());
		Graphics2D g2d = newbi.createGraphics();
		g2d.setBackground(backgroundColor);
		g2d.clearRect(0, 0, newWidth, newHeight);
		g2d.rotate(radians);
		g2d.drawImage(bi, shiftX, -shiftY, null);
		return newbi;
	}
}