package com.usermgmt.service;

import com.usermgmt.repository.DeleteUser;

public class DeleteThread implements Runnable {

	private String file;
	private String id;

	public DeleteThread(String file, String id) {
		this.file = file;
		this.id = id;
	}

	@Override
	synchronized public void run() {
		Delete delete = new DeleteUser(file);
		delete.deleteUser(id);
	}
	
}
