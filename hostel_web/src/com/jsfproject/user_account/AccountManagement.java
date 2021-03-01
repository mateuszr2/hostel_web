package com.jsfproject.user_account;

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
public class AccountManagement implements Serializable {
	@EJB
	UserDAO userDAO;
	@Inject
	FacesContext ctx;
	
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
	

	private static final String PAGE_ACCOUNT = "/pages/user/account?faces-redirect=true";

	public String accountPage() {
		return PAGE_ACCOUNT;

	}
	

	
	private boolean checkPass(){
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		if(RemoteClient.load(session)!=null) {
			this.userr = (User)RemoteClient.load(session).getDetails();
			this.user = (User)RemoteClient.load(session).getDetails();
			userLogin = userDAO.getloginAccount(userr.getLogin(), getPass());
			if(this.userLogin != null){
		//if(user.getPassword().equals(this.getPass())) {
				return true; 
			}else ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Stare haslo jest niepoprawne", null));
	}
		return false;
	}
	
	public void changePass() {
		PasswordEncode pa = new PasswordEncode();
		if(this.checkPass() && this.getNewpass().equals(this.getConfPass())) {
			user.setPassword(pa.hash(this.getConfPass().toCharArray()));
			userDAO.merge(user);
			ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Hasło zostało zmienione", null));
		}else {
			ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Hasła się nie zgadzają", null));
		}
		
		
	}
	
	public static boolean islogged() {
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		if(RemoteClient.load(session)==null) return false;
		else return true;

	}
 
}