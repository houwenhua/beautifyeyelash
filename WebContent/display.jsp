<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="js/jquery.min.js"></script>
    <title>服务展示</title>
    <style>
        *{
            margin:0;
            padding:0;
        }
        .header{
            width:100%;
            height: 100px;
            background: #6DC4D2;
            border-bottom:1px solid pink;
            box-sizing: border-box;
        }
        .headercenter{
            margin:0 auto;
            width: 90%;
            height: 100%;
            line-height: 100px;
            text-align: center;
        }
        .headercenter span{
            font-size: 30px;
            font-weight: bold;
            font-style: oblique;
        }
        .headercenter a{
            color: red;
            text-decoration: none;
            float: right;
        }
        .headercenter img{
            float: left;
            display: inline-block;
            height: 100%;
            width: 200px;
        }
        .main{
            width: 100%;
            height: 900px;
        }
        .center{
            margin:0 auto;
            width: 90%;
            height: 95%;
            background: #6DC4D2;
        }
        .nav{
            margin:0 auto;
            width: 90%;
            height: 5%;
          
        }
        .page{
            margin:0 auto;
            width: 50%;
            height: 100%;
           
        }
        ul{
            width: 100%;
            height: 100%;
            list-style: none;
            
        }
        ul li {
        	border:1px solid #6DC4D2;  
            vertical-align: middle;
            text-align: center;
            float: left;
            width: 16%;
            height: 100%;
            
            font-size: 30px;
            font-weight: bold;
            box-sizing: border-box; 
        }
        ul li:hover{
            background: #6DC4D2;
            cursor:pointer;
        }
        .box{
            margin-left:1%;
            float:left;
            width:32%;
            height: 32%;
            margin-bottom: 0%;
            border:1px solid #6DC4D2;
            box-sizing: border-box; 
        }
        .image{
            width: 100%;
            height: 50%;
        }
        .footer{
            width:100%;
            height: 100px;
            background: #6DC4D2;
        }
    </style>
</head>
<body>
<div id="header" class="header">
    <div class="headercenter">
        <img src="img/huazhuang.jpg">
        <span>3D美睫服务</span>
        <a href="index.jsp">登录</a>
    </div>
</div>
<div id="main" class="main">
    <div class="center"></div>
    <div class="nav">
        <div class="page">
            <ul>
                <li id="firstpage" onclick="firstpage()">&lt;&lt;</li>
                <li id="previouspage" onclick="previouspage()">&lt;</li>
                <li id="currentpage">0</li>
                <li id="nextpage" onclick="nextpage()">&gt;</li>
                <li id="lastpage" onclick="lastpage()">&gt;&gt;</li>
            </ul>
        </div>
    </div>
</div>
<div id="footer" class="footer" style="box-sizing: border-box;background:#6DC4D2;padding:10px;text-align:center;box-s">
	在公共场合使用完毕后请退出本系统!<br />
		重庆3D美睫管理系统 
