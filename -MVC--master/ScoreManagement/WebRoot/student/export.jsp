<%@ page language="java" import="java.util.*,java.io.*,system.model.*" pageEncoding="utf-8"%> 
<%@ page contentType="application/msexcel" %> 
<% 
  //response.setHeader("Content-disposition","inline; filename=videos.xls"); 
  response.setHeader("Content-disposition","attachment; filename=score.xls"); 
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
  <%String Sno = request.getParameter("Sno");
  	ArrayList exportList = (ArrayList)request.getAttribute("exportList");
    if(exportList==null){
       		request.setAttribute("Sno",Sno);
			RequestDispatcher rd = request.getRequestDispatcher(""+path+"/ScoreServlet?operate=export");
			rd.forward(request, response);
       }
       else{
   %>
  <table border="1" width="100%"> 
		<tr>
				<th class="numeric">学号</th>
				<th class="numeric">姓名</th>
                <th class="numeric">课程名</th>
                <th class="numeric">类型</th>
                <th class="numeric">成绩</th>
        </tr>
		<%
         		for(int i=0;i<exportList.size();i++){
         			Score score = (Score)exportList.get(i); 
        %>
     	<tr>
     			<td class="numeric"><%=score.getSno() %></td>
     			<td class="numeric"><%=score.getSname() %></td>
                <td class="numeric"><%=score.getCname() %></td>
                <td class="numeric"><%=score.getType() %></td>
                 <td class="numeric"><%=score.getGrade() %></td>
        </tr>
    	<% 
   		 } 
		 %> 
		</table> 
	<%} %>
  </body>
</html>
