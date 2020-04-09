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
$(function(){
	$.ajax({
		url:"${pageContext.request.contextPath }/schedule/Laliga.action",
		type : "post"
	}); 
})
		
$(document).on("click","#laji",function(){
	$('#myModal').modal('show');
	var schedule_id=$(this).parents("tr").find("td:eq(0)").text();
	$.ajax({
		url:"${pageContext.request.contextPath }/schedule/queryScheduleById.action",
		type:"post",
		dataType:"json",
		data:{
			"schedule_id":schedule_id
		},
		success:function(data){
         $("#home_team").val(data.home_team);
         $("#awayhome_team").val(data.awayhome_team);
         //$("#win_team").append("<option>" + data.home_team + "</option>");
         //$("#win_team").append("<option>" + data.awayhome_team+ "</option>");
         $("#op1").html(data.home_team);
         $("#op2").html(data.awayhome_team);
		}
	})
	return false;
	})
		
		 $(document).on("click","#createOrder",function(){
			 var win_team=$("#win_team").val();
			 var order_money=$("#order_money").val();
		$.ajax({
			url:"${pageContext.request.contextPath }/ord/create.action",
			type : "post",
			data:{
				"win_team":win_team,
				"order_money":order_money
			},
			success:function(result){
				
				 if(result==1){
					
					alert("已经成功下单！继续竞猜其它赛事！");
					$('#myModal').modal('hide');
					document.location.reload();
				}else{
					alert("下单失败");
				} 
			}
		})
		})
		
</script>
<style type="text/css">
.imgvs{
width: 150px;
height: 50px;
}
table{
text-align: center;
font-size: 20px;
}
table td{
height: 80px;
}
</style>
</head>
<body>
<div style="color: blue; float: right; font-size: 20px;">赌球一时爽 一直赌球一直爽<img src="/Ball/images/bad.jpg" style="width: 100px;height: 80px"></div>
<table  class="table table-hover  table-responsive" id="table">

  <tbody>
    <c:forEach items="${listLaliga}" var="list">
          <tr>
            <td style="display: none;">${list.schedule_id}</td>
            <td>${list.home_team}</td>
             <td><img src="/Ball/images/vs.jpg" class="imgvs"></td>
            <td>${list.awayhome_team}</td>
            <td>${list.match_time}</td>
           <td><a href="#"  id="laji">足球竞彩</a></td>
           <td><a href="${pageContext.request.contextPath }/jsp/message.jsp?schedule_id=${list.schedule_id}">评论</a></td>
          </tr>
          </c:forEach>
  </tbody>
  </table>
  <nav aria-label="Page navigation example" style="float: right;">
  <ul class="pagination justify-content-end">
    
    <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath }/schedule/Laliga.action?pageNo=${schedule.pageNum-1}">上一轮</a></li>
    <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath }/schedule/Laliga.action?pageNo=${schedule.pageNum+1}">下一轮</a></li>
  </ul>
</nav>

<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">欢迎来到足球竞彩 请谨慎操作！</h4>
      </div>
      <div class="modal-body">
       <form>
  <div class="form-group row">
    <label for="staticEmail" class="col-sm-2 col-form-label">主队：</label>
    <div class="col-sm-10">
      <input type="text" readonly class="form-control-plaintext" id="home_team">
    </div>
  </div>
  <div class="form-group row">
    <label for="staticEmail" class="col-sm-2 col-form-label">客队：</label>
    <div class="col-sm-10">
      <input type="text" readonly class="form-control-plaintext" id="awayhome_team">
    </div>
  </div>
 选择支持的球队：<select class="form-control" name="win_team" id="win_team">
  <option>--选择球队--</option>
  <option id="op1"></option>
   <option id="op2"></option>
</select>
选择竞猜金额：
<select class="form-control" name="order_money" id="order_money">
<option>--选择金额--</option>
  <option>1000</option>
  <option>2000</option>
  <option>3000</option>
  <option>4000</option>
  <option>5000</option>
</select>
</form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">取消订单</button>
        <button type="button" class="btn btn-primary" id="createOrder">加入订单</button>
      </div>
    </div>
  </div>
</div>
</body>
</html>