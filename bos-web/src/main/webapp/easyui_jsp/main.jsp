<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<link rel="stylesheet" type="text/css"  href="${pageContext.request.contextPath}/js/easyui/themes/default/easyui.css">
<link  rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/js/easyui/themes/icon.css">
<link  rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/js/easyui/demo.css">

<script type="text/javascript"  src="${pageContext.request.contextPath}/js/jquery-1.8.3.js"></script>
<script  type="text/javascript" src="${pageContext.request.contextPath}/js/easyui/jquery.easyui.min.js"></script>

  <link rel="stylesheet" href="${pageContext.request.contextPath}/js/ztree/zTreeStyle.css" type="text/css">
  <script type="text/javascript" src="${pageContext.request.contextPath}/js/ztree/jquery.ztree.all-3.5.js"></script>  
<title>Insert title here</title>
</head>
<body class="easyui-layout">

	<div data-options="region:'north',border:false" style="height:60px;background:#B3DFDA;padding:10px">北部区域</div>
	<div title="系统菜单" style="width:200px;" data-options="region:'west',split:true">
		<ul id="treeDemo" class="ztree"></ul>
	</div>
	<div  id="mycenter" data-options="region:'center',title:'Center'">
	
		<div id="tt" class="easyui-tabs">
			<div title="Home">
			</div>
		</div>
	
	</div>

	<div style="height: 50px" data-options="region:'south'">南部区域</div>	

</body>
  <SCRIPT LANGUAGE="JavaScript">

 
 
   // zTree 的数据属性，深入使用请参考 API 文档（zTreeNode 节点数据详解）
 /*  var zNodes = [
  {name:"客户管理", children:[
      {name:"客户列表"}, {name:"客户增加"}]},
   {name:"联系人管理", children:[
      {name:"联系人列表"}, {name:"添加联系人"}]}
   ];*/
   
   /*var treeNodes = [
	    {"id":1, "pId":0, "name":"客户管理"},
	    {"id":11, "pId":1, "name":"客户列表","page":"treeDemo_2"},
	    {"id":12, "pId":1, "name":"客户增加","page":"treeDemo_3"},
	    {"id":2, "pId":0, "name":"联系人管理"},
	    {"id":21, "pId":2, "name":"联系人列表","page":"treeDemo_5"},
	    {"id":22, "pId":2, "name":"添加联系人","page":"treeDemo_6"},
	];*/
   
  // var url_tree_id={treeDemo_2:"how are you!!"};
   //ztree初使化
   $(document).ready(function(){
	   //alert(url_tree_id.treeDemo_2)
	   var mywidth=$("#mycenter").outerWidth()-4;
	   
	   $("#tt").width(mywidth);
	   var winheight=$(window).height()-174;
	 	//alert(winheight)
	  
	   $(".tabs-panels").height(winheight);
	 	
	   var zTreeObj;
	   // zTree 的参数配置，深入使用请参考 API 文档（setting 配置详解）
	   var setting = {
			   data: {
					simpleData: {
						enable: true,
						idKey: "id",
						pIdKey: "pId",
						rootPId: 0
					}
				},
			   callback: {
					onClick: zTreeOnClick
				}
	   };
	 	var url="${pageContext.request.contextPath}/json/menu.json";
	 	$.post(url,{},function(data){
	 	      zTreeObj = $.fn.zTree.init($("#treeDemo"), setting, data);
	 	},"json")
   });
   
  
   
   //ztree点击 事件！！
   function zTreeOnClick(event, treeId, treeNode) {
	   //treeNode.tId+".jsp"
	
	    	if(treeNode.page==undefined){
	    		setting.callback.onClick=null;
	    	}else{
	    		 addTab(treeNode.name,treeNode.page)
	    		
	    	}
	    	//alert(treeNode.name+"/"+treeNode.tId);
	    
	   
	};
	//easyui点击事件！！
	function addTab(title, url){
		if ($('#tt').tabs('exists', title)){
			$('#tt').tabs('select', title);
		} else {
			var content = '<iframe scrolling="auto" frameborder="0"  src="'+url+'" style="width:100%;height:100%;"></iframe>';
			$('#tt').tabs('add',{
				title:title,
				content:content,
				closable:true
			});
		}
	}
  </SCRIPT>
</html>