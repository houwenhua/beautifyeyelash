<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>vip更新</title>
<link rel="shortcut icon" href="img/favicon.ico" type="image/x-icon" />
<link rel="stylesheet" type="text/css" href="themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="themes/icon.css">
<link rel="stylesheet" type="text/css" href="css/demo.css">
<style>
/* tr {
	margin-top: 15px;
} */
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
						<td>名字:</td>
						<td><input class="easyui-validatebox textbox" name="name" id="name" data-options="required:true"></input></td>
					</tr>
					<tr>
						<td>年龄:</td>
						<td><input class="easyui-numberbox textbox" name="age" id="age" data-options="required:true"></input></td>
					</tr>
					<tr>
						<td>性别:</td>
						<td><select type="text" name="sex">
								<option value="女">女</option>
								<option value="男">男</option>
						</select></td>
					</tr>
					<tr>
						<td>电话:</td>
						<td><input class="easyui-numberbox textbox" name="phone" id="phone" data-options="required:true,validType:'phoneNum'"></input></td>
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
$.extend($.fn.validatebox.defaults.rules, {    
    phoneNum: { //验证手机号   
        validator: function(value, param){ 
         return /^1[3-8]\d{9}$/.test(value);
        },    
        message: '请输入正确的手机号码。'   
    }
});

	function GetQueryString(name) {
		var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
		var r = window.location.search.substr(1).match(reg);
		if (r != null)
			return decodeURI(r[2]);
		return null;
	}
	var id = GetQueryString("id");
	$("input[name='id']").val(GetQueryString("id"));
	
	
	$.ajax({
		url : 'vip_QueryVip.action',
		type : 'POST',
		data : {
			id : id,
		},
		dataType : 'json',
		success : function(data) {
			var json = data;
			$("#name").val(json.name);
			$("select[name='sex']").val(json.sex);
			$("#age").textbox('setValue',json.age);
			$("#phone").textbox('setValue',json.phone);
			$("textarea[name='remark']").val(json.remark);
		}
	});
	
	function submitForm() {
		$('#ff').form('submit', {
			url : 'vip_update.action',
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