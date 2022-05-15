package DAO;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class UserDAO {
	// 싱글톤 패턴
	private static UserDAO instance;
	
	private UserDAO() {
		
	}
	
	public static UserDAO getInstance() {
		if (instance == null)
			instance = new UserDAO();
		
		return instance;
	}

	public static void main(String[] args) {
		String path = System.getProperty("user.dir") + "/local.properties";
		//System.out.println(path);
		
		Properties prop = new Properties();
		
		try {
			FileInputStream stream = new FileInputStream(path);
			prop.load(new BufferedInputStream(stream));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		String driverName = prop.getProperty("drivername");
		String url = prop.getProperty("url");
		String userName = prop.getProperty("username");
		String password = prop.getProperty("password");
		
		System.out.println("driverName: " + driverName
				+ "\nurl: " + url
				+ "\nuserName: " + userName
				+ "\npassword: " + password);
	}
}