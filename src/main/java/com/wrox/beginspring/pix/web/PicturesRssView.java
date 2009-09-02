package com.wrox.beginspring.pix.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.AbstractView;

import com.sun.syndication.feed.synd.SyndContent;
import com.sun.syndication.feed.synd.SyndContentImpl;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndEntryImpl;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.feed.synd.SyndFeedImpl;
import com.sun.syndication.io.SyndFeedOutput;
import com.wrox.beginspring.pix.model.Album;
import com.wrox.beginspring.pix.model.Picture;

public class PicturesRssView extends AbstractView {
	
	private static final String FEED_TYPE = "atom_1.0";
	
	private static final String MIME_TYPE = "application/xml; charset=UTF-8";
	
	private static final String SERVER = "http://localhost:8080"; 
	
	@Override
	protected void renderMergedOutputModel(Map model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Album album = (Album)model.get("album");
		
		SyndFeed feed = new SyndFeedImpl();
		feed.setFeedType(FEED_TYPE);
		feed.setTitle("Pictures from " + album.getName());
		feed.setDescription(album.getDescription());
		feed.setLink(SERVER + request.getRequestURI());
		
		List<SyndEntry> entries = new ArrayList<SyndEntry>();
		
		for (Picture picture : album.getPictures()) {
			SyndEntry entry = new SyndEntryImpl();
			entry.setTitle(picture.getFileName());
			SyndContent desc = new SyndContentImpl();
			desc.setValue(picture.getDescription());
			entry.setDescription(desc);
			entries.add(entry);
		}
		
		feed.setEntries(entries);

		SyndFeedOutput out = new SyndFeedOutput();
		response.setContentType(MIME_TYPE);
		out.output(feed, response.getWriter());
		
	}

}
