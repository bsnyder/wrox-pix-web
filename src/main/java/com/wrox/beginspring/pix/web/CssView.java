package com.wrox.beginspring.pix.web;

import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.View;

public class CssView implements View {
	
	private static final String CONTENT_TYPE = "text/css";

	public String getContentType() {
		return CONTENT_TYPE;
	}

	public void render(Map model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		response.setContentType(CONTENT_TYPE);
		String color = (String)model.get("color");
		PrintWriter writer = response.getWriter();
		writer.print("BODY{ background-color:" + color + "; }");

	}

}
