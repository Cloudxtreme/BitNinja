package system;

import java.util.ArrayList;
import java.util.List;

import bean.Mission;
import bean.Ninja;
import dao.NinjaDAO;

public class BitSystem {
	private static String logPath = "C:/Users/raulh_000/Documents/Eclipse Luna/bit-ninja/WebContent/log/";
	private static List<Integer> onlineIds = new ArrayList<Integer>();
	
	public static String getLogPath() {
		return logPath;
	}

	public static void setLogPath(String logPath) {
		BitSystem.logPath = logPath;
	}
	
	public static boolean pushId(int id){
		if(BitSystem.onlineIds.contains(id)) return false;
		BitSystem.onlineIds.add(id);
		return true;
	}
	
	public static boolean removeId(int id){
		if(BitSystem.onlineIds.contains((Integer) id)){
			BitSystem.onlineIds.remove((Integer) id);
			return true;
		}
		return false;
	}
	
	public static List<Integer> getOnlineIds(){
		return BitSystem.onlineIds;
	}

	public static Ninja missionRewards(Ninja n, String missionRewards, String missionName){
		String ninjaRewardsString = "";
		String[] rewards = missionRewards.split(";");
		for(String reward : rewards){
			try{
				String[] div = reward.split("\\+");
				String type = div[0];
				String value = div[1];
				switch(type){
				case "exp":
					n.setExpCurrent(n.getExpCurrent() + Integer.parseInt(value));
					ninjaRewardsString += "+" + value + " Experience Points; ";
					break;
				case "gld":
					n.getBits().setGold(n.getBits().getGold() + Integer.parseInt(value));
					ninjaRewardsString += "+" + value + " Gold Bits; ";
					break;
				case "acd":
					n.setAcademyLevel(n.getAcademyLevel() + 1);
					break;
				}
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		n.setLastMission(missionName);
		n.setLastMissionRewards(ninjaRewardsString);
		try{
			NinjaDAO dao = new NinjaDAO();
			dao.update(n);
			dao.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		return n;
	}

	public static Ninja levelUp(Ninja n){
		if(n.getExpNeeded() > n.getExpCurrent()) return null;
		n.setExpCurrent(n.getExpCurrent() - n.getExpNeeded());
		n.setExpNeeded(n.getExpNeeded() + ((n.getExpNeeded() * 4) / 10));
		n.setLevel(n.getLevel() + 1);
		try{
			NinjaDAO dao = new NinjaDAO();
			dao.update(n);
			dao.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		return n;
	}
	
	public static List<Mission> generateAvailableMissions(Ninja n, List<Mission> allMissions ){
		if(n == null || allMissions == null)return null;
		List<Mission> available = new ArrayList<Mission>();
		for(int missionRolls = (n.getLevel() / 5) + 3;missionRolls > 0;missionRolls--){
			int chance = Roller.roll(100);
			if(chance < 10){
				continue;
			}else {
				addMission("D",allMissions,available);
			}
		}
		return available;
	}
	
	private static void addMission(String rank, List<Mission> allMissions, List<Mission> available){
		List<Mission> rollingMissions = new ArrayList<Mission>();
		for(Mission m : allMissions){
			if(m.getRankClass().equals(rank)){
				rollingMissions.add(m);
			}
		}
		int missionRoll = Roller.roll(rollingMissions.size()) - 1;
		if(missionRoll > 0) 
			available.add(rollingMissions.get(missionRoll));
	}
}
