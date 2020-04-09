<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/bootStrapCss/bootstrap.min.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/layer/mobile/need/layer.css">
<script src="${pageContext.request.contextPath}/js/jquery-3.4.1.min.js"></script>
<script
	src="${pageContext.request.contextPath}/bootStrapJs/bootstrap.min.js"></script>
<script type="text/javascript">
	$(function() {
		$
				.ajax({
					type : "post",
					url : "${pageContext.request.contextPath }/player/queryByTeam.action",
					success:function(data){
						$.each(data, function (index, obj) {
		                	$("#select").append("<option>" + obj.player_effectiveTeam + "</option>");
		                })
					},
					error:function(data){
						alert("失败");
					}
				})
				return false;
	});
	 $(document).on("click", "#queryPlayerByTeam", function() {
		var player_effectiveTeam = $("#select option:selected").val();
		$.ajax({
			type : "post",
			url : "${pageContext.request.contextPath }/player/queryPlayerByTeam.action",
			data:{
				"player_effectiveTeam":player_effectiveTeam
			},
			success:function(data){
				var str="";
				if(data.length>0){
					$('#tab tr:gt(0)').remove();//删除之前的数据
				$.each(data, function (index,obj) {
					str = "<tr><td>" + obj.player_id + "</td><td>" + obj.player_Ename + "</td><td>"+
					obj.player_Cname +"</td><td>"+obj.player_goal+"</td><td>"+obj.player_assists+
					"</td><td>"+obj.player_interceptions+"</td><td>"+obj.player_number+
					"</td><td>"+obj.player_effectiveTeam+"</td></tr>";
					 $("#tab").append(str);  
					 window.location.href='${pageContext.request.contextPath }/player/outPartExcel.action';
                })
				}else{
					  $('#tab tr:gt(0)').remove();//删除之前的数据
				}
			}
			
		})
		return false;
	})
</script>
</head>
<body>
	<div style="height: 600px; width: 40%; border-right: solid 1px black;">
		<select class="form-control" id="select">
				<option>--选择需要导出的球队--</option>
		</select>
		<button type="button" class="btn btn-info" id="queryPlayerByTeam" style="float: right;margin-top: 10px;">查看并自动导出</button>
	</div>
	
	
	<div
		style="height: 600px; width: 60%; float: right; margin-top: -600px">
		<table  class="table table-hover table-bordered table-responsive" id="tab">
  <thead>
    <tr>
            <th scope="col">球员ID</th>
            <th scope="col">球员英文名</th>
            <th scope="col">球员中文名</th>
            <th scope="col">进球数</th>
            <th scope="col">助攻数</th>
            <th scope="col">拦截数</th>
            <th scope="col">球衣号码</th>
            <th scope="col">效力球队</th>
    </tr>
  </thead>
  <tbody id="tbody">
          <tr id="tr">
           
          </tr>
  </tbody>
</table>
	</div>
</body>
</html>