package com.neoalgoritma.controller.secured;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.PrimeFaces;

import com.neoalgoritma.dao.NeoAlgoritmaPosUserDatabaseDAO;
import com.neoalgoritma.model.NeoAlgoritmaPosUserDatabase;
import com.neoalgoritma.model.User;

public class PosSettingBean implements Serializable {

	private static final long serialVersionUID = 1L;
	public String databaseRePassword;
	public User loggedUser;
	NeoAlgoritmaPosUserDatabase posSetting;
	NeoAlgoritmaPosUserDatabaseDAO neoAlgoritmaPosUserDatabaseDAO;
	
	@PostConstruct
	public void init() {
		loggedUser = (User) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("loggedUser");
		neoAlgoritmaPosUserDatabaseDAO = new NeoAlgoritmaPosUserDatabaseDAO("neoalgoritma", "pos_user_database");
		posSetting = new NeoAlgoritmaPosUserDatabase();
		if(neoAlgoritmaPosUserDatabaseDAO.findUserDatabaseSetting(loggedUser.getId()) != null) {
			System.out.println("dataabaseSetting found");
			posSetting = neoAlgoritmaPosUserDatabaseDAO.findUserDatabaseSetting(loggedUser.getId());
			databaseRePassword = posSetting.getDatabasePassword();
		}
		
	}


	public void save() {
		if(!posSetting.getDatabasePassword().equals(databaseRePassword)) {
			FacesContext.getCurrentInstance().addMessage("posSettingForm:databaseRePassword", new FacesMessage(FacesMessage.SEVERITY_ERROR, 
					"Password entered not the same", "Password entered not the same"));
		}
		
		else {
			if(posSetting.getId() == null) {
				if(neoAlgoritmaPosUserDatabaseDAO.databaseNameTaken(posSetting.getDatabaseName(),loggedUser.getId())) {
					FacesContext.getCurrentInstance().addMessage("posSettingForm:databaseName", new FacesMessage(FacesMessage.SEVERITY_ERROR, 
							"Database name already taken", "Database name taken, please use another name"));
				}
				else {
					posSetting.setUserId(loggedUser.getId());
					neoAlgoritmaPosUserDatabaseDAO.insert(posSetting);
					PrimeFaces.current().executeScript("redirectTo('Setting','Saved','success','../index.xhtml');");
				}
				//System.out.println("New posSetting");
			}
			else {
				if(neoAlgoritmaPosUserDatabaseDAO.databaseNameTaken(posSetting.getDatabaseName(),loggedUser.getId())) {
					FacesContext.getCurrentInstance().addMessage("posSettingForm:databaseName", new FacesMessage(FacesMessage.SEVERITY_ERROR, 
							"Database name already taken", "Database name taken, please use another name"));
				}
				else {
					neoAlgoritmaPosUserDatabaseDAO.updateOneById(posSetting.getId().toString(), posSetting);
					PrimeFaces.current().executeScript("redirectTo('Setting','Updated','success','../index.xhtml');");
				}
				//System.out.println("update posSetting");
			}
		}
		//System.out.println("Saving pos setting...");
	}


	public String getDatabaseRePassword() {
		return databaseRePassword;
	}


	public void setDatabaseRePassword(String databaseRePassword) {
		this.databaseRePassword = databaseRePassword;
	}


	public NeoAlgoritmaPosUserDatabase getPosSetting() {
		return posSetting;
	}


	public void setPosSetting(NeoAlgoritmaPosUserDatabase posSetting) {
		this.posSetting = posSetting;
	}

	
	
	
	
	
	
	
	

}
