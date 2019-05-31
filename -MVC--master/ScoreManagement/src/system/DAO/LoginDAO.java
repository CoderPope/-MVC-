package system.DAO;

import java.sql.*;
import java.util.ArrayList;

import system.tools.*;
import system.model.*;

public class LoginDAO {
	private DBManager db = null;
	
	public LoginDAO(){
		db = new DBManager();
	}
	
	public boolean isExist(Table table,User user){
		boolean t = false;
		String sql ="select * from "+table.getTableName()+" where "+table.getId()+"='"+user.getId()+"' and "+table.getPassword()+"='"+user.getPassword()+"'";
		ResultSet rs = db.executeQuery(sql);
		int row = 0;
		try {
			while(rs.next()){
				row++;
			}
			if(row>0){
				t = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return t;
	}
	
	public Student queryStudentInfo(User user){
		Student student = new Student();
		String sql = "select * from StudentInfo where Sno='"+user.getId()+"'";
		ResultSet rs = db.executeQuery(sql);
		try {
			while(rs.next()){
				
				student.setSno(rs.getString("Sno"));
				student.setSname(rs.getString("Sname"));
				student.setSsex(rs.getString("Ssex"));
				student.setSage(rs.getString("Sage"));
				student.setMajor(rs.getString("Major"));
				student.setPurview(rs.getString("purview"));
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return student;
	}
	
	public Teacher queryTeacherInfo(User user){
		Teacher teacher = new Teacher();
		String sql = "select * from TeacherInfo where Tno='"+user.getId()+"'";
		ResultSet rs = db.executeQuery(sql);
		try {
			while(rs.next()){
				
				teacher.setTno(rs.getString("Tno"));
				teacher.setTname(rs.getString("Tname"));
				teacher.setTsex(rs.getString("Tsex"));
				teacher.setCno(rs.getString("Cno"));
				teacher.setCname(rs.getString("Cname"));
				teacher.setPurview(rs.getString("purview"));
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return teacher;
	}
	
	public Manager queryManagerInfo(User user){
		Manager manager = new Manager();
		String sql = "select * from ManagerInfo where Mno='"+user.getId()+"'";
		ResultSet rs = db.executeQuery(sql);
		try {
			while(rs.next()){
				
				manager.setMno(rs.getString("Mno"));
				manager.setMname(rs.getString("Mname"));
				manager.setPurview(rs.getString("purview"));
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return manager;
	}
	
}
