package servlet;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import system.BitSystem;
import system.Logger;
import bean.Action;
import bean.Attributes;
import bean.Background;
import bean.Bits;
import bean.Location;
import bean.Ninja;
import bean.User;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;

import dao.ActionDAO;



@WebServlet("/Control")
public class Control extends BitServlet{
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		processRequest(request,response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request,response);
	}
	
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//gets the current session
		HttpSession session = request.getSession();
		
		//gets the request ip address
		String ipAddress  = request.getHeader("X-FORWARDED-FOR");
		if(ipAddress == null) ipAddress = request.getRemoteAddr();
		
		//gets the action string from the GET reference
		String action = "";
		if(request.getParameter("action") != null)
			action = request.getParameter("action");
		
		//gets the object of the action in case there is one
		String object = "";
		if(request.getParameter("object") != null)
			object = request.getParameter("object");
		
		//sets the redirect String with it's default value
		String redirect = "game";
		
		//updates every bean
		updateAll(session);
		switch(action){
		case "register":
			User u = new User();
			u.setEmail(request.getParameter("bit-email"));
			u.setName(request.getParameter("bit-name"));
			register(u, request.getParameter("bit-password"));
			Logger.log(ipAddress, request.getParameter("bit-name") + " registered on the website");
			break;
		case "login":
			if(request.getParameter("bit-email").length() < 1
					|| request.getParameter("bit-password").length() <= 1){
				redirect = "?error=1";
			}else{
				int error = login(session, request.getParameter("bit-email"), 
						request.getParameter("bit-password"));
				if(error == 0){
					loadNinjasByPlayer(session);
					redirect = "game";
				}else{
					redirect = "?error=" + error;
				}
			}
			if(session.getAttribute("user") != null){
				Logger.log(ipAddress,((User) session.getAttribute("user")).getName() 
						+ " has successfuly logged in");
			}
			break;
		case "logout":
			if(session.getAttribute("user") != null){
				Logger.log(ipAddress,((User) session.getAttribute("user")).getName() 
						+ " has successfuly logged out");
				BitSystem.removeId(((User) session.getAttribute("user")).getId());
			}
			session.invalidate();
			break;
		case "selectNinja":
			if(request.getParameter("ninja") != null){
				int ninja = Integer.parseInt(request.getParameter("ninja"));
				selectNinja(session,ninja);
			}
			if(session.getAttribute("user") != null){
				Logger.log(ipAddress,((User) session.getAttribute("user")).getName() 
						+ " has selected the ("
						+ ((Ninja) session.getAttribute("ninja")).getName()
						+ ") ninja");
			}
			break;
		case "goToAcademy":
			setAcademyContent(session);
			redirect = "game?page=academy";
			break;
		case "add":
			switch(object){
			case "location":
				Location l = new Location();
				l.setName(request.getParameter("bit-name"));
				l.setDescription(request.getParameter("bit-description"));
				addLocation(session, l);
				break;
			case "background":
				Background ba = new Background();
				ba.setName(request.getParameter("bit-bg-name"));
				ba.setDescription(request.getParameter("bit-bg-description"));
				ba.setBonuses(request.getParameter("bit-bg-bonuses"));
				addBackground(session, ba);
				break;
			case "ninja":
				Attributes a = new Attributes();
				a.setEndurance(Integer.parseInt(request.getParameter("bit-ninja-endurance")));
				a.setIntelligence(Integer.parseInt(request.getParameter("bit-ninja-intelligence")));
				a.setResistance(Integer.parseInt(request.getParameter("bit-ninja-resistance")));
				a.setSpeed(Integer.parseInt(request.getParameter("bit-ninja-speed")));
				a.setStrength(Integer.parseInt(request.getParameter("bit-ninja-strength")));
				a.setWisdom(Integer.parseInt(request.getParameter("bit-ninja-wisdom")));
				Bits b = new Bits();
				b.setBlack(0);
				b.setBlue(0);
				b.setGold(0);
				b.setGray(0);
				b.setGreen(0);
				b.setRed(0);
				Ninja n = new Ninja();
				n.setAttributes(a);
				n.setBits(b);
				n.setExpCurrent(0);
				n.setExpNeeded(100);
				n.setHardcore(Boolean.parseBoolean(request.getParameter("bit-ninja-hardcore")));
				n.setGraduation("Ninja Student");
				n.setAcademyLevel(0);
				//Calculates Health
				int health = 100 + ((100 * a.getEndurance()) / 100);
				//-----------------
				n.setHealthCurrent(health);
				n.setHealthMax(health);
				n.setLevel(1);
				n.setName(request.getParameter("bit-ninja-name"));
				//Gets user and starting location
				int location = Integer.parseInt(request.getParameter("bit-ninja-village"));
				int user = ((User) session.getAttribute("user")).getId();
				addNinja(n,user,location);
				break;
			}
		case "doMission":
			try{
				int missionId = Integer.parseInt(object);
				doMission(session, missionId);
				redirect = "game?page=headquarters";
			}catch(ParseException e){
				e.printStackTrace();
			}
			break;
		case "doAcademyMission":
			if(session.getAttribute("ninja") != null){
				Ninja n = (Ninja) session.getAttribute("ninja");
				if(doAcademyMission(session, n)){
					redirect = "game?page=academy";
				}else{
					redirect = "game?error=default";
				}
			}else{
				redirect = "game?error=no-ninja";
			}
			break;
		case "finish":
			if(session.getAttribute("action") != null){
				Action a = (Action) session.getAttribute("action");
				Ninja n = (Ninja) session.getAttribute("ninja");
				Date now = new Date();
				if(a.getEnd().before(now)){
					if(a.getMission() != null){
						Ninja nu = BitSystem.missionRewards(n, a.getMission().getRewardsSuccess(), 
								a.getMission().getName());
						try{
							ActionDAO dao = new ActionDAO();
							dao.finish(nu);
							dao.close();
							redirect = "game?page=index&info=mission-success";
						}catch(Exception e){
							e.printStackTrace();
						}
					}
				}
			}else{
				redirect = "game?error=illegal-action";
			}
			break;
		case "levelUp":
			if(session.getAttribute("ninja") != null){
				Ninja n = (Ninja) session.getAttribute("ninja");
				session.setAttribute("ninja", BitSystem.levelUp(n));
				redirect = "game?page=ninja";
			}else{
				redirect = "game?error=no-ninja";
			}
			break;
		case "refreshMissions":
			if(session.getAttribute("ninja") != null){
				Ninja n = (Ninja) session.getAttribute("ninja");
				refreshMissions(session,n);
				redirect = "game?page=headquarters";
			}else{
				redirect = "game?error=no-ninja";
			}
			break;
		}
		response.sendRedirect(redirect);
	}
	
}
