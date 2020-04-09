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
/*
 * 页面加载时执行异步查询所有球员的方法
 */
 $(document).ready(function(){
	$.ajax({
	type: "post",
	url: "${pageContext.request.contextPath }/player/queryAllPlayer.action",
	async: true//异步or同步
	});
}) 
/*
 * 删除用户
 */
function deleteUser(player_id) {
	if(window.confirm("确定删除"+player_id+"球员吗")){
	$.ajax({
		url:"${pageContext.request.contextPath }/player/deletePlayer.action",
		type : "post",
		data:{
			"player_id":player_id
		},
		success:function(result){
			if(result==1){
				alert("删除成功");
				window.location.href='${pageContext.request.contextPath }/player/queryAllPlayer.action';
			}else{
				alert("删除失败！您的权限不足，只能查看相关信息");
			}
		}
	}); 
	}
}
/*
 * 插入球员的ajax方法
 */
 $(document).on("click","#savePlayer",function(){
		
		$.ajax({
			url:"${pageContext.request.contextPath }/player/insertPlayer.action",
			type : "post",
			data:$("#myModal form").serialize(),
			success:function(result){
				if(result==1){
					alert("添加成功");
					$('#myModal').modal('hide')
					window.location.href='${pageContext.request.contextPath }/player/queryAllPlayer.action';
				}else{
					alert("插入失败！您的权限不足，只具有查询的权限！");
				}
			}
			
		}); 
		})
		/*  打开修改球员的模态框*/
		 $(document).on("click","#update",function(){
		$('#myModal01').modal('show')
		})
		
		
		/* 
		修改球员
		*/
			 $(document).on("click","#upPlayer",function(){
					$.ajax({
						url:"${pageContext.request.contextPath }/player/updatePlayer.action",
						type : "post",
						data:$("#information").serialize(),
						success:function(result){
							if(result==1){
								alert("更新成功");
								$('#myModal01').modal('hide');
								window.location.href='${pageContext.request.contextPath }/player/queryAllPlayer.action';
							}else{
								alert("更新失败！您的权限不足，只具有查询的权限！");
							}
						}
					}); 
					}) 
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
				/*
				完成chekbox的全选全不选
				*/
				 $(document).on("click","#check_all",function(){
					$(".check_one").prop("checked",$(this).prop("checked"))
					}) 
				/*
				当子checkbox全部选中时，父checkbox同样选中
				*/
				 $(document).on("click",".check_one",function(){
					var flag=$(".check_one:checked").length==$(".check_one").length;
						$("#check_all").prop("checked",flag);
						}) 
			    /*
			    给批量删除按钮绑定单击事件
			    */
				 $(document).on("click","#deleteAll",function(){
					  var id="";
						$.each($(".check_one:checked"),function(){
							id+=$(this).parents("tr").find("td:eq(1)").text()+",";
						});
						id=id.substring(0,id.length-1);
						if(confirm("确认删除【"+id+"】球员吗？删除后信息将全部丢失，请谨慎删除！")){
							//发送ajax请求批量删除
							$.ajax({
								url:"${pageContext.request.contextPath }/player/deleteAll.action?id="+id,
								type : "delete",
								success:function(result){
									if(result==1){
										alert("批量删除成功！");
										window.location.href='${pageContext.request.contextPath }/player/queryAllPlayer.action';
									}else{
										alert("批量删除失败！您的权限不足！");
									}
								}
							})
						}
							});
    /*
	导出excel文件的方法
	*/			
	$(document).on("click","#outExcel",function(){
 			layer.confirm('确定导出所有球员信息吗?',{btn: ['是','否'] }, function(index){
 				window.location.href="${pageContext.request.contextPath }/player/outExcel.action?name="+"information";
 				layer.close(index);
 			});
 			return false;	
 			});	
</script>
</head>
<body>
  
<button type="button" class="btn btn-warning" style="float: right; height: 47px" id="outExcel">导出Excel文件</button>

<!-- Indicates a dangerous or potentially negative action -->
<button type="button" class="btn btn-danger" style="float: right; height: 47px;width:130px; margin-right: 10px;" id="deleteAll">批量删除</button>

<!-- Button trigger modal -->
<button type="button" style="float: right; margin-right: 10px;width: 130px;font-size: 15px;height: 47px;" class="btn btn-primary btn-lg" data-toggle="modal" data-target="#myModal" id="bu">
  新增球员
</button>
<table  class="table table-hover table-bordered table-responsive">
  <thead>
    <tr>
    <th><input type="checkbox" id="check_all"></th>
           
            <th scope="col">球员ID</th>
            <th scope="col">球员英文名</th>
            <th scope="col">球员中文名</th>
            <th scope="col">进球数</th>
            <th scope="col">助攻数</th>
            <th scope="col">拦截数</th>
            <th scope="col">球衣号码</th>
            <th scope="col">效力球队</th>
            <th scope="col">管理</th>
    </tr>
  </thead>
  <tbody>
    <c:forEach items="${pageInfo.list}" var="play">
          <tr>
          <td><input type="checkbox" class="check_one"> </td>
            <td>${play.player_id}</td>
            <td>${play.player_Ename}</td>
            <td>${play.player_Cname}</td>
            <td>${play.player_goal}</td>
            <td>${play.player_assists}</td>
            <td>${play.player_interceptions}</td>
            <td>${play.player_number}</td>
            <td>${play.player_effectiveTeam}</td>
            <td><a href="javascript:void(0);" onclick="deleteUser(${play.player_id})" id="delete">删除</a></td>
            <td><a href="javascript:void(0);" id="update">修改</a></td>
          </tr>
          </c:forEach>
  </tbody>
