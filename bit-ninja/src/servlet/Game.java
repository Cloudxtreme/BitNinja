package servlet;

import java.io.IOException;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import system.Messages;
import bean.Action;
import bean.Ninja;

@WebServlet("/game")
public class Game extends BitServlet{
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
		
		//Makes sure the page has been rendered through this servlet
		request.setAttribute("servletRendered", "true");
		
		//gets the page string from the GET reference
		String page = "";
		if(request.getParameter("page") != null)
			page = request.getParameter("page");
		
		//checks if there is an error to display
		if(request.getParameter("error") != null){
			String error = request.getParameter("error");
			Messages m = new Messages();
			request.setAttribute("msgType", "error");
			request.setAttribute("msgMessage", m.get(error));
		}
		
		if(request.getParameter("info") != null){
			String info = request.getParameter("info");
			Messages m = new Messages();
			request.setAttribute("msgType", "info");
			if(info.split("-")[0].equals("mission")){
				request.setAttribute("msgMessage", writeMissionMessage(session));
			}else{
				request.setAttribute("msgMessage", m.get(info));
			}
		}
		
		//sets the redirect String with it's default value
		String forward = "/game-folder/";
		
		//updates every bean
		updateAll(session);
		
		//
		if(session.getAttribute("action") != null){
			setRemainingTime(request,(Action) session.getAttribute("action"));
		}
		
		//Redirects to the selected page
		switch(page){
		case "index":
			if(session.getAttribute("user") != null)
				forward = "/game-folder/";
			else
				forward = "/";
			break;
		case "ninja":
			forward = "game-folder/?page=ninja";
			break;
		case "register":
			forward = "?page=register";
			break;
		case "create":
			forward = "game-folder/?page=create";
			break;
		case "select":
			forward = "game-folder/?page=select";
			break;
		case "main":
			forward = "game-folder/?page=main";
			break;
		case "academy":
			forward = "game-folder/?page=academy";
			break;
		case "shop":
			forward = "game-folder/?page=shop";
			break;
		case "hospital":
			forward = "game-folder/?page=hospital";
			break;
		case "headquarters":
			forward = "game-folder/?page=headquarters";
			break;
		case "clans":
			forward = "game-folder/?page=clans";
			break;
		case "inventory":
			forward = "game-folder/?page=inventory";
			break;
		}
		RequestDispatcher rd = request.getRequestDispatcher(forward);
		rd.forward(request, response);
		
		//response.sendRedirect(redirect);
	}

	private String writeMissionMessage(HttpSession session) {
		if(session.getAttribute("ninja") != null){
			Ninja n = (Ninja) session.getAttribute("ninja");
			String returnString = "Congratulations! The mission <b>"
				+	n.getLastMission()
				+ "</b> was "
				+ "succesfully finished. Your character received the following bonus(es):<br>"
				+ "<b>"
				+ n.getLastMissionRewards()
				+ "</b>";
			n.setLastMission(null);
			n.setLastMissionRewards(null);
			return returnString;
		}
		return null;
	}

	private void setRemainingTime(HttpServletRequest request, Action a) {
		Date end = a.getEnd();
		Date now = new Date();
		long diff = (end.getTime() - now.getTime()) / 1000;
		request.setAttribute("actionRemainingTime", diff);
	}

}
