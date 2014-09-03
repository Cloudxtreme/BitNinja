package bean;

import java.io.Serializable;

public class Background implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String name;
	private String description;
	private String bonuses;
	
	public Background(){}

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

	public String getBonuses() {
		return bonuses;
	}

	public void setBonuses(String bonuses) {
		this.bonuses = bonuses;
	}
	
	
}
