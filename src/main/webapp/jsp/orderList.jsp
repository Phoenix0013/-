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
 * 用户支付订单的方法
 */
$(document).on("click","#clear",function(){
	$('#myModal').modal('show')
	var user_points=0;
	$.each($(".two"),function(){
		user_points+=parseInt($(this).parents("tr").find("td:eq(2)").text());
	});
	$("#ps").html(user_points);
	$("#pay").click(function (){
		$.ajax({
			url:"${pageContext.request.contextPath }/user/updatePoints.action",
			type : "post",
			data:{
				"user_points":user_points
			},
			success:function(result){
				if(result==1){
					alert("操作失败！您的余额不足！请充值!");
				}else{
					alert("操作成功！继续查看其它赛事！");
					window.location.href='${pageContext.request.contextPath }/ord/updateState.action';
				}
			}
		});
	});
	})
	
	/*
	取消订单的方法
	*/
	function deleteOrder(order_id) {
		$.ajax({
			url:"${pageContext.request.contextPath }/ord/deleteOrder.action",
			type : "post",
			data:{
				"order_id":order_id
			},
			success:function(result){
				if(result==1){
					alert("您已成功取消订单！");
					document.location.reload();
				}else{
					alert("操作失败，请重试！");
				}
			}
		})
	}
	
</script>
</head>
<body>
<!-- Contextual button for informational alert messages -->
<button type="button" class="btn btn-info" style="float: right; height: 50px;width: 150px;font-size: 18px;">账户余额:${user.user_points}</button>
<p class="bg-info" style="height: 50px; font-size: 25px;padding-top: 8px;">${user.user_phone }账户的所有订单：</p>

<table  class="table table-hover table-bordered table-responsive" style="margin-top: -10px">
  <thead>
    <tr>
            <th scope="col">订单号</th>
            <th scope="col">用户电话</th>
            <th scope="col">交易金额</th>
            <th scope="col">获胜球队</th>
            <th scope="col">下单时间</th>
            <th scope="col">订单状态</th>
            <th scope="col">操作</th>
          
    </tr>
  </thead>
  <tbody>
    <c:forEach items="${porders}" var="porders">
          <tr>
            <td>${porders.order_id}</td>
            <td>${porders.user_phone}</td>
            <td class="two">${porders.order_money}</td>
            <td>${porders.win_team}</td>
            <td>${porders.order_time}</td>
            <td class="check">${porders.order_state}</td>
            <td><a href="javascript:void(0);" onclick="deleteOrder(${porders.order_id})"> 取消订单</a> </td>
          </tr>
          </c:forEach>
  </tbody>
</table>
<!-- Indicates a successful or positive action -->
<button type="button" class="btn btn-success" style="margin-left: 600px;height: 50px;width: 150px; margin-top: 100px;" id="clear">结算订单</button>
<!-- Modal -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">结算订单</h4>
      </div>
      <div class="modal-body">
        订单总金额：<span id="ps"></span>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
        <button type="button" class="btn btn-primary" id="pay">付款</button>
      </div>
    </div>
  </div>
</div>
</body>
</html>