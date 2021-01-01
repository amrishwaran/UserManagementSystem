package com.usermgmt.model;

public class User {
	
	private String id;
	private String name;
	private int age;
	private String gender;
	private String optTTL;
	private long startTime;
	private long endTime;
	
	public User(String id, String name, int age, String gender,String optTTL,long startTime,long endTime) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
		this.gender = gender;
		this.optTTL=optTTL;
		this.startTime=startTime;
		this.endTime = endTime;
	}

	
	public String getOptTTL() {
		return optTTL;
	}


	public void setOptTTL(String optTTL) {
		this.optTTL = optTTL;
	}


	public long getStartTime() {
		return startTime;
	}


	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}


	public long getEndTime() {
		return endTime;
	}


	public void setEndTime(long endTime) {
		this.endTime = endTime;
	}


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	
	
	

}
