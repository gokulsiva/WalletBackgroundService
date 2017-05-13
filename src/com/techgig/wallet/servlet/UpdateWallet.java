package com.techgig.wallet.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import com.techgig.wallet.bean.WalletBean;
import com.techgig.wallet.service.UserAdmin;
import com.techgig.wallet.service.WalletAdmin;

/**
 * Servlet implementation class UpdateWallet
 */
@WebServlet("/UpdateWallet")
public class UpdateWallet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateWallet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int senderId = Integer.parseInt(request.getParameter("senderId"));
		String senderPassword = request.getParameter("senderPassword");
		int receiverId = Integer.parseInt(request.getParameter("receiverId"));
		long amount = Long.parseLong(request.getParameter("amount"));
		
		WalletBean senderBean = UserAdmin.getUser(senderId).getWalletBean();
		WalletBean receiverBean = UserAdmin.getUser(receiverId).getWalletBean();
		
		String transaction = "";
		
		if (senderPassword.equals(senderBean.getPassword()) && senderBean.getBalance() >= amount) {
			
			long remaining = senderBean.getBalance() - amount ;
			senderBean.setBalance(remaining);
			long sum = receiverBean.getBalance() + amount;
			receiverBean.setBalance(sum);
			
			String status1 = WalletAdmin.updateWallet(senderBean);
			String status2 = WalletAdmin.updateWallet(receiverBean);
			
			if (status1.equalsIgnoreCase("success") && status2.equals(status1)){
				transaction = "success";
			} else {
				transaction = "failure";
			}
			
		} else {
			transaction = "failure";
		}
		
		JSONObject result = new JSONObject();
		result.put("Transaction", transaction);
		response.getWriter().write(result.toJSONString());
		
	}

}
