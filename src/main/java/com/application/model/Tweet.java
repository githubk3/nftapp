package com.application.model;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Tweet extends Post implements ITag {
	private int totalComment;
	private int totalReTweet;
	private int totalQuote;
	private int totalHeart;

	public Tweet(int id, String url, String content, String datetime, int totalComment, int totalReTweet,
			int totalQuote, int totalHeart) {
		super(id, url, content, datetime);
		this.totalComment = totalComment;
		this.totalReTweet = totalReTweet;
		this.totalQuote = totalQuote;
		this.totalHeart = totalHeart;
		ArrayList<String> tags = this.extractTags(content);
		this.addTag(tags);
	}

	public int getTotalComment() {
		return totalComment;
	}

	public int getTotalReTweet() {
		return totalReTweet;
	}

	public int getTotalQuote() {
		return totalQuote;
	}

	public int getTotalHeart() {
		return totalHeart;
	}

	public ArrayList<String> extractTags(String content) {
		ArrayList<String> tags = new ArrayList<>();

		// Biểu thức chính quy để tìm các từ có ký tự '#' ở phía trước
		Pattern pattern = Pattern.compile("(?<=\\s|^)#\\w+");
		Matcher matcher = pattern.matcher(content);

		while (matcher.find()) {
			String hashtag = matcher.group();
			tags.add(hashtag);
		}

		return tags;
	}

}
