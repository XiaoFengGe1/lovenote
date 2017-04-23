package com.app.model;

import java.util.List;

public class FileInputResponseModel {
	private String error, fileName;
	private List<String> initialPreview;
	private List<String> initialPreviewConfig;
	private List<String> initialPreviewThumbTags;
	private boolean append;
	
	public FileInputResponseModel() {
		super();
	}
	public FileInputResponseModel(String error, List<String> initialPreview, List<String> initialPreviewConfig,
			List<String> initialPreviewThumbTags, boolean append) {
		super();
		this.error = error;
		this.initialPreview = initialPreview;
		this.initialPreviewConfig = initialPreviewConfig;
		this.initialPreviewThumbTags = initialPreviewThumbTags;
		this.append = append;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	public List<String> getInitialPreview() {
		return initialPreview;
	}
	public void setInitialPreview(List<String> initialPreview) {
		this.initialPreview = initialPreview;
	}
	public List<String> getInitialPreviewConfig() {
		return initialPreviewConfig;
	}
	public void setInitialPreviewConfig(List<String> initialPreviewConfig) {
		this.initialPreviewConfig = initialPreviewConfig;
	}
	public List<String> getInitialPreviewThumbTags() {
		return initialPreviewThumbTags;
	}
	public void setInitialPreviewThumbTags(List<String> initialPreviewThumbTags) {
		this.initialPreviewThumbTags = initialPreviewThumbTags;
	}
	public boolean isAppend() {
		return append;
	}
	public void setAppend(boolean append) {
		this.append = append;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	
	
	
}
