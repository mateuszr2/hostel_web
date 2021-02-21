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

@Named
@RequestScoped
public class RoomsAvailableListBB {
	private static final String PAGE_ROOM_EDIT = "roomBooking?faces-redirect=true";
	private static final String PAGE_STAY_AT_THE_SAME = null;

	private String type;
		
	@Inject
	ExternalContext extcontext;
	
	@Inject
	Flash flash;
	
	@EJB
	RoomDAO roomDAO;
		
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
		
		//1. Prepare search params
		Map<String,Object> searchParams = new HashMap<String, Object>();
		
		if (type != null && type.length() > 0){
			searchParams.put("type", type);
		}
		
		//2. Get list
		list = roomDAO.getList(searchParams);
		
		return list;
	}

	public String newRoom(){
		Room room = new Room();
		
		//1. Pass object through session
		//HttpSession session = (HttpSession) extcontext.getSession(true);
		//session.setAttribute("person", person);
		
		//2. Pass object through flash	
		flash.put("room", room);
		
		return PAGE_ROOM_EDIT;
	}

	public String editRoomBooking(Room room){
		//1. Pass object through session
		//HttpSession session = (HttpSession) extcontext.getSession(true);
		//session.setAttribute("person", person);
		
		//2. Pass object through flash 
		flash.put("room", room);
		
		return PAGE_ROOM_EDIT;
	}

	public String deleteRoom(Room room){
		roomDAO.remove(room);
		return PAGE_STAY_AT_THE_SAME;
	}
}
