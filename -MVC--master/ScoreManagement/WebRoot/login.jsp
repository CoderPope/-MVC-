<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<html lang="en">
  <head>
  	<base href="<%=basePath%>">
    
    <title>用户登录</title>
  
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="Dashboard">
    <meta name="keyword" content="Dashboard, Bootstrap, Admin, Template, Theme, Responsive, Fluid, Retina">

    <!-- Bootstrap core CSS -->
    <link href="assets/css/bootstrap.css" rel="stylesheet">
    <!--external css-->
    <link href="assets/font-awesome/css/font-awesome.css" rel="stylesheet" />
        
    <!-- Custom styles for this template -->
    <link href="assets/css/style.css" rel="stylesheet">
    <link href="assets/css/style-responsive.css" rel="stylesheet">

    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
      <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    
    <script type="text/javascript">
   		 function reloadcode(){
                var verify=document.getElementById('code');
                verify.setAttribute('src','makeCertPic.jsp?it='+Math.random());
        }
 	</script>
    
    
  </head>

  <body>
	  <div id="login-page">
	  	<div class="container">
	  		<%
     			String mes = (String)request.getAttribute("mes");
     			if(mes!=null){
      			 out.print("<center><div style='width:360' class='alert alert-warning alert-dismissable'><button type='button' class='close' data-dismiss='alert' aria-hidden='true'>&times;</button><strong>Warning!</strong>"+mes+"</div></center>");
     			}
     		%>
     		
		      <form class="form-login" action="<%=path%>/LoginServlet">
		        <h2 class="form-login-heading">用户登录</h2>
		        <div class="login-wrap">
		            <input type="text" name="user_id" class="form-control" placeholder="用户编号" autofocus>
		            <br>
		            <input type="password" name="password" class="form-control" placeholder="密码">
		            <br>
		            <input type="text" name="certCode" class="btn btn-default" style="width:80" placeholder="验证码"><img src="makeCertPic.jsp" id="code" onclick="reloadcode()" style="cursor: pointer;" alt="看不清楚,换一张">
		            
		            <!-- 用户权限下拉框 -->
		            <label class="checkbox">
		            <button type="button" class="btn btn-theme dropdown-toggle" data-toggle="dropdown">
						    用户权限 <span class="caret"></span>
						  </button>
						  <ul class="dropdown-menu" role="menu">
						    <li>
						    	<input type="radio" name="purview" value="student"/>
                                <span class="from">学生</span>
                            </li>
						    <li>
						    	<input type="radio" name="purview" value="teacher"/>
                                <span class="from">教师</span>
                            </li>
						    <li>
						    	<input type="radio" name="purview" value="manager"/>
                                <span class="from">管理员</span>
                            </li>						    
						  </ul>						  

		            </label>
		            <input type="hidden" name="operate" value="login"/>
		            <button class="btn btn-theme btn-block" type="submit"><i class="fa fa-lock"></i> 登录</button>
		            <button class="btn btn-theme btn-block" type="reset"><i class="fa fa-default"></i> 重置</button>
		            <hr>
		            
		            <div class="login-social-link centered">
		            <p>学生成绩管理系统1.0</p>
		            </div>		
		        </div>				          		
		      </form>	  	
	  	</div>
	  </div>
	  
    <!-- js placed at the end of the document so the pages load faster -->
    <script src="assets/js/jquery.js"></script>
    <script src="assets/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="assets/js/gritter/js/jquery.gritter.js"></script>
    <script type="text/javascript" src="assets/js/gritter-conf.js"></script>

    <!--BACKSTRETCH-->
    <!-- You can use an image of whatever size. This script will stretch to fit in any screen size.-->
    <script type="text/javascript" src="assets/js/jquery.backstretch.min.js"></script>
    <script>
        $.backstretch("assets/img/login-bg.jpg", {speed: 300});
    </script>
  </body>
</html>
