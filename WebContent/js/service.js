$(function(){

	obj = {
		editRow:undefined,
		search:function(){
			$("#datagrid").datagrid('load',{
				servicename:$.trim($("input[name='servicename']").val()),
			});
		},
		//添加一行
		add:function(){
			var content = "<iframe src='serviceadd.jsp' id='context' name='context' width='100%' height='99%' frameborder='0' scrolling='no'></iframe>";
	        $('#dd').dialog({
	            content:content,
	            title: '服务类型',
	            width: 600,
	            height: 400,
	            closed: false,
	            cache: false,
	            modal: true,
	            resizable:true,
	            buttons:[{
	                text:'保存',
	                iconCls:'icon-ok',
	                handler:function(){
	                	//点击得到提交表单方法
	                    document.getElementById("context").contentWindow.submitForm();
	                    //关闭窗口
	                    //$('#dd').window('close');
	                    
	                }
	            },{
	                text:'关闭',
	                iconCls:'icon-no',
	                handler:function(){
	                    $('#dd').window('close');
	                }
	            }],
	        });
		},
		edit:function(){
			var rows = $("#datagrid").datagrid('getSelections');
			if(rows.length == 1){
				var jq = top.jQuery;    
				var url = 'serviceupdate.jsp?id='+rows[0].id;
				var content = '<iframe id="context" name="context" scrolling="auto" frameborder="0"  src="'+url+'" style="width:100%;height:100%;"></iframe>';     
            	$('#dd').dialog({
		            content:content,
		            title: '服务类型',
		            width: 600,
		            height: 400,
		            closed: false,
		            cache: false,
		            modal: true,
		            resizable:true,
		            buttons:[{
		                text:'保存',
		                iconCls:'icon-ok',
		                handler:function(){
		                	//点击得到提交表单方法
		                    document.getElementById("context").contentWindow.submitForm();
		                    //关闭窗口
		                    $('#dd').window('close');
		                }
		            },{
		                text:'关闭',
		                iconCls:'icon-no',
		                handler:function(){
		                    $('#dd').window('close');
		                }
		            }],
		        });     
			}else{
				$.messager.alert('警告','修改必须选中一行且只能选中一行','warning');
			}
			
		},
		remove:function(){
			var rows = $("#datagrid").datagrid('getSelections');
			if(rows.length>0){
				$.messager.confirm('提示','您确定删除这些记录吗？',function(flag){
					if(flag){
						var ids = [];
						for(var i = 0;i<rows.length;i++){
							ids.push(rows[i].id);
						}
						$.ajax({
							type:'post',
							url:'service_remove.action',
							data:{
								ids:ids.join(','),
							},
							beforeSend:function(){
								$("#datagrid").datagrid('loading');
							},
							success:function(data){
								var data = jQuery.parseJSON(data);
								if(data.success){
									$("#datagrid").datagrid('reload');
									$("#datagrid").datagrid('loaded');
									$("#datagrid").datagrid('unselectAll');
									$.messager.show({
										title:'提示',
										msg:data.msg,
									});
								}else{
									$.messager.alert('提示','删除失败','error',function(){
										$("#datagrid").datagrid('loaded');
									});
								}
							}
						});
					}
				});
			}else{
				$.messager.alert('提示','请选择你要删除的记录','info');
			}
		},
		image:function(){
			var rows = $("#datagrid").datagrid('getSelections');
			if(rows.length == 1){
				var jq = top.jQuery;    
				var url = 'serviceimageupload.jsp?id='+rows[0].id;
	            if (jq("#tabs").tabs('exists', '服务图片上传')){    
	                jq("#tabs").tabs('select', '服务图片上传');    
	            } else {  
	            	var content = '<iframe scrolling="auto" frameborder="0"  src="'+url+'" style="width:100%;height:100%;"></iframe>';     
	                jq("#tabs").tabs('add',{    
	                                    title:'服务图片上传',    
	                                    content:content,    
	                                    closable:true    
	                 });   
	             }   
			}else{
				$.messager.alert('警告','修改必须选中一行且只能选中一行','warning');
			}
			
		}
		
	};
	
	
	$("#datagrid").datagrid({
		width:700,
		fit:true,
		url:'service_paginationQuery.action',
		title:'服务基本信息',
		striped:true,
		rownumbers:true,
		fitColumns:true,
		columns:[[
			{
				field:'id',
				title:'编号',
				width:100,
				checkbox:true,
			},
			{
				field:'servicename',
				title:'服务名',
				width:100,
				editor:{
					type:'validatebox',
					options:{
						required:true,
					},
				}
			},
			{
				field:'price',
				title:'全价(元)',
				width:100,
				formatter: function(value,row,index){ 
					return (row["price"]).toFixed(2);
				}
			},
			{
				field:'vipprice',
				title:'会员价(元)',
				width:100,
				formatter: function(value,row,index){ 
					return (row["vipprice"]).toFixed(2);
				}
			},
			{
				field:'material',
				title:'材料',
				width:100,
			},
			{
				field:'remark',
				title:'备注',
				width:100,
				editor:{
					type:'textarea',
					options:{
						required:true,
					},
				}
			},
		]],
		toolbar:'#tb',
		pagination:true,
		pagePosition:'bottom',
		onRowContextMenu:function(e,rowIndex,rowData){
			e.preventDefault();
			$("#menu").menu('show',{
				top:e.pageY,
				left:e.pageX,
			})
		},
		view: detailview,
		detailFormatter: function(rowIndex, rowData){
			return '<table><tr>' +
					'<td rowspan=2 style="border:0"><img src="' + rowData.realpicturepath + '" style="width:200px;"></td>' +
					'<td style="border:0">' +
					'<p>服务名: ' + rowData.servicename + '</p>' +
					'<p>价格: ' + rowData.price + '</p>' +
					'<p>会员价: ' + rowData.vipprice + '</p>' +
					'<p>图片名: ' + rowData.imagepath + '</p>' +
					'<p>备注: ' + rowData.remark + '</p>' +
					'</td>' +
					'</tr></table>';
		},
	});
})
function reloaddatagrid(){
	$("#datagrid").datagrid('reload');
	$("#datagrid").datagrid('loaded');
}