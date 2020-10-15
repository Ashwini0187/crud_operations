package com.sistec.crud.emp;

import java.io.IOException;

import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@WebServlet (urlPatterns= {"/add"})
@MultipartConfig(maxFileSize= 877676734)
public class EmployeeServlet extends HttpServlet {
@Override
protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	String name=req.getParameter("name");
	String email=req.getParameter("email");
	String mno=req.getParameter("mno");
	String gender=req.getParameter("gender");
	String pwd=req.getParameter("pwd");
	Part part=req.getPart("photo");
	InputStream photo=part.getInputStream();
	String city=req.getParameter("city");
	String hobbies[]=req.getParameterValues("hobby");
	String hobby="";
	if(hobbies!=null&& hobbies.length>0) {
		hobby=hobbies[0];
		int i=1;
		while(hobbies.length>i) {
			hobby=hobby+","+hobbies[i++];
		}
	}
	
	EmployeeDto dto=new EmployeeDto();
	dto.setName(name);
	dto.setEmail(email);
	dto.setPwd(pwd);
	dto.setMno(mno);
	dto.setCity(city);
	dto.setHobbies(hobby);
	dto.setGender(gender);
	
	EmployeeDao dao=new EmployeeDao();
	
	if(dao.addEmployee(dto,photo)) {
	System.out.println("Success");
}else {
	System.out.println("Faild");
}
}
}