package bean;

import java.io.Serializable;

public class Bits implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int black;
	private int blue;
	private int gold;
	private int gray;
	private int green;
	private int red;
	
	public Bits(){}

	public int getBlack() {
		return black;
	}

	public void setBlack(int black) {
		this.black = black;
	}

	public int getBlue() {
		return blue;
	}

	public void setBlue(int blue) {
		this.blue = blue;
	}

	public int getGold() {
		return gold;
	}

	public void setGold(int gold) {
		this.gold = gold;
	}

	public int getGray() {
		return gray;
	}

	public void setGray(int gray) {
		this.gray = gray;
	}

	public int getGreen() {
		return green;
	}

	public void setGreen(int green) {
		this.green = green;
	}

	public int getRed() {
		return red;
	}

	public void setRed(int red) {
		this.red = red;
	}
	
	
}
