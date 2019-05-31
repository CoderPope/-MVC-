package system.tools;

import java.sql.*;

public class DBManager {
   private Connection conn = null;
   private Statement stmt = null;
   private ResultSet rs = null;
   private String uri = "jdbc:sqlserver://localhost:1433;DatabaseName=score_manager";
   private String user = "sa";
   private String passWord = "wsad1379519280";
	
   public DBManager(){
	   
	   this.conn = this.getConnection();
   }
   /** 连接数据库方法
    * 
    * @return conn
    */
   private Connection getConnection(){
	   
	   try {
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		conn = DriverManager.getConnection(uri,user,passWord);
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		System.out.println("驱动错误！");
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		System.out.println("获取连接错误！");
	}
	   
	   return conn;
   } 
   
   /** 查询方法
    * @param sql
    * @return rs
    */
   public ResultSet executeQuery(String sql){
	   try {
		stmt = conn.createStatement();
		rs = stmt.executeQuery(sql);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		System.out.println("执行sql语句错误！");
	}
	   
	   return rs;
   }
   /** 释放资源
    * 
    */
   public void releaseSource(){
	  
		try {
			 if(rs!=null) rs.close();
			 if(stmt!=null) stmt.close();
			 if(conn!=null) conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	   
   }
   
   /**
	 * 更新方法
	 * 
	 * @param sql
	 * @return count
	 */
   public int executeUpdate(String sql){
	   int count = 0;
	   try {
		stmt = conn.createStatement();
		count = stmt.executeUpdate(sql);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		System.out.println("执行更新错误！");
	}
	   
	   return count;
   }
   
}
