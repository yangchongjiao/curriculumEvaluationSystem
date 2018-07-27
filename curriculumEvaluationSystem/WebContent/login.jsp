<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <body>
  <h3>用户登录</h3>
  <span style="color: red">${fail_key }</span>
  <br/>
    <form action="<%=request.getContextPath() %>/admin/login" method="post">
      username:<input type="text" name="username" ><br/>
      password:<input type="password" name="password"/><br/>
      <input type="submit"/>
    </form>
    <br/>
   <a href="javascript:void(0);" 
   onclick="javascript:self.location='<%=request.getContextPath() %>/regisit'">注册</a>
  </body>
</html>
