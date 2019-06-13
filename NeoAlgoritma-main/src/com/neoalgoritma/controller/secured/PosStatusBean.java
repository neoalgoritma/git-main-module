package com.neoalgoritma.controller.secured;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import com.neoalgoritma.dao.UserPosDatabaseDAO;
import com.neoalgoritma.model.UserPosDatabase;
import com.neoalgoritma.model.User;

public class PosStatusBean implements Serializable {

	private static final long serialVersionUID = 1L;
	public User loggedUser;
	UserPosDatabase userPosDatabase;
	UserPosDatabaseDAO userPosDatabaseDAO;
	
	
	@PostConstruct
	public void init() {
		loggedUser = (User) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("loggedUser");
		userPosDatabaseDAO = new UserPosDatabaseDAO("neoalgoritma", "user_database_map");
		userPosDatabase = userPosDatabaseDAO.findUserDatabaseSetting(loggedUser.getId());
		
		
	}
	

	public UserPosDatabase getUserPosDatabase() {
		return userPosDatabase;
	}

	public void setUserPosDatabase(UserPosDatabase userPosDatabase) {
		this.userPosDatabase = userPosDatabase;
	}
		





}


		


