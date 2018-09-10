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
<p>&nbsp;&nbsp;&nbsp;新&nbsp;&nbsp;&nbsp;密&nbsp;&nbsp;&nbsp;码：<input type="password" id="pwdone" class="textbox" name="pwdone"></p>
<p>重新输入密码：<input type="password" id="pwdtwo" class="textbox" name="pwdtwo"></p>
</body>
<script type="text/javascript">
$("#pwdone").validatebox({
	required:true,
	validType:'length[6,30]',
	missingMessage:'请输入管理员密码',
	invalidmessage:'密码不能为空',
});
$("#pwdtwo").validatebox({
	required:true,
	validType:'length[6,30]',
	missingMessage:'请输入管理员密码',
	invalidmessage:'密码不能为空',
});

function submitForm() {
	var pwdone = $("#pwdone").val();
	var pwdtwo = $("#pwdtwo").val();
	if(!$("#pwdone").validatebox('isValid')){
		$("#pwdone").focus();
	}else if(!$("#pwdtwo").validatebox('isValid')){
		$("#pwdtwo").focus();
	}else if(pwdone != pwdtwo) {
		parent.$.messager.alert('提示','密码不一致','error');
	}else{
		$.ajax({
			url:'login_updatePwd.action',
			type:'POST',
			data:{
				pwdone:pwdone,
				pwdtwo:pwdtwo
			},
			dataType:'json',
			success:function(data){
				parent.$.messager.alert('提示',data.msg,'info',function(){
					parent.$('#ddmain').window('close');
				});
			},
		})
	}
}
</script>
</html>
