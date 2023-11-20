package org.cmms.common.util;

import java.io.Serializable;

public class ViewBrowersBean implements Serializable{
	private static final long serialVersionUID = 3282193006976953567L;
	
	private int userId;
	private int siteId;
	private int cityId;
	
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	public int getUserId() {
		return userId;
	}

	public void setSiteId(int siteId) {
		this.siteId = siteId;
	}

	public int getSiteId() {
		return siteId;
	}

	public void setCityId(int cityId) {
		this.cityId = cityId;
	}

	public int getCityId() {
		return cityId;
	}
}
