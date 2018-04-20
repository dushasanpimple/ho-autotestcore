package com.ho.autotest.model;

public class FileInfo {
	private String name;
	private String mimeType;
	private long size;
	private String extn;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMimeType() {
		return mimeType;
	}
	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}
	public long getSize() {
		return size;
	}
	public void setSize(long size) {
		this.size = size;
	}
	public String getExtn() {
		return extn;
	}
	public void setExtn(String extn) {
		this.extn = extn;
	}

}
