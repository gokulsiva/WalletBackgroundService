package com.techgig.wallet.servlet;

import java.io.IOException;
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
 * Servlet implementation class GetWallet
 */
@WebServlet("/GetWallet")
public class GetWallet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetWallet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userId = request.getParameter("userId");
		System.out.println(userId);
		int id = Integer.parseInt(userId);
		UserBean bean = UserAdmin.getUser(id);
		long balance = bean.getWalletBean().getBalance();
		response.getWriter().write(Long.toString(balance));
		
	}

}
