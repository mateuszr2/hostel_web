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

	public boolean confirmData() {
		try {
			PasswordEncode pa = new PasswordEncode();
			if (!this.user.getPassword().equals(this.confPass)) {
				String messageText = "Powtórz has³o";
				FacesMessage errorMessage = new FacesMessage(messageText);
				errorMessage.setSeverity(FacesMessage.SEVERITY_ERROR);
				throw new ValidatorException(errorMessage);
			}
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

		} catch (ValidatorException e) {
			ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
			return false;
		} catch (Exception e) {
			ctx.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "B³¹d przetwarzania danych", null));
			return false;
		}
	}

	public String register_AJAX() {
		if (confirmData()) {
			ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Rejestracja udana", null));
			ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "PrzejdŸ do logowania", null));
		}
		return null;
	}

}
