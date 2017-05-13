package com.techgig.wallet.service;

import com.techgig.wallet.bean.WalletBean;
import com.techgig.wallet.dao.WalletDAO;

public class WalletAdmin {
	
	public static String createWallet(WalletBean walletBean) {
		String status = "";
		if (!(walletBean.getPassword().equals("") )) {
			status = WalletDAO.create(walletBean);
		} else {
			status = "Invalid fields please fill all fields";
		}
		return status;
	}
	
	public static String updateWallet(WalletBean walletBean) {
		String status = "";
		if (!(walletBean.getWalletId() == 0 || walletBean.getPassword().equals(""))) {
			status = WalletDAO.update(walletBean);
		} else {
			status = "Invalid object";
		}
		return status;
	}
	
		
	public static WalletBean getWallet(int id) {
		return WalletDAO.read(id);
	}
	
	
	public static String deleteWallet(WalletBean walletBean) {
		return WalletDAO.delete(walletBean);
	}
	
	
	public static void main(String[] args) {
		WalletBean bean = getWallet(1);
		System.out.println(bean.toString());
		bean.setBalance(1000l);
		System.out.println(updateWallet(bean));
	}
	
	

}
