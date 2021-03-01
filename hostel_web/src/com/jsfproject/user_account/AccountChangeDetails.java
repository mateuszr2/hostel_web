package com.jsfproject.user_account;

import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
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
import com.jsf.dao.LogDAO;
import jpa_entities.ActionLog;

@Named
@ViewScoped
public class AccountChangeDetails implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
	FacesContext ctx;
	private User loaded;
	private User user = new User();
	private static final String PAGE_DETAILS = "/pages/user/detailsChange?faces-redirect=true";
	private static final String PAGE_ACCOUNT = "account?faces-redirect=true";
	private ActionLog actionlog = new ActionLog();
	@EJB
	UserDAO userDAO;
	@EJB
	LogDAO logDAO;
	
	public User getLoaded() {
		return loaded;
	}

	public void setLoaded(User loaded) {
		this.loaded = loaded;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	
	
	public String detailsPage() {
		return PAGE_DETAILS;

	}
	
	public String account() {
		return PAGE_ACCOUNT;

	}
	
	
	
	
	
	
	public void onLoad() throws IOException {
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		loaded = (User)RemoteClient.load(session).getDetails(); //if nie ma sesji
		// cleaning: attribute received => delete it from session
		if (loaded != null) {
			this.user = loaded;
			// session.removeAttribute("person");
		} else {
			ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "B³¹d u¿ycia systemu!", null));
			// if (!context.isPostback()) { //possible redirect
			// context.getExternalContext().redirect("personList.xhtml");
			// context.responseComplete();
			// }
		}
		
	}

	public void detailsChange() {
		try {
		userDAO.merge(this.user);
		ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Zapisano!", null));
		ctx.getExternalContext().getFlash().setKeepMessages(true);
		Date data = new Date();
		actionlog.setLog("Użytkownik o loginie : " + user.getLogin()+" zmienił swoje dane");
		actionlog.setDatetime(data);
		logDAO.create(actionlog);
		ctx.getExternalContext().redirect("http://localhost:8080/hostel_web/pages/user/account.xhtml");
		
		}catch(Exception e) {
			ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "B³¹d u¿ycia systemu", null));
		}
	}
 
}