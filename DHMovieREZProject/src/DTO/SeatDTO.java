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
	
	public String getMDate() {
		return mDate.toString();
	}
	
	public void setMDate (String mDate) {
		String[] str = mDate.split("-");
		this.mDate = LocalDate.of(Integer.parseInt(str[0])
				, Integer.parseInt(str[1]), Integer.parseInt(str[2]));
	}
	
	public String getSeatNum() {
		return seatNum;
	}
	
	public void setSeatNum(String seatNum) {
		this.seatNum = seatNum;
	}
}