package system.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.Response;

import system.DAO.CourseDAO;
import system.DAO.ScoreDAO;
import system.model.Page;
import system.model.Score;
import system.tools.DBManager;

public class ScoreServlet extends HttpServlet {

	/**
		 * Constructor of the object.
		 */
	public ScoreServlet() {
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

		System.out.println("进入score_servlet");
		String operate = request.getParameter("operate");
		
		if(operate.equals("student_query")){
			this.doScoreQueryBySno(request,response);
		}else if(operate.equals("course_query")){
			this.doCourseQuery(request,response);			
		}else if(operate.equals("score_queryByCourse")){
			this.doScoreQueryByCourse(request,response);
		}else if(operate.equals("queryByType")){
			this.doScoreQueryByType(request,response);
		}else if(operate.equals("allScoreQuery")){
			this.doAllScoreQuery(request,response);
		}else if(operate.equals("classQuery")){
			this.doClassQuery(request,response);
		}else if(operate.equals("delete")){
			System.out.println("-----删除学生成绩----");
			this.doDelete(request, response);
		}else if(operate.equals("updateQuery")){
			this.doUpdateQuery(request,response);
		}else if(operate.equals("updateScore")){
			this.doUpdateScore(request,response);
		}else if(operate.equals("insertQuery")){
			this.doInsertQuery(request,response);
		}else if(operate.equals("export")){
			this.doScoreExport(request,response);
		}else if(operate.equals("classScoreExport")){
			this.doClassScoreExport(request,response);
		}else if(operate.equals("sortByGrade")){
			this.doSortByGrade(request,response);
		}else if(operate.equals("sortBySno")){
			this.doSortBySno(request,response);
		}else if(operate.equals("bracket")){
			this.doBracket(request,response);
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

		System.out.println("进入score_servlet");
		String operate = request.getParameter("operate");
		if(operate.equals("insertScore")){
			this.doInsertScore(request,response);
		}else if(operate.equals("intoTemp")){
			this.doIntoTemp(request,response);
		}else if(operate.equals("deleteAll")){
			System.out.println("-----批量删除学生成绩----");
			this.doDeleteAll(request, response);
		}else if(operate.equals("singleUpdate")){
			this.doSingleUpdate(request,response);
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
	
	public void doScoreQueryBySno(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
				
				String Sno = request.getParameter("Sno");
				String pageN = null;
				pageN = request.getParameter("pageN");
				if(pageN == null || pageN==""){
					 pageN = "1";
				}
				int pageSize = 6;
				int nowPage = Integer.parseInt(pageN);
			    int beginNum= (nowPage-1)*pageSize+1 ;    
			    int endNum= pageSize* nowPage;
			    
			    Page page = new Page();
			    page.setNowPage(nowPage);
			    page.setBeginNum(beginNum);
			    page.setEndNum(endNum);
				
				ScoreDAO scoreDAO = new ScoreDAO();
				ArrayList scoreList = scoreDAO.queryScoreList(Sno,page);
				int recordsNum = scoreDAO.queryScoreNum(Sno);
				int sum = scoreDAO.queryCreditSum(Sno);
				
				request.setAttribute("scoreList",scoreList);
				RequestDispatcher rd = request.getRequestDispatcher("student/score_student_query.jsp?creditSum="+sum+"&recordsNum="+recordsNum+"&endNum="+endNum+"&nowPage="+nowPage+"");
				rd.forward(request, response);
			
	}
	
	public void doCourseQuery(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
				
				String Sno = request.getParameter("Sno");
				
				ScoreDAO courseDAO = new ScoreDAO();
				ArrayList courseList = courseDAO.queryCourseList(Sno);
				
				HttpSession session = request.getSession();
				session.setAttribute("courseList",courseList);
				response.sendRedirect("student/score_queryByCondition.jsp");
			}
	
	public void doScoreQueryByCourse(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
				
				String Cno = (String)request.getParameter("Cno");
				String Sno = request.getParameter("Sno");
				
				ScoreDAO conditionDAO = new ScoreDAO();
				ArrayList scoreList = conditionDAO.queryScoreListByCourse(Cno,Sno);
				
				request.setAttribute("scoreList",scoreList);
				RequestDispatcher rd = request.getRequestDispatcher("student/score_queryByCondition.jsp");
				rd.forward(request, response);
			
	}
	
	public void doScoreQueryByType(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
				
				String type = (String)request.getParameter("type");
				String Sno = request.getParameter("Sno");
				
				ScoreDAO typeDAO = new ScoreDAO();
				ArrayList scoreList = typeDAO.queryScoreListByType(type,Sno);
				
				request.setAttribute("scoreList",scoreList);
				RequestDispatcher rd = request.getRequestDispatcher("student/score_queryByCondition.jsp");
				rd.forward(request, response);
			
	}
	
	public void doAllScoreQuery(HttpServletRequest request, HttpServletResponse response)
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
		
				ScoreDAO allScoreDAO = new ScoreDAO();
				int recordsNum = allScoreDAO.queryAllScoreNum();
				ArrayList allScoreList = allScoreDAO.queryAllScoreList(page);
				
				request.setAttribute("allScoreList",allScoreList);
				RequestDispatcher rd = request.getRequestDispatcher("teacher/allScoreQuery.jsp?recordsNum="+recordsNum+"&endNum="+endNum+"&nowPage="+nowPage+"");
				rd.forward(request, response);
			
	}
	
	public void doClassQuery(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {				
				String Tno = (String)request.getParameter("Tno");
				
				ScoreDAO classScoreDAO = new ScoreDAO();
				ArrayList classScoreList = classScoreDAO.queryClassScoreList(Tno);
				
				request.setAttribute("classScoreList",classScoreList);
				RequestDispatcher rd = request.getRequestDispatcher("/teacher/classScoreQuery.jsp");
				rd.forward(request, response);
			
	}
	
	public void doSortBySno(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {				
				String Tno = (String)request.getParameter("Tno");
				
				ScoreDAO sortScoreDAO = new ScoreDAO();
				ArrayList sortScoreList = sortScoreDAO.querySortBySnoScoreList(Tno);
				
				request.setAttribute("classScoreList",sortScoreList);
				RequestDispatcher rd = request.getRequestDispatcher("/teacher/classScoreQuery.jsp");
				rd.forward(request,response);
			
	}
	
	public void doSortByGrade(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {				
				String Tno = (String)request.getParameter("Tno");
				
				ScoreDAO sortScoreDAO = new ScoreDAO();
				ArrayList sortScoreList = sortScoreDAO.querySortScoreList(Tno);
				
				request.setAttribute("classScoreList",sortScoreList);
				RequestDispatcher rd = request.getRequestDispatcher("/teacher/classScoreQuery.jsp");
				rd.forward(request,response);
			
	}
	
	public void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
				String Sno = request.getParameter("Sno");
				String Cno = request.getParameter("Cno");
				
				ScoreDAO deleteDAO = new ScoreDAO();
		    	boolean t =  deleteDAO.deleteByIdAndCno(Sno,Cno);
		    	if(t==true){
		    		RequestDispatcher rd = request.getRequestDispatcher("/teacher/classScoreQuery.jsp");
					rd.forward(request, response);
		    			
		    	}else{
					System.out.println("删除失败！");
		    	}	    	
	}
	
	public void doDeleteAll(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
				String ID[]=request.getParameterValues("ids");
				String Cno = request.getParameter("Cno");
				
				if(ID==null){
					System.out.println("未选中删除记录");
					response.sendRedirect("teacher/classScoreQuery.jsp");
				}else{
					ScoreDAO deleteAllDAO = new ScoreDAO();
					boolean t =false;
					for(int i=0;i<ID.length;i++){
					      String id = ID[i];

					      t = deleteAllDAO.deleteByIdAndCno(id,Cno); 
					}
					if(t==true){
						
						RequestDispatcher rd = request.getRequestDispatcher("teacher/classScoreQuery.jsp");
						rd.forward(request, response);
						
					}
				}
				
			}
	
	public void doUpdateQuery(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {	
		String path = request.getContextPath();
				String Tno = (String)request.getParameter("Tno");
				ScoreDAO classScoreDAO = new ScoreDAO();
				ArrayList classScoreList = classScoreDAO.queryClassScoreList(Tno);
				
				HttpSession session = request.getSession();
				session.setAttribute("classScoreList",classScoreList);
				RequestDispatcher rd = request.getRequestDispatcher("/teacher/updateClassScore.jsp?Tno="+Tno+"");
				rd.forward(request, response);
			
	}
	
	public void doUpdateScore(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
				String ID[]=request.getParameterValues("ids");
				String Cno[] = request.getParameterValues("Cno");
				String grade[] =request.getParameterValues("grade");
				String Tno = request.getParameter("Tno");
				
				if(ID==null){
					System.out.println("未选中修改记录");
					RequestDispatcher back = request.getRequestDispatcher("/teacher/updateClassScore.jsp?Tno="+Tno+"");
					back.forward(request, response);
					
				}else{
					ScoreDAO updateDAO = new ScoreDAO();
					Score updateScore = new Score();
					boolean t =false;
					for(int i=0;i<ID.length;i++){
						
						updateScore.setSno(ID[i]);
						updateScore.setCno(Cno[i]);
						updateScore.setGrade(grade[i]);

					    t = updateDAO.updateByIdAndCno(updateScore); 
					}
					if(t==true){
						
						RequestDispatcher rd = request.getRequestDispatcher("/teacher/classScoreQuery.jsp?Tno="+Tno+"");
						rd.forward(request, response);
						
					}
				}
				
			}
	
	public void doInsertQuery(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {				
				String Cno = (String)request.getParameter("Cno");
				String Tno = (String)request.getParameter("Tno");
				
				ScoreDAO insertDAO = new ScoreDAO();
				ArrayList classNumberList = insertDAO.queryClassNumberList(Cno);
				
				request.setAttribute("classNumberList",classNumberList);
				RequestDispatcher rd = request.getRequestDispatcher("teacher/insertClassScore.jsp?Cno="+Cno+"&Tno="+Tno+"");
				rd.forward(request, response);
			
	}
	
	public void doInsertScore(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
				String Tno = (String)request.getParameter("Tno");
				String ID[]=request.getParameterValues("Sno");
				String Cno = request.getParameter("Cno");
				String grade[] =request.getParameterValues("grade");
				int count = 0;
				
				if(grade==null || grade[0]==""){
					System.out.println("未给予成绩");
					request.setAttribute("mes","未给予成绩");
					RequestDispatcher rd = request.getRequestDispatcher("teacher/insertClassScore.jsp?Cno="+Cno+"");
					rd.forward(request, response);
				}else{
					ScoreDAO insertScoreDAO = new ScoreDAO();
					Score insertScore = new Score();
					boolean t =false;
					boolean mark = false;
					
					for(int i=0;i<ID.length;i++){
						
						insertScore.setSno(ID[i]);
						insertScore.setCno(Cno);
						insertScore.setGrade(grade[i]);
						mark = insertScoreDAO.isRepeat(insertScore);
						if(mark==false){
							t = insertScoreDAO.insertByIdAndCno(insertScore); 
						}else{
							count++;
							System.out.println("该条数据已存在！该条数据录入失败！");
						}					    
					}
					if(t==true){
						RequestDispatcher rd = request.getRequestDispatcher("teacher/classScoreQuery.jsp?Tno="+Tno+"&count="+count+"");
						rd.forward(request, response);
						
					}else{
						RequestDispatcher rd = request.getRequestDispatcher("/teacher/insertClassScore.jsp?Cno="+Cno+"");
						rd.forward(request, response);
					}
				}
				
			}
	
	public void doIntoTemp(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
				String Tno = (String)request.getParameter("Tno");
				String ID[]=request.getParameterValues("Sno");
				String Cno = request.getParameter("Cno");
				String grade[] =request.getParameterValues("grade");
				
				ArrayList allClassScoreList = new ArrayList();
				
				if(grade==null){
					System.out.println("未给予成绩");
					RequestDispatcher rd = request.getRequestDispatcher("/teacher/insertClassScore.jsp?Cno="+Cno+"");
					rd.forward(request, response);
				}else{					
					for(int i=0;i<ID.length;i++){
						Score tempScore = new Score();
						tempScore.setSno(ID[i]);
						tempScore.setGrade(grade[i]);
						allClassScoreList.add(tempScore);
					}	
					HttpSession session = request.getSession();
					session.setAttribute("allClassScoreList",allClassScoreList);
					RequestDispatcher rd = request.getRequestDispatcher("/teacher/temp.jsp?Tno="+Tno+"&Cno="+Cno+"");
					rd.forward(request, response);	
					
				}
				
			}
	
	public void doScoreExport(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
				
				String Sno = request.getParameter("Sno");				
				
				ScoreDAO exportDAO = new ScoreDAO();
				ArrayList exportList = exportDAO.queryExportScoreList(Sno);
				
				request.setAttribute("exportList",exportList);
				RequestDispatcher rd = request.getRequestDispatcher("student/export.jsp?Sno="+Sno+"");
				rd.forward(request, response);
			
	}
	
	public void doClassScoreExport(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
				
				String Tno = request.getParameter("Tno");				
				
				ScoreDAO classExportDAO = new ScoreDAO();
				ArrayList classExportList = classExportDAO.queryClassExportScoreList(Tno);
				
				request.setAttribute("classExportList",classExportList);
				RequestDispatcher rd = request.getRequestDispatcher("teacher/classScoreExport.jsp");
				rd.forward(request, response);
			
	}
	
	public void doSingleUpdate(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
				String Sno=request.getParameter("Sno");
				String Cno = request.getParameter("Cno");
				String grade =request.getParameter("grade");
			
				ScoreDAO updateDAO = new ScoreDAO();
				Score updateScore = new Score();
				boolean t =false;
										
				updateScore.setSno(Sno);
				updateScore.setCno(Cno);
				updateScore.setGrade(grade);

				t = updateDAO.updateByIdAndCno(updateScore); 
				if(t==true){
						
						RequestDispatcher rd = request.getRequestDispatcher("/teacher/classScoreQuery.jsp");
						rd.forward(request, response);	
				}
			
		}
	
	public void doBracket(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
				
				String Cno = request.getParameter("Cno");
				
				ScoreDAO bracketDAO = new ScoreDAO();
				ArrayList bracketList = bracketDAO.queryBracketList(Cno);
				
				HttpSession session = request.getSession();
				session.setAttribute("bracketList",bracketList);
				response.sendRedirect("teacher/teacher_index.jsp");
			}

}
