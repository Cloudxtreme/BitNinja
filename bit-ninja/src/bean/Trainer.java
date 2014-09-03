package bean;

import java.util.List;

public class Trainer {
	private int id;
	private String name;
	private String type;
	
	private List<Technique> techniques;
	
	public Trainer(){}

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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<Technique> getTechniques() {
		return techniques;
	}

	public void setTechniques(List<Technique> techniques) {
		this.techniques = techniques;
	}
	
	
}
