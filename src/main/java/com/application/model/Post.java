package com.application.model;

import java.util.ArrayList;
import java.util.List;

public class Post {
	private int id;
	private String url;
	private String content;
	private String image;
	private String datetime;
	private List<String> tags = new ArrayList<>();
	
	public Post() {
		
	}

	public Post(int id, String url, String content, String image, String datetime) {
		this.id = id;
		this.url = url;
		this.content = content;
		this.image = image;
		this.datetime = datetime;
	}
	
	public int getId() {
		return this.id;
	}
	
	public String getUrl() {
		return this.url;
	}
	
	public String getContent() {
		return this.content;
	}
	
	public String getDatetime() {
		return this.datetime;
	}
	
	public List<String> getTags(){
		return this.tags;
	}

	public String getImage() {
		return image;
	}

	// todo
	public void addTag(String tag) {
		tags.add(tag);
	}

	// todo
	public void addTag(List<String> tag) {
		tags.addAll(tag);
	}

}
