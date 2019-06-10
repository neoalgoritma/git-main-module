package com.neoalgoritma.controller.secured;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import com.neoalgoritma.dao.UserDAO;
import com.neoalgoritma.model.User;

public class SecuredIndexBean implements Serializable {

	private static final long serialVersionUID = 1L;
	public User loggedUser; 
	public UserDAO userDAO;

	
	@PostConstruct
	public void init() {
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		userDAO = new UserDAO("neoalgoritma", "user");
		if(session.getAttribute("loggedUser") != null) {
			loggedUser = (User) session.getAttribute("loggedUser");
		}
		
		System.out.println(loggedUser);

	}


	public User getLoggedUser() {
		return loggedUser;
	}


	public void setLoggedUser(User loggedUser) {
		this.loggedUser = loggedUser;
	}
	
	
	
	
	
}
