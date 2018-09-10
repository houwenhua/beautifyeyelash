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
						<td><input id="vipid" name="vipid" class="easyui-combobox"
							data-options="required:true,validType:'special'"></input></td>
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
					<tr>
						<td>积分:</td>
						<td><input id="integral" name="integral"
							class="easyui-numberbox textbox" data-options="required:true"></input></td>
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
//加载消费者
$('#vipid').combobox({
	url:'consume_queryVip.action',
	valueField: 'vipid',    
    textField: 'vipname',
    delay:500,
    filter: function(q, row){  
        var opts = $(this).combobox('options');  
        return row[opts.textField].indexOf(q) > -1;//这里改成>=即可在任意地方匹配  
    },  
});

//加载下拉框
$('#waiterid').combobox({
	editable:false,
	url:'consume_queryWaiter.action',
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
	if(filterInputError()){
		$('#ff').form('submit',{
			url:'consume_add.action',
			onSubmit:function(param){
				return $(this).form('validate');
			},  
			success:function(data){
				parent.$('#dd').window('close');
				resultMsg(data);
				parent.reloaddatagrid();
			}
		});
	} else {
		parent.$.messager.alert('系统提示', '该消费者不存在', 'error');
	}
	
}


function filterInputError() {
	var data = $('#vipid').combobox('getData');
	var input = $('#vipid').combobox('getText');
	for(var i=0;i<data.length;i++) {
		if(input.indexOf(data[i].vipname) == 0 && input == data[i].vipname) {
			return true;
		} 
	}
	return false;
}



</script>
</html>