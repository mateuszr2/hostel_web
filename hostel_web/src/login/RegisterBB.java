package login;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.Date;
import java.util.List;

import com.jsf.dao.LogDAO;
import com.jsf.dao.RoleDAO;
import com.jsf.dao.UserDAO;

import jpa_entities.ActionLog;
import jpa_entities.User;

@Named
@RequestScoped
public class RegisterBB {
	private static final String PAGE_REGISTER_EDIT = "registerView?faces-redirect=true";
	@Inject
	FacesContext ctx;

	@EJB
	UserDAO userDAO;
	@EJB
	RoleDAO roleDAO;
	@EJB
	LogDAO logDAO;
	private User user = new User();
	private String confPass;
	private ActionLog actionlog = new ActionLog();
	
	public User getUser() {
		return user;
	}

	public void setConfPass(String confPass) {
		this.confPass = confPass;
	}

	public String getConfPass() {
		return confPass;
	}

	public void register() {

	}

	public String registerPage() {
		return PAGE_REGISTER_EDIT;
	}
	
	public boolean validateUser(User user) {
		List<User> duplicates = userDAO.searchForDuplicate(user.getLogin(), user.getEmail());
		if(duplicates.isEmpty() || duplicates == null) return true;
		else return false;
	}

	public boolean confirmData() {
		try {
			PasswordEncode pa = new PasswordEncode();
			if (!this.user.getPassword().equals(this.confPass)) {
				String messageText = "Powt�rz has�o";
				FacesMessage errorMessage = new FacesMessage(messageText);
				errorMessage.setSeverity(FacesMessage.SEVERITY_ERROR);
				throw new ValidatorException(errorMessage);
			}
			if(validateUser(user)) {
			Date data = new Date();
			user.setPassword(pa.hash(user.getPassword().toCharArray()));
			user.setRole(roleDAO.find(3));
			user.getLogin();
			user.setRegisterDate(data);
			actionlog.setLog("rejestracja nowego uzytkownika o loginie: " + user.getLogin());
			actionlog.setDatetime(data);
			logDAO.create(actionlog);
			userDAO.create(user);

			return true;
			}
			else {
				ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
						"Nie uda�o si� utworzy� uzytkownika: login lub email wyst�puj� w systemie", null));
			}
		} catch (ValidatorException e) {
			ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
			return false;
		} catch (Exception e) {
			ctx.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "B��d przetwarzania danych", null));
			return false;
		}
		return false;
	}

	public String register_AJAX() {
		if (confirmData()) {
			ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Rejestracja udana", null));
			ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Przejd� do logowania", null));
		}
		return null;
	}

}