</table>
<nav aria-label="Page navigation example" style="float: right;">
  <ul class="pagination justify-content-end">
    <li class="page-item disabled">
    <a class="page-link" href="#" tabindex="-1" aria-disabled="true">当前 ${pageInfo.pageNum }页</a>
    <a class="page-link" href="#" tabindex="-1" aria-disabled="true">共${pageInfo.pages }页</a>
      <a class="page-link" href="#" tabindex="-1" aria-disabled="true">共 ${pageInfo.total } 条记录</a>
    </li>
     <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath }/player/queryAllPlayer.action?pageNo=${pageInfo.navigateFirstPage}">首页</a></li>
    <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath }/player/queryAllPlayer.action?pageNo=${pageInfo.pageNum-1}">上一页</a></li>
    <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath }/player/queryAllPlayer.action?pageNo=${pageInfo.pageNum+1}">下一页</a></li>
    <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath }/player/queryAllPlayer.action?pageNo=${pageInfo.navigateLastPage}">尾页</a></li>
  </ul>
</nav>
<!-- Modal -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">添加球员</h4>
      </div>
      <div class="modal-body">
       <form class="form-horizontal">
       
  <div class="form-group">
    <label for="inputEmail3" class="col-sm-2 control-label">英文名称</label>
    <div class="col-sm-10">
      <input name="player_Ename" type="text" class="form-control" id="inputEmail3" placeholder="ename">
    </div>
  </div>
  <div class="form-group">
    <label for="inputEmail3" class="col-sm-2 control-label">中文名称</label>
    <div class="col-sm-10">
      <input type="text" name="player_Cname" class="form-control" id="inputEmail3" placeholder="cname">
    </div>
  </div>
<div class="form-group">
    <label for="inputEmail3" class="col-sm-2 control-label">进球数</label>
    <div class="col-sm-10">
      <input type="text" name="player_goal" class="form-control" id="inputEmail3" placeholder="goal">
    </div>
  </div>
  <div class="form-group">
    <label for="inputEmail3" class="col-sm-2 control-label">助攻数</label>
    <div class="col-sm-10">
      <input type="text" name="player_assists" class="form-control" id="inputEmail3" placeholder="assist">
    </div>
  </div>
  <div class="form-group">
    <label for="inputEmail3" class="col-sm-2 control-label">拦截数</label>
    <div class="col-sm-10">
      <input type="text" name="player_interceptions" class="form-control" id="inputEmail3" placeholder="interceptor">
    </div>
  </div>
  <div class="form-group">
    <label for="inputEmail3" class="col-sm-2 control-label">球员编号</label>
    <div class="col-sm-10">
      <input type="text" name="player_number" class="form-control" id="inputEmail3" placeholder="number">
    </div>
  </div>
  <div class="form-group">
    <label for="inputEmail3" class="col-sm-2 control-label">效力球队</label>
    <div class="col-sm-10">
      <input type="text" name="player_effectiveTeam" class="form-control" id="inputEmail3" placeholder="team">
    </div>
  </div>
</form>
      </div>
      <div class="modal-footer">
      <button type="button" class="btn btn-primary" id="savePlayer">Save changes</button>
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        
      </div>
    </div>
  </div>
</div>
  
  <!-- 修改球员的模态框 -->
  <!-- Modal -->
<div class="modal fade" id="myModal01" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">修改球员信息</h4>
      </div>
      <div class="modal-body">
       <form class="form-horizontal" id="information">
        <div class="form-group">
    <label for="inputEmail3" class="col-sm-2 control-label">要修改球员id</label>
    <div class="col-sm-10">
      <input name="player_id"  class="form-control" id="inputEmail3" placeholder="ename">
    </div>
  </div> 
  <div class="form-group">
    <label for="inputEmail3" class="col-sm-2 control-label">英文名称</label>
    <div class="col-sm-10">
      <input name="player_Ename" type="text" class="form-control" id="player_Ename" placeholder="ename">
    </div>
  </div>
  <div class="form-group">
    <label for="inputEmail3" class="col-sm-2 control-label">中文名称</label>
    <div class="col-sm-10">
      <input type="text" name="player_Cname" class="form-control" id="player_Cname" placeholder="cname">
    </div>
  </div>
<div class="form-group">
    <label for="inputEmail3" class="col-sm-2 control-label">进球数</label>
    <div class="col-sm-10">
      <input type="text" name="player_goal" class="form-control" id="player_goal" placeholder="goal">
    </div>
  </div>
  <div class="form-group">
    <label for="inputEmail3" class="col-sm-2 control-label">助攻数</label>
    <div class="col-sm-10">
      <input type="text" name="player_assists" class="form-control" id="player_assists" placeholder="assist">
    </div>
  </div>
  <div class="form-group">
    <label for="inputEmail3" class="col-sm-2 control-label">拦截数</label>
    <div class="col-sm-10">
      <input type="text" name="player_interceptions" class="form-control" id="player_interceptions" placeholder="interceptor">
    </div>
  </div>
  <div class="form-group">
    <label for="inputEmail3" class="col-sm-2 control-label">球员编号</label>
    <div class="col-sm-10">
      <input type="text" name="player_number" class="form-control" id="player_number" placeholder="number">
    </div>
  </div>
  <div class="form-group">
    <label for="inputEmail3" class="col-sm-2 control-label">效力球队</label>
    <div class="col-sm-10">
      <input type="text" name="player_effectiveTeam" class="form-control" id="player_effectiveTeam" placeholder="team">
    </div>
  </div>
</form>
      </div>
      <div class="modal-footer">
      <button type="button" class="btn btn-primary" id="upPlayer">Save changes</button>
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        
      </div>
    </div>
  </div>
</div>
</body>
</html>