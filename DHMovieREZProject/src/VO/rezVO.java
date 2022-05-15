package VO;

import java.time.LocalDate;

public class rezVO {
	private String id;
	private String title;
	private LocalDate mDate;
	private String seatNum;
	
	public rezVO(String id, String title, LocalDate mDate, String seatNum) {
		this.id = id;
		this.title = title;
		this.mDate = mDate;
		this.seatNum = seatNum;
	}

	public static void main(String[] args) {

	}
}