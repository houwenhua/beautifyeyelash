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
	margin-top: 15px;
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
						<td>消费人:</td>
						<td><input id="vipid" name="vipid" class="easyui-validatebox textbox"
							data-options="required:true"></input></td>
					</tr>
					<tr>
						<td>服务类型:</td>
						<td><input id="serviceid" name="serviceid"
							class="easyui-validatebox" data-options="required:true"></input></td>
					</tr>
					<tr>
						<td>服务人:</td>
						<td><input id="waiterid" name="waiterid"
							class="easyui-validatebox" data-options="required:true"></input></td>
					</tr>
					<tr>
						<td>消费金额:</td>
						<td><input id="money" name="money"
							class="easyui-numberbox textbox"
							data-options="required:true,min:0,precision:2"></input></td>
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


//加载下拉框
$('#serviceid').combobox({
	editable:false,
	url:'visitor_queryService.action',
	valueField: 'serviceid',    
    textField: 'servicename',
    onLoadSuccess: function () { 
    	var data = $('#serviceid').combobox('getData');
    	if (data.length > 0) {
            $('#serviceid').combobox('select', data[0].serviceid);
        }              
   }
});
//加载下拉框
$('#waiterid').combobox({
	editable:false,
	url:'visitor_queryWaiter.action',
	valueField: 'waiterid',    
    textField: 'waitername',
    onLoadSuccess: function () { 
    	var data = $('#waiterid').combobox('getData');
    	if (data.length > 0) {
            $('#waiterid').combobox('select', data[0].waiterid);
        }              
   }
});

function submitForm(){
	$('#ff').form('submit',{
		url:'visitor_add.action',
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