package com.junapp.pms;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionListener;
import javax.persistence.Entity;

import com.junapp.pms.entity.User;
import com.junapp.pms.persistence.EntityDAO;

import java.io.Serializable;
import java.util.List;

@ManagedBean
@SessionScoped
public class HelloBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public ActionListener getAL() {
		return null;
	}
	
	public static void main(String[] args)
	{
//		User user = new User("Jun Jun Jun Jun 4", "abcdefg321");
//		EntityDAO.instance.save(user);
		
		List<User> allUsers = EntityDAO.instance.getAllUsers();
		for (User user2 : allUsers) {
			System.err.println(user2.getName() + " " + user2.getPasswordHash());
		}
	}
}