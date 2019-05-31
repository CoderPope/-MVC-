<%@ page language="java" import="java.util.*,system.model.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<html lang="en">
  <head>   
    <title>增添教师信息界面</title>

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
       		ArrayList CnoList = (ArrayList)request.getAttribute("CnoList");
       		if(CnoList==null){
       			response.sendRedirect(""+path+"/CourseServlet?operate=queryCno&href=teacher");
			
       		}
       		else{
      	%> 
      
          	<h3><i class="fa fa-angle-right"></i> 添加教师：</h3>
		  		<div class="row mt">
			  		<div class="col-lg-12">
                      <div class="content-panel">
                      <h4><i class="fa fa-angle-right"></i>输入信息：</h4>
                          <section id="unseen">
                          <form action="<%=path%>/TeacherServlet">
                            <table class="table table-bordered table-striped table-condensed">
                              <thead>                             
                              <tr>
                              	  <th class="numeric">职工号</th>
                              	  <th class="numeric">教师姓名</th>
                              	  <th class="numeric">性别</th>
                              	  <th class="numeric">密码</th>
                              	  <th class="numeric">教授课程</th>
                              	  
                              </tr>
                              </thead>
                              <tbody>
                              <tr>                            	  
                              	  <td class="numeric"><input type="text" name="Tno" class="form-control" value="以45开头"/></td>
                              	  <td class="numeric"><input type="text" name="Tname" class="form-control" value=""/></td>
                              	  <td class="numeric"><input type="text" name="Tsex" class="form-control" value=""/></td>
                              	  <td class="numeric"><input type="text" name="Tpassword" class="form-control" value=""/></td>
                              	  <td class="numeric">
                              	  
                              	  	<div class="btn-group">
						  				<span class="btn btn-theme03">选择教授课程</span>
						  				<button type="button" class="btn btn-theme03 dropdown-toggle" data-toggle="dropdown">
											<span class="caret"></span>
						    				<span class="sr-only">Toggle Dropdown</span>
						  				</button>
						 				<ul class="dropdown-menu" role="menu">
						 				<%
         									for(int i=0;i<CnoList.size();i++){
         									Course course = (Course)CnoList.get(i); 
         					    		%>
						    				<li><input type="radio" name="CnoCname" value="<%=course.getCno()%>,<%=course.getCname()%>"/><%=course.getCname()%></li>
										<%} %>
						  				</ul>
									</div>
								  </td>                                                                   
                              </tr>                                                         
                              </tbody>
                          </table>
                          <span class="btn btn-warning btn-lg"><a href="<%=path%>/manager/manager_index.jsp">取消</a></span>
                                                   
                          <input type="hidden" name="purview" value="teacher"/>
                          <input type="hidden" name="operate" value="teacherInfoInsert"/>
                          <input type="submit" class="btn btn-danger btn-lg" value="添加"/>
						</form>
                  </div><!-- /content-panel -->
               </div><!-- /col-lg-4 -->			
		  	</div><!-- /row -->		  			  	                         
			<%} %>
 
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