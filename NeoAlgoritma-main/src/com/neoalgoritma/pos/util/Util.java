package com.neoalgoritma.pos.util;

import javax.faces.context.FacesContext;
import org.bson.types.ObjectId;
import com.neoalgoritma.dao.UserPosDatabaseDAO;
import com.neoalgoritma.model.User;
import com.neoalgoritma.model.UserPosDatabase;
import com.neoalgoritma.util.Utils;;

public class Util extends Utils {

	public Util() {
		
	}
	
	public User getLoggedUser() {
		return (User) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("loggedUser");
	}
	
	public UserPosDatabase getUserPosDatabase(ObjectId userId) {
		UserPosDatabaseDAO userPosDatabaseDAO = new UserPosDatabaseDAO("neoalgoritma", "user_database_map");
    	return userPosDatabaseDAO.findUserDatabaseSetting(userId);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
