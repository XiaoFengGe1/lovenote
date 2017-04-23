package com.app.model;

public class FileInputInitialPreviewConfigModel {
	private String caption, width, url;
	private int key;
	
	
	public FileInputInitialPreviewConfigModel() {
		super();
	}
	public FileInputInitialPreviewConfigModel(String caption, String width, String url, int key) {
		super();
		this.caption = caption;
		this.width = width;
		this.url = url;
		this.key = key;
	}
	public String getCaption() {
		return caption;
	}
	public void setCaption(String caption) {
		this.caption = caption;
	}
	public String getWidth() {
		return width;
	}
	public void setWidth(String width) {
		this.width = width;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public int getKey() {
		return key;
	}
	public void setKey(int key) {
		this.key = key;
	}
	
	
}
