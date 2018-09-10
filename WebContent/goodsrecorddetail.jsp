<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="themes/icon.css">
<link rel="stylesheet" type="text/css" href="css/demo.css">
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="js/easyui-lang-zh_CN.js"></script>
</head>
<body>
<table id="datagrid"></table>
</body>
<script type="text/javascript">
function GetQueryString(name){
    var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if(r!=null)
   	 return  decodeURI(r[2]); 
    return null;
}
var id = GetQueryString("id");
$("#datagrid").datagrid({
	width:700,
	fit:true,
	url:'goodsRecord_goodsRecordDetail.action',
	striped:true,
	rownumbers:true,
	fitColumns:true,
	queryParams: {
		id:id,
	},
	columns:[[
		{
			field:'goodsid',
			title:'商品id',
			width:100,
		},
		{
			field:'name',
			title:'商品名',
			width:100,
		},
		{
			field:'price',
			title:'商品单价',
			width:100,
		},
		{
			field:'number',
			title:'商品数量',
			width:100,
		},
		{
			field:'grmoney',
			title:'消费金额(元)',
			width:100,
		},
		{
			field:'grdate',
			title:'消费日期',
			width:100,
			
		},
	]],
});  
</script>
</html>