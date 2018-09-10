<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>员工添加</title>
<link rel="stylesheet" type="text/css" href="themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="themes/icon.css">
<link rel="stylesheet" type="text/css" href="css/demo.css">
</head>
<body>
	<div id="form1" style="width:400px;" class="easyui-panel" title="员工添加">
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
			    			<td><input class="easyui-validatebox textbox" name="phone" data-options="required:true"></input></td>
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
			    			<td><input class="easyui-validatebox textbox" name="age" data-options="required:true"></input></td>
			    		</tr>
			    		<tr>
			    			<td>店面:</td>
			    			<td><input class="easyui-validatebox" name="storeid" data-options="required:true" id="store"></input></td>
			    		</tr>
			    	</table>
			    </form>
		    </div>
	    <div style="text-align:center;padding:5px">
	    	<a href="javascript:void(0)" iconCls="icon-add" class="easyui-linkbutton" onclick="submitForm()">增加</a>
	    	<!-- <a href="javascript:void(0)" iconCls="icon-redo" class="easyui-linkbutton" onclick="clearForm()">清空</a>  -->
	    </div>
</body>
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="js/easyui-lang-zh_CN.js"></script>
<script>
//加载下拉框
$('#store').combobox({
	editable:false,
	url:'operator_queryStore.action',
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
		url:'operator_add.action',
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
					parent.reloadDatagridOperator("技术员工信息");
			    	closeTab('员工增加');
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
}
</script>
</html>