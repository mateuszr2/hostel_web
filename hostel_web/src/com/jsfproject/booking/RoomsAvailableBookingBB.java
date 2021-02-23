package com.jsfproject.booking;

import java.io.IOException;
import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.simplesecurity.RemoteClient;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import com.jsf.dao.RoomDAO;
import com.jsf.dao.UserDAO;
import com.jsf.dao.BookingDAO;
import jpa_entities.Room;
import jpa_entities.RoomBooking;
import jpa_entities.User;

@Named
@ViewScoped
public class RoomsAvailableBookingBB implements Serializable {
	private static final long serialVersionUID = 1L;

	private static final String PAGE_ROOMS_LIST = "roomsAvailableList?faces-redirect=true";
	private static final String PAGE_STAY_AT_THE_SAME = null;

	private Room room = new Room();
	private RoomBooking roombooking = new RoomBooking();
	private Room loaded = null;
	private User user = new User();
	private User userloaded = null;
	@EJB
	RoomDAO roomDAO;
	@EJB
	BookingDAO bookingDAO;
	@EJB
	UserDAO userDAO;
	
	@Inject
	FacesContext context;

	@Inject
	Flash flash;

	public Room getRoom() {
		return room;
	}
	public RoomBooking getRoomBooking() {
		return roombooking;
	}
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	public void onLoad() throws IOException {

		
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		userloaded = (User)RemoteClient.load(session).getDetails();    
		loaded = (Room) flash.get("room");

		
		if (loaded != null) {
			room = loaded;
			
		} else {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Nieprawid³owa opracja", null));
		
		}

	}
	
	public String saveData() {
			try {
			
			
				
				this.roombooking.setUser(this.user);
				
				
				
				
		
				bookingDAO.create(this.roombooking);
				bookingDAO.create(roombooking);
			
		} catch (Exception e) {
			e.printStackTrace();
			context.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "B³¹d zapisu", null));
			return PAGE_STAY_AT_THE_SAME;
		}

		return PAGE_ROOMS_LIST;
	}
}
