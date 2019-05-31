<%@ page language="java" contentType="text/html" import="java.util.*,system.model.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<html >
  <head>   
    <title>学生成绩录入界面</title>

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
    
    <script type="text/javaScript">
    	var	scale
    	function getValue(value){
			scale = value;
		};
		function count(obj,i){
			var eg =  document.getElementById("examGrade"+i+"");
			var rg = document.getElementById("regularGrade"+i+"");
			
			if(eg=="" || rg==""){
				return;
			}
			document.getElementById("grade"+i+"").value = parseInt(eg.value*(1-scale))+parseInt(rg.value*scale);
			
		}
		function save(){

        	document.insert_form.action="<%=path %>/ScoreServlet?operate=intoTemp";
        	document.insert_form.submit();
    	}
    	function insert() {
    		
        	document.insert_form.action = "<%=path %>/ScoreServlet?operate=insertScore";
        	document.insert_form.submit();
    	}
    </script>
    
    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
      <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
  </head>
  
  <body>  	
  	
    <%
       Teacher teacherInfo = (Teacher)session.getAttribute("teacherInfo");
       String mes = (String)request.getAttribute("mes");
       if(mes!=null){
      		out.print("<center><h2><font>"+mes+"</font></h2></center>");
       }
       
       ArrayList classNumberList = (ArrayList)request.getAttribute("classNumberList");
       if(classNumberList==null){
       		response.sendRedirect(""+path+"/ScoreServlet?operate=insertQuery&Cno="+teacherInfo.getCno()+"&Tno="+teacherInfo.getTno()+"");
       }
       else{
      %>            
   		       
          	<h3><i class="fa fa-angle-right"></i> 班级成绩录入：</h3>
		  		<div class="row mt">
			  		<div class="col-lg-12">
                      <div class="content-panel">
                      <h4><i class="fa fa-angle-right"></i>操作：</h4>
                          <section id="unseen">
                          <form name="insert_form" action="" method="post">
                            <table class="table table-bordered table-striped table-condensed">
                            
                            <div class="btn-group">
						    				<button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
						      					选择平时成绩所占比重
						      					<span class="caret"></span>
						    				</button>
						    				<ul class="dropdown-menu">
						    					<li><input type="radio" name="proportion" onclick="getValue(this.value)" value="0.1"/>10%</li>						      			
						    					<li><input type="radio" name="proportion" onclick="getValue(this.value)" value="0.2"/>20%</li>
						    					<li><input type="radio" name="proportion" onclick="getValue(this.value)" value="0.3"/>30%</li>
						    					<li><input type="radio" name="proportion" onclick="getValue(this.value)" value="0.4"/>40%</li>
						    			</ul>
									</div>
                            
                              <thead>                             
                              <tr>
                              	  <th class="numeric">学号</th>
                              	  <th class="numeric">学生姓名</th>
                                  <th class="numeric">卷面成绩</th>
                                  <th class="numeric">平时成绩</th>
                                  <th class="numeric">折算后总成绩</th>
                              </tr>
                              </thead>
                              <tbody>
                              <%
         						for(int i=0;i<classNumberList.size();i++){
         						Score classNumber = (Score)classNumberList.get(i); 
         					  %>
                              <tr>                            	  
                              	  <td width="300" class="numeric"><input type="text" class="form-control" name="Sno" value="<%=classNumber.getSno() %>" readonly/></td>
                              	  <td width="300" class="numeric"><%=classNumber.getSname() %></td>                                  
                                  <td width="300" class="numeric"><input type="text" id="examGrade<%=i%>" name="examGrade" onkeyup="count('this','<%=i%>')" class="form-control" size="3" value=""/></td>
                                  <td width="300" class="numeric"><input type="text" id="regularGrade<%=i%>" name="regularGrade" onkeyup="count('this','<%=i%>')" class="form-control" size="3" value=""/></td>
                                  <td width="300" class="numeric"><input type="text" id="grade<%=i%>" name="grade" class="form-control" size="3" value="" readonly/></td>
                              </tr>
                              <%} %>                              
                               	       
                              </tbody>                                 
                          </table>
                          
                          <span class="btn btn-warning btn-lg"><a href="<%=path %>/teacher/teacher_index.jsp">取消</a></span>
                          <input type="hidden" name="Cno" value="<%=teacherInfo.getCno()%>"/>
                          <input type="hidden" name="Tno" value="<%=teacherInfo.getTno()%>"/>
                          <input type="button" class="btn btn-success btn-lg" onclick="save()" value="成绩审查"/>
                          <input type="button" class="btn btn-danger btn-lg" onclick="insert()" value="直接录入"/>
						</form>
                  </div><!-- /content-panel -->
               </div><!-- /col-lg-4 -->			
		  	</div><!-- /row -->
		  			  	                         
      <%} %>

      <!--main con     <!--footer start-->
      <footer class="site-footer">
          <div class="text-center">
              2018 - 韦宇林 - 暑期课程设计  - Design 
              <a href=""+path+"/teacher/insertClassScore.jsp#" class="go-top">
                  <i class="fa fa-angle-up"></i>
              </a>
          </div>
      </footer>
      <!--footer end-->
  
      <!-- js placed at the end of the document so the pages load faster -->
    <script src="<%=path %>/assets/js/jquery.js"></script>
    <script src="<%=path %>/assets/js/bootstrap.min.js"></script>
    <script class="include" type="text/javascript" src="<%=path %>/assets/js/jquery.dcjqaccordion.2.7.js"></script>
    <script src="<%=path %>/assets/js/jquery.scrollTo.min.js"></script>
    <script src="<%=path %>/assets/js/jquery.nicescroll.js" type="text/javascript"></script>


    <!--common script for all pages-->
    <script src="<%=page %>/assets/js/common-scripts.js"></script>

    <!--script for this page-->
       

  </body>
</html>