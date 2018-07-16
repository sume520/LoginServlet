package com.database;

public class UserData {
	private int ID;
	private int deviceID;
	private String username;
	private String nickname;
	
	private static UserData userData;
	
	public static UserData createInstance() {
		if(userData==null)
			userData=new UserData();
		return userData;
	}
	
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public int getDeviceID() {
		return deviceID;
	}
	public void setDeviceID(int deviceID) {
		this.deviceID = deviceID;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
	public void setAllData(int ID,int deviceID,String username,String nickname) {
		this.ID=ID;
		this.deviceID=deviceID;
		this.username=username;
		this.nickname=nickname;
	}
	
	public void initUserData() {
		this.ID=-1;
		this.deviceID=-1;
		this.username=null;
		this.nickname=null;
	}
	
}
