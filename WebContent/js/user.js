$(function() {
	obj = {
		editRow : undefined,
		search : function() {
			$("#datagrid").datagrid('load', {
				name:$.trim($("input[name='name']").val()),
			});
		},
		// 添加一行
		add : function() {
			var content = "<iframe src='useradd.jsp' id='context' name='context' width='100%' height='99%' frameborder='0' scrolling='no'></iframe>";
			$('#dd').dialog({
								content : content,
								title : '会员',
								width : 600,
								height : 400,
								closed : false,
								cache : false,
								modal : true,
								resizable : true,
								buttons : [{
											text : '保存',
											iconCls : 'icon-ok',
											handler : function() {
												// 点击得到提交表单方法
												document
														.getElementById("context").contentWindow
														.submitForm();

											}
										}, {
											text : '关闭',
											iconCls : 'icon-no',
											handler : function() {
												$('#dd').window('close');
											}
										}],
							});
		},
		edit : function() {
			var rows = $("#datagrid").datagrid('getSelections');
			if (rows.length == 1) {
				var jq = top.jQuery;
				var url = 'userupdate.jsp?id=' + rows[0].id;
				var content = '<iframe id="context" name="context" scrolling="auto" frameborder="0"  src="'+url+'" style="width:100%;height:100%;"></iframe>';     
            	$('#dd').dialog({
		            content:content,
		            title: '会员',
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
			} else {
				$.messager.alert('警告', '修改必须选中一行且只能选中一行', 'warning');
			}

		},
		remove : function() {
			var rows = $("#datagrid").datagrid('getSelections');
			if (rows.length > 0) {
				$.messager.confirm('提示', '您确定删除这些记录吗？', function(flag) {
					if (flag) {
						var ids = [];
						for (var i = 0; i < rows.length; i++) {
							ids.push(rows[i].id);
						}
						$.ajax({
							type : 'post',
							url : 'user_remove.action',
							data : {
								ids : ids.join(','),
							},
							beforeSend : function() {
								$("#datagrid").datagrid('loading');
							},
							success : function(data) {
								var data = jQuery.parseJSON(data);
								if (data.success) {
									$("#datagrid").datagrid('reload');
									$("#datagrid").datagrid('loaded');
									$("#datagrid").datagrid('unselectAll');
									$.messager.show({
										title : '提示',
										msg : data.msg,
									});
								} else {
									$.messager.alert('提示', '删除失败', 'error',
											function() {
												$("#datagrid").datagrid(
														'loaded');
											});
								}
							}
						});
					}
				});
			} else {
				$.messager.alert('提示', '请选择你要删除的记录', 'info');
			}
		}

	};

	$("#datagrid").datagrid({
		width : 700,
		fit : true,
		url : 'user_paginationQuery.action',
		title : '用户基本信息',
		striped : true,
		rownumbers : true,
		fitColumns : true,
		columns : [ [ {
			field : 'id',
			title : '编号',
			width : 100,
			checkbox : true,
		}, {
			field : 'name',
			title : '用户名',
			width : 100,
		}, {
			field : 'loginname',
			title : '登录名',
			width : 100,
		}, {
			field : 'password',
			title : '密码',
			width : 100,
		}, {
			field : 'email',
			title : '邮箱',
			width : 100,
		}, {
			field : 'jurisdiction',
			title : '权限',
			width : 100,
		}, {
			field : 'store',
			title : '所属店面',
			width : 100,
		}, 
		]],
		toolbar : '#tb',
		pagination : true,
		pagePosition : 'bottom',
		onRowContextMenu : function(e, rowIndex, rowData) {
			e.preventDefault();
			$("#menu").menu('show', {
				top : e.pageY,
				left : e.pageX,
			})
		}
	});
})
function reloadDatagridUpdata() {
	$("#datagrid").datagrid('reload');
	$("#datagrid").datagrid('loaded');
}