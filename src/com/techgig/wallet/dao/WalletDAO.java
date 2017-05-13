package com.techgig.wallet.dao;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.hibernate.Session;

import com.techgig.wallet.bean.WalletBean;
import com.techgig.wallet.util.DBUtil;

public class WalletDAO {
	
	
public static String create(WalletBean walletBean) {
		
		String password = walletBean.getPassword();
		String hash = "";
		try {
			MessageDigest messageDigest = MessageDigest.getInstance("MD5");
			messageDigest.update(password.getBytes());
			hash = new BigInteger(1, messageDigest.digest()).toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			System.out.println("Unable to set this password.");
		}
		walletBean.setPassword(hash);
		walletBean.setBalance(0);
		
		String status = "";
		Session session = DBUtil.getSession();
		session.beginTransaction();
		try {
			session.save(walletBean);
			session.save(walletBean);
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

	public static String update(WalletBean walletBean) {
		String status = "";
		Session session = DBUtil.getSession();
		session.beginTransaction();
		try {
			
			session.update(walletBean);
			session.getTransaction().commit();
			status = "success";
			
		}catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
			status = "failure";
		} finally {
			DBUtil.closeSession(session);
		}
		
		return status;
	}
	
	public static WalletBean read(int id) {
		Session session = DBUtil.getSession();
		session.beginTransaction();
		WalletBean walletBean = session.get(WalletBean.class, id);
		session.getTransaction().commit();
		DBUtil.closeSession(session);
		return walletBean;
	}
	
	public static String delete(WalletBean walletBean) {
		String status = "";
		Session session = DBUtil.getSession();
		session.beginTransaction();
		try {
			
			session.delete(walletBean);
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
	
	public static void main(String[] args) {
		WalletBean bean = WalletDAO.read(1);
		System.out.println("Balance = "+bean.getBalance());
		bean.setBalance(1000);
		update(bean);
		bean = WalletDAO.read(1);
		System.out.println("Balance = "+bean.getBalance());
	}
	
}
