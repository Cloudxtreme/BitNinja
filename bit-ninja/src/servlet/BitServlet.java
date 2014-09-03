package servlet;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;

import system.BitSystem;
import bean.Background;
import bean.Data;
import bean.Location;
import bean.Mission;
import bean.New;
import bean.Ninja;
import bean.User;
import dao.ActionDAO;
import dao.BackgroundDAO;
import dao.LocationDAO;
import dao.MissionDAO;
import dao.NewDAO;
import dao.NinjaDAO;
import dao.TechniqueDAO;
import dao.TrainerDAO;
import dao.UserDAO;


@WebServlet("/BitServlet")
public class BitServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public void updateAll(HttpSession session){
		loadNews(session);
		loadData(session);
		loadVillages(session);
		loadBackgrounds(session);
		loadMissions(session);
		loadTechniques(session);
		loadTrainers(session);
		if(session.getAttribute("user") != null){
			loadNinjasByPlayer(session);
			loadRemainingMissionTime(session);
			if(session.getAttribute("ninja") != null){
				loadAvailableMissions(session);
				loadAction(session);
				setRemainingMissionTime(session);
			}
		}
	}
	
	public void loadData(HttpSession session){
		int softcore = 0;
		int hardcore = 0;
		try{
			UserDAO uDao = new UserDAO();
			List<User> listUsers = uDao.loadAll();
			uDao.close();
			NinjaDAO nDao = new NinjaDAO();
			for(User u : listUsers){
				u.setNinjas(nDao.loadAll(u.getId()));
				for(Ninja n : u.getNinjas()){
					if(n.isHardcore()) hardcore++;
					else softcore++;
				}
			}
			nDao.close();
			//----------------------------------------
			Data d = new Data();
			d.setUserLogged(BitSystem.getOnlineIds().size());
			d.setHardcore(hardcore);
			d.setSoftcore(softcore);
			session.setAttribute("data", d);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	private void loadTrainers(HttpSession session) {
		try{
			TrainerDAO dao = new TrainerDAO();
			session.setAttribute("trainers", dao.loadAll());
			dao.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	private void loadTechniques(HttpSession session) {
		try{
			TechniqueDAO dao = new TechniqueDAO();
			session.setAttribute("techniques", dao.loadAll());
			dao.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	private void loadRemainingMissionTime(HttpSession session) {
		try{
			ActionDAO dao = new ActionDAO();
			dao.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	private void setRemainingMissionTime(HttpSession session) {
		try{
			ActionDAO dao = new ActionDAO();
			Date last = dao.getLastMissionRefresh((Ninja) session.getAttribute("ninja"));
			if(last != null){
				Date current = new Date();
				long diff = (current.getTime() - last.getTime()) / 1000;
				session.setAttribute("lastMissionRefresh", diff);
				dao.close();
			}else{
				session.removeAttribute("lastMissionRefresh");
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}

	public void loadNews(HttpSession session){
		try{
			NewDAO dao = new NewDAO();
			List<New> list = dao.loadMainNews();
			session.setAttribute("news", list);
			dao.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void loadMissions(HttpSession session){
		try{
			MissionDAO dao = new MissionDAO();
			List<Mission> missions = dao.loadAll();
			session.setAttribute("missions", missions);
			dao.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public int login(HttpSession session, String email, String password){
		try{
			UserDAO dao = new UserDAO();
			User u = dao.login(email,password);
			dao.close();
			if(u != null){
				if(BitSystem.pushId(u.getId())){
					session.setAttribute("user", u);
					return 0;
				}
				return 3;
			}
			return 2;
		}catch(Exception e){
			e.printStackTrace();
			return 2;
		}
	}
	
	public void register(User u, String password){
		try{
			UserDAO dao = new UserDAO();
			dao.save(u, password);
			dao.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void loadVillages(HttpSession session){
		try{
			LocationDAO dao = new LocationDAO();
			List<Location> list = dao.loadAll();
			Iterator<Location> iter = list.iterator();
			while (iter.hasNext()) {
			    if(!iter.next().getName().contains("Village")) {
			        iter.remove();
			    }
			}
			session.setAttribute("villages", list);
			dao.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void addLocation(HttpSession session, Location l){
		try{
			LocationDAO dao = new LocationDAO();
			dao.save(l);
			dao.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void loadBackgrounds(HttpSession session){
		try{
			BackgroundDAO dao = new BackgroundDAO();
			session.setAttribute("backgrounds", dao.loadAll());
			dao.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void addBackground(HttpSession session, Background b){
		try{
			BackgroundDAO dao = new BackgroundDAO();
			dao.save(b);
			dao.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void loadNinjasByPlayer(HttpSession session){
		try{
			User user = (User) session.getAttribute("user");
			NinjaDAO dao = new NinjaDAO();
			((User) session.getAttribute("user")).setNinjas(dao.loadAll(user.getId()));
			dao.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void selectNinja(HttpSession session,int ninja){
		User u = (User) session.getAttribute("user");
		for(Ninja n : u.getNinjas()){
			if(n.getId() == ninja){
				session.setAttribute("ninja", n);
				break;
			}
		}
	}
	
	public void addNinja(Ninja n, int user,int location){
		try{
			NinjaDAO dao = new NinjaDAO();
			dao.save(n, user, location);
			dao.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void setAcademyContent(HttpSession session){
		
	}
	
	public void doMission(HttpSession session, int missionId){
		Ninja n = ((Ninja) session.getAttribute("ninja"));
		if(n != null){
			try{
				MissionDAO mDao = new MissionDAO();
				Mission m = mDao.load(missionId);
				mDao.removeFromPool(n.getId(), missionId);
				mDao.close();
				ActionDAO aDao = new ActionDAO();
				aDao.setMissionAction(n.getId(), m.getId(), m.getDuration());
				aDao.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	
	public void loadAction(HttpSession session){
		try{
			Ninja n = (Ninja) session.getAttribute("ninja");
			ActionDAO dao = new ActionDAO();
			session.setAttribute("action", dao.loadAction(n));
			dao.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void loadAvailableMissions(HttpSession session){
		try{
			Ninja n = (Ninja) session.getAttribute("ninja");
			MissionDAO dao = new MissionDAO();
			List<Mission> list = dao.loadPool(n.getId());
			dao.close();
			session.setAttribute("availableMissions", list);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void refreshMissions(HttpSession session, Ninja n) {
		try{
			MissionDAO dao = new MissionDAO();
			dao.clearPool(n.getId());
			List<Mission> availableMissions = BitSystem.generateAvailableMissions(n, dao.loadAll());
			availableMissions = removeDuplicateMissions(availableMissions);
			for(Mission m : availableMissions){
				dao.addToPool(n.getId(), m.getId());
			}
			dao.close();
			ActionDAO aDao = new ActionDAO();
			aDao.setLastMissionRefresh(n);
			aDao.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	public List<Mission> removeDuplicateMissions(List<Mission> list){
		List<Mission> newList = new ArrayList<Mission>();
		for(Mission m : list){
			if(!newList.contains(m)) newList.add(m);
		}
		return newList;
	}
	
	public boolean doAcademyMission(HttpSession session,Ninja n){
		String missionTarget = "Academy Lesson #" + (n.getAcademyLevel() + 1);
		try{
			MissionDAO dao = new MissionDAO();
			List<Mission> list = dao.loadAll();
			dao.close();
			for(Mission m : list){
				if(m.getName().equals(missionTarget)){
					doMission(session, m.getId());
					return true;
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
