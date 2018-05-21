<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
      <meta charset="utf-8"/>
  </head>
  
  <body>
    上传配件图片：<br/>
    <form action="${pageContext.request.contextPath}/uploadimage" method="post" 
    enctype="multipart/form-data">
    	image:<input type="file" name="image" value="" /><br/>
    	<input type="submit" value="submit" />
    </form>

    <form action="${pageContext.request.contextPath}/RegisterServlet" method="post">
        tag:<input type="text" name="tag"/><br/>
        name:<input type="text" name="name"/><br/>
        email:<input type="text" name="email"/><br/>
        password:<input type="text" name="password"/><br/>

        <input type="submit" value="submit" />
    </form>

    <form action="${pageContext.request.contextPath}/LoginServlet" method="post">
        tag:<input type="text" name="tag"/><br/>
        email:<input type="text" name="email"/><br/>
        password:<input type="text" name="password"/><br/>

        <input type="submit" value="submit" />
    </form>


    <form action="${pageContext.request.contextPath}/FunctionServlet" method="post">
        function:<input type="text" name="function" value="repair" /><br/>
        args1:<input type="text" name="args1" value="repair" /><br/>
        args2:<input type="text" name="args2" value="哈" /><br/>
        args3:<input type="text" name="args3" value="a" /><br/>
        args4:<input type="text" name="args4" value="s" /><br/>
        <input type="submit" value="submit" />
    </form>
    
    <hr>
  </body>
</html>
