package com.jsfproject.menage_booking;

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

import com.jsf.dao.BookingDAO;
import jpa_entities.RoomBooking;

@Named
@RequestScoped
public class BookingListBB {
	private static final String PAGE_BOOKING_EDIT = "bookingEdit?faces-redirect=true";
	private static final String PAGE_STAY_AT_THE_SAME = null;

	private String type;
	private int bookingId;
	@Inject
	ExternalContext extcontext;
	
	@Inject
	Flash flash;
	
	@EJB
	BookingDAO bookingDAO;
		

	public int getBookingId() {
		return bookingId;
	}
	
	public void setBookingId(int bookingId) {
		this.bookingId = bookingId;
	}

	public List<RoomBooking> getFullList(){
		return bookingDAO.getFullList();
	}

	public List<RoomBooking> getList(){
		List<RoomBooking> list = null;
		
		//1. Prepare search params
		Map<String,Object> searchParams = new HashMap<String, Object>();
		
	
		
		//2. Get list
		list = bookingDAO.getList(searchParams);
		
		return list;
	}

	public String newRoomBooking(){
		RoomBooking roombooking = new RoomBooking();
		
		//1. Pass object through session
		//HttpSession session = (HttpSession) extcontext.getSession(true);
		//session.setAttribute("person", person);
		
		//2. Pass object through flash	
		flash.put("roombooking", roombooking);
		
		return PAGE_BOOKING_EDIT;
	}

	public String editRoomBooking(RoomBooking roombooking){
		//1. Pass object through session
		//HttpSession session = (HttpSession) extcontext.getSession(true);
		//session.setAttribute("person", person);
		
		//2. Pass object through flash 
		flash.put("roombooking", roombooking);
		
		return PAGE_BOOKING_EDIT;
	}

	public String deleteRoomBooking(RoomBooking roombooking){
		bookingDAO.remove(roombooking);
		return PAGE_STAY_AT_THE_SAME;
	}
}
