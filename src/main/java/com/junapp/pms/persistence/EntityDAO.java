package com.junapp.pms.persistence;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.junapp.pms.entity.Department;
import com.junapp.pms.entity.Employee;
import com.junapp.pms.entity.PersistentEntity;

public class EntityDAO {
	
	private EntityDAO() {}
	public static final EntityDAO instance = new EntityDAO();
	
	public void save(List<PersistentEntity> entities) {
        
        Session session = HibernateUtil.getSessionFactory().openSession();
  
        session.beginTransaction();
 
        for (PersistentEntity persistentEntity : entities) {
        	session.saveOrUpdate(persistentEntity);
		}
      
        session.getTransaction().commit();
 
        Query q = session.createQuery("From Employee ");
                 
        List<Employee> resultList = q.list();
        System.out.println("num of employess:" + resultList.size());
        for (Employee next : resultList) {
            System.out.println("next employee: " + next);
        }
    }
}
