<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录</title>
<style type="text/css">
	#img {
	border-radius:10px;
	height:24px;
	 float:left;
	 display:inlne;
	}
	#imageText ,#spanText{
	 float:left;
	 display:inlne;
	}
</style>
<link rel="stylesheet" type="text/css" href="themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="themes/icon.css">
<link rel="stylesheet" type="text/css" href="css/demo.css">
<link rel="stylesheet" type="text/css" href="css/login.css">

</head>
<body>
	<div id="login">
		<p>管理员账户：<input type="text" id="loginname" class="textbox" name="loginname"></p>
		<p>管理员密码：<input type="password" id="pwd" class="textbox" name="pwd"></p>
		<p>
			<span id="spanText">验&nbsp;&nbsp;&nbsp;证&nbsp;&nbsp;&nbsp;码：</span><input type="text" id="imageText" class="textbox" name="imageText"style="width:60px;">
			<img id="img" alt="换一张" src="${pageContext.request.contextPath }/login_image.action" style="cursor:hand;" onclick="changeImage(this)"><!-- ${pageContext.request.contextPath }/login_image.action -->
		</p>
	</div>
	<div id="btn">
		<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-ok'">登录</a>
		<a href="#" class="easyui-linkbutton" onclick="clearAll()" data-options="iconCls:'icon-undo'">重置</a>
	</div>
</body>
<script>
	function changeImage(img){
		img.src = img.src+'?'+new Date().getTime();
	}
	function clearAll(){
		document.getElementById("loginname").value="";
		document.getElementById("pwd").value="";
		$("#loginname").focus();
	}
	
	//解决session过期后登陆页面嵌套在子框架中
	if (top != window){
		 top.location.href = window.location.href; 
	}    
</script>
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="js/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="js/index.js"></script>
</html>