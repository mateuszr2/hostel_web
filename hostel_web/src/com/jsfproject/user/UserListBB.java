package com.jsfproject.user;

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
import javax.servlet.http.HttpSession;

import com.jsf.dao.UserDAO;
import jpa_entities.User;
import jpa_entities.Role;
import jpa_entities.Room;
@Named
@RequestScoped
public class UserListBB {
	private static final String PAGE_USER_EDIT = "userEdit?faces-redirect=true";
	private static final String PAGE_USER_ADD = "userAdd?faces-redirect=true";
	private static final String PAGE_STAY_AT_THE_SAME = null;
	private static final String PAGE_PASSWORD_EDIT = "userPasswordEdit?faces-redirect=true";
	private String name;
		
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

	public String newUser(){
		User user = new User();
		
		
		flash.put("user", user);
		
		return PAGE_USER_ADD;
	}

	public String editUser(User user){
		
		flash.put("user", user);
		
		return PAGE_USER_EDIT;
	}
public String passwordUserEdit(User user){
		
		flash.put("user", user);
		
		return PAGE_PASSWORD_EDIT;
	}

	public String deleteUser(User user){
		userDAO.remove(user);
		return PAGE_STAY_AT_THE_SAME;
	}
}
