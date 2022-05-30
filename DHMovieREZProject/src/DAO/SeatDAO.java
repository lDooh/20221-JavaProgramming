package DAO;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.sql.*;

import DTO.SeatDTO;

public class SeatDAO {
	// 싱글톤 패턴
	private static SeatDAO instance;
	
	private static String driverName;
	private static String url;
	private static String userName;
	private static String password;
	
	private static Connection connection = null;
	private static PreparedStatement statement = null;
	private static ResultSet resultSet = null;
	
	// 객체 생성을 막기 위해 생성자 private
	private SeatDAO() {
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
	
	public static SeatDAO getInstance() {
		if (instance == null)
			instance = new SeatDAO();
		
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
	
	public boolean signUp(SeatDAO seatDAO) {
		String sql = "INSERT INTO userinfo VALUES (?, ?, ?, ?, ?, ?)";
		int count = 0;
		
		connect();
		
		// TODO: SeatDTO로 변경
		try {
			statement = connection.prepareStatement(sql);
			statement.setString(1,  userDTO.getId());
			statement.setString(2,  userDTO.getPassword());
			statement.setString(3,  userDTO.getNickname());
			statement.setString(4,  userDTO.getbd());
			statement.setString(5,  userDTO.getGender());
			statement.setString(6,  userDTO.getCallNum());
			
			count = statement.executeUpdate();
		} catch (SQLException e) {
			System.out.println("회원가입 SQL 오류");
			e.printStackTrace();
		} finally {
			connectionClose();
		}
		
		if (count == 1)
		{
			//System.out.println("회원 가입 성공");
			return true;
		}
		
		return false;
	}
	
	// -2: SQL 오류, -1: ID 없음, 0: 비밀번호 틀림, 1: 로그인 성공
	public int signIn(String id, String password) {
		String sql = "SELECT password FROM userinfo WHERE id = '" + id + "'";
		
		connect();
		
		try {
			statement = connection.prepareStatement(sql);
			resultSet = statement.executeQuery(sql);
			
			if (resultSet.next())
			{
				if (resultSet.getString("password").equals(password))
					return 1;
				else
					return 0;
			}
			else
			{
				//System.out.println("없는 ID");
				return -1;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connectionClose();
		}
		return -2;
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
		SeatDAO seatDAO = SeatDAO.getInstance();
		seatDAO.connect();
		seatDAO.connectionClose();
		
		System.out.println("driverName: " + driverName
				+ "\nurl: " + url
				+ "\nuserName: " + userName
				+ "\npassword: " + password);
	}
}