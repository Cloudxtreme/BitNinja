package bean;

import java.io.Serializable;

public class Mission implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String name;
	private String description;
	private String rank;
	private String rankClass;
	private String rewardsFailure;
	private String rewardsSuccess;
	private int duration;
	
	public Mission(){}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getRank() {
		return rank;
	}

	public void setRank(String rank) {
		this.rank = rank;
	}

	public String getRewardsFailure() {
		return rewardsFailure;
	}

	public void setRewardsFailure(String rewardsFailure) {
		this.rewardsFailure = rewardsFailure;
	}

	public String getRewardsSuccess() {
		return rewardsSuccess;
	}

	public void setRewardsSuccess(String rewardsSuccess) {
		this.rewardsSuccess = rewardsSuccess;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public String getRankClass() {
		return rankClass;
	}

	public void setRankClass(String rankClass) {
		this.rankClass = rankClass;
	}
	
	@Override
	public boolean equals(Object obj){
		if(this == obj) return true;
		if(!(obj instanceof Mission)) return false;
		Mission m = (Mission) obj;
		if(this.getId() == m.getId())
			return true;
		else
			return false;
	}
}