</div>
</body>
<script>
    var json = [];
    var first = 1;
    var last = 1;
    $.ajax({
    	url:'service_queryDispaly.action',
    	type:'POST',
    	data:{
    		page:parseInt($("#currentpage").text())+1,
    	},
    	success:function(data){
    		json = eval('(' + data + ')');
    		last = Math.ceil(parseInt(json.total)/9);
    		$("#firstpage").text("1");
    		$("#lastpage").text(last);
    		$("#currentpage").text(json.page);
    		for(var i = 0; i < json.rows.length; i++){
    	        var html = "<div class='box'>"+
    	                    "<img src='http://localhost:8088/upload/"+json.rows[i].realpicturepath+"' class='image'>"+
    	                    "<p style='color:red'>服务名: "+json.rows[i].servicename+"</p>"+
    	                    "<p>原&nbsp;&nbsp;&nbsp;价: "+json.rows[i].price+"</p>"+
    	                    "<p>会员价: "+json.rows[i].vipprice+"</p><hr>"+
    	                    "<p>"+json.rows[i].remark+"</p>"+
    	                    "</div>";
    	        $(".center").append(html);
    	    }
    	}
    });
    //下一页
    function nextpage() {
    	 $.ajax({
    	    	url:'service_queryDispaly.action',
    	    	type:'POST',
    	    	data:{
    	    		page:(parseInt($("#currentpage").text())+1)>last?last:parseInt($("#currentpage").text())+1,
    	    	},
    	    	success:function(data){
    	    		$(".center").children().remove();
    	    		json = eval('(' + data + ')');
    	    		$("#currentpage").text(json.page);
    	    		for(var i = 0; i < json.rows.length; i++){
    	    	        var html = "<div class='box'>"+
    	    	                    "<img src='http://localhost:8088/upload/"+json.rows[i].realpicturepath+"' class='image'>"+
    	    	                    "<p style='color:red'>服务名: "+json.rows[i].servicename+"</p>"+
    	    	                    "<p>原&nbsp;&nbsp;&nbsp;价: "+json.rows[i].price+"</p>"+
    	    	                    "<p>会员价: "+json.rows[i].vipprice+"</p><hr>"+
    	    	                    "<p>"+json.rows[i].remark+"</p>"+
    	    	                    "</div>";
    	    	        $(".center").append(html);
    	    	    }
    	    	}
    	    });
    }
    //上一页
    function previouspage() {
   	 $.ajax({
   	    	url:'service_queryDispaly.action',
   	    	type:'POST',
   	    	data:{
   	    		page:(parseInt($("#currentpage").text())-1)<1?1:parseInt($("#currentpage").text())-1,
   	    	},
   	    	success:function(data){
   	    		$(".center").children().remove();
   	    		json = eval('(' + data + ')');
   	    		$("#currentpage").text(json.page);
   	    		for(var i = 0; i < json.rows.length; i++){
   	    	        var html = "<div class='box'>"+
   	    	                    "<img src='http://localhost:8088/upload/"+json.rows[i].realpicturepath+"' class='image'>"+
   	    	                    "<p style='color:red'>服务名: "+json.rows[i].servicename+"</p>"+
   	    	                    "<p>原&nbsp;&nbsp;&nbsp;价: "+json.rows[i].price+"</p>"+
   	    	                    "<p>会员价: "+json.rows[i].vipprice+"</p><hr>"+
   	    	                    "<p>"+json.rows[i].remark+"</p>"+
   	    	                    "</div>";
   	    	        $(".center").append(html);
   	    	    }
   	    	}
   	    });
   }
    //第一页
    function firstpage() {
      	 $.ajax({
      	    	url:'service_queryDispaly.action',
      	    	type:'POST',
      	    	data:{
      	    		page:first,
      	    	},
      	    	success:function(data){
      	    		$(".center").children().remove();
      	    		json = eval('(' + data + ')');
      	    		$("#currentpage").text(json.page);
      	    		for(var i = 0; i < json.rows.length; i++){
      	    	        var html = "<div class='box'>"+
      	    	                    "<img src='http://localhost:8088/upload/"+json.rows[i].realpicturepath+"' class='image'>"+
      	    	                    "<p style='color:red'>服务名: "+json.rows[i].servicename+"</p>"+
      	    	                    "<p>原&nbsp;&nbsp;&nbsp;价: "+json.rows[i].price+"</p>"+
      	    	                    "<p>会员价: "+json.rows[i].vipprice+"</p><hr>"+
      	    	                    "<p>"+json.rows[i].remark+"</p>"+
      	    	                    "</div>";
      	    	        $(".center").append(html);
      	    	    }
      	    	}
      	    });
      }
  //最后页
    function lastpage() {
      	 $.ajax({
      	    	url:'service_queryDispaly.action',
      	    	type:'POST',
      	    	data:{
      	    		page:last,
      	    	},
      	    	success:function(data){
      	    		$(".center").children().remove();
      	    		json = eval('(' + data + ')');
      	    		$("#currentpage").text(json.page);
      	    		for(var i = 0; i < json.rows.length; i++){
      	    	        var html = "<div class='box'>"+
      	    	                    "<img src='http://localhost:8088/upload/"+json.rows[i].realpicturepath+"' class='image'>"+
      	    	                    "<p style='color:red'>服务名: "+json.rows[i].servicename+"</p>"+
      	    	                    "<p>原&nbsp;&nbsp;&nbsp;价: "+json.rows[i].price+"</p>"+
      	    	                    "<p>会员价: "+json.rows[i].vipprice+"</p><hr>"+
      	    	                    "<p>"+json.rows[i].remark+"</p>"+
      	    	                    "</div>";
      	    	        $(".center").append(html);
      	    	    }
      	    	}
      	    });
      }
</script>
</html>