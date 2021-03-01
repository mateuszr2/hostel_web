package com.jsfproject.booking;

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

import com.jsf.dao.RoomDAO;
import jpa_entities.Room;
import com.jsf.dao.BookingDAO;
import jpa_entities.RoomBooking;

@Named
@RequestScoped
public class RoomsAvailableListBB {
	private static final String PAGE_ROOMBOOKING = "roomBooking?faces-redirect=true";
	private static final String PAGE_STAY_AT_THE_SAME = null;

	private String type;
		
	@Inject
	ExternalContext extcontext;
	
	@Inject
	Flash flash;
	
	@EJB
	RoomDAO roomDAO;
	@EJB
	BookingDAO bookingDAO;
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<Room> getFullList(){
		return roomDAO.getFullList();
	}
	

	public List<Room> getList(){
		List<Room> list = null;
		
		
		Map<String,Object> searchParams = new HashMap<String, Object>();
		
		if (type != null && type.length() > 0){
			searchParams.put("type", type);
		}
		
		
		list = roomDAO.getList(searchParams);
		
		return list;
	}



	public String nowRoomBooking(Room room){

		flash.put("room", room);
		
		
		return PAGE_ROOMBOOKING;
	}
	

}
