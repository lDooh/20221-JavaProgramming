package DTO;

public class MovieDTO {
	private String title;
	private int runningTime;
	
	public MovieDTO(String title, int runningTime) {
		this.title = title;
		this.runningTime = runningTime;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public int getRunningTime() {
		return runningTime;
	}
	
	public void setRunningTime(int runningTime) {
		this.runningTime = runningTime;
	}
}