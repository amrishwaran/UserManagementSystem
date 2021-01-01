package com.usermgmt.service;

import com.usermgmt.repository.CreateUser;

public class CreateThread implements Runnable {

	private String file;

	public CreateThread(String file) {
		System.out.println("sdfsdf");
		this.file = file;
	}

	@Override
	synchronized public void run() {
		
		Create create = new CreateUser(file);
		create.addUser();
	}

}