//package com.anna.coupons.servlets;
//
//
//import java.io.IOException;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//@WebServlet("/register")
//public class RegisterServlet extends HttpServlet {
//
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		
//		String user = request.getParameter("user");
//		String password = request.getParameter("password");
//		System.out.println(user);
//		System.out.println(password);
//		if (!user.equals("Admin") && !password.equals("1234")){
//			// We have a case of a wrong password
//			response.setStatus(401);
//		}
//		
//		response.sendRedirect("https://www.walla.co.il/");
//		System.out.println("===============");
//	}
//
//}
