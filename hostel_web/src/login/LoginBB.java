package login;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.simplesecurity.RemoteClient;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import com.jsf.dao.UserDAO;
import jpa_entities.User;
import javax.servlet.http.HttpServletRequest;

@Named
@RequestScoped
public class LoginBB {

	@Inject
	FacesContext ctx;

	@EJB
	UserDAO userDAO;
	private static final String PAGE_LOGIN = "/public/login?faces-redirect=true";
	private static final String PAGE_ADMIN_EDIT = "/pages/admin/home?faces-redirect=true";
	private static final String HOME_ADMIN = "/pages/admin/home?faces-redirect=true";
	private static final String HOME_USER = "/pages/user/home?faces-redirect=true";
	private static final String PAGE_HOME_PUBLIC = "/public/home?faces-redirect=true";
	private User user = new User();
	private User userLogin;

	public User getUser() {
		return user;
	}

	public String loginPage() {
		HttpSession session = (HttpSession) ctx.getExternalContext().getSession(true);
		if(RemoteClient.load(session)==null) return PAGE_LOGIN;
		else return HOME_ADMIN;

	}

	
	public String figlerPage() {
		return "/public/figler";
	}

	public String indexPage() {
		return HOME_ADMIN;
	}

	public String login() {
		userLogin = userDAO.getloginAccount(user.getLogin(), user.getPassword());
		// 1.Search user in DB
		if (this.userLogin != null) {

			// 2. if logged in: get User roles, save in RemoteClient and store it in session

			RemoteClient<User> client = new RemoteClient<User>(); // create new RemoteClient
			client.setDetails(this.userLogin);

			String role = userLogin.getRole().getName(); // get User roles

			if (role != null) { // save roles names in RemoteClient
				client.getRoles().add(role);
			}

			// store RemoteClient with request info in session (needed for SecurityFilter)
			HttpServletRequest request = (HttpServletRequest) ctx.getExternalContext().getRequest();
			client.store(request);
			ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Zalogowano", null));
			String x = "user";
			if(role.equals(x))
			{
				return HOME_USER;
			}
			else
			{	
			return HOME_ADMIN;
			}
			// and enter the system (now SecurityFilter will pass the request)
		} else {
			ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Niepoprawny login lub has³o", null));
			return null;
		}
	}

//	public  isInanyrole() {
//		HttpSession session = (HttpSession) ctx.getExternalContext().getSession(true);
//
//		if(RemoteClient.load(session)!=null) return null;
//		else return PAGE_INDEX_EDIT;
//	}

	public String doLogout() {
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		// Invalidate session
		// - all objects within session will be destroyed
		// - new session will be created (with new ID)
		session.invalidate();
		ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Wylogowano!", null));
		return PAGE_HOME_PUBLIC;
	}

}
