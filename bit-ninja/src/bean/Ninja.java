package bean;

import java.io.Serializable;
import java.util.List;

public class Ninja implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String name;
	private int level;
	private int expCurrent;
	private int expNeeded;
	private int healthCurrent;
	private int healthMax;
	private boolean isHardcore;
	private String graduation;
	private int academyLevel;
	
	private Attributes attributes;
	private Bits bits;
	private Location location;
	private List<Technique> techniques;
	
	private String lastMission;
	private String lastMissionRewards;
	
	public Ninja(){}

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

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getExpCurrent() {
		return expCurrent;
	}

	public void setExpCurrent(int expCurrent) {
		this.expCurrent = expCurrent;
	}

	public int getExpNeeded() {
		return expNeeded;
	}

	public void setExpNeeded(int expNeeded) {
		this.expNeeded = expNeeded;
	}

	public int getHealthCurrent() {
		return healthCurrent;
	}

	public void setHealthCurrent(int healthCurrent) {
		this.healthCurrent = healthCurrent;
	}

	public int getHealthMax() {
		return healthMax;
	}

	public void setHealthMax(int healthMax) {
		this.healthMax = healthMax;
	}

	public Attributes getAttributes() {
		return attributes;
	}

	public void setAttributes(Attributes attributes) {
		this.attributes = attributes;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public Bits getBits() {
		return bits;
	}

	public void setBits(Bits bits) {
		this.bits = bits;
	}

	public boolean isHardcore() {
		return isHardcore;
	}

	public void setHardcore(boolean isHardcore) {
		this.isHardcore = isHardcore;
	}

	public String getLastMission() {
		return lastMission;
	}

	public void setLastMission(String lastMission) {
		this.lastMission = lastMission;
	}

	public String getLastMissionRewards() {
		return lastMissionRewards;
	}

	public void setLastMissionRewards(String lastMissionRewards) {
		this.lastMissionRewards = lastMissionRewards;
	}

	public String getGraduation() {
		return graduation;
	}

	public void setGraduation(String graduation) {
		this.graduation = graduation;
	}

	public int getAcademyLevel() {
		return academyLevel;
	}

	public void setAcademyLevel(int academyLevel) {
		this.academyLevel = academyLevel;
	}

	public List<Technique> getTechniques() {
		return techniques;
	}

	public void setTechniques(List<Technique> techniques) {
		this.techniques = techniques;
	}
	
}
