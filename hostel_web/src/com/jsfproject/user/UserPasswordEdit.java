package com.jsfproject.user;

import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.simplesecurity.RemoteClient;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import com.jsf.dao.UserDAO;
import jpa_entities.User;
import login.PasswordEncode;
@Named
@ViewScoped
public class UserPasswordEdit implements Serializable {
	private static final String PAGE_USER_LIST = "userList?faces-redirect=true";
	private static final String PAGE_STAY_AT_THE_SAME = null;
	@EJB
	UserDAO userDAO;
	@Inject
	FacesContext ctx;
	@Inject
	FacesContext context;
	@Inject
	Flash flash;
	private String pass;
	private User userLogin;
	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getNewpass() {
		return newpass;
	}

	public void setNewpass(String newpass) {
		this.newpass = newpass;
	}

	public String getConfPass() {
		return confPass;
	}

	public void setConfPass(String confPass) {
		this.confPass = confPass;
	}

	private String newpass;
	private String confPass;
	private User user;
	private User userr;

	private User loaded = null;

	


	
	public void onLoad() throws IOException {
		
		loaded = (User) flash.get("user");
		

	
		if (loaded != null) {
			user = loaded;
			
		} else {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Niedozwolona operacja", null));
			
		}

	}
	
	
	
	public String changePass() {
		PasswordEncode pa = new PasswordEncode();
		if(this.getNewpass().equals(this.getConfPass())) {
			user.setPassword(pa.hash(this.getConfPass().toCharArray()));
			userDAO.merge(user);
			ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Hasło zostało zmienione", null));
		}else {
			ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Hasła się nie zgadzają", null));
			return PAGE_STAY_AT_THE_SAME;
		}
		return PAGE_USER_LIST;
		
		
	}
	
	public static boolean islogged() {
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		if(RemoteClient.load(session)==null) return false;
		else return true;

	}
 
}