<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-3.4.1.min.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/bootStrapCss/bootstrap.min.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.easyui.min.js"></script>
<script src="${pageContext.request.contextPath}/bootStrapJs/bootstrap.min.js"></script>
<script type="text/javascript">
	/*
	 * 验证码的切换
	 */
	window.onload = function() {
		var img = document.getElementById("change");
		img.onclick = function() {
			var date = new Date().getTime();
			img.src = "${pageContext.request.contextPath }/checking/code.action?" + date;
		}
	}
	/*
	 * 根据电话判断用户名是否存在
	 */
	function CheckUserPhone() {
		var user_phone = $("#user_phone").val();
		$.ajax({
					url : "${pageContext.request.contextPath }/user/findUserByPhone.action",
					type : "post",
					data : {
						"user_phone" : user_phone
					},
					success : function(data) {
						if (data == 1) {
							$("#pic1")[0].src = "/Ball/images/yes.png";
							$("#pic1").show();
						} else {
							$("#pic1")[0].src = "/Ball/images/no.png";
							$("#pic1").show();
						}
					}
				});
	}
	/*
	 * 根据密码判断用户是否存在
	 */
	function CheckUserPassword() {
		var user_password = $("#user_password").val();
		$.ajax({
					url : "${pageContext.request.contextPath }/user/findUserByPassword.action",
					type : "post",
					data : {
						"user_password" : user_password
					},
					success : function(data) {
						if (data == 1) {
							$("#pic2")[0].src = "/Ball/images/yes.png";
							$("#pic2").show();
						} else {
							$("#pic2")[0].src = "/Ball/images/no.png";
							$("#pic2").show();

						}
					}
				});
	} 
	/*
	注册时查找用户电话是否存在
	*/
	 function CheckUserPhoneExist() {
		var user_phone = $("#user_phone").val();
		//var regularPhone="^1[3|4|5|7|8][0-9]{9}$";
		$.ajax({
					url : "${pageContext.request.contextPath }/user/findUserByPhone.action",
					type : "post",
					data : {
						"user_phone" : user_phone
					},
					success : function(data) {
						if (data==1) {
							$("#span").html("<font color='red'>该号码已被注册</font>");
						} else {
							$("#span").html("<font color='green'>该号码名可用</font>");
						}
					}
				});
	} 
/*
 * 打开对话框
 */
 $(document).on("click","#userRegist",function(){
		$('#myModal').modal('show')
		})
		
		
		/*
		注册用户
		*/
		 $(document).on("click","#saveUser",function(){
			$.ajax({
				url:"${pageContext.request.contextPath }/user/userRegist.action",
				type : "post",
				data:$("#myModal form").serialize(),
				success:function(result){
					if(result==1){
						alert("注册成功！系统赠送您3000点券！");
						$('#myModal').modal('hide');
					}else{
						alert("注册失败");
					}
				}
			}); 
			}) 
			
</script>
<style type="text/css">
.pic1 {
	width: 30px;
	height: 30px;
	display: none;
}

.pic2 {
	width: 30px;
	height: 30px;
	display: none;
}
.bigdiv{
width:70%;
margin-left:300px;
height: 500px;
margin-top:50px; 
}
div{
padding: 10px;
}
.div1{
width: 78%;
}
.div2{
width: 82%;
}
input{
width: 80%;
height: 35px;
}
.span1{
position:absolute;
margin-left: 727px;
margin-top: -50px;
}
</style>
<title>Insert title here</title>
</head>
<body>

<div style="margin:20px auto;width:100px;height:100px;border-radius:100px;overflow:hidden">
			<img src="/Ball/images/realmardrid.jpg" style="margin:0;width:100%;height:100%;">
		</div>
<div class="bigdiv">
	<form action="${pageContext.request.contextPath }/user/login.action" method="post">
		<div>
		<input type="text" name="user_phone" id="user_phone" onblur="CheckUserPhone()" value="请输入电话号" onfocus="if(value=='请输入电话号')value=''" onblur="if(!value)value='请输入电话号'"> 
		<img src="" id="pic1" class="pic1"><br>
		</div>
		<div>
		<input type="text" name="user_password" id="user_password" onblur="CheckUserPassword()" value="请输入密码" onfocus="if(value=='请输入密码')value=''" onblur="if(!value)value='请输入密码'"> <img src="" id="pic2" class="pic2"><br>
		</div>
		<div class="div1">
		<input type="text" name="code" value="请输入验证码"  onfocus="if(value=='请输入验证码') value=''" onblur="if(!value)value='请输入验证码'">
		</div>
		<span class="span1">
		 <img src="${pageContext.request.contextPath }/checking/code.action" id="change" class="yanzhengma"><br>
			</span>
			<h3 style="color: red"> ${msg}</h3>
			<div class="div2">
		 <input type="submit" value="点击登录">
		<a href="#" id="userRegist">没有账号？去注册</a>
	</div>
	</form>
	<!-- Modal -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">Modal title</h4>
      </div>
      <div class="modal-body">
        <form class="form-horizontal">
  <div class="form-group">
    <label for="inputEmail3" class="col-sm-2 control-label">Phone</label>
    <div class="col-sm-10">
      <input type="text" name="user_phone" class="form-control" id="inputEmail3" placeholder="Phone" onblur="CheckUserPhoneExist()"><span id="span"></span><br>
      <span id="helpBlock2" class="help-block"></span>
    </div>
  </div>
  <div class="form-group">
    <label for="inputPassword3" class="col-sm-2 control-label">Password</label>
    <div class="col-sm-10">
      <input type="text" name="user_password" class="form-control" id="inputPassword3" placeholder="Password">
    </div>
  </div>
  
</form>

      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        <button type="button" class="btn btn-primary" id="saveUser">Save changes</button>
      </div>
    </div>
  </div>
</div>
</div>
</body>
</html>