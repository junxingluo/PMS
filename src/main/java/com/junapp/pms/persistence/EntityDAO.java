package com.junapp.pms.persistence;

import java.util.LinkedList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.junapp.pms.entity.User;
import com.junapp.pms.entity.PersistentEntity;

public class EntityDAO {
	
	private EntityDAO() {}
	public static final EntityDAO instance = new EntityDAO();
	
	public <T extends PersistentEntity> void save(List<T> entities) {
        
        Session session = HibernateUtil.getSession();
  
        session.beginTransaction();
 
        for (PersistentEntity persistentEntity : entities) {
        	session.saveOrUpdate(persistentEntity);
		}
      
        session.getTransaction().commit();
    }
	
	public <T extends PersistentEntity> void save(T... entites) {
		List<T> list = new LinkedList<>();
		for (T persistentEntity : entites) {
			list.add(persistentEntity);
		}
		save(list);
	}
	
	@SuppressWarnings("unchecked")
	public List<User> getAllUsers() {
		Session session = HibernateUtil.getSession();
		
		session.beginTransaction();
		
        Query q = session.createQuery("From User ");
                 
        List<User> resultList = q.list();
        
        session.getTransaction().commit();
        
		return resultList;
	}
}
