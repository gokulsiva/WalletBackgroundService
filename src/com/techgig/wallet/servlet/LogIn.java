package com.techgig.wallet.servlet;

import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONObject;

import com.techgig.wallet.bean.UserBean;
import com.techgig.wallet.service.UserAdmin;


/**
 * Servlet implementation class LogIn
 */
@WebServlet("/LogIn")
public class LogIn extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LogIn() {
        super();
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	
	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		JSONObject result = new JSONObject();
		
		String hash = "";
		try {
			MessageDigest messageDigest = MessageDigest.getInstance("MD5");
			messageDigest.update(password.getBytes());
			hash = new BigInteger(1, messageDigest.digest()).toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			System.out.println("Unable to set this password.");
		}
		
		UserBean user = UserAdmin.getUser(email);
		if(user == null){
			result.put("login", "failed");
			result.put("message", "Invalid Email/Password");
			response.getWriter().write(result.toJSONString());
		} else {
			
			if(!user.getPassword().equals(hash)){
				result.put("login", "failed");
				result.put("message", "Invalid Email/Password");
				response.getWriter().write(result.toJSONString());
			} else {
				result.put("login", "success");
				result.put("user", user);
				response.getWriter().write(result.toJSONString());
			}
			
		}
			
		}

}
