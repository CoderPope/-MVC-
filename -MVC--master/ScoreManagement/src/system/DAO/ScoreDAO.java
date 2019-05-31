package system.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import system.model.Course;
import system.model.Page;
import system.model.Score;
import system.model.Table;
import system.model.User;
import system.tools.DBManager;

public class ScoreDAO {
	public  ArrayList queryScoreList(String Sno,Page page){

		ArrayList scoreList = new ArrayList();
		String sql = "select Cname,type,grade from (select  Cname,type,grade, num = row_number() over (order by grade ASC) from  Score,Course where Score.Sno="+Sno+" and Score.Cno = Course.Cno) t where num between "+page.getBeginNum()+" and "+page.getEndNum()+"";
		System.out.println(sql);
		DBManager db = new DBManager();
		ResultSet rs = db.executeQuery(sql);
		
		try {
			while(rs.next()){
				Score score = new Score();
				score.setCname(rs.getString("Cname"));
				score.setType(rs.getString("type"));
				score.setGrade(rs.getString("grade"));
				scoreList.add(score);
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			db.releaseSource();
		}
		return scoreList;
		
	}
	
	public  int queryCreditSum(String Sno){
		int sum = 0;
		String sql = "select sum(credit) as sum from Course,Score where Score.Sno='"+Sno+"' and Score.grade>=60 and Score.Cno = Course.Cno";
		System.out.println(sql);
		DBManager db = new DBManager();
		ResultSet rs = db.executeQuery(sql);
		
		try {
			while(rs.next()){
				sum=rs.getInt("sum");	
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			db.releaseSource();
		}
		return sum;
		
	}
	
	public  ArrayList queryCourseList(String Sno){

		ArrayList courseList = new ArrayList();
		String sql = "select Cno,Cname from Course where Cno in (select Cno from Score where Sno='"+Sno+"')";
		System.out.println(sql);
		DBManager db = new DBManager();
		ResultSet rs = db.executeQuery(sql);
		
		try {
			while(rs.next()){
				Course course = new Course();
				course.setCno(rs.getString("Cno"));
				course.setCname(rs.getString("Cname"));
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
	
	public  ArrayList queryScoreListByCourse(String Cno,String Sno){

		ArrayList scoreList = new ArrayList();
		String sql = "select Cname,type,grade from Score,Course where Course.Cno="+Cno+" and Score.Cno = Course.Cno and Score.Sno="+Sno+"";
		System.out.println(sql);
		DBManager db = new DBManager();
		ResultSet rs = db.executeQuery(sql);
		
		try {
			while(rs.next()){
				Score score = new Score();
				score.setCname(rs.getString("Cname"));
				score.setType(rs.getString("type"));
				score.setGrade(rs.getString("grade"));
				scoreList.add(score);
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			db.releaseSource();
		}
		return scoreList;
		
	}
	
	public  ArrayList queryScoreListByType(String type,String Sno){

		ArrayList scoreList = new ArrayList();
		String sql = "select Cname,type,grade from Score,Course where Course.type='"+type+"' and Score.Cno = Course.Cno and Score.Sno="+Sno+"";
		System.out.println(sql);
		DBManager db = new DBManager();
		ResultSet rs = db.executeQuery(sql);
		
		try {
			while(rs.next()){
				Score score = new Score();
				score.setCname(rs.getString("Cname"));
				score.setType(rs.getString("type"));
				score.setGrade(rs.getString("grade"));
				scoreList.add(score);
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			db.releaseSource();
		}
		return scoreList;
		
	}
	
	public  ArrayList queryAllScoreList(Page page){

		ArrayList allScoreList = new ArrayList();
		String sql = "select * from (select Score.Sno,StudentInfo.Sname,Course.Cname,Course.type,Score.grade, num = row_number() over (order by grade ASC) from  Score,Course,StudentInfo where Score.Cno = Course.Cno and Score.Sno = StudentInfo.Sno) t where num between "+page.getBeginNum()+" and "+page.getEndNum()+"";
		System.out.println(sql);
		DBManager db = new DBManager();
		ResultSet rs = db.executeQuery(sql);
		
		try {
			while(rs.next()){
				Score allScore = new Score();
				allScore.setSno(rs.getString(1));
				allScore.setSname(rs.getString("Sname"));
				allScore.setCname(rs.getString("Cname"));
				allScore.setType(rs.getString("type"));
				allScore.setGrade(rs.getString("grade"));
				allScoreList.add(allScore);
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			db.releaseSource();
		}
		return allScoreList;
		
	}
	
	public  int queryAllScoreNum(){

		String sql = "select count(grade) as nums from Score";
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
	
	public  ArrayList queryClassScoreList(String Tno){

		ArrayList classScoreList = new ArrayList();
		String sql = "select * from Score,StudentInfo,TeacherInfo where TeacherInfo.Tno="+Tno+" and TeacherInfo.Cno = Score.Cno and Score.Sno = StudentInfo.Sno";
		System.out.println(sql);
		DBManager db = new DBManager();
		ResultSet rs = db.executeQuery(sql);
		
		try {
			while(rs.next()){
				Score classScore = new Score();
				classScore.setSno(rs.getString(1));
				classScore.setCno(rs.getString("Cno"));
				classScore.setSname(rs.getString("Sname"));
				classScore.setSsex(rs.getString("Ssex"));
				classScore.setMajor(rs.getString("major"));
				classScore.setCname(rs.getString("Cname"));				
				classScore.setGrade(rs.getString("grade"));
				classScoreList.add(classScore);
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			db.releaseSource();
		}
		return classScoreList;
		
	}
	
	public  ArrayList querySortScoreList(String Tno){

		ArrayList sortScoreList = new ArrayList();
		String sql = "select * from Score,StudentInfo,TeacherInfo where TeacherInfo.Tno="+Tno+" and TeacherInfo.Cno = Score.Cno and Score.Sno = StudentInfo.Sno order by Grade Desc";
		System.out.println(sql);
		DBManager db = new DBManager();
		ResultSet rs = db.executeQuery(sql);
		
		try {
			while(rs.next()){
				Score classScore = new Score();
				classScore.setSno(rs.getString(1));
				classScore.setCno(rs.getString("Cno"));
				classScore.setSname(rs.getString("Sname"));
				classScore.setSsex(rs.getString("Ssex"));
				classScore.setMajor(rs.getString("major"));
				classScore.setCname(rs.getString("Cname"));				
				classScore.setGrade(rs.getString("grade"));
				sortScoreList.add(classScore);
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			db.releaseSource();
		}
		return sortScoreList;
		
	}
	
	public  ArrayList querySortBySnoScoreList(String Tno){

		ArrayList sortScoreList = new ArrayList();
		String sql = "select * from Score,StudentInfo,TeacherInfo where TeacherInfo.Tno="+Tno+" and TeacherInfo.Cno = Score.Cno and Score.Sno = StudentInfo.Sno order by Score.Sno Desc";
		System.out.println(sql);
		DBManager db = new DBManager();
		ResultSet rs = db.executeQuery(sql);
		
		try {
			while(rs.next()){
				Score classScore = new Score();
				classScore.setSno(rs.getString(1));
				classScore.setCno(rs.getString("Cno"));
				classScore.setSname(rs.getString("Sname"));
				classScore.setSsex(rs.getString("Ssex"));
				classScore.setMajor(rs.getString("major"));
				classScore.setCname(rs.getString("Cname"));				
				classScore.setGrade(rs.getString("grade"));
				sortScoreList.add(classScore);
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			db.releaseSource();
		}
		return sortScoreList;
		
	}
	
	public  boolean deleteByIdAndCno(String Sno,String Cno){
		boolean t = false;
		DBManager db=new DBManager();
        String sql="delete from Score where Sno='"+Sno+"' and Cno='"+Cno+"'";
        int count=db.executeUpdate(sql);
		if(count>0){
			t = true;
		}
		db.releaseSource();
		return t;  
	}
	
	public  boolean updateByIdAndCno(Score updateScore){
		boolean t = false;
		DBManager db=new DBManager();
        String sql="update Score set grade="+updateScore.getGrade()+" where Sno='"+updateScore.getSno()+"' and Cno='"+updateScore.getCno()+"'";
        System.out.println(sql);
        int count=db.executeUpdate(sql);
		if(count>0){
			t = true;
		}
		db.releaseSource();
		return t;  
	}
	
	public  ArrayList queryClassNumberList(String Cno){

		ArrayList classNumberList = new ArrayList();
		String sql = "select Sno,Sname from StudentInfo,Major where Major.Cno="+Cno+" and Major.major = StudentInfo.major";
		System.out.println(sql);
		DBManager db = new DBManager();
		ResultSet rs = db.executeQuery(sql);
		
		try {
			while(rs.next()){
				Score classNumber = new Score();
				classNumber.setSno(rs.getString("Sno"));
				classNumber.setSname(rs.getString("Sname"));
				classNumberList.add(classNumber);
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			db.releaseSource();
		}
		return classNumberList;
		
	}
	
	public  boolean insertByIdAndCno(Score insertScore){
		boolean t = false;
		DBManager db=new DBManager();
        String sql="insert into Score values("+insertScore.getSno()+","+insertScore.getCno()+",'"+insertScore.getGrade()+"')";
        System.out.println(sql);
        int count=db.executeUpdate(sql);
		if(count>0){
			t = true;
		}
		db.releaseSource();
		return t;  
	}
	
	public  int queryScoreNum(String Sno){

		String sql = "select count(Score.Cno) as nums from Score,Course where Score.Sno='"+Sno+"' and Score.Cno = Course.Cno";
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
	
	public  ArrayList queryExportScoreList(String Sno){

		ArrayList scoreList = new ArrayList();
		String sql = "select Score.Sno,Sname,Cname,type,grade from Score,Course,StudentInfo where Score.Sno="+Sno+" and Score.Cno=Course.Cno and Score.Sno=StudentInfo.Sno";
		System.out.println(sql);
		DBManager db = new DBManager();
		ResultSet rs = db.executeQuery(sql);
		
		try {
			while(rs.next()){
				Score score = new Score();
				score.setSno(rs.getString("Sno"));
				score.setSname(rs.getString("Sname"));
				score.setCname(rs.getString("Cname"));
				score.setType(rs.getString("type"));
				score.setGrade(rs.getString("grade"));
				scoreList.add(score);
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			db.releaseSource();
		}
		return scoreList;
		
	}
	
	public boolean isRepeat(Score insertScore){
		boolean t = false;
		String sql ="select * from Score where Sno="+insertScore.getSno()+" and Cno="+insertScore.getCno()+"";
		DBManager db = new DBManager();
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
	
	public  ArrayList queryClassExportScoreList(String Tno){

		ArrayList classScoreList = new ArrayList();
		String sql = "select * from Score,StudentInfo,TeacherInfo where TeacherInfo.Tno="+Tno+" and TeacherInfo.Cno = Score.Cno and Score.Sno = StudentInfo.Sno";
		System.out.println(sql);
		DBManager db = new DBManager();
		ResultSet rs = db.executeQuery(sql);
		
		try {
			while(rs.next()){
				Score classScore = new Score();
				classScore.setSno(rs.getString(1));
				classScore.setCno(rs.getString("Cno"));
				classScore.setSname(rs.getString("Sname"));
				classScore.setSsex(rs.getString("Ssex"));
				classScore.setMajor(rs.getString("major"));
				classScore.setCname(rs.getString("Cname"));				
				classScore.setGrade(rs.getString("grade"));
				classScoreList.add(classScore);
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			db.releaseSource();
		}
		return classScoreList;
		
	}
	
	public  ArrayList queryBracketList(String Cno){

		ArrayList bracketList = new ArrayList();
		String sql = "select count(case when grade>=90 then 1 end)as A,count(case when grade>=80 and grade<90 then 1 end)as B,count(case when grade>70 and grade<80 then 1 end)as C,count(case when grade>=60 and grade<70 then 1 end)as D,count(case when grade<60 then 1 end)as E from Score where Cno="+Cno+"";
		System.out.println(sql);
		DBManager db = new DBManager();
		ResultSet rs = db.executeQuery(sql);
		try {
			while(rs.next()){

				bracketList.add(rs.getString("A"));
				bracketList.add(rs.getString("B"));
				bracketList.add(rs.getString("C"));
				bracketList.add(rs.getString("D"));
				bracketList.add(rs.getString("E"));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			db.releaseSource();
		}
		return bracketList;
		
	}
	
}
