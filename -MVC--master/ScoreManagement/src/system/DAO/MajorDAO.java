package system.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import system.model.Major;
import system.model.Page;
import system.model.Student;
import system.tools.DBManager;

public class MajorDAO {
	public  int queryAllMajorNum(){

		String sql = "select count(Cno) as nums from Major";
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
	
	public  ArrayList queryAllMajorList(Page page){

		ArrayList allMajorList = new ArrayList();
		String sql = "select * from (select *, num = row_number() over (order by Cno ASC) from Major) t where num between "+page.getBeginNum()+" and "+page.getEndNum()+"";
		System.out.println(sql);
		DBManager db = new DBManager();
		ResultSet rs = db.executeQuery(sql);
		
		try {
			while(rs.next()){
				Major allMajor = new Major();
				allMajor.setCno(rs.getString("Cno"));
				allMajor.setCname(rs.getString("Cname"));
				allMajor.setMajor(rs.getString("major"));
				allMajorList.add(allMajor);
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			db.releaseSource();
		}
		return allMajorList;
		
	}
	
	public  boolean deleteByCno(String Cno){
		boolean t = false;
		DBManager db=new DBManager();
        String sql="delete from Major where Cno="+Cno+"";
        int count=db.executeUpdate(sql);
		if(count>0){
			t = true;
		}
		db.releaseSource();
		return t;  
	}
	
	public  boolean updateByCno(Major majorInfo){
		boolean t = false;
		DBManager db=new DBManager();
        String sql="update Major set Cname='"+majorInfo.getCname()+"',major='"+majorInfo.getMajor()+"' where Cno="+majorInfo.getCno()+"";
        System.out.println(sql);
        int count=db.executeUpdate(sql);
		if(count>0){
			t = true;
		}
		db.releaseSource();
		return t;  
	}
	
	public  boolean insertMajorInfo(Major majorInfo){
		boolean t = false;
		DBManager db=new DBManager();
        String sql="insert into Major values("+majorInfo.getCno()+",'"+majorInfo.getCname()+"','"+majorInfo.getMajor()+"')";
        System.out.println(sql);
        int count=db.executeUpdate(sql);
		if(count>0){
			t = true;
		}
		db.releaseSource();
		return t;  
	}
}
