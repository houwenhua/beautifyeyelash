<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>服务员添加</title>
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
		<div style="padding:10px 0 10px 60px;">
			    <form id="ff" method="post">
			    	<table>
			    		<tr style="display:none;">
			    			<td>id:</td>
			    			<td><input type="hidden" name="id"></input></td>
			    		</tr> 
			    		<tr>
			    			<td>姓名:</td>
			    			<td><input class="easyui-validatebox textbox" name="name" data-options="required:true"></input></td>
			    		</tr>
			    		<tr>
			    			<td>电话:</td>
			    			<td><input class="easyui-numberbox textbox" name="phone" data-options="required:true,validType:'phoneNum'"></input></td>
			    		</tr>
			    		<tr>
			    			<td>性别:</td>
			    			<td>
				    			<select type="text" name="sex">
									<option value="男">男</option>
									<option value="女">女</option>
								</select>
							</td>
			    		</tr> 
			    		<tr>
			    			<td>年龄:</td>
			    			<td><input class="easyui-numberbox textbox" name="age" data-options="required:true"></input></td>
			    		</tr>
			    		<!-- <tr>
			    			<td>工资:</td>
			    			<td><input class="easyui-numberbox textbox" name="salary" data-options="required:true"></input></td>
			    		</tr> -->
			    		<tr>
			    			<td>店面:</td>
			    			<td><input class="easyui-validatebox" name="storeid" data-options="required:true" id="store"></input></td>
			    		</tr>
			    		<tr>
			    			<td>职位:</td>
			    			<td><input class="easyui-validatebox textbox" name="job" data-options="required:true"></input></td>
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
$.extend($.fn.validatebox.defaults.rules, {    
    phoneNum: { //验证手机号   
        validator: function(value, param){ 
         return /^1[3-8]\d{9}$/.test(value);
        },    
        message: '请输入正确的手机号码。'   
    }
});
//加载下拉框
$('#store').combobox({
	editable:false,
	url:'waiter_queryStore.action',
	valueField: 'storeid',    
    textField: 'storename',
    onLoadSuccess: function () { 
    	var data = $('#store').combobox('getData');
    	if (data.length > 0) {
            $('#store').combobox('select', data[0].storeid);
        }              
   }
});
function submitForm(){
	$('#ff').form('submit',{
		url:'waiter_add.action',
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