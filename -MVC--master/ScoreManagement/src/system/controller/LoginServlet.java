package system.controller;

import java.io.IOException;



import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import system.model.*;
import system.DAO.LoginDAO;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/LoginServlet" })
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#getServletConfig()
	 */
	public ServletConfig getServletConfig() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("进入LoginServlet");
		String operate = request.getParameter("operate");
		String certCode=request.getParameter("certCode");
		HttpSession session = request.getSession();
			if(certCode.equals((String)session.getAttribute("certCode"))){
				System.out.println("验证码输入正确");
				if(operate.equals("login")){
					this.doLogin(request,response);
				}
			}else{
				System.out.println("验证码输入错误");
				request.setAttribute("mes","登录失败！验证码输入错误。");
				RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
				rd.forward(request,response);
			}

		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	public void doLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String purview = request.getParameter("purview");
		String user_id = request.getParameter("user_id");
		String password = request.getParameter("password");
		
		User user = new User();
		user.setId(user_id);
		user.setPassword(password);
		user.setPurview(purview);
		
		Table table = new Table();
		LoginDAO loginDAO = new LoginDAO();
		boolean mark = false; 
		
		if(purview.equals("student")){
			table.setTableName("StudentInfo");
			table.setId("Sno");
			table.setPassword("Spassword");
			
			mark = loginDAO.isExist(table,user);
			if(mark){
				System.out.println("学生登陆成功");

				Student studentInfo = loginDAO.queryStudentInfo(user);
				//用session传id
				HttpSession session = request.getSession();
				session.setAttribute("studentInfo",studentInfo);
				response.sendRedirect("student/student_index.jsp");
			}
			
		}else if(purview.equals("teacher")){
			table.setTableName("TeacherInfo");
			table.setId("Tno");
			table.setPassword("Tpassword");
			
			mark = loginDAO.isExist(table,user);
			if(mark){
				System.out.println("教师登陆成功");
				
				Teacher teacherInfo = loginDAO.queryTeacherInfo(user);
				
				//用session传id
				HttpSession session = request.getSession();
				session.setAttribute("teacherInfo",teacherInfo);
				response.sendRedirect("teacher/teacher_index.jsp");
			}
			
		}else if(purview.equals("manager")){
			table.setTableName("ManagerInfo");
			table.setId("Mno");
			table.setPassword("Mpassword");
			
			mark = loginDAO.isExist(table,user);
			if(mark){
				System.out.println("管理员登录成功");
				//用session传id
				Manager managerInfo = loginDAO.queryManagerInfo(user);
				
				//用session传id
				HttpSession session = request.getSession();
				session.setAttribute("managerInfo",managerInfo);
				response.sendRedirect("manager/manager_index.jsp");
			}
		}
		
		if(mark==false){
			request.setAttribute("mes","登录失败！请检查用户名与密码是否正确。");
			RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
			rd.forward(request,response);
		}
		
		
	}	
	
}
