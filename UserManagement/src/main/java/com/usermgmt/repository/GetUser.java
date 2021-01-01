package com.usermgmt.repository;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.usermgmt.service.Read;

public class GetUser implements Read {

	private String file;

	public GetUser(String file) {
		this.file = file;
		if (new File(file).exists()) {
			new DeleteUser(file);
		}
	}

	@Override
	public void getUserDetails() {
		Scanner in = new Scanner(System.in);
		System.out.println("Enter the User ID ");
		String id = in.next();

		this.checkUser(id, file);
	}

	private void checkUser(String id, String file) {

		File newFile = new File(file);
		if (newFile.exists()) {
			JSONParser parser = new JSONParser();
			int tempCnt = 0;
			try {
				Object obj = parser.parse(new FileReader(newFile));
				JSONArray readArr = (JSONArray) obj;
				for (int i = 0; i < readArr.size(); i++) {
					JSONObject tempObj = (JSONObject) readArr.get(i);
					if (tempObj.get("id").equals(id)) {
						System.out.println("Required User Details:\n--------------------");
						System.out.println(tempObj);
						++tempCnt;
						System.out.println("READ Process has been completed");
					}
				}
				if (tempCnt == 0) {
					System.out.println("User does not Exists. READ Process has been completed");
				}
			} catch (IOException | ParseException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("No file to read.Terminating READ process");
			System.exit(0);
		}
	}
}
