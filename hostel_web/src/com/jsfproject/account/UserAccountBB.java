/*package com.jsfproject.account;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.ejb.EJB;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.simplesecurity.RemoteClient;
import javax.servlet.http.HttpSession;

import com.jsf.dao.UserDAO;
import jpa_entities.User;

@Named
@RequestScoped
public class UserAccountBB {
	private static final String PAGE_ACCOUNT_EDIT = "accountEdit?faces-redirect=true";
	private static final String PAGE_STAY_AT_THE_SAME = null;

	private String name;
	HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
	userloaded = (User)RemoteClient.load(session).getDetails();    
	
	@Inject
	ExternalContext extcontext;
	
	@Inject
	Flash flash;
	
	@EJB
	UserDAO userDAO;
		
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<User> getList(){
		List<User> list = null;
		
	
		Map<String,Object> searchParams = new HashMap<String, Object>();
		
		if (name != null && name.length() > 0){
			searchParams.put("name", name);
		}
		
		
		list = userDAO.getList(searchParams);
		
		return list;
	}

	

	public String editAccount(User user){
		
		flash.put("user", user);
		
		return PAGE_ACCOUNT_EDIT;
	}

	
}
*/