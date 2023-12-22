package com.application.model;

import java.util.*;

public interface IPostManager {
	public void addPost(Post post);
	public void addPost(List<Post> post);
	public ArrayList<Post> getPostsByTag(String tag);
	public ArrayList<Post> getPostsByKeyWord(String keyword);
	public ArrayList<String> getTagHot(int day, int month, int year);
	public ArrayList<String> getTagHot(int month, int year);
}
