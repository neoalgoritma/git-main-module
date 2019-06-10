package com.neoalgoritma.controller.pub;

import java.io.IOException;

import javax.faces.context.FacesContext;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.neoalgoritma.dao.UserDAO;
import com.neoalgoritma.model.User;
import com.neoalgoritma.util.Utils;

public class LoginFilter implements Filter {

	private Utils controllerUtils = new Utils();
	
	public LoginFilter() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {


		try {
			HttpServletRequest reqt = (HttpServletRequest) request;
			HttpServletResponse resp = (HttpServletResponse) response;
			HttpSession ses = reqt.getSession();
			
			if(ses != null && (ses.getAttribute("loggedUser") == null)){
				Cookie cookie = null;
				cookie = controllerUtils.getCookie(reqt,resp,"loggedUser");
				if(cookie != null) {
					System.out.println("############### Start cookie not null ###################");
					User user = new UserDAO("neoalgoritma","user").findOneById(cookie.getValue());
					System.out.println("# creating session #" + ses);
					ses.setAttribute("loggedUser", user);
					//HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
					//session.setAttribute("loggedUser", user);
					System.out.println("# session created #");
					//System.out.println("Setting session from cookie UserID: " + cookie.getValue());
					//System.out.println("ses" + ses.getAttribute("loggedUser"));
					System.out.println("############### END cookie not null ###################");
					
					
				}
				
			}
			
						
			String reqURI = reqt.getRequestURI();
			if (reqURI.indexOf("/login.xhtml") >= 0
					|| (ses != null && (ses.getAttribute("loggedUser") != null ))
					|| reqURI.indexOf("/public/") >= 0
					|| reqURI.contains("javax.faces.resource")
				) {
				
				chain.doFilter(request, response);
			}
			else {
				resp.sendRedirect(reqt.getContextPath() + "/web/public/login.xhtml");
			}
		} catch (IOException e) {
			System.out.println("error from login filter");
			System.out.println(e.getMessage());
			e.printStackTrace();
		}


	}

}
