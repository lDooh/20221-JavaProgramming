package DTO;

public class RezDTO {
	private String id;
	private String title;
	private String mDate;		// yyyymmdd 8자리 형식으로 저장
	private String mTime;		// hhmmss 6자리 형식으로 저장
	private String seatNum;
	
	public RezDTO(String id, String title, String mDate, String mTime, String seatNum) {
		this.id = id;
		this.title = title;
		if (mDate.length() == 10)
		{
			String[] str = mDate.split("-");
			this.mDate = str[0] + str[1] + str[2];
		}
		else
		{
			this.mDate = mDate;
		}
		if (mTime.length() == 8)
		{
			String[] str = mTime.split(":");
			this.mTime = str[0] + str[1] + str[2];
		}
		else
		{
			this.mTime = mTime;
		}
		this.mTime = mTime;
		this.seatNum = seatNum;
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getMDate() {
		return mDate;
	}
	
	public void setMDate(String mDate) {
		this.mDate = mDate;
	}
	
	public String getMTime() {
		return mTime;
	}
	
	public void setMTime(String mTime) {
		this.mTime = mTime;
	}
	
	public String getSeatNum() {
		return seatNum;
	}
	
	public void setSeatNum(String seatNum) {
		this.seatNum = seatNum;
	}
}