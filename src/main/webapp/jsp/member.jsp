<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
 
<!DOCTYPE html>
<html>
<head>


<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/bootStrapCss/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/layer/mobile/need/layer.css">
<script src="${pageContext.request.contextPath}/js/jquery-3.4.1.min.js"></script>
<script src="${pageContext.request.contextPath}/bootStrapJs/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/layer/layer.js"></script>
<script type="text/javascript">
function imgChange(e) {
    var reader = new FileReader();
    reader.onload = (function (file) {
        return function (e) {
            $('.user-header').attr('src',this.result);
        };
    })(e.target.files[0]);
    reader.readAsDataURL(e.target.files[0]);
}

$('.user-header').click(function () {
    $("#file").click();
});
$(document).on("blur","#email",function(){
	var email=$("#email").val();
	$.ajax({
		url:"${pageContext.request.contextPath }/user/sendMail.action",
		type : "post",
		data:{
			"email":email
		}
		
	}); 
	return false;
	})
</script>
</head>
<body>
<h4 style="color: red;">请保证输入的邮箱的准确性，否则将接收不到激活码，导致升级失败！</h4> 
<form action="${pageContext.request.contextPath}/user/updateMember.action" method="post" enctype="multipart/form-data">
  <div class="form-group" >
    <label class="col-sm-2 control-label">用户账号</label>
    <div class="col-sm-10">
      <input class="form-control" id="user_phone" name="user_phone" type="text" value="${user.user_phone}"  disabled>
    </div>
  </div>
  <div class="form-group" >
    <label class="col-sm-2 control-label">用户密码</label>
    <div class="col-sm-10">
      <input class="form-control" id="user_password" name="user_password" type="text" value="${user.user_password}" disabled >
    </div>
  </div>
 
  
  <div class="form-group">
    <label for="inputEmail3" class="col-sm-2 control-label">真实姓名</label>
    <div class="col-sm-10">
      <input type="text" class="form-control" id="real_name" name="real_name" placeholder="真实姓名" >
    </div>
  </div>
  <div class="form-group">
    <label for="inputEmail3" class="col-sm-2 control-label">身份证号</label>
    <div class="col-sm-10">
      <input type="text" class="form-control" id="id_number" name="id_number" placeholder="身份证号" >
    </div>
  </div>
  <div class="form-group">
    <label for="inputEmail3" class="col-sm-2 control-label">邮箱</label>
    <div class="col-sm-10">
      <input type="email" class="form-control" id="email" name="email" placeholder="邮箱">
    </div>
  </div>
  <div class="form-group">
    <label for="inputEmail3" class="col-sm-2 control-label">请输入邮件中的激活码</label>
    <div class="col-sm-10">
      <input type="text" class="form-control" id="active_code" name="active_code" placeholder="输入激活码"><span style="color: red;">${errorMail}</span>
    </div>
  </div>
  <div class="form-group" style="margin-left: 10px;">
  <img alt="" src="" class="user-header" style="height: 100px;width: 100px;">
    <label for="exampleInputFile">上传照片</label>
    <input type="file" id="file" name="file" onchange="imgChange(event)" style="margin-top: 10px;">
  </div>
   <button type="submit" class="btn btn-default" style="margin-left: 600px;">点击升级</button>
</form>   
<p style="color: red;">${photoExist }</p>
</body>
</html>