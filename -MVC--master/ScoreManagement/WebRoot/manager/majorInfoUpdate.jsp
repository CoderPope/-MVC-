<%@ page language="java" import="java.util.*,system.model.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<html lang="en">
  <head>   
    <title>批量修改专业信息界面</title>

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
       
       ArrayList majorInfoList = (ArrayList)request.getAttribute("majorInfoList");
       if(majorInfoList==null){
			RequestDispatcher rd = request.getRequestDispatcher("/MajorServlet?operate=updateQuery");
			rd.forward(request, response);
       }
       else{
       		int pageSize = 10;
       		int recordsNum = Integer.parseInt(request.getParameter("recordsNum"));
      	 	int endNum =  Integer.parseInt(request.getParameter("endNum"));
       		int nowPage = Integer.parseInt(request.getParameter("nowPage"));
       		int pageNum = (recordsNum-1+pageSize)/ pageSize;
      %>             
          	<h3><i class="fa fa-angle-right"></i> 批量修改：</h3>
		  		<div class="row mt">
			  		<div class="col-lg-12">
                      <div class="content-panel">
                      <h4><i class="fa fa-angle-right"></i>查询结果：</h4>
                          <section id="unseen">
                          <form action="<%=path%>/MajorServlet">
                            <table class="table table-bordered table-striped table-condensed">
                              <thead>                             
                              <tr>
                              	  <th class="numeric">选择</th>
                              	  <th class="numeric">课程号</th>
                              	  <th class="numeric">课程名</th>
                              	  <th class="numeric">所属专业</th>
                              </tr>
                              </thead>
                              <tbody>
                              <%
         						for(int i=0;i<majorInfoList.size();i++){
         						Major majorInfo = (Major)majorInfoList.get(i); 
         					  %>
                              <tr>
                              	  <td class="numeric"><input type="checkbox" name="ids" value="<%=majorInfo.getCno()%>"/></td>                            	  
                              	  <td class="numeric"><%=majorInfo.getCno() %></td>
                              	  <td class="numeric"><input type="text" name="Cname" class="form-control" value="<%=majorInfo.getCname() %>"/></td>
                              	  <td class="numeric"><input type="text" name="major" class="form-control" value="<%=majorInfo.getMajor() %>"/></td>                                                                   
                              </tr>
                              <%} %>
                                                         
                              </tbody>
                          </table>
                          <span class="btn btn-warning btn-lg"><a href="<%=path%>/manager/majorInfoManagement.jsp">取消</a></span>
                          <input type="hidden" name="operate" value="majorInfoUpdate"/>
                          <input type="submit" class="btn btn-danger btn-lg" value="批量修改"/>
						</form>
                  </div><!-- /content-panel -->
                  
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
        					<td ><a href="<%=path%>/MajorServlet?operate=updateQuery&pageN=<%=nowPage-1 %>">上一页</a></td>       			
        			<%}%>     			
        			
        			<%
        				for(int i =1;i<=pageNum;i++){       					
        			%>                                                       
                    <td ><a href="<%=path%>/MajorServlet?operate=updateQuery&pageN=<%=i %>"><%=i %></a></td>
                    <%} %>
                    
                    <% 
        				if(nowPage==pageNum){
        			%>
        					<td ><a href="javascript:;">下一页</a></td>
        			<% 
        				}else{
        			%>
        					<td ><a href="<%=path%>/MajorServlet?operate=updateQuery&pageN=<%=nowPage+1 %>">下一页</a></td>       			
        			<%}%>
                    
            	</tr>
            </table>
                  
               </div><!-- /col-lg-4 -->			
		  	</div><!-- /row -->
		  			  	                         
      <%} %>


      <!--main con     <!--footer start-->
      <footer class="site-footer">
          <div class="text-center">
              2018 - 韦宇林 - 暑期课程设计  - Design 
              <a href="<%=path%>/manager/majorInfoUpdate.jsp#" class="go-top">
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
