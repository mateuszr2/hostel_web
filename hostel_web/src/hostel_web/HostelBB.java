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
	private static final String PAGE_HOME_PUBLIC = "../home.xhtml";
	private static final String PAGE_HOME_PUBLIC_ADMIN = "../admin/home.xhtml";
	private static final String PAGE_HOME_PUBLIC_USER = "../user/home.xhtml";
	private static final String PAGE_HOME = "home.xhtml";
	private static final String PAGE_ABOUT_US = "public/about_us.xhtml";
	private static final String PAGE_ABOUT_US_ADMIN = "about_us.xhtml";
	private static final String PAGE_ABOUT_US_USER = "about_us.xhtml";
	private static final String PAGE_USER_LIST = "userList.xhtml";
	private static final String LOGIN_PAGE = "public/login.xhtml";
	private static final String LOGIN_LINK_REGISTRATION = "../public/login.xhtml";
	private static final String PAGE_REGISTRATION = "public/register.xhtml";
	private static final String PAGE_ROOMS_LIST = "roomsList.xhtml";
	private static final String PAGE_BOOKING_LIST = "bookingList.xhtml";
	private static final String PAGE_LOG_LIST = "logList.xhtml";
	public String homePage() {
		return PAGE_HOME;
	}
	public String homePagePublic() {
		return PAGE_HOME_PUBLIC;
	}
	public String homePageAdmin() {
		return PAGE_HOME_PUBLIC_ADMIN;
	}
	public String homePageUser() {
		return PAGE_HOME_PUBLIC_USER;
	}
	public String about_usPage() {
		return PAGE_ABOUT_US;
	}
	public String about_us_adminPage() {
		return PAGE_ABOUT_US_ADMIN;
	}
	public String about_us_userPage() {
		return PAGE_ABOUT_US_USER;
	}
	public String userListPage() {
		return PAGE_USER_LIST;
	}
	public String LoginPage() {
		return LOGIN_PAGE;
	}
	public String LoginLinkRegistration() {
		return LOGIN_LINK_REGISTRATION;
	}
	public String registrationPage() {
		return PAGE_REGISTRATION;
	}
	public String roomsListPage() {
		return PAGE_ROOMS_LIST;
	}
	public String bookingListPage() {
		return PAGE_BOOKING_LIST;
	}
	public String logListPage() {
		return PAGE_LOG_LIST;
	}

}
