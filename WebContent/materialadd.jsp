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
						<td>商品名:</td>
						<td><input id="name" name="name" class="easyui-validatebox textbox"
							data-options="required:true"></input></td>
					</tr>
					<tr>
						<td>品牌:</td>
						<td><input id="brand" name="brand" class="easyui-validatebox textbox"
							data-options="required:true"></input></td>
					</tr>
					<tr>
						<td>生产日期:</td>
						<td><input id="productiondate" name="productiondate"
							class="easyui-datetimebox" data-options="required:false"></input></td>
					</tr>
					<tr>
						<td>生产商:</td>
						<td><input id="producer" name="producer" class="easyui-validatebox textbox"
							data-options="required:true"></input></td>
					</tr>
					<tr>
						<td>服务类型:</td>
						<td><input id="serviceid" name="serviceid"
							class="easyui-validatebox" data-options="required:true"></input></td>
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
 $('#serviceid').combobox({
		editable:false,
		url:'consume_queryService.action',
		valueField: 'serviceid',    
	    textField: 'servicename',
	    onLoadSuccess: function () { 
	    	var data = $('#serviceid').combobox('getData');
	    	if (data.length > 0) {
	            $('#serviceid').combobox('select', data[0].serviceid);
	        }              
	   }
	});
function submitForm(){
	$('#ff').form('submit',{
		url:'material_add.action',
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