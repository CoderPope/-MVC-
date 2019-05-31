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

    <title>管理员登录界面</title>

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
    <script type="text/javascript">
    	function startTime(){
    		var today = new Date();
    		var d = today.getDay();
    		var h = today.getHours();
    		var m = today.getMinutes();
    		var s = today.getSeconds();
    		m=checkTime(m);
    		s=checkTime(s);
    		document.getElementById("time").innerHTML="星期"+d+"/"+h+":"+m+":"+s;
    		t=setTimeout(function(){startTime(),500});
    	}
    	function checkTime(i){
    		if(i<10){
    			i="0"+i;
    		}
    		return i;
    	}
    </script>
    
    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
      <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
  </head>

  <body onload="startTime()">

  <%
     	Manager managerInfo = (Manager)session.getAttribute("managerInfo");
     	
     	if(application.getAttribute("Mcount")==null){ 
			application.setAttribute("Mcount", new Integer(0)); 
		} 
		Integer Mcount = (Integer)application.getAttribute("Mcount"); 
		//使用application对象读取count参数的值，再在原值基础上累加1 
		application.setAttribute("Mcount",new Integer(Mcount.intValue()+1));
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
                            <li>
                                <a href="javascript:;">
                                    <span class="message">
                                                                         欢迎您访问，本页面已经被访问过 <font color="#ff0000"><%=application.getAttribute("Mcount") %></font>次
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
                      <a class="active" href="<%=path%>/manager/manager_index.jsp">
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
                          <li><a  href="<%=path%>/StudentServlet?operate=studentInfo">学生信息管理</a></li>
                          <li><a  href="<%=path%>/manager/studentAdd.jsp">添加学生</a></li>
                      </ul>           
                  </li>
                  

                  <li class="sub-menu">
                      <a href="javascript:;" >
                          <i class="fa fa-cogs"></i>
                          <span>教师管理模块</span>
                      </a>
                      <ul class="sub">
                          <li><a  href="<%=path%>/TeacherServlet?operate=teacherInfo">教师信息管理</a></li>
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
      <section id="main-content">
          <section class="wrapper">
    
      <!-- **********************************************************************************************************************************************************
      RIGHT SIDEBAR CONTENT
      *********************************************************************************************************************************************************** -->                  
                                

                        <!-- WEATHER-3 PANEL -->
						<div class="col-lg-4 col-md-4 col-sm-4 mb">
							<div class="weather-3 pn centered">
								<i class="fa fa-sun-o"></i>
								<h1>30o C</h1>
								<div class="info">
									<div class="row">
											<h3 id="time" class="centered">time</h3>
										<div class="col-sm-6 col-xs-6 pull-left">
											<p class="goleft"><i class="fa fa-tint"></i> 13%</p>
										</div>
										<div class="col-sm-6 col-xs-6 pull-right">
											<p class="goright"><i class="fa fa-flag"></i> 45 MPH</p>
										</div>
									</div>
								</div>
							</div>
						</div><! --/col-md-4 -->
                        <!-- CALENDAR-->
                        <div id="calendar" class="mb">
                            <div class="panel green-panel no-margin">
                                <div class="panel-body">
                                    <div id="date-popover" class="popover top" style="cursor: pointer; disadding: block; margin-left: 33%; margin-top: -50px; width: 175px;">
                                        <div class="arrow"></div>
                                        <h3 class="popover-title" style="disadding: none;"></h3>
                                        <div id="date-popover-content" class="popover-content"></div>
                                    </div>
                                    <div id="my-calendar"></div>
                                </div>
                            </div>
                        </div><!-- / calendar -->
                      
                  </div><!-- /col-lg-3 -->
              </div><! --/row -->
          </section>
      </section>

      <!--main content end-->
      <!--footer start-->
      <footer class="site-footer">
          <div class="text-center">
              2018 - 韦宇林 - 暑期课程设计  - Design
              <a href="<%=path%>/manager/manager_index.jsp" class="go-top">
                  <i class="fa fa-angle-up"></i>
              </a>
          </div>
      </footer>
      <!--footer end-->
  </section>

    <!-- js placed at the end of the document so the pages load faster -->
    <script src="<%=path%>/assets/js/jquery.js"></script>
    <script src="<%=path%>/assets/js/jquery-1.8.3.min.js"></script>
    <script src="<%=path%>/assets/js/bootstrap.min.js"></script>
    <script class="include" type="text/javascript" src="<%=path%>/assets/js/jquery.dcjqaccordion.2.7.js"></script>
    <script src="<%=path%>/assets/js/jquery.scrollTo.min.js"></script>
    <script src="<%=path%>/assets/js/jquery.nicescroll.js" type="text/javascript"></script>
    <script src="<%=path%>/assets/js/jquery.sparkline.js"></script>


    <!--common script for all pages-->
    <script src="<%=path%>/assets/js/common-scripts.js"></script>
    
    <script type="text/javascript" src="<%=path%>/assets/js/gritter/js/jquery.gritter.js"></script>
    <script type="text/javascript" src="<%=path%>/assets/js/gritter-conf.js"></script>

    <!--script for this page-->
    <script src="<%=path%>/assets/js/sparkline-chart.js"></script>    
	<script src="<%=path%>/assets/js/zabuto_calendar.js"></script>	
	
	<script type="text/javascript">
        $(document).ready(function () {
        var unique_id = $.gritter.add({
            // (string | mandatory) the heading of the notification
            title: '欢迎来到学生成绩管理系统!',
            // (string | mandatory) the text inside the notification
            text: '您现在进入的是管理员登录界面，该界面包含学生管理模块、教师管理模块和课程管理模块.',
            // (string | optional) the image to display on the left
            image: '<%=path%>/assets/img/ui-sam.jpg',
            // (bool | optional) if you want it to fade out on its own or just sit there
            sticky: true,
            // (int | optional) the time you want it to be alive for before fading out
            time: '',
            // (string | optional) the class、name you want to apply to that specific message
            class_name: 'my-sticky-class'
        });

        return false;
        });
	</script>
	
	<script type="application/javascript">
        $(document).ready(function () {
            $("#date-popover").popover({html: true, trigger: "manual"});
            $("#date-popover").hide();
            $("#date-popover").click(function (e) {
                $(this).hide();
            });
        
            $("#my-calendar").zabuto_calendar({
                action: function () {
                    return myDateFunction(this.id, false);
                },
                action_nav: function () {
                    return myNavFunction(this.id);
                },
                ajax: {
                    url: "show_data.php?action=1",
                    modal: true
                },
                legend: [
                    {type: "text", label: "Special event", badge: "00"},
                    {type: "block", label: "Regular event", }
                ]
            });
        });
        
        
        function myNavFunction(id) {
            $("#date-popover").hide();
            var nav = $("#" + id).data("navigation");
            var to = $("#" + id).data("to");
            console.log('nav ' + nav + ' to: ' + to.month + '/' + to.year);
        }
    </script>
  

  </body>
</html>