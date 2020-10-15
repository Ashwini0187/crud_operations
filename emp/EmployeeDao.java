package com.sistec.crud.emp;

import java.io.InputStream;
import java.security.spec.EncodedKeySpec;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Base64.Encoder;

import com.sistec.crud.db.EmpDb;

public class EmployeeDao {
private Connection conn=null;
private PreparedStatement ps=null;
private ResultSet rs=null;

public boolean addEmployee(EmployeeDto dto,InputStream photo) {
	boolean flag = false;
	if(conn==null) {
		EmpDb.getEmpDb();
	}
	try {
		String sql="insert into emp_master( name, email, pwd, mno, city, gender, hobbies, photo) values(?,?,?,?,?,?,?,?,)";
		ps=conn.prepareStatement(sql);
		ps.setString(1, dto.getName());
		ps.setString(2,dto.getEmail());
		ps.setString(3, dto.getPwd());
		ps.setString(4, dto.getMno());
		ps.setString(5, dto.getCity());
		ps.setString(6, dto.getGender());
		ps.setString(7, dto.getHobbies());
		ps.setString(8, dto.getPhoto());
		if(ps.executeUpdate()>0) {
			flag=true;
		}
		
		
		
	} catch (Exception e) {
		System.out.println("Exception at addEmployee():"+e);
	}finally {
		ps=null;
		conn=null;
		return flag;
	}
}

public ArrayList<EmployeeDto> getAllStudents(){
	 ArrayList<EmployeeDto> al=new ArrayList<>();
	 if(conn==null) {
		 conn=EmpDb.getEmpDb();
	 }
	 EmployeeDto dto;
	 try {
		String sql="select *from emp_master";
		ps=conn.prepareStatement(sql);
		rs=ps.executeQuery();
		while(rs.next()) {
		dto=new EmployeeDto();
		dto.setEno(rs.getInt("eno"));
		dto.setName(rs.getString("name"));
		dto.setEmail(rs.getString("email"));
		dto.setPwd(rs.getString("pwd"));
		dto.setCity(rs.getString("city"));
		dto.setHobbies(rs.getString("hobbies"));
		dto.setGender(rs.getString("gender"));
		byte arr[]=rs.getBytes("photo");
		Encoder encoder = Base64.getEncoder();
		String photo=encoder.encodeToString(arr);
		dto.setPhoto(photo);
		
		
		
			al.add(dto);
			
		}
	} catch (Exception e) {
		System.out.println("Exception at getAll Students():"+e);
	}finally {
		rs=null;
		ps=null;
		conn=null;
			if(al.isEmpty()) {
			al=null;
		}
		return al;
	}
}

public boolean deleteEmployee(String eno) {
	boolean flag=false;
	if(conn==null) {
		conn=EmpDb.getEmpDb();
	}
	try {
		String sql="delete from emp_master where eno="+eno;
		ps=conn.prepareStatement(sql);
		if(ps.executeLargeUpdate()>0) {
			flag=true;
		}
		} catch (Exception e) {
		System.out.println("Exception at deleteStudent():"+e);
	}finally {
		ps=null;
		conn=null;
		return flag;
	}
}
public EmployeeDto getEmployee(String eno) {
	EmployeeDto dto=null;
	if(conn==null) {
		conn=EmpDb.getEmpDb();
		}
	try {
		String sql="select *from emp_master where eno="+eno;
		ps=conn.prepareStatement(sql);
		rs=ps.executeQuery();
		if(rs.next()) {
			dto=new EmployeeDto();
			dto.setEno(rs.getInt("eno"));
			dto.setName(rs.getString("name"));
			dto.setEmail(rs.getString("email"));
			dto.setCity(rs.getString("city"));
			dto.setGender(rs.getString("gender"));
			dto.setHobbies(rs.getString("hobbies"));
			dto.setMno(rs.getString("mno"));
			
		}
		
	} catch (Exception e) {
		System.out.println("Exception at getEmployee():"+e);
	}finally {
		rs=null;
		ps=null;
		conn=null;
		return dto;
	}
}
public boolean UpdateEmployee(EmployeeDto dto) {
	boolean flag=false;
	if(conn==null) {
		conn=EmpDb.getEmpDb();
	}
	try {
		String sql="update emp_master set name=?,email=?,mno=?,gender=?,hobbies=?,city=? where eno=";
		ps=conn.prepareStatement(sql);
		ps.setString(1, dto.getName());
		ps.setString(2, dto.getEmail());
		ps.setString(3, dto.getMno());
		ps.setString(4, dto.getGender());
		ps.setString(5, dto.getHobbies());
		ps.setString(6, dto.getCity());
		ps.setInt(7, dto.getEno());
		if(ps.executeUpdate()>0) {
			flag=true;
		}
		
	} catch (Exception e) {
		System.out.println("Exception at updateEmplyee():"+e);
	}finally {
		
		ps=null;
		conn=null;
		return flag;
	}
	
}
}
