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
		<div id="dd"></div> 
		<div style="padding:5px;">
			姓名：<input type="text" name="name" class="textbox" id="name1"/>
			<button iconCls="icon-search" onclick="obj.search()" id="btn1" class="easyui-linkbutton" style="height:26px;">查询</button>
			
			<input type="text" name="name1" class="textbox" id="name2"/>
			<button iconCls="icon-search" onclick="searchName()" id="btn2" class="easyui-linkbutton" style="height:26px;">查询</button>
			查询月份：<input editable="false" id="date"  type="text" class= "easyui-datebox" required ="required"></input>   
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
<script type="text/javascript" src="js/salary.js"></script>

<script language="javascript">
//得到当前日期
formatterDate = function(date) {
var day = date.getDate() > 9 ? date.getDate() : "0" + date.getDate();
var month = (date.getMonth() + 1) > 9 ? (date.getMonth() + 1) : "0"
+ (date.getMonth() + 1);
return date.getFullYear() + '-' + month + '-' + day;
};

window.onload = function() {
$('#date').datebox('setValue', formatterDate(new Date()));
};

function searchName(){
	$("#datagrid").datagrid('load',{
		name:$.trim($("input[name='name1']").val()),
		date: $('#date').datebox('getValue'),
	});
}

$("#name2").hide();
$("#btn2").hide();

$('#date').datebox({
    onSelect: function(date){
    	$("#name1").hide();
    	$("#btn1").hide();
    	$("#name2").show();
    	$("#btn2").show();
    	$("#datagrid").datagrid({
    		width:700,
    		fit:true,
    		url:'salary_querySomeMonthSalary.action',
    		title:'工资信息',
    		striped:true,
    		rownumbers:true,
    		fitColumns:true,
    		queryParams: {
    			date: $('#date').datebox('getValue'),
    		},
    		columns:[[
    			{
    				field:'id',
    				title:'编号',
    				width:100,
    				checkbox:true,
    			},
    			{
    				field:'watiername',
    				title:'员工姓名',
    				width:100,
    			},
    			{
    				field:'minsalary',
    				title:'底薪(元)',
    				width:100,
    				formatter: function(value,row,index){ 
    					return (row["minsalary"]).toFixed(2);
    				}
    			},
    			{
    				field:'reward',
    				title:'提成(元)',
    				width:100,
    				formatter: function(value,row,index){ 
    					return (row["reward"]).toFixed(2);
    				}
    			},
    			{
    				field:'summoney',
    				title:'总工资(元)',
    				width:100,
    				formatter: function(value,row,index){ 
    					return (row["summoney"]).toFixed(2);
    				}
    			},
    		]],
    		toolbar:'#tb',
    		pagination:true,
    		pagePosition:'bottom',
    		onRowContextMenu:function(e,rowIndex,rowData){
    			e.preventDefault();
    			$("#menu").menu('show',{
    				top:e.pageY,
    				left:e.pageX,
    			})
    		},
    	});
    }
});


</script>

</html>