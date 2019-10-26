<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">

<link rel="stylesheet"  href="${pageContext.request.contextPath}/js/layui/css/layui.css">
<script src="${pageContext.request.contextPath}/js/layui/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/js/layui/layui.js"></script>
<title>Insert title here</title>

</head>
<body  style="background:#2F4056;margin:0px;padding:0px;">	
<div class="layui-container">  

  <div class="layui-row">
    <div class="layui-col-md6 layui-col-md-offset3" style="background:#1E9FFF;margin-top:30px;padding-top:50px;
    padding-right:50px;padding-bottom:20px;box-shadow:10px 10px 10px #eee;border-radius:20px;
    ">
    <div class="layui-col-md12">
    	  <h2 style="margin-left:45%;margin-bottom:20px;font-size:40px;text-shadow:5px 5px 5px #aaa;">用户注册</h2>
    </div>
  
		<form class="layui-form" action="userAction_regist.action" method="post">
		  <div class="layui-form-item">
		    <label class="layui-form-label">用户名</label>
		    <div class="layui-input-block">
		      <input type="text" name="username" required  lay-verify="required" placeholder="请输入用户名" autocomplete="off" class="layui-input">
		    </div>
		  </div>
		   <div class="layui-form-item">
		    <label class="layui-form-label">密码</label>
		    <div class="layui-input-block">
		      <input type="password" name="password" required  lay-verify="required" placeholder="请输入密码" autocomplete="off" class="layui-input">
		    </div>
		  </div>	  
		  <div class="layui-form-item">
		    <label class="layui-form-label">薪水</label>
		    <div class="layui-input-block">
		      <input type="text" name="salary" required  lay-verify="required|number" placeholder="请输入薪水" autocomplete="off" class="layui-input">
		    </div>
		  </div>
		   <div class="layui-form-item">
		     <label class="layui-form-label">生日</label>
		  	<div class="layui-inline"> <!-- 注意：这一层元素并不是必须的 -->
			  <input type="text" class="layui-input" id="test1" name="birthday" required  lay-verify="required" >
			</div>
			</div>
		  
		   <div class="layui-form-item">
			    <label class="layui-form-label">性别：</label>
			    <div class="layui-input-block">
			      <input type="radio" name="gender" value="男" title="男">
			      <input type="radio" name="gender" value="女" title="女" checked>
			    </div>
			 </div>
			  
			    <div class="layui-form-item">
				    <label class="layui-form-label">职位：</label>
				    <div class="layui-input-block">
				      <select name="station" lay-verify="required">
				        <option value=""></option>
				        <option value="老板">老板</option>
				        <option value="总经理">总经理</option>
				        <option value="主管">主管</option>
				        <option value="小组长">小组长</option>
				        <option value="员工">员工</option>
				      </select>
				    </div>
				 </div>	  
		   <div class="layui-form-item">
		    <label class="layui-form-label">电话</label>
		    <div class="layui-input-block">
		      <input type="text" name="telephone" required  lay-verify="required|phone" placeholder="请输入电话" autocomplete="off" class="layui-input">
		    </div>
		  </div>
		<div class="layui-form-item">
		    <label class="layui-form-label">标记</label>
		    <div class="layui-input-block">
		      <input type="checkbox" name="remark" title="是否标记">
		    </div>
		  </div>
		  <div class="layui-form-item">
		    <div class="layui-input-block">
		      <button class="layui-btn" lay-submit lay-filter="formDemo">立即提交</button>
		      <button type="reset" class="layui-btn layui-btn-primary">重置</button>
		    </div>
		  </div>
	</form>
  </div>
</div>
</div>
	
	<script>
	layui.use('laydate', function(){
		  var laydate = layui.laydate;
		  //执行一个laydate实例
		  laydate.render({
		    elem: '#test1' //指定元素
		  });
		});
	
	layui.use('form', function(){
		  var form = layui.form;
		  
		  //监听提交
		  form.on('submit(formDemo)', function(data){
			 
		    layer.msg(JSON.stringify(data.field));
		   
		  });
		});
	
	</script>
</body>
</html>