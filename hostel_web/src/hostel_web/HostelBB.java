package hostel_web;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import com.jsf.dao.UserDAO;
import jpa_entities.User;
import javax.servlet.http.HttpServletRequest;

@Named
@RequestScoped
public class HostelBB {

	@Inject
	FacesContext ctx;

	@EJB
	UserDAO userDAO;
	private static final String PAGE_HOME = "home.xhtml";
	private static final String PAGE_ABOUT_US = "about_us.xhtml";
	private static final String PAGE_USER_LIST = "userList.xhtml";
	private static final String PAGE_LOGIN = "login?faces-redirect=true";
	private static final String PAGE_REGISTRATION = "registration?faces-redirect=true";
	private static final String PAGE_ROOMS_LIST = "roomsList.xhtml";

	public String homePage() {
		return PAGE_HOME;
	}
	public String about_usPage() {
		return PAGE_ABOUT_US;
	}
	public String userListPage() {
		return PAGE_USER_LIST;
	}
	public String loginPage() {
		return PAGE_LOGIN;
	}
	public String registrationPage() {
		return PAGE_REGISTRATION;
	}
	public String roomsListPage() {
		return PAGE_ROOMS_LIST;
	}
	

}
