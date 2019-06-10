package com.neoalgoritma.controller.pub;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.primefaces.PrimeFaces;

import com.neoalgoritma.dao.UserDAO;
import com.neoalgoritma.model.User;
import com.neoalgoritma.util.Utils;

public class LoginBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private String email;
	private String password;
	private User user;
	private boolean remember = false;
	private Utils controllerUtils = new Utils();
	private UserDAO userDAO;
	
	PrimeFaces current = PrimeFaces.current();
	
	
	@PostConstruct
	public void init() {
		
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();	
		HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
		
		userDAO = new UserDAO("neoalgoritma","user");
		
		if (controllerUtils.getCookie(request,response,"loggedUser") != null){
			System.out.println("cookie found........");
			//user = userDAO(Integer.valueOf(controllerUtils.getCookie(request,response,"loggedUser").getValue()));
			//session.setAttribute("loggedUser", user);
			//System.out.println("before redirect cookie found........");
			//current.executeScript("redirectToMainLogin();");
			//System.out.println("after redirect found...........");
		}
		else {
			System.out.println("cookie not found........");
			//user = new UserDAO();	
		}
		
		System.out.println("login bean called ------ ");
	
	}

	public void login() throws Exception {
		System.out.println("login");
		System.out.println("email:" + email);
		if(email.trim().length() > 0 && password.trim().length() > 0) {
			System.out.println("credential checked------------------");
			user = userDAO.getUserLogin(email, password);
			System.out.println("user created............");
			if(user.getId() != null) {
				
				HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
				session.setAttribute("loggedUser", user);
				if(remember == true) {
					controllerUtils.setCookie("loggedUser", String.valueOf(user.getId()), 10000);
				}
				System.out.println("before redirect from login........");
				current.executeScript("redirectToMainLogin();");
				System.out.println("after redirect from login...........");
			}
			else {
				//current.executeScript("M.validate_field();");
				FacesContext.getCurrentInstance().addMessage("loginForm:email", new FacesMessage(FacesMessage.SEVERITY_ERROR, 
						"Invalid email address", "Invalid login credential"));
			}
		}
		
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


	public boolean isRemember() {
		return remember;
	}

	public void setRemember(boolean remember) {
		this.remember = remember;
	}
	
	

}
