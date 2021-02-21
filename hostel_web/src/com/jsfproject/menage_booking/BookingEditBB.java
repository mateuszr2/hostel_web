package com.jsfproject.menage_booking;

import java.io.IOException;
import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import com.jsf.dao.BookingDAO;
import jpa_entities.RoomBooking;

@Named
@ViewScoped
public class BookingEditBB implements Serializable {
	private static final long serialVersionUID = 1L;

	private static final String PAGE_ROOMS_LIST = "bookingList?faces-redirect=true";
	private static final String PAGE_STAY_AT_THE_SAME = null;

	private RoomBooking roombooking = new RoomBooking();
	private RoomBooking loaded = null;

	@EJB
	BookingDAO bookingDAO;

	@Inject
	FacesContext context;

	@Inject
	Flash flash;

	public RoomBooking getRoomBooking() {
		return roombooking;
	}

	public void onLoad() throws IOException {
		// 1. load person passed through session
		// HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
		// loaded = (Person) session.getAttribute("person");

		// 2. load person passed through flash
		loaded = (RoomBooking) flash.get("roombooking");

		// cleaning: attribute received => delete it from session
		if (loaded != null) {
			roombooking = loaded;
			// session.removeAttribute("person");
		} else {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Nieprawid³owa opracja", null));
			// if (!context.isPostback()) { //possible redirect
			// context.getExternalContext().redirect("personList.xhtml");
			// context.responseComplete();
			// }
		}

	}

	public String saveData() {
		// no Person object passed
		if (loaded == null) {
			return PAGE_STAY_AT_THE_SAME;
		}

		try {
			if (roombooking.getBookingId() == null) {
				// new record
				bookingDAO.create(roombooking);
			} else {
				// existing record
				bookingDAO.merge(roombooking);
			}
		} catch (Exception e) {
			e.printStackTrace();
			context.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "B³¹d zapisu", null));
			return PAGE_STAY_AT_THE_SAME;
		}

		return PAGE_ROOMS_LIST;
	}
}
