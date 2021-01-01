package com.usermgmt.util;

import java.util.Scanner;

public class IdCheck {

	public static String getId() {
		Scanner in = new Scanner(System.in);
		System.out.println("Enter the id");
		String id = in.next();

		if (id.length() > 5) {
			System.out.println(
					"Length of ID is greater than 32\nEnter 'y' to retype the ID or others to terminate the process");
			char choice = in.next().charAt(0);
			if (choice == 'y') {
				id=IdCheck.getId();
			} else {
				System.exit(0);
			}
		}
		return id;
	}
}
