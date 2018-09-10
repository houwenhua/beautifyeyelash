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
						<td>员工:</td>
						<td><input id="waiterid" name="waiterid"
							class="easyui-validatebox" data-options="required:true"></input></td>
					</tr>
					<tr>
						<td>底薪:</td>
						<td><input id="minsalary" name="minsalary"
							class="easyui-numberbox textbox" 
							data-options="required:true,min:0,precision:2"></input></td>
					</tr>
					<tr>
						<td>提成:</td>
						<td><input id="reward" name="reward"
							class="easyui-numberbox textbox" value="0"
							data-options="required:true,min:0,precision:2,editable:false"></input></td>
					</tr>
					<tr>
						<td>总工资:</td>
						<td><input id="summoney" name="summoney"
							class="easyui-numberbox textbox" value="0"
							data-options="required:true,min:0,precision:2,editable:false"></input></td>
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

$('#minsalary').numberbox({  
    onChange: function(value) {
    	var minsalary = $("#minsalary").textbox("getValue");
    	var reward = $("#reward").textbox("getValue");
    	 var minsalary=parseInt(minsalary);   
         var reward=parseInt(reward);   
        $("#summoney").textbox("setValue",minsalary+reward)
    }
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
		$('#ff').form('submit',{
			url:'salary_add.action',
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