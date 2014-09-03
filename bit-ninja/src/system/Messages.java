package system;

import java.util.HashMap;



public class Messages {
	private HashMap<String,String> messages;
	
	public Messages(){
		this.messages = new HashMap<String, String>();
		generateErrorMessages();
		generateInfoMessages();
	}
	
	public String get(String key){
		if(this.messages.containsKey(key))
			return this.messages.get(key);
		return "Undefined error";
	}

	private void generateErrorMessages() {
		this.messages.put("no-ninja", "It was not possible to execute that action because there was no"
				+ " character selected.");
		this.messages.put("illegal-action", "There was an attempt to execute an illegal action. If you were"
				+ " not responsiple for it, please submit a ticket explaining your situation.");
	}
	
	private void generateInfoMessages() {
		this.messages.put("mission-success", "Congratulations! The mission <b>#1#</b> was "
				+ "succesfully finished. Your character received the following bonus(es):<br>"
				+ "<b>#2#</b>");
	}
}
