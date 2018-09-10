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
	<div id="form1" style="width: 100%;margin: 0px auto;">
		<div style="padding: 10px 0 10px 60px;">
				<table>
					<tr>
						<!-- <td>id:</td>
						<td><input type="hidden" name="id"></input></td> -->
				
						<td>商品名:</td>
						<td>
						  <input id="goods" name="goods"
							class="textbox" data-options="required:true"></input>
						</td>
						
						
						<td>商品数量:</td>
						<td><input id="number" name="number"
							class="easyui-numberbox textbox"
							data-options="required:true" style="width:100px;"></input>
						</td>
						
						<!-- <td>总金额(元):</td>
						<td><input id="grmoney" name="grmoney"
							class="easyui-numberbox textbox"
							data-options="required:true,min:0,precision:2" style="width:100px;"></input>
						</td> -->
						<td>服务人:</td>
						<td><input id="waiterid" name="waiterid"
							class="easyui-validatebox" data-options="required:true"></input>
						</td>
						
						<td>
						    <button iconCls="icon-add" onclick="addGoods();" class="easyui-linkbutton" style="height:26px;">增加</button>
						   
						</td>
					</tr>
				</table>
		</div>
	</div>
	<table id="datagrid"></table>
</body>
<script>
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

Date.prototype.format = function(fmt) { 
    var o = { 
       "M+" : this.getMonth()+1,                 //月份 
       "d+" : this.getDate(),                    //日 
       "h+" : this.getHours(),                   //小时 
       "m+" : this.getMinutes(),                 //分 
       "s+" : this.getSeconds(),                 //秒 
       "q+" : Math.floor((this.getMonth()+3)/3), //季度 
       "S"  : this.getMilliseconds()             //毫秒 
   }; 
   if(/(y+)/.test(fmt)) {
           fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length)); 
   }
    for(var k in o) {
       if(new RegExp("("+ k +")").test(fmt)){
            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));
        }
    }
   return fmt; 
}        



var jsondata;
//加载下拉框
$('#goods').combobox({
	editable:false,
	url:'goodsRecord_queryGoods.action',
	valueField: 'goodsid',    
    textField: 'goodsname',
    onLoadSuccess: function () { 
    	var data = $('#goods').combobox('getData');
    	jsondata = data;
    	if (data.length > 0) {
            $('#goods').combobox('select', data[0].goodsid);
        }              
   }
});

function isNull( str ){
	if ( str == "" ) return true;
	var regu = "^[ ]+$";
	var re = new RegExp(regu);
	return re.test(str);
}
function addGoods() {
	var goods = $('#goods').combobox('getText');
	var ids = $('#goods').combobox('getValue');
	var numbers = $.trim($("#number").val());
	var grmoneys = $("#grmoney").val();
	var price;
	if(!isNull(numbers)){
		for(var i = 0; i < jsondata.length; i++){
			if(ids == jsondata[i].goodsid){
				price = jsondata[i].goodsprice;
				grmoneys = numbers * price;
			}
		}
		   
		$('#datagrid').datagrid('appendRow',{
			goodsid: ids,
			name: goods,
			price:price,
			number: numbers,
			grmoney: grmoneys,
			grdate: new Date().format("yyyy-MM-dd hh:mm:ss")
		});
	} else {
		$.messager.alert('提示','请输入数量','error');
	}
	
}
$("#datagrid").datagrid({
	width:700,
	fit:true,
	url:null,
	striped:true,
	rownumbers:true,
	fitColumns:true,
	columns:[[
		{
			field:'goodsid',
			title:'商品id',
			width:100,
		},
		{
			field:'name',
			title:'商品名',
			width:100,
		},
		{
			field:'price',
			title:'商品单价',
			width:100,
		},
		{
			field:'number',
			title:'商品数量',
			width:100,
		},
		{
			field:'grmoney',
			title:'消费金额(元)',
			width:100,
		},
		{
			field:'grdate',
			title:'消费日期',
			width:100,
			
		},{
			field:'operate',
			title:'操作',
			align:'center',
			width:100,
			formatter:function(value, row, index){  
		        var str = '<a href="#" onclick="remove('+index+');" id="opera" name="opera" class="easyui-linkbutton" >删除</a>';  
		        return str;
			},
		}
	]],
	onLoadSuccess:function(data){    
        $("#opera").linkbutton({text:'删除',plain:true,iconCls:'icon-remove'});    
    }
});  

function remove(index){
	$('#datagrid').datagrid('deleteRow',index);
	$('#datagrid').datagrid('reload');
}

function submitForm() {

	//这段代码是获取当前页的所有行。
	var rows = $("#datagrid").datagrid("getRows"); 
	var myJsonString = JSON.stringify(rows);
	$.ajax({
		type:'post',
		url:'goodsRecord_add.action',
		dataType:'json',
		//contentType:"application/json",
		data:{
			grdv:myJsonString,
			waiterid:$("#waiterid").val()
		},
		success:function(data){
			if(data.success){
				parent.$.messager.alert('提示','添加成功','info',function(){
					parent.reloadDatagridGoodsRecord('商品消费记录');
					parent.$('#ddmainadd').window('close');
				});
			} else {
				parent.$.messager.alert('提示',data.msg,'error',function(){
					
				});
			}
			
		}
	});
}


/* var json;
$.ajax({
	type:'post',
	url:'goodsRecord_queryGoods.action',
	dataType : 'json',
	success:function(data){
		json = data;
		for(var i = 0; i < data.length; i++){
		    var html = "<input type='checkbox' class='goods' name='goods' value='"+ data[i].goodsid+"'> "+"<span>"+data[i].goodsname+"  单价"+data[i].goodsprice+"</span><br/>";
			$("#sp").append(html); 
		} 
	}
});
$('#goods').combo({
    required : true,
    editable : false,
    multiple : true
});
$('#sp').appendTo($('#goods').combo('panel'));


	
var sum = 0;
$(function(){
	$("#sp").delegate(".goods","click", function(){
		var _value = "";
		var _text = "";
		$("input[name='goods']:checked").each(function() {
			_value += $(this).val() + ",";

			_text += $(this).next("span").text() + ",";
		});
		//设置下拉选中值
		$('#goods').combo('setValue', _value).combo('setText', _text);
		for(var i = 0; i < json.length; i++) {
			if($("input[name='goods']:checked").val() == json[i].goodsid){
				alert(json[i].goodsid);
				alert($("input[name='goods']:checked").val());
				alert(sum);
				sum = sum +json[i].goodsprice;
				alert(sum);
				$('#grmoney').textbox('setValue',sum);
			}
		}
	});
});
 */
</script>
</html>