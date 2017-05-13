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


/**
 * Servlet implementation class CreateUser
 */
@WebServlet("/CreateUser")
public class CreateUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateUser() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userName = request.getParameter("name");
		String userEmail = request.getParameter("email");
		String userPassword = request.getParameter("password");
		String walletPassword = request.getParameter("walletPassword");
		System.out.println(userEmail+"\n"+userName+"\n"+userPassword+"\n"+walletPassword);
		UserBean user = new UserBean();
		user.setName(userName);
		user.setEmail(userEmail);
		user.setPassword(userPassword);
		user.setWalletBean(new WalletBean());
		user.getWalletBean().setPassword(walletPassword);
		String status = UserAdmin.createUser(user);
		response.setContentType("text/html");
		response.getWriter().write(status);
	}

}
