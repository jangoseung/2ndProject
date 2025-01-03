package com.smhrd.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.smhrd.model.userDAO;
import com.smhrd.model.userVO;

@WebServlet("/LC")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		
		String id = req.getParameter("id");
		String pw = req.getParameter("pw");
		String name = req.getParameter("name");
		String gender = req.getParameter("gender");
		int age = Integer.parseInt(req.getParameter("age"));
		
		userVO vo = new userVO();
		
		vo.setId(id);
		vo.setPw(pw);
		vo.setName(name);
		vo.setGender(gender);
		vo.setAge(age);
		
		userDAO dao = new userDAO();
		int result = dao.sign(vo);
		
		if(result == 1) {
			System.out.println("회원가입 성공");

			
		}else if(result == 0) {
			System.out.println("회원가입 실패");

		}
	}
}
