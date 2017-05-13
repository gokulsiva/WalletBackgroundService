package com.techgig.wallet.service;

import java.util.List;

import com.techgig.wallet.bean.UserBean;
import com.techgig.wallet.bean.WalletBean;
import com.techgig.wallet.dao.UserDAO;


public class UserAdmin {
	
	public static String createUser(UserBean user) {
		String status = "";
		if (!(user.getName().equals("") || user.getEmail().equals("") || user.getPassword().equals("") )) {
			status = UserDAO.create(user);
		} else {
			status = "Invalid fields please fill all fields";
		}
		return status;
	}
	
	public static String updateUser(UserBean user) {
		String status = "";
		if (!(user.getUserId() == 0 || user.getName().equals("") || user.getPassword().equals(""))) {
			status = UserDAO.update(user);
		} else {
			status = "Invalid object";
		}
		return status;
	}
	
	public static List<UserBean> getAllUsers(){
		return UserDAO.readAll();
	}
	
	public static UserBean getUser(int id) {
		return UserDAO.read(id);
	}
	
	public static UserBean getUser(String email) {
		return UserDAO.read(email);
	}
	
	public static String deleteUser(UserBean user) {
		return UserDAO.delete(user);
	}
	
	
	public static void main(String[] args) {
		
		
		UserBean user = new UserBean();
		user.setName("demo");
		user.setEmail("demo@gmail.com");
		user.setPassword("12345");
		user.setWalletBean(new WalletBean());
		user.getWalletBean().setPassword("54321");
		String status = UserAdmin.createUser(user);
		System.out.println(status);
		
//		UserBean bean = getUser("demo@gmail.com");
//		System.out.println(bean.getWalletBean().getWalletId());
//		System.out.println(deleteUser(bean));
//		System.out.println(WalletAdmin.deleteWallet(bean.getWalletBean()));
		
		
	}


}
