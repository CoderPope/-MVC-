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

    <title>学生登录界面</title>

    <!-- Bootstrap core CSS -->
    <link href="<%=path %>/assets/css/bootstrap.css" rel="stylesheet">
    <!--external css-->
    <link href="<%=path %>/assets/font-awesome/css/font-awesome.css" rel="stylesheet" />
    <link rel="stylesheet" type="text/css" href="<%=path %>/assets/css/zabuto_calendar.css">
    <link rel="stylesheet" type="text/css" href="<%=path %>/assets/js/gritter/css/jquery.gritter.css" />
    <link rel="stylesheet" type="text/css" href="<%=path %>/assets/lineicons/style.css">    
    
    <!-- Custom styles for this template -->
    <link href="<%=path %>/assets/css/style.css" rel="stylesheet">
    <link href="<%=path %>/assets/css/style-responsive.css" rel="stylesheet">

    <script src="<%=path %>/assets/js/chart-master/Chart.js"></script>
    
    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
      <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
  </head>

  <body>
  
  	<%
     	Student studentInfo = (Student)session.getAttribute("studentInfo");
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
            <a href="javascript:;" class="logo"><b>学生操作界面</b></a>
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
                                <p class="green">学生信息</p>
                            </li>
                            <li>
                                <a href="javascript:;">
                                    <div class="task-info">
                                        <div class="desc">学号：</div>
                                        <div class="message"><%=studentInfo.getSno() %></div>
                                    </div>
                                </a>
                            </li>
                            <li>
                                <a href="javascript:;">
                                    <div class="task-info">
                                        <div class="desc">学生姓名：</div>
                                        <div class="message"><%=studentInfo.getSname() %></div>
                                    </div>
                                </a>
                            </li>
                            <li>
                                <a href="javascript:;">
                                    <div class="task-info">
                                        <div class="desc">性别：</div>
                                        <div class="message"><%=studentInfo.getSsex() %></div>
                                    </div>
                                </a>
                            </li>
                            <li>
                                <a href="javascript:;">
                                    <div class="task-info">
                                        <div class="desc">年龄：</div>
                                        <div class="message"><%=studentInfo.getSage() %></div>
                                    </div>
                                </a>
                            </li>
                            <li>
                                <a href="javascript:;">
                                    <div class="task-info">
                                        <div class="desc">专业：</div>
                                        <div class="message"><%=studentInfo.getMajor() %></div>
                                    </div>
                                </a>
                            </li>
                            <li>
                                <a href="javascript:;">
                                    <div class="task-info">
                                        <div class="desc">用户权限：</div>
                                        <div class="message"><%=studentInfo.getPurview() %></div>
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
                                    <span class="photo"><img alt="avatar" src="<%=path %>/assets/img/ui-zac.jpg"></span>
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
                                    <span class="photo"><img alt="avatar" src="<%=path %>/assets/img/ui-divya.jpg"></span>
                                    <span class="subject">
                                    <span class="from">软件学院</span>
                                    <span class="time">5 分钟前.</span>
                                    </span>
                                    <span class="message">
                                     	三班同学下午到机房完成课程设计.
                                    </span>
                                </a>
                            </li>
                            <li>
                                <a href="javascript:;">
                                    <span class="photo"><img alt="avatar" src="<%=path %>/assets/img/ui-danro.jpg"></span>
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
                                    <span class="photo"><img alt="avatar" src="<%=path %>/assets/img/ui-sherman.jpg"></span>
                                    <span class="subject">
                                    <span class="from">系主任</span>
                                    <span class="time">4 小时前.</span>
                                    </span>
                                    <span class="message">
                                                                         经讨论决定，不追究你考试作弊<br>的严重违纪行为.但将会把你的学籍<br>信息从数据库中删除。
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
                    <li><a class="logout" href="login.jsp">Logout</a></li>
            	</ul>
            </div>
        </header>
      <!--header end-->
      
      <!--sidebar start-->
      <aside>
          <div id="sidebar"  class="nav-collapse ">
              <!-- sidebar menu start-->
              <ul class="sidebar-menu" id="nav-accordion">
              
              	  <p class="centered"><a href="javascript:;"><img src="<%=path %>/assets/img/ui-sam.jpg" class="img-circle" width="60"></a></p>
              	  <h5 class="centered"><%=studentInfo.getSname() %>,欢迎回来</h5>
              	  	
                  <li class="mt">
                      <a  href="<%=path %>/student/student_index.jsp">
                          <i class="fa fa-dashboard"></i>
                          <span>主界面</span>
                      </a>
                  </li>

				  <li class="sub-menu">
                      <a href="<%=path %>/CourseServlet?operate=course_query&major=<%=studentInfo.getMajor()%>">
                          <i class="fa fa-th"></i>
                          <span>课程查询</span>
                      </a>
                  </li>
                  
                  <li class="sub-menu">
                      <a class="active" href="javascript:;" >
                          <i class=" fa fa-bar-chart-o"></i>
                          <span>成绩查询</span>
                      </a>
                      <ul class="sub">
                          <li class="active"><a  href="<%=path %>/ScoreServlet?operate=student_query&Sno=<%=studentInfo.getSno()%>">全部查询</a></li>
                          <li><a  href="<%=path %>/ScoreServlet?operate=course_query&Sno=<%=studentInfo.getSno()%>">条件查询</a></li>
                      </ul>
                  </li>

              </ul>
              <!-- sidebar menu end-->
          </div>
      </aside>
      <!--sidebar end-->
      
      <!-- **********************************************************************************************************************************************************
      MAIN CONTENT
      *********************************************************************************************************************************************************** -->
      <!--main content start-->
      <%
       int pageSize = 6;
       int recordsNum = Integer.parseInt(request.getParameter("recordsNum"));
       int endNum =  Integer.parseInt(request.getParameter("endNum"));
       int nowPage = Integer.parseInt(request.getParameter("nowPage"));
       int pageNum = (recordsNum-1+pageSize)/ pageSize;
       ArrayList scoreList = (ArrayList)request.getAttribute("scoreList");
       String creditSum = (String)request.getParameter("creditSum");
       if(scoreList==null){
       		request.setAttribute("Sno",studentInfo.getSno());
			RequestDispatcher rd = request.getRequestDispatcher(""+path+"/ScoreServlet?operate=student_query");
			rd.forward(request, response);
       }
       else{
      %>    
      <section id="main-content">
          <section class="wrapper">
          	<h3><i class="fa fa-angle-right"></i> 全部成绩：</h3>
		  		<div class="row mt">
			  		<div class="col-lg-12">
                      <div class="content-panel">
                      <h4><i class="fa fa-angle-right"></i>已修学分：<%=creditSum%></h4>
                          <section id="unseen">
                            <table class="table table-bordered table-striped table-condensed">
                              <thead>
                              
                              <tr>
                                  <th class="numeric">课程名</th>
                                  <th class="numeric">类型</th>
                                  <th class="numeric">成绩</th>
                              </tr>
                              </thead>
                              <tbody>
                              <%
         						for(int i=0;i<scoreList.size();i++){
         						Score score = (Score)scoreList.get(i); 
         					  %>
                              <tr>
                                  <td class="numeric"><%=score.getCname() %></td>
                                  <td class="numeric"><%=score.getType() %></td>
                                  <td class="numeric"><%=score.getGrade() %></td>
                              </tr>
                              <%} %>
                                                         
                              </tbody>
                          </table>

						<a class="btn btn-success" href="<%=path %>/ScoreServlet?operate=export&Sno=<%=studentInfo.getSno()%>">导出Excel</a>

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
        					<td ><a href="<%=path %>/ScoreServlet?operate=student_query&Sno=<%=studentInfo.getSno()%>&pageN=<%=nowPage-1 %>">上一页</a></td>       			
        			<%}%>     			
        			
        			<%
        				for(int i =1;i<=pageNum;i++){       					
        			%>                                                       
                    <td ><a href="<%=path %>/ScoreServlet?operate=student_query&Sno=<%=studentInfo.getSno()%>&pageN=<%=i %>"><%=i %></a></td>
                    <%} %>
                    
                    <% 
        				if(nowPage==pageNum){
        			%>
        					<td ><a href="javascript:;">下一页</a></td>
        			<% 
        				}else{
        			%>
        					<td ><a href="<%=path %>/ScoreServlet?operate=student_query&Sno=<%=studentInfo.getSno()%>&pageN=<%=nowPage+1 %>">下一页</a></td>       			
        			<%}%>
                    
            	</tr>
            </table>

		</section><! --/wrapper -->		  			  	                         
      <%} %>

      <!--main con     <!--footer start-->
      <footer class="site-footer">
          <div class="text-center">
              2018 - 韦宇林 - 暑期课程设计  - Design 
              <a href=""+path+"/student/score_student_query.jsp#" class="go-top">
                  <i class="fa fa-angle-up"></i>
              </a>
          </div>
      </footer>
      <!--footer end-->
  </section>

    <!-- js placed at the end of the document so the pages load faster -->
    <script src="<%=path %>/assets/js/jquery.js"></script>
    <script src="<%=path %>/assets/js/bootstrap.min.js"></script>
    <script class="include" type="text/javascript" src="<%=path %>/assets/js/jquery.dcjqaccordion.2.7.js"></script>
    <script src="<%=path %>/assets/js/jquery.scrollTo.min.js"></script>
    <script src="<%=path %>/assets/js/jquery.nicescroll.js" type="text/javascript"></script>


    <!--common script for all pages-->
    <script src="<%=path %>/assets/js/common-scripts.js"></script>

    <!--script for this page-->
    

  </body>
</html>