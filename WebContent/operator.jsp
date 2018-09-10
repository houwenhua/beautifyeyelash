<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>员工信息</title>
<link rel="stylesheet" type="text/css" href="themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="themes/icon.css">
<link rel="stylesheet" type="text/css" href="css/demo.css">
<style type="text/css">
	.textbox{
	    height:20px;
		margin:0;
		padding:0 2px;
		box-sizing:content-box; 
	}
</style>
</head>
<body style="padding:0px;">
	<table id="datagrid"></table>
	<div id="tb" style="padding:5px;">
		<div>
			<a href="#" iconCls="icon-add" class="easyui-linkbutton" plain="true" onclick="obj.add()">添加</a>
			<a href="#" iconCls="icon-edit" class="easyui-linkbutton" plain="true" onclick="obj.edit()">修改</a>
			<a href="#" iconCls="icon-remove" class="easyui-linkbutton" plain="true" onclick="obj.remove();">删除</a>
		</div>
		<div style="padding:5px;">
			姓名：<input type="text" name="name" class="textbox"/>
			<button iconCls="icon-search" onclick="obj.search()" class="easyui-linkbutton" style="height:26px;">查询</button>
			<input onclick="reloaddatagrid()" id="btn" type="hidden"></input>
		</div>
	</div>
	<div class="easyui-menu" id="menu" style="width:120px;display:none;">
		<div iconCls="icon-edit" onclick="obj.edit()">编辑</div>
		<div iconCls="icon-remove" onclick="obj.remove()">删除</div>
	</div>
</body>
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="js/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="js/operator.js"></script>
</html>