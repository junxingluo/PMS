package com.junapp.pms.security;
 
import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import com.junapp.pms.entity.User;
import com.junapp.pms.persistence.EntityDAO;
 
@ManagedBean(name="login")
@SessionScoped
public class Login implements Serializable {
 
    private static final long serialVersionUID = 1094801825228386363L;
     
    private String pwd;
    private String msg;
    private String user;
    
    public String getPwd() {
        return pwd;
    }
 
    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
 
    public String getMsg() {
        return msg;
    }
 
    public void setMsg(String msg) {
        this.msg = msg;
    }
 
    public String getUser() {
        return user;
    }
 
    public void setUser(String user) {
        this.user = user;
    }
 
    //validate login
    public String validateUsernamePassword() {
        boolean valid = PasswordHashUtils.validateUser(user, pwd);
        if (valid) {
            HttpSession session = SessionBean.getSession();
            session.setAttribute("username", user);
            return "admin";
        } else {
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN,
                            "Incorrect Username and Passowrd",
                            "Please enter correct username and Password"));
            return "login";
        }
    }
    
    /**
     * TODO: to be moved out
     * @return
     */
    
    private String newUserPwd;
    public String getNewUserPwd() {
		return newUserPwd;
	}

	public String getNewUserName() {
		return newUserName;
	}

	private String newUserName;
    
	public void setNewUserPwd(String newUserPwd) {
		this.newUserPwd = newUserPwd;
	}

	public void setNewUserName(String newUserName) {
		this.newUserName = newUserName;
	}

    public List<User> getAllUsers()
    {
    	return EntityDAO.instance.getAllUsers();
    }
    
    /**
     * TODO implement and move out
     */
    public void addUser()
    {
    	User newUser = new User(newUserName, newUserPwd);
    	EntityDAO.instance.save(newUser);
    }
    
    //logout event, invalidate session
    public String logout() {
        HttpSession session = SessionBean.getSession();
        session.invalidate();
        return "login";
    }
}