package com.soft.entity;

public class Orientation {
	private long id;
	private int orientation;
	private String dateTime;

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public int getOrientation() {
		return orientation;
	}
	public void setOrientation(int orientation) {
		this.orientation = orientation;
	}
		
    public String getDateTime() {
		return dateTime;
	}
	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}
	@Override
    public String toString() {
            return "orient: " + orientation + " datetime: " + dateTime;
    }
	
}
