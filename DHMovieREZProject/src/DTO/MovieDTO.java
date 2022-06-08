package DTO;

public class MovieDTO {
	private String title;
	private String runningTime;		// hhmmss 6자리 형식으로 저장
	
	public MovieDTO(String title, String runningTime) {
		this.title = title;
		if (runningTime.length() == 8)
		{
			String[] str = runningTime.split(":");
			this.runningTime = str[0] + str[1] + str[2];
		}
		else
		{
			this.runningTime = runningTime;
		}
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getRunningTime() {
		return runningTime;
	}
	
	public void setRunningTime(String runningTime) {
		this.runningTime = runningTime;
	}
}