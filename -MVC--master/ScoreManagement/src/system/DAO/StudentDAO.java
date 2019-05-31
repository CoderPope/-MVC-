package system.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import system.model.Page;
import system.model.Score;
import system.model.Student;
import system.tools.DBManager;

public class StudentDAO {
	public  int queryAllStudentNum(){

		String sql = "select count(Sno) as nums from StudentInfo";
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
	
	public  ArrayList queryAllStudentList(Page page){

		ArrayList allStudentList = new ArrayList();
		String sql = "select * from (select *, num = row_number() over (order by Sno ASC) from StudentInfo) t where num between "+page.getBeginNum()+" and "+page.getEndNum()+"";
		System.out.println(sql);
		DBManager db = new DBManager();
		ResultSet rs = db.executeQuery(sql);
		
		try {
			while(rs.next()){
				Student allStudent = new Student();
				allStudent.setSno(rs.getString("Sno"));
				allStudent.setSname(rs.getString("Sname"));
				allStudent.setSsex(rs.getString("Ssex"));
				allStudent.setSage(rs.getString("Sage"));
				allStudent.setMajor(rs.getString("major"));
				allStudentList.add(allStudent);
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			db.releaseSource();
		}
		return allStudentList;
		
	}
	
	public  boolean deleteBySno(String Sno){
		boolean t = false;
		DBManager db=new DBManager();
        String sql="delete from StudentInfo where Sno="+Sno+"";
        int count=db.executeUpdate(sql);
		if(count>0){
			t = true;
		}
		db.releaseSource();
		return t;  
	}
	
	public  boolean updateBySno(Student student){
		boolean t = false;
		DBManager db=new DBManager();
        String sql="update StudentInfo set Ssex='"+student.getSsex()+"',Sage="+student.getSage()+",major='"+student.getMajor()+"' where Sno="+student.getSno()+"";
        System.out.println(sql);
        int count=db.executeUpdate(sql);
		if(count>0){
			t = true;
		}
		db.releaseSource();
		return t;  
	}
	
	public  ArrayList queryAllStudentList(){

		ArrayList allStudentList = new ArrayList();
		String sql = "select * from StudentInfo";
		System.out.println(sql);
		DBManager db = new DBManager();
		ResultSet rs = db.executeQuery(sql);
		
		try {
			while(rs.next()){
				Student allStudent = new Student();
				allStudent.setSno(rs.getString("Sno"));
				allStudent.setSname(rs.getString("Sname"));
				allStudent.setSsex(rs.getString("Ssex"));
				allStudent.setSage(rs.getString("Sage"));
				allStudent.setMajor(rs.getString("major"));
				allStudentList.add(allStudent);
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			db.releaseSource();
		}
		return allStudentList;
		
	}
	
	public  boolean insertStudentInfo(Student studentInfo){
		boolean t = false;
		DBManager db=new DBManager();
        String sql="insert into StudentInfo values("+studentInfo.getSno()+",'"+studentInfo.getSname()+"','"+studentInfo.getSsex()+"',"+studentInfo.getSage()+","+studentInfo.getSpassword()+",'"+studentInfo.getMajor()+"','"+studentInfo.getPurview()+"')";
        System.out.println(sql);
        int count=db.executeUpdate(sql);
		if(count>0){
			t = true;
		}
		db.releaseSource();
		return t;  
	}
	
}
