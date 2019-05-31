package system.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import system.DAO.MajorDAO;
import system.DAO.StudentDAO;
import system.model.Major;
import system.model.Page;
import system.model.Student;

public class MajorServlet extends HttpServlet {

	/**
		 * Constructor of the object.
		 */
	public MajorServlet() {
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

		System.out.println("进入MajorServlet");
		String operate = request.getParameter("operate");
		
		if(operate.equals("majorInfo")){
			this.doQueryMajorInfo(request,response);
		}else if(operate.equals("delete")){
			this.doDeleteByCno(request,response);
		}else if(operate.equals("deleteAll")){
			this.doDeleteAll(request,response);
		}else if(operate.equals("updateQuery")){
			this.doUpdateQuery(request,response);
		}else if(operate.equals("majorInfoUpdate")){
			this.doMajorInfoUpdate(request,response);
		}else if(operate.equals("majorInfoInsert")){
			this.doMajorInfoInsert(request,response);
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
	
	public void doQueryMajorInfo(HttpServletRequest request, HttpServletResponse response)
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
		
				MajorDAO majorDAO = new MajorDAO();
				int recordsNum = majorDAO.queryAllMajorNum();
				ArrayList allMajorList = majorDAO.queryAllMajorList(page);
				
				request.setAttribute("allMajorList",allMajorList);
				RequestDispatcher rd = request.getRequestDispatcher("manager/majorInfoManagement.jsp?recordsNum="+recordsNum+"&endNum="+endNum+"&nowPage="+nowPage+"");
				rd.forward(request, response);
			
	}
	
	public void doDeleteByCno(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
				String Cno = request.getParameter("Cno");
				
				MajorDAO deleteDAO = new MajorDAO();
		    	boolean t =  deleteDAO.deleteByCno(Cno);
		    	if(t==true){
		    		RequestDispatcher rd = request.getRequestDispatcher("manager/majorInfoManagement.jsp");
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
					RequestDispatcher rd = request.getRequestDispatcher("manager/majorInfoManagement.jsp");
					rd.forward(request, response);
				}else{
					MajorDAO deleteAllDAO = new MajorDAO();
					boolean t =false;
					for(int i=0;i<ID.length;i++){
					      String id = ID[i];

					      t = deleteAllDAO.deleteByCno(id); 
					}
					if(t==true){
						
						RequestDispatcher rd = request.getRequestDispatcher("manager/majorInfoManagement.jsp");
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
				
				MajorDAO updateQueryDAO = new MajorDAO();
				int recordsNum = updateQueryDAO.queryAllMajorNum();
				ArrayList majorInfoList = updateQueryDAO.queryAllMajorList(page);
				
				request.setAttribute("majorInfoList",majorInfoList);
				RequestDispatcher rd = request.getRequestDispatcher("manager/majorInfoUpdate.jsp?recordsNum="+recordsNum+"&endNum="+endNum+"&nowPage="+nowPage+"");
				rd.forward(request, response);
			
	}
	
	public void doMajorInfoUpdate(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
				String ID[]=request.getParameterValues("ids");
				String Cname[] = request.getParameterValues("Cname");
				String major[] =request.getParameterValues("major");

				if(ID==null){
					System.out.println("未选中修改记录");
					response.sendRedirect("manager/majorInfoUpdate.jsp");
				}else{
					MajorDAO updateDAO = new MajorDAO();
					Major majorInfo = new Major();
					boolean t =false;
					for(int i=0;i<ID.length;i++){
						
						majorInfo.setCno(ID[i]);
						majorInfo.setCname(Cname[i]);
						majorInfo.setMajor(major[i]);

					    t = updateDAO.updateByCno(majorInfo); 
					}
					if(t==true){
						
						RequestDispatcher rd = request.getRequestDispatcher("manager/majorInfoUpdate.jsp");
						rd.forward(request, response);
						
					}
				}
				
			}
	
	public void doMajorInfoInsert(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
				String CnoCname = request.getParameter("CnoCname");
				String[] words = CnoCname.split(",");
				String Cno= words[0];
				String Cname = words[1];
				String major= request.getParameter("major");
				
				if(Cno==null){
					System.out.println("课程号不能为空！");
					response.sendRedirect("manager/majortAdd.jsp");
				}else{
					MajorDAO insertDAO = new MajorDAO();
					Major majorInfo = new Major();
					
					majorInfo.setCno(Cno);
					majorInfo.setCname(Cname);
					majorInfo.setMajor(major);
					
					boolean t =false;

					t = insertDAO.insertMajorInfo(majorInfo); 
					
					if(t==true){
						response.sendRedirect("manager/majorInfoManagement.jsp");						
						
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
