package com.techgig.wallet.dao;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import org.hibernate.Session;

import com.techgig.wallet.bean.UserBean;
import com.techgig.wallet.bean.WalletBean;
import com.techgig.wallet.util.DBUtil;


public class UserDAO {

	public static String create(UserBean user) {
		
		String password = user.getPassword();
		String hash = "";
		try {
			MessageDigest messageDigest = MessageDigest.getInstance("MD5");
			messageDigest.update(password.getBytes());
			hash = new BigInteger(1, messageDigest.digest()).toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			System.out.println("Unable to set this password.");
		}
		user.setPassword(hash);
		
		boolean bool = WalletDAO.create(user.getWalletBean()).equalsIgnoreCase("success");
		if(!bool){
			return "Unable to create wallet"; 
		}
		
		String status = "";
		Session session = DBUtil.getSession();
		session.beginTransaction();
		try {
			
			session.save(user);
			session.getTransaction().commit();
			status = "success";
			
		} catch (PersistenceException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
			if(e.getCause() instanceof org.hibernate.exception.ConstraintViolationException) {
				status = "Email Id already exists";
		      } else {
		    	  status = "failure";
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
			status = "failure";
		} finally {
			DBUtil.closeSession(session);
		}
		
		return status;
	}
	
	
	public static String update(UserBean user) {
		String status = "";
		Session session = DBUtil.getSession();
		session.beginTransaction();
		try {
			
			session.update(user);
			session.getTransaction().commit();
			status = "success";
			
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
			status = "failure";
		} finally {
			DBUtil.closeSession(session);
		}
		
		return status;
	}
	
	
	public static UserBean read(int id) {
		Session session = DBUtil.getSession();
		session.beginTransaction();
		UserBean userBean = session.get(UserBean.class, id);
		session.getTransaction().commit();
		DBUtil.closeSession(session);
		return userBean;
	}
	
	
	public static UserBean read(String email){
		Session session = DBUtil.getSession();
		UserBean userBean = new UserBean();
		session.beginTransaction();
		try {
			
			TypedQuery<UserBean> query = session.createQuery("from UserBean where email = :email", UserBean.class);
			query.setParameter("email", email);
			userBean = query.getSingleResult();
			
		} catch (NoResultException e) {
			userBean = null;
		}
		session.getTransaction().commit();
		DBUtil.closeSession(session);
		return userBean;
	}
	
	
	public static List<UserBean> readAll(){
		Session session = DBUtil.getSession();
		session.beginTransaction();
		TypedQuery<UserBean> query = session.createQuery("from UserBean order by userId asc", UserBean.class);
		List<UserBean> users = query.getResultList();
		session.getTransaction().commit();
		DBUtil.closeSession(session);
		return users;
	}
	
	public static String delete(UserBean user) {
		String status = "";
		Session session = DBUtil.getSession();
		session.beginTransaction();
		try {
			
			session.delete(user);
			session.getTransaction().commit();
			status = "Successfully deleted user.";
			
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
			status = "Deletion failed";
		} finally {
			DBUtil.closeSession(session);
		}
		return status;
	}
	
	
	public static void main(String[] args) {
		UserBean user = new UserBean();
		user.setName("gokul");
		user.setEmail("gsgokul305@gmail.com");
		user.setPassword("myPassword");
		user.setWalletBean(new WalletBean());
		user.getWalletBean().setPassword("myPassword123");
		System.out.println(create(user));
	}
	
}
