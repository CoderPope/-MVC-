package system.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import system.model.Page;
import system.model.Student;
import system.model.Teacher;
import system.tools.DBManager;

public class TeacherDAO {
	public  int queryAllTeacherNum(){

		String sql = "select count(Tno) as nums from TeacherInfo";
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
	
	public  ArrayList queryAllTeacherList(Page page){

		ArrayList allTeacherList = new ArrayList();
		String sql = "select * from (select *, num = row_number() over (order by Tno ASC) from TeacherInfo) t where num between "+page.getBeginNum()+" and "+page.getEndNum()+"";
		System.out.println(sql);
		DBManager db = new DBManager();
		ResultSet rs = db.executeQuery(sql);
		
		try {
			while(rs.next()){
				Teacher allTeacher = new Teacher();
				allTeacher.setTno(rs.getString("Tno"));
				allTeacher.setTname(rs.getString("Tname"));
				allTeacher.setTsex(rs.getString("Tsex"));
				allTeacher.setCno(rs.getString("Cno"));
				allTeacher.setCname(rs.getString("Cname"));
				allTeacherList.add(allTeacher);
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			db.releaseSource();
		}
		return allTeacherList;
		
	}
	
	public  ArrayList queryAllTeacherList(){

		ArrayList allTeacherList = new ArrayList();
		String sql = "select * from TeacherInfo";
		System.out.println(sql);
		DBManager db = new DBManager();
		ResultSet rs = db.executeQuery(sql);
		
		try {
			while(rs.next()){
				Teacher allTeacher = new Teacher();
				allTeacher.setTno(rs.getString("Tno"));
				allTeacher.setTname(rs.getString("Tname"));
				allTeacher.setTsex(rs.getString("Tsex"));
				allTeacher.setCno(rs.getString("Cno"));
				allTeacher.setCname(rs.getString("Cname"));
				allTeacherList.add(allTeacher);
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			db.releaseSource();
		}
		return allTeacherList;
		
	}
	
	public  boolean deleteByTno(String Tno){
		boolean t = false;
		DBManager db=new DBManager();
        String sql="delete from TeacherInfo where Tno="+Tno+"";
        int count=db.executeUpdate(sql);
		if(count>0){
			t = true;
		}
		db.releaseSource();
		return t;  
	}
	
	public  boolean updateByTno(Teacher teacher){
		boolean t = false;
		DBManager db=new DBManager();
        String sql="update TeacherInfo set Tsex='"+teacher.getTsex()+"',Cno="+teacher.getCno()+",Cname='"+teacher.getCname()+"' where Tno="+teacher.getTno()+"";
        System.out.println(sql);
        int count=db.executeUpdate(sql);
		if(count>0){
			t = true;
		}
		db.releaseSource();
		return t;  
	}
	
	public  boolean insertTeacherInfo(Teacher teacherInfo){
		boolean t = false;
		DBManager db=new DBManager();
        String sql="insert into TeacherInfo values("+teacherInfo.getTno()+",'"+teacherInfo.getTname()+"','"+teacherInfo.getTsex()+"',"+teacherInfo.getTpassword()+","+teacherInfo.getCno()+",'"+teacherInfo.getCname()+"','"+teacherInfo.getPurview()+"')";
        System.out.println(sql);
        int count=db.executeUpdate(sql);
		if(count>0){
			t = true;
		}
		db.releaseSource();
		return t;  
	}
}
