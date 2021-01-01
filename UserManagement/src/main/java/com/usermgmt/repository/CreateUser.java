package com.usermgmt.repository;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.usermgmt.model.User;
import com.usermgmt.service.Create;
import com.usermgmt.util.IdCheck;

public class CreateUser implements Create {
	
	private String file;
	
	public CreateUser(String file) {
		this.file=file;
		if(new File(file).exists()) {
			new DeleteUser(file);
		}
	}

	@Override
	public void addUsers(JSONArray user) {
		File newFile = new File(this.file);
		try {
			FileWriter writer = new FileWriter(newFile);
			user.writeJSONString(writer);
			writer.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void addUser() {
		String optTTL="no";
		String id=null;
		String name=null;
		int age=0;
		String gender=null;
		long startTime=0;
		long endTime=0;
		
		Scanner in = new Scanner(System.in);
		System.out.println("Enter the user details");

		id = IdCheck.getId();

		System.out.println("Enter the name");
		 name = in.next();

		System.out.println("Enter the age");
		 age = in.nextInt();

		System.out.println("Enter the gender");
		 gender = in.next();

		System.out.println("Do you want to set expiry time?? press '1' to set TTL");
		int checkTTL = in.nextInt();
		if(checkTTL==1) {
			optTTL="yes";
			System.out.println("Enter the time in seconds");
			int time = in.nextInt();
			startTime=System.currentTimeMillis();
			endTime=startTime+(time*1000);
		}
		User user = new User(id, name, age, gender,optTTL,startTime,endTime);

		this.storeUser(user, this.file);
	}

	@SuppressWarnings("unchecked")
	private void storeUser(User user, String file) {
		JSONObject obj = new JSONObject();

		obj.put("id", user.getId());
		obj.put("Name", user.getName());
		obj.put("Age", user.getAge());
		obj.put("Gender", user.getGender());
		obj.put("optTTL", user.getOptTTL());
		obj.put("startTime", user.getStartTime());
		obj.put("endTime", user.getEndTime());

		File newFile = new File(file);
		if (newFile.exists()) {
			JSONParser parser = new JSONParser();
			try {
				int chkId = 0;
				Object parObj = parser.parse(new FileReader(newFile));
				JSONArray parArr = (JSONArray) parObj;
				for (int i = 0; i < parArr.size(); i++) {
					JSONObject tempObj = (JSONObject) parArr.get(i);
					String tempId = (String) tempObj.get("id");
					if (tempId.equals(user.getId())) {
						++chkId;
					}
				}
				if (chkId == 0) {
					parArr.add(obj);
					FileWriter writer = new FileWriter(newFile);
					parArr.writeJSONString(writer);
					writer.close();
					System.out.println("User has been Added Successfully");
				} else {
					System.out.println("User ID already exists.\nTerminating CREATE process");
					System.exit(0);
				}

			} catch (IOException | ParseException e) {
				e.printStackTrace();
			}
		} else {
			JSONArray arr = new JSONArray();
			arr.add(obj);
			try {
				FileWriter writer = new FileWriter(file);
				arr.writeJSONString(writer);
				writer.close();
				System.out.println("User has been Added Successfully");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
