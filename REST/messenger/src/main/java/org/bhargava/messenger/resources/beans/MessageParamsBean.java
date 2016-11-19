package org.bhargava.messenger.resources.beans;

import javax.ws.rs.QueryParam;

public class MessageParamsBean {
	private @QueryParam("year") int year;
	private @QueryParam("start") int start;
	private @QueryParam("end") int end;

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getEnd() {
		return end;
	}

	public void setEnd(int end) {
		this.end = end;
	}
}
