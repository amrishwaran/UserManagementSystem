package com.usermgmt.service;

import com.usermgmt.repository.GetUser;

public class ReadThread implements Runnable {

	private String file;

	public ReadThread(String file) {
		this.file = file;
	}

	@Override
	synchronized public void run() {
		Read read = new GetUser(file);
		read.getUserDetails();
	}
}
