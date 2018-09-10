<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>服务更新</title>
<link rel="stylesheet" type="text/css" href="themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="themes/icon.css">
<link rel="stylesheet" type="text/css" href="css/demo.css">
<style>
	tr {
	   margin-top:15px;
	}
</style>
</head>
<body>
	<div id="form1" style="width:400px;margin:0px auto;">
		<div style="padding:10px 0 10px 60px;">
			    <form id="ff" method="post">
			    	<table>
			    		<tr style="display:none;">
			    			<td>id:</td>
			    			<td><input type="hidden" name="id"></input></td>
			    		</tr> 
			    		<tr>
			    			<td>服务名:</td>
			    			<td><input class="easyui-validatebox textbox" type="text" name="servicename" data-options="required:true"></input></td>
			    		</tr>
			    		<tr>
			    			<td>全价:</td>
			    			<td><input class="easyui-numberbox textbox" type="text" id="price" name="price" data-options="required:true,min:0,precision:2"></input></td>
			    		</tr>
			    		<tr>
			    			<td>会员价:</td>
			    			<td><input class="easyui-numberbox textbox" type="text" id="vipprice" name="vipprice" data-options="required:true,min:0,precision:2"></input></td>
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
	url:'service_queryObject.action',
	type:'POST',
	data:{
		id:id,
	},
	dataType: 'json',
	success:function(data){
		var json = data;
		$("input[name='servicename']").val(json.servicename);
		$("#price").textbox('setValue',json.price);
		$("#vipprice").textbox('setValue',json.vipprice);
		$("textarea[name='remark']").val(json.remark);
	}
});

function submitForm(){
	$('#ff').form('submit',{
		url:'service_update.action',
		onSubmit:function(param){
			return $(this).form('validate');
		},
		success:function(data){
			resultMsg(data);
			parent.reloaddatagrid();
		}
	});
}	
	
</script>
</html>