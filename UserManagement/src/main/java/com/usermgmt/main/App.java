package com.usermgmt.main;

import java.util.Scanner;

import com.usermgmt.service.CreateThread;
import com.usermgmt.service.DeleteThread;
import com.usermgmt.service.ReadThread;

public class App {
	/**
	 * @param args
	 */
	public static void main(String[] args) {

		Scanner in = new Scanner(System.in);
		System.out.println("Enter 1 to 'create'\t2 to 'read'\t 3 to 'delete' user");
		int opt = in.nextInt();

		System.out.println("Enter the file name(with path)");
		String file = in.next();

		switch (opt) {
		case 1:
			CreateThread ct = new CreateThread(file);
			Thread create = new Thread(ct);
			create.start();
			break;
		case 2:
			ReadThread rt = new ReadThread(file);
			Thread read = new Thread(rt);
			read.start();
			break;
		case 3:
			System.out.println("Enter the ID of user to delete");
			String id = in.next();
			DeleteThread dt = new DeleteThread(file, id);
			Thread delete = new Thread(dt);
			delete.start();
			break;
		}

	}
}
