package system.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import system.DAO.ScoreDAO;
import system.DAO.StudentDAO;
import system.model.Page;
import system.model.Score;
import system.model.Student;

public class StudentServlet extends HttpServlet {

	/**
		 * Constructor of the object.
		 */
	public StudentServlet() {
		super();
	}

	/**
		 * Destruction of the servlet. <br>
		 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
		 * The doGet method of the servlet. <br>
		 *
		 * This method is called when a form has its tag value method equals to get.
		 * 
		 * @param request the request send by the client to the server
		 * @param response the response send by the server to the client
		 * @throws ServletException if an error occurred
		 * @throws IOException if an error occurred
		 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		System.out.println("进入StudentServlet");
		String operate = request.getParameter("operate");
		
		if(operate.equals("studentInfo")){
			this.doQueryStudentInfo(request,response);
		}else if(operate.equals("delete")){
			this.doDeleteBySno(request,response);
		}else if(operate.equals("deleteAll")){
			this.doDeleteAll(request,response);
		}else if(operate.equals("updateQuery")){
			this.doUpdateQuery(request,response);
		}else if(operate.equals("studentInfoUpdate")){
			this.doStudentInfoUpdate(request,response);
		}else if(operate.equals("studentInfoInsert")){
			this.doStudentInfoInsert(request,response);
		}
	}

	/**
		 * The doPost method of the servlet. <br>
		 *
		 * This method is called when a form has its tag value method equals to post.
		 * 
		 * @param request the request send by the client to the server
		 * @param response the response send by the server to the client
		 * @throws ServletException if an error occurred
		 * @throws IOException if an error occurred
		 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		out.print("    This is ");
		out.print(this.getClass());
		out.println(", using the POST method");
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
	}

	public void doQueryStudentInfo(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {				
				
				String pageN = null;
				pageN = request.getParameter("pageN");
				if(pageN == null || pageN==""){
					pageN = "1";
				}
				int pageSize = 10;
				int nowPage = Integer.parseInt(pageN);
				int beginNum= (nowPage-1)*pageSize+1 ;    
				int endNum= pageSize* nowPage;
	    
				Page page = new Page();
				page.setNowPage(nowPage);
				page.setBeginNum(beginNum);
				page.setEndNum(endNum);
		
				StudentDAO studentDAO = new StudentDAO();
				int recordsNum = studentDAO.queryAllStudentNum();
				ArrayList allStudentList = studentDAO.queryAllStudentList(page);
				
				request.setAttribute("allStudentList",allStudentList);
				RequestDispatcher rd = request.getRequestDispatcher("/manager/studentInfoManagement.jsp?recordsNum="+recordsNum+"&endNum="+endNum+"&nowPage="+nowPage+"");
				rd.forward(request, response);
			
	}
	
	public void doDeleteBySno(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
				String Sno = request.getParameter("Sno");
				
				StudentDAO deleteDAO = new StudentDAO();
		    	boolean t =  deleteDAO.deleteBySno(Sno);
		    	if(t==true){
		    		RequestDispatcher rd = request.getRequestDispatcher("/manager/studentInfoManagement.jsp");
					rd.forward(request, response);
		    			
		    	}else{
					System.out.println("删除失败！");
		    	}	    	
	}
	
	public void doDeleteAll(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
				String ID[]=request.getParameterValues("ids");
				
				if(ID==null){
					System.out.println("未选中删除记录");
					RequestDispatcher rd = request.getRequestDispatcher("/manager/studentInfoManagement.jsp");
					rd.forward(request, response);
				}else{
					StudentDAO deleteAllDAO = new StudentDAO();
					boolean t =false;
					for(int i=0;i<ID.length;i++){
					      String id = ID[i];

					      t = deleteAllDAO.deleteBySno(id); 
					}
					if(t==true){
						
						RequestDispatcher rd = request.getRequestDispatcher("/manager/studentInfoManagement.jsp");
						rd.forward(request, response);
						
					}
				}
				
			}
	
	public void doUpdateQuery(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {				
				
				StudentDAO updateQueryDAO = new StudentDAO();
				ArrayList studentInfoList = updateQueryDAO.queryAllStudentList();
				
				request.setAttribute("studentInfoList",studentInfoList);
				RequestDispatcher rd = request.getRequestDispatcher("/manager/studentInfoUpdate.jsp");
				rd.forward(request, response);
			
	}
	
	public void doStudentInfoUpdate(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
				String ID[]=request.getParameterValues("ids");
				String Ssex[] = request.getParameterValues("Ssex");
				String Sage[] =request.getParameterValues("Sage");
				String major[] =request.getParameterValues("major");

				if(ID==null){
					System.out.println("未选中修改记录");
					RequestDispatcher rd = request.getRequestDispatcher("/manager/studentInfoUpdate.jsp");
					rd.forward(request, response);
				}else{
					StudentDAO updateDAO = new StudentDAO();
					Student student = new Student();
					boolean t =false;
					for(int i=0;i<ID.length;i++){
						
						student.setSno(ID[i]);
						student.setSsex(Ssex[i]);
						student.setSage(Sage[i]);
						student.setMajor(major[i]);

					    t = updateDAO.updateBySno(student); 
					}
					if(t==true){
						
						RequestDispatcher rd = request.getRequestDispatcher("/manager/studentInfoUpdate.jsp");
						rd.forward(request, response);
						
					}
				}
				
			}
	
	public void doStudentInfoInsert(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
				String Sno= request.getParameter("Sno");
				String Sname = request.getParameter("Sname");
				String Ssex = request.getParameter("Ssex");
				String Sage = request.getParameter("Sage");
				String Spassword = request.getParameter("Spassword");
				String major= request.getParameter("major");
				String purview = request.getParameter("purview");
				
				if(Sno==null){
					System.out.println("学号不能为空！");
					RequestDispatcher rd = request.getRequestDispatcher("/manager/studentAdd.jsp");
					rd.forward(request, response);
				}else{
					StudentDAO insertDAO = new StudentDAO();
					Student studentInfo = new Student();
					
					studentInfo.setSno(Sno);
					studentInfo.setSname(Sname);
					studentInfo.setSsex(Ssex);
					studentInfo.setSage(Sage);
					studentInfo.setSpassword(Spassword);
					studentInfo.setMajor(major);
					studentInfo.setPurview(purview);
					
					boolean t =false;

					t = insertDAO.insertStudentInfo(studentInfo); 
					
					if(t==true){
						RequestDispatcher rd = request.getRequestDispatcher("/manager/studentInfoManagement.jsp");
						rd.forward(request, response);						
						
					}
				}
				
			}
	
	/**
		 * Returns information about the servlet, such as 
		 * author, version, and copyright. 
		 *
		 * @return String information about this servlet
		 */
	public String getServletInfo() {
		return "This is my default servlet created by Eclipse";
	}

	/**
		 * Initialization of the servlet. <br>
		 *
		 * @throws ServletException if an error occurs
		 */
	public void init() throws ServletException {
		// Put your code here
	}

}
