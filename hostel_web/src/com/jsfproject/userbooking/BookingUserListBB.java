package com.jsfproject.userbooking;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.simplesecurity.RemoteClient;
import javax.servlet.http.HttpSession;

import com.jsf.dao.BookingDAO;
import com.jsf.dao.RoomDAO;
import com.jsf.dao.UserDAO;
import jpa_entities.RoomBooking;
import jpa_entities.Room;
import jpa_entities.User;




@Named
@RequestScoped
public class BookingUserListBB {
	

	
	
	private User userloaded = null;
	
	@Inject
	ExternalContext extcontext;
	
	@Inject
	Flash flash;
	
	@EJB
	BookingDAO bookingDAO;
	@EJB
	RoomDAO roomDAO;
	@EJB
	UserDAO userDAO;
	

	
	
	public List <RoomBooking> getBookingUserList() {
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true); //if nie ma sesji
		User user = new User();
		user = (User)RemoteClient.load(session).getDetails();
		return bookingDAO.getBookingUserList(user);
		
	}

	

	
}
