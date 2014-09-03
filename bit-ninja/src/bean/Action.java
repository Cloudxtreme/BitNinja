package bean;

import java.io.Serializable;
import java.util.Date;

public class Action implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Mission mission;
	private String training;
	private String trainingTitle;
	private Date end;
	private boolean finished;
	
	public Action(){}

	public Mission getMission() {
		return mission;
	}

	public void setMission(Mission mission) {
		this.mission = mission;
	}

	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end = end;
	}

	public boolean isFinished() {
		return finished;
	}

	public void setFinished(boolean finished) {
		this.finished = finished;
	}

	public String getTraining() {
		return training;
	}

	public void setTraining(String training) {
		this.training = training;
	}

	public String getTrainingTitle() {
		return trainingTitle;
	}

	public void setTrainingTitle(String trainingTitle) {
		this.trainingTitle = trainingTitle;
	}
	
	
}
