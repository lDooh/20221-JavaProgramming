package DTO;

public class movieDTO {
	private String title;
	private int runningTime;
	
	public movieDTO(String title, int runningTime) {
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