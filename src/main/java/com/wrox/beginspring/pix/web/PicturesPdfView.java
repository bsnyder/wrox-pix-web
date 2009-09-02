package com.wrox.beginspring.pix.web;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.lowagie.text.Document;
import com.lowagie.text.Image;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;
import com.wrox.beginspring.pix.model.Album;
import com.wrox.beginspring.pix.model.Picture;

public class PicturesPdfView extends AbstractPdfView {

	@Override
	protected void buildPdfDocument(Map model, Document doc, PdfWriter writer,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		Album album = (Album) model.get("album");
		for (Picture picture : album.getPictures()) {
			Image img = Image.getInstance(picture.getFileLocation());
			doc.add(img);
			doc.add(new Paragraph(picture.getDescription()));
		}

	}
}
