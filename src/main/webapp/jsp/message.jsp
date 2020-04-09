<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
	 var urlParam= window.location.search;
     var schedule_id = urlParam.substring(urlParam.lastIndexOf('=')+1, urlParam.length);
     $.ajax({
    	 url:"${pageContext.request.contextPath }/message/queryMessageById.action",
			type : "post",
			data:{
				"schedule_id":schedule_id
			},
			success:function(data){
				var str="";
				 $.each(data, function (index,obj) { 
					str = "<tr><td>" + obj.user_phone+ "</td><td>" + obj.messgae_context + "</td><td>"+
					obj.message_date +"</td></tr>";
					 $("#tab").append(str);  
                 }) 
			}
     })
     $.ajax({
    	 url:"${pageContext.request.contextPath }/message/totalMessage.action",
			type : "post",
			data:{
				"schedule_id":schedule_id
			},
			success:function(data){
				$("#span").html(data);
			}
     })
})
 $(document).on("click","#publicMessage",function(){
	 var messgae_context=$("#text").val();
	 var urlParam= window.location.search;
     var schedule_id = urlParam.substring(urlParam.lastIndexOf('=')+1, urlParam.length);
		$.ajax({
			url:"${pageContext.request.contextPath }/message/publicMessage.action",
			type : "post",
			data:{
				"schedule_id":schedule_id,
				"messgae_context":messgae_context
			},
			success:function(result){
				if(result==1){
					window.location.reload();
					alert("成功发表评论");
				}else{
					alert("貌似出现了点小问题");
				}
			}
			
			
		}) 
		})
</script>
</head>
<body>
<form role="form"> 
<h3 style="color: SkyBlue;">欢迎使用足球论坛--本场比赛共(<strong><span id="span" style="color: red;"></span></strong>)条评论:</h3>
  <div class="form-group"> 
    <textarea class="form-control" rows="4"  placeholder="在这里发表您的评论，注意友善度哦。。。。" id="text"></textarea> 
  </div> 
  <button type="button" class="btn btn-info"  style="float: right;margin-top: 10px;" id="publicMessage">发表评论</button>
</form> 
<div>
<table id="tab" class="table table-hover table-bordered table-responsive">

 <tr id="tr">
           
          </tr>
</table>
</div>  

</body>
</html>