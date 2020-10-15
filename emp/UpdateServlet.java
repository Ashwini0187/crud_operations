package com.sistec.crud.emp;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet(urlPatterns= {"/update"})
public class UpdateServlet extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name=req.getParameter("name");
		String email=req.getParameter("email");
		String gender=req.getParameter("gender");
		String mno=req.getParameter("mno");
		String city=req.getParameter("city");
		String eno=req.getParameter("eno");
		String hobbies[]=req.getParameterValues("hobby");
		String hobby="";
		if(hobbies!=null && hobbies.length>0) {
			hobby=hobbies[0];
			int i=1;
			while(i<hobbies.length) {
				
				hobby=hobby+","+hobbies[i];		
				i++;
			}
		}
		EmployeeDto dto=new EmployeeDto();
		dto.setEno(Integer.parseInt(eno));
		dto.setEmail(email);
		dto.setName(name);
		dto.setMno(mno);
		dto.setGender(gender);
		dto.setHobbies(hobby);
		dto.setCity(city);
		EmployeeDao dao=new EmployeeDao();
		if(dao.UpdateEmployee(dto)) {
			System.out.println("success");
		}else {
			System.out.println("failed");
		}
	}

}
