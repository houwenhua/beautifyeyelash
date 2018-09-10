<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>店面增加</title>
<link rel="stylesheet" type="text/css" href="themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="themes/icon.css">
<link rel="stylesheet" type="text/css" href="css/demo.css">
</head>
<body>
	<div id="form1" style="width: 400px; margin: 0px auto;">
		<div style="padding: 10px 0 10px 60px;">
			    <form id="ff" method="post">
			    	<table>
			    		<tr style="display:none;">
			    			<td>id:</td>
			    			<td><input type="hidden" name="id"></input></td>
			    		</tr> 
			    		<tr>
			    			<td>店名:</td>
			    			<td><input class="easyui-validatebox textbox" type="text" name="name" data-options="required:true"></input></td>
			    		</tr>
			    		<tr>
			    			<td>地址:</td>
			    			<td><input class="easyui-validatebox textbox" type="text" name="address" data-options="required:true"></input></td>
			    		</tr>
			    		<tr>
			    			<td>电话:</td>
			    			<td><input class="easyui-numberbox textbox" name="phone" data-options="required:true,validType:'phoneNum'"></input></td>
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

function submitForm() {
	$('#ff').form('submit', {
		url : 'store_add.action',
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
	
	/* function submitForm(){
		$('#ff').form('submit',{
			url:'store_add.action',
			onSubmit:function(param){
				return $(this).form('validate');
			},
			success:function(data){
				if(data==1){
					$.messager.show({
						title:'提示',
						msg:'插入成功了'+data,
					});
					$.messager.alert('系统提示', '增加成功！！！', 'info', function(){
						parent.reloadDatagridStore("店面信息");
				    	closeTab('店面增加');
					 });
				}else{
					 $.messager.alert('系统提示', '添加失败', 'error');
				}
			}
		});
	}
	function clearForm(){
		$('#ff').form('clear');
	}
	function closeTab(title){
		var jq = top.jQuery;
		jq('#tabs').tabs('close', title);
	} */
</script>
</html>