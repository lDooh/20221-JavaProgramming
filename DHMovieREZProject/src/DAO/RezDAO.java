package DAO;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.sql.*;

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
			statement.setString(1, rezDTO.getId());
			statement.setString(2, rezDTO.getTitle());
			statement.setString(3, rezDTO.getMDate());
			statement.setString(4, rezDTO.getMTime());
			statement.setString(5, rezDTO.getSeatNum());
			
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
	
	// 해당 유저의 예약 정보 모두 가져오기
	public RezDTO[] getRezs(String id) {
		String sql = "SELECT * FROM rez WHERE ID = ? ORDER BY mDate, mTime, title, seatNum";
		RezDTO[] rezDTO = null;
		
		connect();
		
		try {
			statement = connection.prepareStatement("SELECT count(ID) FROM rez WHERE ID = ?");
			statement.setString(1, id);
			resultSet = statement.executeQuery();
			resultSet.next();
			int rows = Integer.parseInt(resultSet.getString("count(ID)"));
			rezDTO = new RezDTO[rows];
			
			statement = connection.prepareStatement(sql);
			statement.setString(1, id);
			resultSet = statement.executeQuery();
			
			RezDTO tmprezDTO = null;
			resultSet.next();
			for (int i = 0; i < rows; i++)
			{				
				tmprezDTO = new RezDTO(resultSet.getString("id"),
										resultSet.getString("title"),
										resultSet.getString("mDate"),
										resultSet.getString("mTime"),
										resultSet.getString("seatNum"));
				rezDTO[i] = tmprezDTO;
				resultSet.next();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connectionClose();
		}
		
		return rezDTO;
	}
	
	// 해당 좌석이 예약이 되어있는지 반환
	public boolean isRezed(RezDTO rezDTO) {
		String sql = "SELECT * FROM rez WHERE title = ? AND "
				+ "mDate = ? AND mTime = ? AND seatNum = ?";
		
		connect();
		
		try {
			statement = connection.prepareStatement(sql);
			statement.setString(1, rezDTO.getTitle());
			statement.setString(2, rezDTO.getMDate());
			statement.setString(3, rezDTO.getMTime());
			statement.setString(4, rezDTO.getSeatNum());
			resultSet = statement.executeQuery();
			
			return resultSet.next();
		} catch (SQLException e) {
			System.out.print("예매 여부 SQL 오류");
			e.printStackTrace();
		} finally {
			connectionClose();
		}
		
		return true;
	}

	// 예약 정보 삭제
	public void deleteRez(RezDTO rezDTO) {
		String sql = "DELETE FROM rez WHERE ID = ? AND title = ? AND "
				+ "mDate  = ? AND mTime = ? AND seatNum = ?";
		
		connect();
		
		try {
			statement = connection.prepareStatement(sql);
			statement.setString(1,  rezDTO.getId());
			statement.setString(2,  rezDTO.getTitle());
			statement.setString(3,  rezDTO.getMDate());
			statement.setString(4,  rezDTO.getMTime());
			statement.setString(5,  rezDTO.getSeatNum());
			statement.executeUpdate();
		} catch (SQLException e) {
			System.out.println("티켓 삭제 SQL 오류");
			e.printStackTrace();
		} finally {
			connectionClose();
		}
	}
	
	// 예약 현황(수)
	public int countRez(String title) {
		String sql = "SELECT COUNT(title) FROM rez WHERE title = ?";
		int count = 0;
		
		connect();
		
		try {
			statement = connection.prepareStatement(sql);
			statement.setString(1, title);
			resultSet = statement.executeQuery();
			resultSet.next();
			count += resultSet.getInt("COUNT(title)");
		} catch (SQLException e) {
			System.out.println("예약 현황 SQL 오류");
			e.printStackTrace();
		} finally {
			connectionClose();
		}
		
		return count;
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