<%@ page language="java" import="java.util.*,system.model.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="Dashboard">
    <meta name="keyword" content="Dashboard, Bootstrap, Admin, Template, Theme, Responsive, Fluid, Retina">

    <title>教师信息管理界面</title>

    <!-- Bootstrap core CSS -->
    <link href="<%=path%>/assets/css/bootstrap.css" rel="stylesheet">
    <!--external css-->
    <link href="<%=path%>/assets/font-awesome/css/font-awesome.css" rel="stylesheet" />
    <link rel="stylesheet" type="text/css" href="<%=path%>/assets/css/zabuto_calendar.css">
    <link rel="stylesheet" type="text/css" href="<%=path%>/assets/js/gritter/css/jquery.gritter.css" />
    <link rel="stylesheet" type="text/css" href="<%=path%>/assets/lineicons/style.css">    
    
    <!-- Custom styles for this template -->
    <link href="<%=path%>/assets/css/style.css" rel="stylesheet">
    <link href="<%=path%>/assets/css/style-responsive.css" rel="stylesheet">

    <script src="<%=path%>/assets/js/chart-master/Chart.js"></script>
    
    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
      <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
  </head>

  <body>

  <%
     	Manager managerInfo = (Manager)session.getAttribute("managerInfo");
    %>

  <section id="container" >
      <!-- **********************************************************************************************************************************************************
      TOP BAR CONTENT & NOTIFICATIONS
      *********************************************************************************************************************************************************** -->
      <!--header start-->
      <header class="header black-bg">
              <div class="sidebar-toggle-box">
                  <div class="fa fa-bars tooltips" data-placement="right" data-original-title="Toggle Navigation"></div>
              </div>
            <!--logo start-->
            <a href="javascript:;" class="logo"><b>管理员操作界面</b></a>
            <!--logo end-->
            <div class="nav notify-row" id="top_menu">
                <!--  notification start -->
                <ul class="nav top-menu">
                    <!-- settings start -->
                    <li class="dropdown">
                        <a data-toggle="dropdown" class="dropdown-toggle" href="javascript:;">
                            <i class="fa fa-user"></i>
                        </a>
                        <ul class="dropdown-menu extended tasks-bar">
                            <div class="notify-arrow notify-arrow-green"></div>
                            <li>
                                <p class="green">管理员信息</p>
                            </li>
                            <li>
                                <a href="javascript:;">
                                    <div class="task-info">
                                        <div class="desc">职工号：</div>
                                        <div class="message"><%=managerInfo.getMno() %></div>
                                    </div>
                                </a>
                            </li>
                            <li>
                                <a href="javascript:;">
                                    <div class="task-info">
                                        <div class="desc">管理员姓名：</div>
                                        <div class="message"><%=managerInfo.getMname() %></div>
                                    </div>
                                </a>
                            </li>                            
                            <li>
                                <a href="javascript:;">
                                    <div class="task-info">
                                        <div class="desc">用户权限：</div>
                                        <div class="message"><%=managerInfo.getPurview() %></div>
                                    </div>
                                </a>
                            </li>  
                        </ul>
                    </li>
                    <!-- settings end -->
                    <!-- inbox dropdown start-->
                    <li id="header_inbox_bar" class="dropdown">
                        <a data-toggle="dropdown" class="dropdown-toggle" href="javascript:;">
                            <i class="fa fa-envelope-o"></i>
                            <span class="badge bg-theme">5</span>
                        </a>
                        <ul class="dropdown-menu extended inbox">
                            <div class="notify-arrow notify-arrow-green"></div>
                            <li>
                                <p class="green">你有5条新通知</p>
                            </li>
                            <li>
                                <a href="javascript:;">
                                    <span class="photo"><img alt="avatar" src="<%=path%>/assets/img/ui-zac.jpg"></span>
                                    <span class="subject">
                                    <span class="from">教务处</span>
                                    <span class="time">刚刚</span>
                                    </span>
                                    <span class="message">
                                        20周周四放假！
                                    </span>
                                </a>
                            </li>
                            <li>
                                <a href="javascript:;">
                                    <span class="photo"><img alt="avatar" src="<%=path%>/assets/img/ui-divya.jpg"></span>
                                    <span class="subject">
                                    <span class="from">软件学院</span>
                                    <span class="time">5 分钟前.</span>
                                    </span>
                                    <span class="message">
                                     	老师们记得点名，不要拿错点名册。
                                    </span>
                                </a>
                            </li>
                            <li>
                                <a href="javascript:;">
                                    <span class="photo"><img alt="avatar" src="<%=path%>/assets/img/ui-danro.jpg"></span>
                                    <span class="subject">
                                    <span class="from">计算机中心</span>
                                    <span class="time">2 小时前.</span>
                                    </span>
                                    <span class="message">
                                                                         发来了一串神秘连接：Hello，there.
                                    </span>
                                </a>
                            </li>
                            <li>
                                <a href="javascript:;">
                                    <span class="photo"><img alt="avatar" src="<%=path%>/assets/img/ui-sherman.jpg"></span>
                                    <span class="subject">
                                    <span class="from">系主任</span>
                                    <span class="time">4 小时前.</span>
                                    </span>
                                    <span class="message">
                                                                         今年的指标是至少要抓三个学生！
                                    </span>
                                </a>
                            </li>
                        </ul>
                    </li>
                    <!-- inbox dropdown end -->
                </ul>
                <!--  notification end -->
            </div>
            <div class="top-menu">
            	<ul class="nav pull-right top-menu">
                    <li><a class="logout" href="<%=path%>/login.jsp">Logout</a></li>
            	</ul>
            </div>
        </header>
      <!--header end-->
      
      <!-- **********************************************************************************************************************************************************
      MAIN SIDEBAR MENU
      *********************************************************************************************************************************************************** -->
      <!--sidebar start-->
      <aside>
          <div id="sidebar"  class="nav-collapse ">
              <!-- sidebar menu start-->
              <ul class="sidebar-menu" id="nav-accordion">
              
              	  <p class="centered"><a href="javascript:;"><img src="<%=path%>/assets/img/ui-sam.jpg" class="img-circle" width="60"></a></p>
              	  <h5 class="centered"><%=managerInfo.getMname() %>,欢迎回来</h5>
              	  	
                  <li class="mt">
                      <a href="<%=path%>/manager/manager_index.jsp">
                          <i class="fa fa-dashboard"></i>
                          <span>主界面</span>
                      </a>
                  </li>

                  <li class="sub-menu">
                      <a href="javascript:;">
                          <i class="fa fa-desktop"></i>
                          <span>学生管理模块</span>
                      </a> 
                      <ul class="sub">
                          <li class="active"><a  href="<%=path%>/StudentServlet?operate=studentInfo">学生信息管理</a></li>
                          <li><a  href="<%=path%>/manager/studentAdd.jsp">添加学生</a></li>
                      </ul>           
                  </li>
                  

                  <li class="sub-menu">
                      <a href="javascript:;" >
                          <i class="fa fa-cogs"></i>
                          <span>教师管理模块</span>
                      </a>
                      <ul class="sub">
                          <li class="active"><a  href="<%=path%>/TeacherServlet?operate=teacherInfo">教师信息管理</a></li>
                          <li><a  href="<%=path%>/manager/teacherAdd.jsp">添加教师</a></li>
                      </ul>
                  </li>
                  <li class="sub-menu">
                      <a href="javascript:;">
                          <i class="fa fa-th"></i>
                          <span>课程管理模块</span>
                      </a>
                      <ul class="sub">
                          <li><a  href="<%=path%>/CourseServlet?operate=allCourseQuery&href=management">课程信息管理</a></li>
                          <li><a  href="<%=path%>/manager/courseAdd.jsp">添加课程</a></li>
                      </ul>
                  </li>
                  <li class="sub-menu">
                      <a href="javascript:;">
                          <i class="fa fa-book"></i>
                          <span>专业管理模块</span>
                      </a>
                      <ul class="sub">
                          <li><a  href="<%=path%>/MajorServlet?operate=majorInfo">专业信息管理</a></li>
                          <li><a  href="<%=path%>/manager/majorAdd.jsp">添加专业</a></li>
                      </ul>
                  </li>                 

              <!-- sidebar menu end-->
          </div>
      </aside>
      <!--sidebar end-->
      <div class="copyrights">2018 - 韦宇林 - 暑期课程设计  - Design</div>
      
      <!-- **********************************************************************************************************************************************************
      MAIN CONTENT
      *********************************************************************************************************************************************************** -->
      <!--main content start-->
      <%
       
       ArrayList allTeacherList = (ArrayList)request.getAttribute("allTeacherList");
       if(allTeacherList==null){
       		response.sendRedirect(""+path+"/TeacherServlet?operate=teacherInfo");
       }
       else{
       		int pageSize = 10;
       		int recordsNum = Integer.parseInt(request.getParameter("recordsNum"));
      	 	int endNum =  Integer.parseInt(request.getParameter("endNum"));
       		int nowPage = Integer.parseInt(request.getParameter("nowPage"));
       		int pageNum = (recordsNum-1+pageSize)/ pageSize;
      %>    
      <section id="main-content">
          <section class="wrapper">
          	<h3><i class="fa fa-angle-right"></i> 教师信息管理：</h3>
		  		<div class="row mt">
			  		<div class="col-lg-12">
                      <div class="content-panel">
                      <h4><i class="fa fa-angle-right"></i>查询结果：</h4>
                          <section id="unseen">
                          <form action="<%=path%>/TeacherServlet">
                            <table class="table table-bordered table-striped table-condensed">
                              <thead>
                              
                              <tr>
                              	  <th class="numeric">选择</th>
                              	  <th class="numeric">职工号</th>
                              	  <th class="numeric">教师姓名</th>
                              	  <th class="numeric">性别</th>
                              	  <th class="numeric">教授课程号</th>
                                  <th class="numeric">教授课程名</th>
                                  <th class="numeric">操作</th>
                              </tr>
                              </thead>
                              <tbody>
                              <%
         						for(int i=0;i<allTeacherList.size();i++){
         						Teacher teacherInfo = (Teacher)allTeacherList.get(i); 
         					  %>
                              <tr>
                              	  <td class="numeric"><input type="checkbox" name="ids" value="<%=teacherInfo.getTno()%>"/></td>                            	  
                              	  <td class="numeric"><%=teacherInfo.getTno() %></td>
                              	  <td class="numeric"><%=teacherInfo.getTname() %></td>
                              	  <td class="numeric"><%=teacherInfo.getTsex() %></td>
                              	  <td class="numeric"><%=teacherInfo.getCno() %></td>
                                  <td class="numeric"><%=teacherInfo.getCname() %></td>                                  
                                  <td class="numeric"><span class="badge bg-theme04"><i class="fa fa-trash-o "><a href="<%=path%>/TeacherServlet?operate=delete&Tno=<%=teacherInfo.getTno()%>">删除</a></i></span></td>
                              </tr>
                              <%} %>
                                                         
                              </tbody>
                          </table>
                          <span class="btn btn-warning btn-lg"><a href="<%=path%>/TeacherServlet?operate=updateQuery">批量修改</a></span>
                          <input type="hidden" name="operate" value="deleteAll"/>
                          <input type="submit" class="btn btn-danger btn-lg" value="批量删除"/>
						</form>
                  </div><!-- /content-panel -->
               </div><!-- /col-lg-4 -->			
		  	</div><!-- /row -->

			<div class="message">共<span class="time"><%=recordsNum%></span>条记录，当前显示第&nbsp;<span class="time"><%=nowPage %>&nbsp;</span>页</div>
        	<table  align="right" width="300" > 
        		<tr >
        			<% 
        				if(nowPage==1){
        			%>
        					<td ><a href="javascript:;">上一页</a></td>
        			<% 
        				}else{
        			%>
        					<td ><a href="<%=path%>/TeacherServlet?operate=teacherInfo&pageN=<%=nowPage-1 %>">上一页</a></td>       			
        			<%}%>     			
        			
        			<%
        				for(int i =1;i<=pageNum;i++){       					
        			%>                                                       
                    <td ><a href="<%=path%>/TeacherServlet?operate=teacherInfo&pageN=<%=i %>"><%=i %></a></td>
                    <%} %>
                    
                    <% 
        				if(nowPage==pageNum){
        			%>
        					<td ><a href="javascript:;">下一页</a></td>
        			<% 
        				}else{
        			%>
        					<td ><a href="<%=path%>/TeacherServlet?operate=teacherInfo&pageN=<%=nowPage+1 %>">下一页</a></td>       			
        			<%}%>
                    
            	</tr>
            </table>

		</section><! --/wrapper -->		  			  	                         
      <%} %>

      <!--main con     <!--footer start-->
      <footer class="site-footer">
          <div class="text-center">
              2018 - 韦宇林 - 暑期课程设计  - Design 
              <a href=""+path+"/manager/teacherInfoManagement.jsp#" class="go-top">
                  <i class="fa fa-angle-up"></i>
              </a>
          </div>
      </footer>
      <!--footer end-->
  </section>

    <!-- js placed at the end of the document so the pages load faster -->
    <script src="<%=path%>/assets/js/jquery.js"></script>
    <script src="<%=path%>/assets/js/bootstrap.min.js"></script>
    <script class="include" type="text/javascript" src="<%=path%>/assets/js/jquery.dcjqaccordion.2.7.js"></script>
    <script src="<%=path%>/assets/js/jquery.scrollTo.min.js"></script>
    <script src="<%=path%>/assets/js/jquery.nicescroll.js" type="text/javascript"></script>


    <!--common script for all pages-->
    <script src="<%=path%>/assets/js/common-scripts.js"></script>

    <!--script for this page-->
    

  </body>
</html>