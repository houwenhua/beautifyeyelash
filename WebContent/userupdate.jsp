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
				<table>
					<tr style="display: none;">
						<td>id:</td>
						<td><input type="hidden" name="id"></input></td>
					</tr>
					<tr>
						<td>用户名:</td>
						<td><input class="easyui-validatebox textbox" type="text"
							id="name" name="name" data-options="required:true"></input></td>
					</tr>
					<tr>
						<td>登录名:</td>
						<td><input class="easyui-validatebox textbox" type="text"
							id="loginname" name="loginname" data-options="required:true"></input></td>
					</tr>
					<tr>
						<td>密码:</td>
						<td><input class="easyui-validatebox textbox" type="text"
							id="password" name="password" data-options="required:true"></input></td>
					</tr>
					<tr>
						<td>邮箱:</td>
						<td><input class="easyui-validatebox textbox" type="text"
							name="email" id="email" data-options="required:true,validType:'email'"></input></td>
					</tr>
					<tr>
						<td>权限:</td>
						<td><select type="text" name="jurisdiction" id="jurisdiction">
								<option value="系统管理员">系统管理员</option>
								<option value="管理员">管理员</option>
						    </select>
						</td>
					</tr>
					<tr>
						<td>店面:</td>
						<td><input class="easyui-validatebox" name="storeid" data-options="required:true" id="store"></input></td>
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
function GetQueryString(name){
    var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if(r!=null)
   	 return  decodeURI(r[2]); 
    return null;
}
var id = GetQueryString("id");
$("input[name='id']").val(GetQueryString("id"));

$.ajax({
	url:'user_queryObject.action',
	type:'POST',
	data:{
		id:id,
	},
	dataType: 'json',
	success:function(data){
		var tempDate = data;
		$("#name").val(tempDate.name);
		$("#loginname").val(tempDate.loginname);
		$("#password").val(tempDate.password);
		$("#email").val(tempDate.email);
		$("#jurisdiction").val(tempDate.jurisdiction);
		//加载下拉框
		$('#store').combobox({
			editable:false,
			url:'user_queryStore.action',
			valueField: 'storeid',    
		    textField: 'storename',
		    onLoadSuccess: function () { 
		    	var data = $('#store').combobox('getData');
		    	if (data.length > 0) {
		    		for(var i=0;i<data.length;i++){
		    			if(data[i].storename === tempDate.store){
		    				 $('#store').combobox('select', data[i].storeid);
		    			}
		    		}
		        }              
		   }
		});
	}
});

function submitForm() {
	$('#ff').form('submit', {
		url : 'user_update.action',
		onSubmit : function(param) {
			return $(this).form('validate');
		},
		success : function(data) {
			resultMsg(data);
			parent.reloadDatagridUpdata();
			parent.$('#dd').window('close');
		}
	});
}
	
</script>
</html>