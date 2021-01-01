package com.usermgmt.repository;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.usermgmt.service.Delete;

public class DeleteUser implements Delete {

	private String file;

	public DeleteUser(String file) {
		this.file = file;
		if (new File(this.file).exists()) {
			JSONParser parser = new JSONParser();
			try {
				Object obj = parser.parse(new FileReader(file));
				JSONArray ttlArr = (JSONArray) obj;

				for (int i = 0; i < ttlArr.size(); i++) {
					JSONObject jObj = (JSONObject) ttlArr.get(i);
					if (jObj.get("optTTL").equals("yes")) {
						long endTime = (long) jObj.get("endTime");
						if (System.currentTimeMillis() > endTime) {
							ttlArr.remove(i);
						}
					}
				}
				FileWriter writer = new FileWriter(file);
				ttlArr.writeJSONString(writer);
				writer.close();
			} catch (IOException | ParseException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void deleteUser(String id) {

		int temp = 0;
		File newFile = new File(file);
		if (newFile.exists()) {

			try {
				Object obj = new JSONParser().parse(new FileReader(newFile));
				JSONArray delArr = (JSONArray) obj;

				for (int i = 0; i < delArr.size(); i++) {
					JSONObject jObj = (JSONObject) delArr.get(i);
					if (jObj.get("id").equals(id)) {
						delArr.remove(i);
						++temp;
						new CreateUser(file).addUsers(delArr);
						System.out.println("User has been deleted successfully");
						break;
					}
				}
				if (temp == 0) {
					System.out.println("User ID does not exists. Terminating DELETE Process");
				}

			} catch (IOException | ParseException e) {
				e.printStackTrace();
			}
		} else {

		}

	}

}
