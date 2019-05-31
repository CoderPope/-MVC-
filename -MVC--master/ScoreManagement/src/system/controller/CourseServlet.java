package system.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import system.DAO.CourseDAO;
import system.DAO.StudentDAO;
import system.model.Course;
import system.model.Page;
import system.model.Student;

public class CourseServlet extends HttpServlet {

	/**
		 * Constructor of the object.
		 */
	public CourseServlet() {
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

		System.out.println("进入course_servlet");
		String operate = request.getParameter("operate");
		
		if(operate.equals("course_query")){
			this.doCourseQuery(request,response);
		}else if(operate.equals("allCourseQuery")){
			this.doAllCourseQuery(request,response);
		}else if(operate.equals("delete")){
			this.doDeleteByCno(request,response);
		}else if(operate.equals("deleteAll")){
			this.doDeleteAll(request,response);
		}else if(operate.equals("updateQuery")){
			this.doUpdateQuery(request,response);
		}else if(operate.equals("courseInfoUpdate")){
			this.doCourseInfoUpdate(request,response);
		}else if(operate.equals("courseInsert")){
			this.doCourseInsert(request,response);
		}else if(operate.equals("queryCno")){
			this.doQueryCno(request,response);
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
	
	public void doCourseQuery(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
				String major = request.getParameter("major");
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
			    
				CourseDAO courseDAO = new CourseDAO();
				ArrayList courseList = courseDAO.queryCourseList(major,page);
				int recordsNum = courseDAO.queryCourseNum(major);
				
				request.setAttribute("courseList",courseList);
				RequestDispatcher rd = request.getRequestDispatcher("student/course_query.jsp?recordsNum="+recordsNum+"&endNum="+endNum+"&nowPage="+nowPage+"");
				rd.forward(request, response);
			}
	
	public void doAllCourseQuery(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {				
				String href = request.getParameter("href");
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
			
				CourseDAO allCourseDAO = new CourseDAO();
				int recordsNum = allCourseDAO.queryAllCourseNum();
				ArrayList allCourseList = allCourseDAO.queryAllCourseList(page);
				
				
				request.setAttribute("allCourseList",allCourseList);
				RequestDispatcher rd = null;
				
				if(href.equals("teacher")){
					rd = request.getRequestDispatcher("teacher/allCourseQuery.jsp?recordsNum="+recordsNum+"&endNum="+endNum+"&nowPage="+nowPage+"");
				}else if(href.equals("management")){
					rd = request.getRequestDispatcher("/manager/courseInfoManagement.jsp?recordsNum="+recordsNum+"&endNum="+endNum+"&nowPage="+nowPage+"");
				}
				 
				rd.forward(request, response);
			}
	
	public void doDeleteByCno(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
				String Cno = request.getParameter("Cno");
				
				CourseDAO deleteDAO = new CourseDAO();
		    	boolean t =  deleteDAO.deleteByCno(Cno);
		    	if(t==true){
		    		RequestDispatcher rd = request.getRequestDispatcher("/manager/courseInfoManagement.jsp");
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
					RequestDispatcher rd = request.getRequestDispatcher("/manager/courseInfoManagement.jsp");
					rd.forward(request, response);
				}else{
					CourseDAO deleteAllDAO = new CourseDAO();
					boolean t =false;
					for(int i=0;i<ID.length;i++){
					      String id = ID[i];

					      t = deleteAllDAO.deleteByCno(id); 
					}
					if(t==true){
						
						RequestDispatcher rd = request.getRequestDispatcher("/manager/courseInfoManagement.jsp");
						rd.forward(request, response);
						
					}
				}
				
			}
	
	public void doUpdateQuery(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {				;
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
				
				CourseDAO updateQueryDAO = new CourseDAO();
				int recordsNum = updateQueryDAO.queryAllCourseNum();
				ArrayList courseInfoList = updateQueryDAO.queryAllCourseList(page);
				
				request.setAttribute("courseInfoList",courseInfoList);
				RequestDispatcher rd = request.getRequestDispatcher("/manager/courseInfoUpdate.jsp?recordsNum="+recordsNum+"&endNum="+endNum+"&nowPage="+nowPage+"");
				rd.forward(request, response);
			
	}
	
	public void doCourseInfoUpdate(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
				String ID[]=request.getParameterValues("ids");
				String credit[] = request.getParameterValues("credit");
				String type[] =request.getParameterValues("type");

				if(ID==null){
					System.out.println("未选中修改记录");
					RequestDispatcher rd = request.getRequestDispatcher("/manager/courseInfoUpdate.jsp");
					rd.forward(request, response);
				}else{
					CourseDAO updateDAO = new CourseDAO();
					Course course = new Course();
					boolean t =false;
					for(int i=0;i<ID.length;i++){
						
						course.setCno(ID[i]);
						course.setCredit(credit[i]);
						course.setType(type[i]);

					    t = updateDAO.updateByCno(course); 
					}
					if(t==true){
						
						RequestDispatcher rd = request.getRequestDispatcher("/manager/courseInfoUpdate.jsp");
						rd.forward(request, response);
						
					}
				}
				
			}
	
	public void doCourseInsert(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
				String Cno= request.getParameter("Cno");
				String Cname = request.getParameter("Cname");
				String credit= request.getParameter("credit");
				String type = request.getParameter("type");
				
				if(Cno==null){
					System.out.println("课程号不能为空！");
					RequestDispatcher rd = request.getRequestDispatcher("/manager/courseAdd.jsp");
					rd.forward(request, response);
				}else{
					CourseDAO insertDAO = new CourseDAO();
					Course courseInfo = new Course();
					
					courseInfo.setCno(Cno);
					courseInfo.setCname(Cname);
					courseInfo.setCredit(credit);
					courseInfo.setType(type);
					
					boolean t =false;

					t = insertDAO.insertCourseInfo(courseInfo); 
					
					if(t==true){
						RequestDispatcher rd = request.getRequestDispatcher("/manager/courseInfoManagement.jsp");
						rd.forward(request, response);						
						
					}
				}
				
			}
	
	public void doQueryCno(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {								
				String href = request.getParameter("href");
				CourseDAO allCnoDAO = new CourseDAO();
				ArrayList CnoList = allCnoDAO.queryAllCnoList();				
				
				request.setAttribute("CnoList",CnoList);
				RequestDispatcher rd = null;
				if(href.equals("teacher")){
					rd = request.getRequestDispatcher("/manager/teacherAdd.jsp");
				}else if(href.equals("major")){
					rd = request.getRequestDispatcher("/manager/majorAdd.jsp");
				}							
				rd.forward(request, response);
			}
	
	public void init() throws ServletException {
		// Put your code here
	}

}
