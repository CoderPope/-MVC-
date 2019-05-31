<%@ page language="java" import="java.util.*,system.model.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<html lang="en">
  <head>   
    <title>批量修改学生成绩界面</title>

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
       ArrayList studentInfoList = (ArrayList)request.getAttribute("studentInfoList");
       if(studentInfoList==null){
			RequestDispatcher rd = request.getRequestDispatcher("/StudentServlet?operate=updateQuery");
			rd.forward(request, response);
       }
       else{
      %>             
          	<h3><i class="fa fa-angle-right"></i> 批量修改：</h3>
		  		<div class="row mt">
			  		<div class="col-lg-12">
                      <div class="content-panel">
                      <h4><i class="fa fa-angle-right"></i>查询结果：</h4>
                          <section id="unseen">
                          <form action="<%=path%>/StudentServlet">
                            <table class="table table-bordered table-striped table-condensed">
                              <thead>                             
                              <tr>
                              	  <th class="numeric">选择</th>
                              	  <th class="numeric">学号</th>
                              	  <th class="numeric">学生姓名</th>
                              	  <th class="numeric">性别</th>
                              	  <th class="numeric">年龄</th>
                                  <th class="numeric">所属专业</th>
                              </tr>
                              </thead>
                              <tbody>
                              <%
         						for(int i=0;i<studentInfoList.size();i++){
         						Student studentInfo = (Student)studentInfoList.get(i); 
         					  %>
                              <tr>
                              	  <td class="numeric"><input type="checkbox" name="ids" value="<%=studentInfo.getSno()%>"/></td>                            	  
                              	  <td class="numeric"><%=studentInfo.getSno() %></td>
                              	  <td class="numeric"><%=studentInfo.getSname() %></td>
                              	  <td class="numeric"><input type="text" name="Ssex" class="form-control" value="<%=studentInfo.getSsex() %>"/></td>
                              	  <td class="numeric"><input type="text" name="Sage" class="form-control" value="<%=studentInfo.getSage() %>"/></td>
                              	  <td class="numeric"><input type="text" name="major" class="form-control" value="<%=studentInfo.getMajor() %>"/></td>                                                                   
                              </tr>
                              <%} %>
                                                         
                              </tbody>
                          </table>
                          <span class="btn btn-warning btn-lg"><a href="<%=path%>/manager/studentInfoManagement.jsp">取消</a></span>
                          <input type="hidden" name="operate" value="studentInfoUpdate"/>
                          <input type="submit" class="btn btn-danger btn-lg" value="批量修改"/>
						</form>
                  </div><!-- /content-panel -->
               </div><!-- /col-lg-4 -->			
		  	</div><!-- /row -->
		  			  	                         
      <%} %>


      <!--main con     <!--footer start-->
      <footer class="site-footer">
          <div class="text-center">
              2018 - 韦宇林 - 暑期课程设计  - Design 
              <a href=""+path+"/manager/studentInfoUpdate.jsp#" class="go-top">
                  <i class="fa fa-angle-up"></i>
              </a>
          </div>
      </footer>
      <!--footer end-->
  
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
