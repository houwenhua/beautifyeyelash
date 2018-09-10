
$(function(){
	
	obj = {
		editRow:undefined,
		search:function(){
			if($("input[name='startdate']").val() == "" || $("input[name='enddate']").val() == ""){
				$.messager.alert('警告','请选择日期','warning');
			} else {
				$("#datagrid").datagrid('load',{
					startdate:$.trim($("input[name='startdate']").val()),
					enddate:$.trim($("input[name='enddate']").val()),
				});
			}
		},
		//添加一行
		add:function(){
			 var jq = top.jQuery;   
			 var content = "<iframe src='goodsrecordadd.jsp' id='goodsrecordadd' name='goodsrecordadd' width='100%' height='99%' frameborder='0' scrolling='no'></iframe>";
		        parent.$('#ddmainadd').dialog({
		            content:content,
		            title: '消费记录',
		            width: $(window).width() - 10,
		            height: $(window).height() - 10,
		            closed: false,
		            cache: false,
		            modal: true,
		            resizable:true,
		            buttons:[{
		                text:'保存',
		                iconCls:'icon-ok',
		                handler:function(){
		                  parent.getGoodsRecordAdd();
		                }
		            },{
		                text:'关闭',
		                iconCls:'icon-no',
		                handler:function(){
		                	parent.$('#ddmainadd').window('close');
		                }
		            }],
		        });
		},
		edit:function(){
			var rows = $("#datagrid").datagrid('getSelections');
			if(rows.length == 1){
				var jq = top.jQuery;    
				var url = 'goodsrecorddetail.jsp?id='+rows[0].id;
				var content = '<iframe id="goodsrecorddetail" name="goodsrecorddetail" scrolling="auto" frameborder="0"  src="'+url+'" style="width:100%;height:100%;"></iframe>';     
				parent.$('#ddmainadd').dialog({
		            content:content,
		            title: '记录详情',
		            width: $(window).width() - 10,
		            height: $(window).height() - 10,
		            closed: false,
		            cache: false,
		            modal: true,
		            resizable:true,
		            buttons:[{
		                text:'关闭',
		                iconCls:'icon-no',
		                handler:function(){
		                	parent.$('#ddmainadd').window('close');
		                }
		            }],
		        });  
			}else{
				$.messager.alert('警告','必须选中一行且只能选中一行','warning');
			}
			
		},
		update:function(){
			var rows = $("#datagrid").datagrid('getSelections');
			if(rows.length == 1){
				var jq = top.jQuery;    
				var url = 'goodsrecordupdate.jsp?id='+rows[0].id;
				var content = '<iframe id="context" name="context" scrolling="auto" frameborder="0"  src="'+url+'" style="width:100%;height:100%;"></iframe>';     
				$('#dd').dialog({
		            content:content,
		            title: '修改',
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
				$.messager.alert('警告','必须选中一行且只能选中一行','warning');
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
							url:'goodsRecord_remove.action',
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
		
	};
	
	$("#datagrid").datagrid({
		width:700,
		fit:true,
		url:'goodsRecord_paginationQuery.action',
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
				field:'name',
				title:'商品名',
				width:100,
			},
			{
				field:'grmoney',
				title:'消费金额(元)',
				width:100,
				formatter: function(value,row,index){ 
					return (row["grmoney"]).toFixed(2);
				}
			},
			{
				field:'grdate',
				title:'消费日期',
				width:100,
				
			},
			{
				field:'waitername',
				title:'收费人',
				width:100,
				
			},
			{
				field:'reallmoney',
				title:'实际收费',
				width:100,
				
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
		}
	});
});
function reloaddatagrid(){
	$("#datagrid").datagrid('reload');
	$("#datagrid").datagrid('loaded');
}