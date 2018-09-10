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
<style>
 tr {
	margin-top: 0px;
} 
td{
	width:150px;
}
</style>
</head>
<body>
	<div id="form1" style="width: 400px; margin: 0px auto;">
		<div style="padding: 10px 0 10px 60px;">
			<form id="ff" method="post">
				<table style="border-collapse: separate; border-spacing: 0px 10px;">
					<tr style="display: none;">
						<td>id:</td>
						<td><input type="hidden" name="id"></input></td>
					</tr>
					<tr>
						<td>采购时间:</td>
						<td><input id="date" name="date"
							class="easyui-datetimebox" data-options="required:true"></input></td>
					</tr>
					<tr>
						<td>供货商:</td>
						<td><input id="provider" name="provider" class="easyui-validatebox textbox"
							data-options="required:false"></input></td>
					</tr>
					<tr>
						<td>采购员:</td>
						<td><input id="purchasename" name="purchasename"
							class="easyui-validatebox textbox" data-options="required:true"></input></td>
					</tr>
					<tr>
						<td>金额:</td>
						<td><input id="money" name="money"
							class="easyui-numberbox textbox"
							data-options="required:true,min:0,precision:2"></input></td>
					</tr>
					<tr>
						<td>备注:</td>
						<td><textarea name="remark" id="remark"></textarea></td>
					</tr>
				</table>
			</form>
		</div>
	</div>
</body>
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="js/easyui-lang-zh_CN.js"></script>
<script>
function submitForm(){
	$('#ff').form('submit',{
		url:'purchase_add.action',
		onSubmit:function(param){
			return $(this).form('validate');
		},  
		success:function(data){
			parent.$('#dd').window('close');
			resultMsg(data);
			parent.reloaddatagrid();
		}
	});
}

</script>
</html>