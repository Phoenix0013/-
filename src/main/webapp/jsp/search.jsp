<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/bootStrapCss/bootstrap.min.css">
<script src="${pageContext.request.contextPath}/js/jquery-3.4.1.min.js"></script>
<script src="${pageContext.request.contextPath}/bootStrapJs/bootstrap.min.js"></script>
<script type="text/javascript">
/*
键盘弹起搜索球员
*/
	$(function(){
		$("#search1").keyup(function(){
			var player_Ename=$("#search1").val();
			if(player_Ename==""){
				$("#search3").hide();
			}else{
				$.post("${pageContext.request.contextPath }/player/dimQueryPlayer.action",{player_Ename:player_Ename},function(data,status){
					$("#search3").show();
					$("#search3").html(data);
				});
			}
			
		})
		
	})
</script>
<style type="text/css">
.search{
position: absolute;
margin-left: 250px;
margin-top: 50px;
}
.search input{
height: 45px;
}
#search1{
width: 500px;
margin-left: 20px;
}
#search2{
width: 160px;
height: 45px;
}
.search label{
margin-left: 50px;
}
#search3{
font-size: 20px;
color: black;
}
</style>
</head>
<body>
<div class="search">
<form class="form-inline" id="searchForm">
  <div class="form-group">
    <label for="exampleInputName2">搜索</label>
    <input type="text" class="form-control" id="search1" placeholder="输入球员的英文名进行搜索" name="player_Ename">
  </div>
  <div id="search3" style="width:1300px;height: 228px;margin-left:-200px;margin-top:50px;display: none">
  
  </div>

</form>
 </div>
 
</body>
</html>