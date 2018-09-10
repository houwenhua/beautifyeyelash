<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script  type="text/javascript" src="js/ichart.1.2.min.js"></script>
<script type="text/javascript" src="js/jquery.min.js"></script>
</head>
<body>
<div id="canvasDiv"></div>
<button onclick="clickOne();">消费百分比</button>
<button onclick="clickTwo();">上月消费记录</button>
</body>
</html>
<script type="text/javascript">
$(function(){	
	$.ajax({
		type:'post',
		url:'statistics_queryGoodsNumber.action',
		dataType: 'json',
		success: function(data) {
			var chart = new iChart.Column2D({
				render : 'canvasDiv',//渲染的Dom目标,canvasDiv为Dom的ID
				data: data,//绑定数据
				title : '上个月商品销售统计图',//设置标题
				width : 800,//设置宽度，默认单位为px
				height : 400,//设置高度，默认单位为px
				shadow:true,//激活阴影
				shadow_color:'#c7c7c7',//设置阴影颜色
				coordinate:{//配置自定义坐标轴
					scale:[{//配置自定义值轴
						 position:'left',//配置左值轴	
						 start_scale:0,//设置开始刻度为0
						 end_scale:100,//设置结束刻度为50
						 scale_space:5,//设置刻度间距
						 listeners:{//配置事件
							parseText:function(t,x,y){//设置解析值轴文本
								return {text:t+""}
							}
						}
					}]
				}
			});
			//调用绘图方法开始绘图
			chart.draw();
		}
	});
});
 
function clickOne() {
	$.ajax({
		type:'post',
		url:'statistics_queryGoodsAll.action',
		dataType: 'json',
		success: function(data) {
			new iChart.Column2D({
				render : 'canvasDiv',
				data: data,
				title : {
					text:'记录总百分比',
					color:'#b5bcc5'
				},
				subtitle : {
					text:'包含启动该软件以来的所有商品消费记录',
					color:'#afb6c0'
				},
				footnote : '来商品记录',
				width : 800,
				height : 400,
				coordinate:{
					offsetx:20,
					width:620,
					height:260,
					grid_color:'#d03737',
					axis:{
						color:'#4e5464',
						width:[0,0,8,8]
					},
					scale:[{
						 position:'left',	
						 start_scale:0,
						 end_scale:40,
						 scale_space:5,
						 label:{color:'#ffffff'},
						 listeners:{
							parseText:function(t,x,y){
								return {text:t+"%"}
							}
						 }
					}]
				},
				label:{color:'#dcdcdc'},
				background_color : '#3c4251',
				sub_option:{
					listeners:{
						parseText:function(r,t){
							return t+"%";
						}
					}
				},
				tip:{enable:true},
				legend:{enable:false}
			}).draw();
		}
	});
}
	
function clickTwo(){
	 $.ajax({
			type:'post',
			url:'statistics_queryGoodsNumber.action',
			dataType: 'json',
			success: function(data) {
				var chart = new iChart.Column2D({
					render : 'canvasDiv',//渲染的Dom目标,canvasDiv为Dom的ID
					data: data,//绑定数据
					title : '上个月商品销售统计图',//设置标题
					width : 800,//设置宽度，默认单位为px
					height : 400,//设置高度，默认单位为px
					shadow:true,//激活阴影
					shadow_color:'#c7c7c7',//设置阴影颜色
					coordinate:{//配置自定义坐标轴
						scale:[{//配置自定义值轴
							 position:'left',//配置左值轴	
							 start_scale:0,//设置开始刻度为0
							 end_scale:100,//设置结束刻度为50
							 scale_space:5,//设置刻度间距
							 listeners:{//配置事件
								parseText:function(t,x,y){//设置解析值轴文本
									return {text:t+""}
								}
							}
						}]
					}
				});
				//调用绘图方法开始绘图
				chart.draw();
			}
		});
 }
</script>