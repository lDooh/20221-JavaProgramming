package DAO;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.sql.*;

import DTO.UserDTO;
import DTO.RezDTO;

public class RezDAO {
	// 싱글톤 패턴
	private static RezDAO instance;
	
	private static String driverName;
	private static String url;
	private static String userName;
	private static String password;
	
	private static Connection connection = null;
	private static PreparedStatement statement = null;
	private static ResultSet resultSet = null;
	
	// 객체 생성을 막기 위해 생성자 private
	private RezDAO() {
		String path = System.getProperty("user.dir") + "/local.properties";
		// System.out.println(path);
		
		Properties prop = new Properties();
		
		try {
			FileInputStream stream = new FileInputStream(path);
			prop.load(new BufferedInputStream(stream));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		driverName = prop.getProperty("drivername");
		url = prop.getProperty("url");
		userName = prop.getProperty("username");
		password = prop.getProperty("password");
	}
	
	public static RezDAO getInstance() {
		if (instance == null)
			instance = new RezDAO();
		
		return instance;
	}
	
	private void connect() {
		try {
			Class.forName(driverName);
			connection = DriverManager.getConnection(url, userName, password);
		} catch (SQLException e) {
			System.out.println("connect sql 에러");
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			System.out.println("connect class 에러");
			e.printStackTrace();
		}
	}
	
	private void connectionClose() {
		try {
			if (resultSet != null)
				resultSet.close();
			if (statement != null)
				statement.close();
			if (connection != null)
				connection.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	// 티켓 등록
	public boolean reserve(RezDTO rezDTO) {
		String sql = "INSERT INTO rez VALUES (?, ?, ?, ?, ?)";
		int count = 0;
		
		connect();
		
		try {
			statement = connection.prepareStatement(sql);
			statement.setString(1,  rezDTO.getId());
			statement.setString(2,  rezDTO.getTitle());
			statement.setString(3,  rezDTO.getMDate());
			statement.setString(4,  rezDTO.getMTime());
			statement.setString(5,  rezDTO.getSeatNum());
			
			count = statement.executeUpdate();
		} catch (SQLException e) {
			System.out.println("티켓 등록 SQL 오류");
			e.printStackTrace();
		} finally {
			connectionClose();
		}
		
		if (count == 1)
		{
			//System.out.println("티켓 등록 성공");
			return true;
		}
		
		return false;
	}
	
	public UserDTO[] getUsers() {
		String sql = "SELECT * FROM userinfo";
		UserDTO[] userDTO = null;
		
		connect();
		
		try {
			statement = connection.prepareStatement("SELECT count(id) FROM userinfo;");
			resultSet = statement.executeQuery("SELECT count(id) FROM userinfo;");
			resultSet.next();
			int rows = Integer.parseInt(resultSet.getString("count(id)"));
			userDTO = new UserDTO[rows];
			
			statement = connection.prepareStatement(sql);
			resultSet = statement.executeQuery(sql);
			
			UserDTO tmpUserDTO = null;
			resultSet.next();
			for (int i = 0; i < rows; i++)
			{				
				tmpUserDTO = new UserDTO(resultSet.getString("id"),
										resultSet.getString("password"),
										resultSet.getString("nickname"),
										resultSet.getString("birthday"),
										resultSet.getString("gender"),
										resultSet.getString("callNum"));
				userDTO[i] = tmpUserDTO;
				resultSet.next();
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		} finally {
			connectionClose();
		}
		
		return userDTO;
	}
	
	public UserDTO getUserDTO(String id) {
		UserDTO userDTO;
		String sql = "SELECT * FROM userinfo WHERE id = '" + id + "'";
		
		connect();
		
		try {
			statement = connection.prepareStatement(sql);
			resultSet = statement.executeQuery(sql);
			
			resultSet.next();
			userDTO = new UserDTO(resultSet.getString("id"),
					resultSet.getString("password"),
					resultSet.getString("nickname"),
					resultSet.getString("birthday"),
					resultSet.getString("gender"),
					resultSet.getString("callNum"));
			return userDTO;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connectionClose();
		}
		
		return null;
	}
	
	public void deleteUser(String id) {
		String sql = "DELETE FROM userinfo WHERE id = ?";
		
		connect();
		
		try {
			statement = connection.prepareStatement(sql);
			statement.setString(1,  id);
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connectionClose();
		}
	}

	public static void main(String[] args) {
		RezDAO rezDAO = RezDAO.getInstance();
		rezDAO.connect();
		rezDAO.connectionClose();
		
		System.out.println("driverName: " + driverName
				+ "\nurl: " + url
				+ "\nuserName: " + userName
				+ "\npassword: " + password);
	}
}