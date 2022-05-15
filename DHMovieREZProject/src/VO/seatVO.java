package VO;

import java.time.LocalDate;

public class seatVO {
	private String title;
	private LocalDate mDateLD;
	private String seatNum;
	
	public seatVO(String title, LocalDate mDateLD, String seatNum) {
		this.title = title;
		this.mDateLD = mDateLD;
		this.seatNum = seatNum;
	}

	public static void main(String[] args) {

	}
}