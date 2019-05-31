package system.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import system.DAO.StudentDAO;
import system.DAO.TeacherDAO;
import system.model.Page;
import system.model.Student;
import system.model.Teacher;

public class TeacherServlet extends HttpServlet {

	/**
		 * Constructor of the object.
		 */
	public TeacherServlet() {
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

		System.out.println("进入TeacherServlet");
		String operate = request.getParameter("operate");
		
		if(operate.equals("teacherInfo")){
			this.doQueryTeacherInfo(request,response);
		}else if(operate.equals("delete")){
			this.doDeleteByTno(request,response);
		}else if(operate.equals("deleteAll")){
			this.doDeleteAll(request,response);
		}else if(operate.equals("updateQuery")){
			this.doUpdateQuery(request,response);
		}else if(operate.equals("teacherInfoUpdate")){
			this.doTeacherInfoUpdate(request,response);
		}else if(operate.equals("teacherInfoInsert")){
			this.doTeacherInfoInsert(request,response);
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
	
	public void doQueryTeacherInfo(HttpServletRequest request, HttpServletResponse response)
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
		
				TeacherDAO teacherDAO = new TeacherDAO();
				int recordsNum = teacherDAO.queryAllTeacherNum();
				ArrayList allTeacherList = teacherDAO.queryAllTeacherList(page);
				
				request.setAttribute("allTeacherList",allTeacherList);
				RequestDispatcher rd = request.getRequestDispatcher("/manager/teacherInfoManagement.jsp?recordsNum="+recordsNum+"&endNum="+endNum+"&nowPage="+nowPage+"");
				rd.forward(request, response);
			
	}
	
	public void doDeleteByTno(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
				String Tno = request.getParameter("Tno");
				
				TeacherDAO deleteDAO = new TeacherDAO();
		    	boolean t =  deleteDAO.deleteByTno(Tno);
		    	if(t==true){
		    		response.sendRedirect("manager/teacherInfoManagement.jsp");
		    			
		    	}else{
					System.out.println("删除失败！");
		    	}	    	
	}
	
	public void doDeleteAll(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
				String ID[]=request.getParameterValues("ids");
				
				if(ID==null){
					System.out.println("未选中删除记录");
					RequestDispatcher rd = request.getRequestDispatcher("/manager/teacherInfoManagement.jsp");
					rd.forward(request, response);
				}else{
					TeacherDAO deleteAllDAO = new TeacherDAO();
					boolean t =false;
					for(int i=0;i<ID.length;i++){
					      String id = ID[i];

					      t = deleteAllDAO.deleteByTno(id); 
					}
					if(t==true){
						
						RequestDispatcher rd = request.getRequestDispatcher("/manager/teacherInfoManagement.jsp");
						rd.forward(request, response);
						
					}
				}
				
			}
	
	public void doUpdateQuery(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {				;				
				
				TeacherDAO updateQueryDAO = new TeacherDAO();
				ArrayList teacherInfoList = updateQueryDAO.queryAllTeacherList();
				
				request.setAttribute("teacherInfoList",teacherInfoList);
				RequestDispatcher rd = request.getRequestDispatcher("/manager/teacherInfoUpdate.jsp");
				rd.forward(request, response);
			
	}

	public void doTeacherInfoUpdate(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
				String ID[]=request.getParameterValues("ids");
				String Tsex[] = request.getParameterValues("Tsex");
				String Cno[] =request.getParameterValues("Cno");
				String Cname[] =request.getParameterValues("Cname");
				
				if(ID==null){
					System.out.println("未选中修改记录");
					RequestDispatcher rd = request.getRequestDispatcher("/manager/teacherInfoUpdate.jsp");
					rd.forward(request, response);
				}else{
					TeacherDAO updateDAO = new TeacherDAO();
					Teacher teacher = new Teacher();
					boolean t =false;
					for(int i=0;i<ID.length;i++){
						
						teacher.setTno(ID[i]);
						teacher.setTsex(Tsex[i]);
						teacher.setCno(Cno[i]);
						teacher.setCname(Cname[i]);

					    t = updateDAO.updateByTno(teacher); 
					}
					if(t==true){
						
						RequestDispatcher rd = request.getRequestDispatcher("/manager/manager_index.jsp");
						rd.forward(request, response);
						
					}
				}
				
			}
	
	public void doTeacherInfoInsert(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
				String Tno= request.getParameter("Tno");
				String Tname = request.getParameter("Tname");
				String Tsex = request.getParameter("Tsex");
				String CnoCname = request.getParameter("CnoCname");
				String[] words = CnoCname.split(",");
				String Cno = words[0];
				String Cname= words[1];
				String Tpassword = request.getParameter("Tpassword");
				String purview = request.getParameter("purview");
			
				if(Tno==null){
					System.out.println("职工号不能为空！");
					RequestDispatcher rd = request.getRequestDispatcher("/manager/teacherAdd.jsp");
					rd.forward(request, response);
				}else{
					TeacherDAO insertDAO = new TeacherDAO();
					Teacher teacherInfo = new Teacher();
					
					teacherInfo.setTno(Tno);
					teacherInfo.setTname(Tname);
					teacherInfo.setTsex(Tsex);
					teacherInfo.setCno(Cno);
					teacherInfo.setTpassword(Tpassword);
					teacherInfo.setCname(Cname);
					teacherInfo.setPurview(purview);
					
					boolean t =false;

					t = insertDAO.insertTeacherInfo(teacherInfo); 
					
					if(t==true){
						RequestDispatcher rd = request.getRequestDispatcher("/manager/teacherInfoManagement.jsp");
						rd.forward(request, response);						
						
					}
				}
				
			}
	
	private String words(int i) {
		// TODO Auto-generated method stub
		return null;
	}

	private String split(String cno_Cname, String string) {
		// TODO Auto-generated method stub
		return null;
	}

	private String StringOfValue(int index) {
		// TODO Auto-generated method stub
		return null;
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
