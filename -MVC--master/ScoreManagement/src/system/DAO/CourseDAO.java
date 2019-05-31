package system.DAO;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import system.model.*;
import system.tools.*;

public class CourseDAO {
	
	public  ArrayList queryCourseList(String major,Page page){

		ArrayList courseList = new ArrayList();
		String sql = "select * from (select *, num = row_number() over (order by Cno ASC) from  Course) t where num between "+page.getBeginNum()+" and "+page.getEndNum()+" and Cno in (select Cno from Major where major='"+major+"')";
		System.out.println(sql);
		DBManager db = new DBManager();
		ResultSet rs = db.executeQuery(sql);
		
		try {
			while(rs.next()){
				Course course = new Course();
				course.setCno(rs.getString("Cno"));
				course.setCname(rs.getString("Cname"));
				course.setCredit(rs.getString("credit"));
				course.setType(rs.getString("type"));
				courseList.add(course);
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			db.releaseSource();
		}
		return courseList;
		
	}
	
	public  ArrayList queryAllCourseList(Page page){

		ArrayList allCourseList = new ArrayList();
		String sql = "select * from (select *, num = row_number() over (order by Cno ASC) from  Course) t where num between "+page.getBeginNum()+" and "+page.getEndNum()+"";
		System.out.println(sql);
		DBManager db = new DBManager();
		ResultSet rs = db.executeQuery(sql);
		
		try {
			while(rs.next()){
				Course allCourse = new Course();
				allCourse.setCno(rs.getString("Cno"));
				allCourse.setCname(rs.getString("Cname"));
				allCourse.setCredit(rs.getString("credit"));
				allCourse.setType(rs.getString("type"));
				allCourseList.add(allCourse);
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			db.releaseSource();
		}
		return allCourseList;
		
	}
	
	public  int queryAllCourseNum(){

		String sql = "select count(Cno) as nums from Course";
		System.out.println(sql);
		DBManager db = new DBManager();
		ResultSet rs = db.executeQuery(sql);
		int recordsNum=0;
		
		try {
			while(rs.next()){
				recordsNum = rs.getInt("nums");	
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			db.releaseSource();
		}
		return recordsNum;
		
	}
	
	public  int queryCourseNum(String major){

		String sql = "select count(Cno) as nums from Course where Cno in (select Cno from Major where major='"+major+"')";
		System.out.println(sql);
		DBManager db = new DBManager();
		ResultSet rs = db.executeQuery(sql);
		int recordsNum=0;
		
		try {
			while(rs.next()){
				recordsNum = rs.getInt("nums");	
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			db.releaseSource();
		}
		return recordsNum;
		
	}
	
	public  boolean deleteByCno(String Cno){
		boolean t = false;
		DBManager db=new DBManager();
        String sql="delete from Course where Cno="+Cno+"";
        int count=db.executeUpdate(sql);
		if(count>0){
			t = true;
		}
		db.releaseSource();
		return t;  
	}
	
	public  boolean updateByCno(Course course){
		boolean t = false;
		DBManager db=new DBManager();
        String sql="update Course set credit='"+course.getCredit()+"',type='"+course.getType()+"' where Cno="+course.getCno()+"";
        System.out.println(sql);
        int count=db.executeUpdate(sql);
		if(count>0){
			t = true;
		}
		db.releaseSource();
		return t;  
	}
	
	public  boolean insertCourseInfo(Course courseInfo){
		boolean t = false;
		DBManager db=new DBManager();
        String sql="insert into Course values("+courseInfo.getCno()+",'"+courseInfo.getCname()+"',"+courseInfo.getCredit()+",'"+courseInfo.getType()+"')";
        System.out.println(sql);
        int count=db.executeUpdate(sql);
		if(count>0){
			t = true;
		}
		db.releaseSource();
		return t;  
	}
	
	public  ArrayList queryAllCnoList(){

		ArrayList allCnoList = new ArrayList();
		String sql = "select Cno,Cname from Course";
		System.out.println(sql);
		DBManager db = new DBManager();
		ResultSet rs = db.executeQuery(sql);
		
		try {
			while(rs.next()){
				Course allCourse = new Course();
				allCourse.setCno(rs.getString("Cno"));
				allCourse.setCname(rs.getString("Cname"));
				allCnoList.add(allCourse);
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			db.releaseSource();
		}
		return allCnoList;
		
	}
}
