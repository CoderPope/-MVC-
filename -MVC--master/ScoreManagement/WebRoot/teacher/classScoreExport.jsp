<%@ page language="java" import="java.util.*,java.io.*,system.model.*" pageEncoding="utf-8"%> 
<%@ page contentType="application/msexcel" %> 
<% 
  //response.setHeader("Content-disposition","inline; filename=videos.xls"); 
  response.setHeader("Content-disposition","attachment; filename=classScore.xls"); 
  //以上这行设定传送到前端浏览器时的档名为test.xls 
  //就是靠这一行，让前端浏览器以为接收到一个excel档  
%>  
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'export.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
  <%String Tno = request.getParameter("Tno");
  	ArrayList classExportList = (ArrayList)request.getAttribute("classExportList");
    if(classExportList==null){
    		response.sendRedirect("ScoreServlet?operate=classScoreExport&Tno="+Tno+"");
       }
       else{
   %>
  <table border="1" width="100%"> 
		<tr>
				<th class="numeric">学号</th>
                <th class="numeric">学生姓名</th>
                <th class="numeric">性别</th>
                <th class="numeric">专业</th>
                <th class="numeric">课程名</th>
                <th class="numeric">成绩</th>
        </tr>
		<%
         		for(int i=0;i<classExportList.size();i++){
         			Score classscore = (Score)classExportList.get(i); 
        %>
     	<tr>
     			<td class="numeric"><%=classscore.getSno() %></td>
     			<td class="numeric"><%=classscore.getSname() %></td>
     			<td class="numeric"><%=classscore.getSsex() %></td>
     			<td class="numeric"><%=classscore.getMajor() %></td>
                <td class="numeric"><%=classscore.getCname() %></td>
                 <td class="numeric"><%=classscore.getGrade() %></td>
        </tr>
    	<% 
   		 } 
		 %> 
		</table> 
	<%} %>
  </body>
</html>