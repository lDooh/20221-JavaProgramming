package DTO;

import java.time.LocalDate;

public class userDTO {
	private String id;
	private String password;
	private String nickname;
	private LocalDate bd;
	private char gender;
	private String callNum;
	
	public userDTO(String id, String password, String nickname, LocalDate bd, char gender, String callNum) {
		this.id = id;
		this.password = password;
		this.nickname = nickname;
		this.bd = bd;
		this.gender = gender;
		this.callNum = callNum;
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getNickname() {
		return nickname;
	}
	
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
	public LocalDate getbd() {
		return bd;
	}
	
	public void setbd(LocalDate bd) {
		this.bd = bd;
	}
	
	public char getGender() {
		return gender;
	}
	
	public void setGender(char gender) {
		this.gender = gender;
	}
	
	public String getCallNum() {
		return callNum;
	}
	
	public void setCallNum(String callNum) {
		this.callNum = callNum;
	}
}