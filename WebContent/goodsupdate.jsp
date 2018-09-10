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
						<td>purid:</td>
						<td><input type="hidden" name="purid"></input></td>
					</tr>
					<tr style="display: none;">
						<td>id:</td>
						<td><input type="hidden" name="id"></input></td>
					</tr>
					<tr>
						<td>商品名:</td>
						<td><input id="name" name="name" class="easyui-validatebox textbox"
							data-options="required:true,editable:false"></input></td>
						<td>不可修改</td>
					</tr>
					<tr>
						<td>品牌:</td>
						<td><input id="brand" name="brand" class="easyui-validatebox textbox"
							data-options="required:true,editable:false"></input></td>
						<td>不可修改</td>
					</tr>
					<tr>
						<td>进价(元):</td>
						<td><input id="ginprice" name="ginprice"
							class="easyui-numberbox textbox"
							data-options="required:true,min:0,precision:2"></input></td>
					</tr>
					<tr>
						<td>售价(元):</td>
						<td><input id="goutprivce" name="goutprivce"
							class="easyui-numberbox textbox"
							data-options="required:true,min:0,precision:2"></input></td>
					</tr>
					<tr>
						<td>生产日期:</td>
						<td><input id="productiondate" name="productiondate"
							class="easyui-datebox" data-options="required:false"></input></td>
					</tr>
					<tr>
						<td>采购数量:</td>
						<td><input id="productiontotal" name="productiontotal"
							class="easyui-numberbox textbox"
							data-options="required:true"></input></td>
					</tr>
					<tr>
						<td>供货商:</td>
						<td><input id="supplier" name="supplier" class="easyui-validatebox textbox"
							data-options="required:false"></input></td>
					</tr>
					<tr>
						<td>采购时间:</td>
						<td><input id="storagetime" name="storagetime"
							class="easyui-datetimebox" data-options="required:true"></input></td>
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
	url : 'goods_queryGoods.action',
	type : 'POST',
	data : {
		id : id,
	},
	dataType : 'json',
	success : function(data) {
		$("input[name='purid']").val(data.purid);
		$("#name").val(data.name);
		$("#brand").val(data.brand);
		$('#ginprice').textbox('setValue', data.ginprice);  
		$('#goutprivce').textbox('setValue', data.goutprivce);  
		$('#productiondate').datebox('setValue', data.productiondate); 
		$('#productiontotal').textbox('setValue', data.productiontotal);
		$("#supplier").val(data.supplier);
		$('#storagetime').datetimebox('setValue', data.storagetime);  
	}
});

function submitForm(){
	$('#ff').form('submit',{
		url:'goods_update.action',
		onSubmit:function(param){
			return $(this).form('validate');
		},  
		success:function(data){
			parent.$('#ddmainadd').window('close');
			resultMsg(data);
			parent.reloadDatagridGoods();
		}
	});
}

</script>
</html>