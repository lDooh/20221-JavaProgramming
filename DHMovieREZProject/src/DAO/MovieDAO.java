package DAO;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.sql.*;

import DTO.MovieDTO;

public class MovieDAO {
	// 싱글톤 패턴
	private static MovieDAO instance;
	
	private static String driverName;
	private static String url;
	private static String userName;
	private static String password;
	
	private static Connection connection = null;
	private static PreparedStatement statement = null;
	private static ResultSet resultSet = null;
	
	// 객체 생성을 막기 위해 생성자 private
	private MovieDAO() {
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
	
	public static MovieDAO getInstance() {
		if (instance == null)
			instance = new MovieDAO();
		
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
	
	// Main화면에 표시할 영화들의 정보 가져오기
	public MovieDTO[] getMovies() {
		String sql = "SELECT * FROM movie ORDER BY title";
		MovieDTO[] movieDTO = null;
		
		connect();
		
		try {
			statement = connection.prepareStatement("SELECT count(title) FROM movie;");
			resultSet = statement.executeQuery("SELECT count(title) FROM movie;");
			resultSet.next();
			int rows = Integer.parseInt(resultSet.getString("count(title)"));
			movieDTO = new MovieDTO[rows];
			
			statement = connection.prepareStatement(sql);
			resultSet = statement.executeQuery(sql);
			
			MovieDTO tmpmovieDTO = null;
			resultSet.next();
			for (int i = 0; i < rows; i++)
			{				
				tmpmovieDTO = new MovieDTO(resultSet.getString("title"),
						resultSet.getString("runningTime"));
				movieDTO[i] = tmpmovieDTO;
				resultSet.next();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connectionClose();
		}
		
		return movieDTO;
	}

	public static void main(String[] args) {
		MovieDAO movieDAO = MovieDAO.getInstance();
		movieDAO.connect();
		movieDAO.connectionClose();
		
		System.out.println("driverName: " + driverName
				+ "\nurl: " + url
				+ "\nuserName: " + userName
				+ "\npassword: " + password);
	}
}