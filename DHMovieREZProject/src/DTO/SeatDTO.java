package DTO;

import java.time.LocalDate;

public class SeatDTO {
	private String title;
	private LocalDate mDate;
	private String seatNum;
	
	public SeatDTO(String title, LocalDate mDate, String seatNum) {
		this.title = title;
		this.mDate = mDate;
		this.seatNum = seatNum;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public LocalDate getMDate() {
		return mDate;
	}
	
	public void setMDate (LocalDate mDate) {
		this.mDate = mDate;
	}
	
	public String getSeatNum() {
		return seatNum;
	}
	
	public void setSeatNum(String seatNum) {
		this.seatNum = seatNum;
	}
}