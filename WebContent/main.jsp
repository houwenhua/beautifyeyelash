<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.meijie.entity.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>主页</title>
	<script type="text/javascript">
		/* function RefreshCloudHomePageTab(title){
		　　　　if ($("#tabs").tabs('exists', title)) {
		　　　　　　	$('#tabs').tabs('select', title);
		　　　　　　　　window.top.Refresh_CloudHomePage_Content.call();
	　　			}
		　　}*/
	</script>
	<link rel="stylesheet" type="text/css" href="themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="themes/icon.css">
	<link rel="stylesheet" type="text/css" href="css/demo.css">
</head>
<%
	SysUser user = (SysUser)session.getAttribute("user");
%>
<body class="easyui-layout" id="cc">
	<div data-options="region:'north',border:false,split:true,noheader:true" style="height:80px;background:#B3DFDA;padding:10px">
		<div style="float:left;line-height:55px;">
			<h2 style="margin:0;padding:0;">3D美睫商店管理系统</h2>
		</div>
		<div style="width:500px;height:100%;float:right;line-height:60px;font-size:18px;">
			登录人：<span id="admin" style="font-size:15px;color:red;width:50px;display:inline-block;"><%=user.getName() %></span>
			权限：<span id="jurisdiction" style="font-size:15px;color:red;width:100px;display:inline-block;"><%=user.getJurisdiction()%></span>
			<a href="javascript:void(0);" onclick="updatePwd()" style="text-decoration:none;font-size:15px;color:red;width:100px;display:inline-block;">修改密码</a>
			<a href="login_logout.action" style="text-decoration:none;font-size:15px;color:red;width:50px;display:inline-block;">注销</a>
		</div>
	</div>
	<div data-options="region:'west',split:true,title:'导航'" style="width:180px;padding:10px;">
		<ul id="nav"></ul>
	</div>
	<div data-options="region:'south',border:false" style="height:60px;background:#A9FACD;padding:10px;text-align:center;">
		在公共场合使用完毕后请退出本系统!<br />
		重庆3D美睫管理系统 
	</div>
	<div data-options="region:'center'" style="overflow:hidden;">
		<div id="tabs">
			<div title="起始页" iconCls="icon-house">
				<iframe style="background-image:url('img/back.jpg');background-size:100% 100%;" width="100%" height="100%" id="iframe" name="iframe" frameborder="0" scrolling="auto" src="welcome.jsp"></iframe>
			</div>
		</div>
	</div>
	<div id="ddmain"></div>
	<div id="ddmainadd"></div>
</body>
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="js/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="js/main.js"></script>
<script type="text/javascript" src="js/user.js"></script>
<script type="text/javascript">
function updatePwd() {
	 var content = "<iframe src='updatepwd.jsp' id='context' name='context' width='100%' height='99%' frameborder='0' scrolling='no'></iframe>";
	$('#ddmain').dialog({
	    content:content,
	    title: '修改密码',
	    width: 280,
	    height: 200,
	    closed: false,
	    cache: false,
	    modal: true,
	    buttons:[{
	        text:'保存',
	        iconCls:'icon-ok',
	        handler:function(){
	        	document.getElementById("context").contentWindow.submitForm();
	        }
	    },{
	        text:'关闭',
	        iconCls:'icon-no',
	        handler:function(){
	            $('#ddmain').window('close');
	        }
	    }],
	});
}

</script>
</html>