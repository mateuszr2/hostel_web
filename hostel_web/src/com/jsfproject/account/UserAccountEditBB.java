/*package com.jsfproject.account;

import java.io.IOException;
import java.io.Serializable;
import java.util.Date;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.simplesecurity.RemoteClient;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import com.jsf.dao.RoleDAO;
import com.jsf.dao.UserDAO;
import jpa_entities.User;
import login.PasswordEncode;
import com.jsf.dao.LogDAO;
import jpa_entities.ActionLog;

@Named
@ViewScoped
public class UserAccountEditBB implements Serializable {
	private static final long serialVersionUID = 1L;

	private static final String PAGE_ACCOUNT = "account?faces-redirect=true";
	private static final String PAGE_STAY_AT_THE_SAME = null;

	private User user = new User();
	private User loaded = null;
	private HttpSession session;
	private User userloaded = null;
	@EJB
	UserDAO userDAO;
	@EJB
	RoleDAO roleDAO;
	@EJB
	LogDAO logDAO;
	@Inject
	FacesContext context;

	@Inject
	Flash flash;

	private ActionLog actionlog = new ActionLog();
	private String login;
	public User getUser() {
		return user;
	}
	
	
	public void onLoad() throws IOException {
	
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		userloaded = (User)RemoteClient.load(session).getDetails();    

	
		if (loaded != null) {
			user = userloaded;
			
		} else {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Niedozwolona operacja", null));
			
		}

	}

	public String saveData() {
		
		session = (HttpSession) context.getExternalContext().getSession(false);
		
		if (loaded == null) {
			return PAGE_STAY_AT_THE_SAME;
		}

		try {
		
				
				userDAO.merge(user);
				Date data = new Date();
				actionlog.setLog("user: " + user.getLogin()+" edytowa³ informacje na swoim koncie");
				actionlog.setDatetime(data);
				logDAO.create(actionlog);
				
		
		} catch (Exception e) {
			e.printStackTrace();
			context.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "B³¹d podczas zapisu", null));
			return PAGE_STAY_AT_THE_SAME;
		}

		return PAGE_ACCOUNT;
	}
}
*/