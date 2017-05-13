package com.techgig.wallet.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.techgig.wallet.bean.UserBean;
import com.techgig.wallet.bean.WalletBean;
import com.techgig.wallet.service.UserAdmin;
import com.techgig.wallet.service.WalletAdmin;

/**
 * Servlet implementation class UpdateAmount
 */
@WebServlet("/UpdateAmount")
public class UpdateAmount extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateAmount() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		int userId = Integer.parseInt(request.getParameter("userId"));
		long amount = Long.parseLong(request.getParameter("amount"));
		PrintWriter writer = response.getWriter();
		
		UserBean bean = UserAdmin.getUser(userId);
		if(bean == null){
			writer.write("Invalid User");
			return;
		}
		
		WalletBean walletBean = bean.getWalletBean();
		long actualAmount = walletBean.getBalance();
		walletBean.setBalance(amount+actualAmount);
		
		String status = WalletAdmin.updateWallet(walletBean);
		
		writer.write(status);
		writer.flush();
		writer.close();
		
		
		
	}

}
