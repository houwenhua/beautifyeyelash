
function GetQueryString(name){
    var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if(r!=null)
   	 return  decodeURI(r[2]); 
    return null;
}

$(function(){

	var id = GetQueryString("id");
	obj = {
		editRow:undefined,
		search:function(){
			$("#datagrid").datagrid('load',{
				name:$.trim($("input[name='name']").val()),
				id:id,
			});
		},
		//添加一行
		add:function(){
			 var jq = top.jQuery;   
			 var content = "<iframe src='goodsadd.jsp?purid="+id+"' id='contextgoodsadd' name='contextgoodsadd' width='100%' height='99%' frameborder='0' scrolling='no'></iframe>";
		        parent.$('#ddmainadd').dialog({
		            content:content,
		            title: '采购详情',
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
		                    //iframe的跨域调用直接会报错，只能讲两个域
		                	//因为浏览器为了安全，禁止了不同域访问。因此只要调用与执行的双方是同域则可以相互访问。
                            //这里goodsadd.jsp和goods.jsp都在同一个域main下面的两个iframe中，所有在main中
		                	//写了个getGoodsAdd()调用goodsadd.jsp的方法，让后在goods.jsp中调用父窗口main中的方法
		                	parent.getGoodsAdd();
		               
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
				var url = 'goodsupdate.jsp?id='+rows[0].id;
				var content = '<iframe id="contextgoodsadd" name="contextgoodsadd" scrolling="auto" frameborder="0"  src="'+url+'" style="width:100%;height:100%;"></iframe>';     
				parent.$('#ddmainadd').dialog({
		            content:content,
		            title: '采购详情',
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
		                	parent.getGoodsAdd();
		                }
		            },{
		                text:'关闭',
		                iconCls:'icon-no',
		                handler:function(){
		                	parent.$('#ddmainadd').window('close');
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
							url:'goods_remove.action',
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
		url:'goods_paginationQuery.action',
		striped:true,
		rownumbers:true,
		fitColumns:true,
		queryParams: {
			id:id,
		},
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
				field:'brand',
				title:'品牌',
				width:100,
			},
			{
				field:'ginprice',
				title:'进价(元)',
				width:100,
				formatter: function(value,row,index){ 
					return (row["ginprice"]).toFixed(2);
				}
			},
			{
				field:'goutprivce',
				title:'售价(元)',
				width:100,
				formatter: function(value,row,index){ 
					return (row["goutprivce"]).toFixed(2);
				}
			},
			{
				field:'productiondate',
				title:'生产日期',
				width:100,
				
			},
			{
				field:'productiontotal',
				title:'采购数量',
				width:100,
			},
			{
				field:'supplier',
				title:'供货商家',
				width:100,
			},
			{
				field:'storagetime',
				title:'采购时间',
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
})
function reloaddatagrid(){
	$("#datagrid").datagrid('reload');
	$("#datagrid").datagrid('loaded');
}