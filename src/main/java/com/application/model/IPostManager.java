package com.application.model;

import javafx.collections.ObservableList;

import java.util.*;

public interface IPostManager {
	public void addPost(Post post);
	public void addPost(List<Post> post);
	public ObservableList<Post> getPostsByTag(String tag);
	public ObservableList<Post> getPostsByKeyWord(String keyword);
	public ObservableList<String> getTagHot(int day, int month, int year);
	public ObservableList<String> getTagHot(int month, int year);
}
