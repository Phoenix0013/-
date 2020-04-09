<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/easyui.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/icon.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/demo.css">
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery.easyui.min.js"></script>
</head>
<body>


<form action="${pageContext.request.contextPath}/player/inExcel.action" method="post" enctype="multipart/form-data" style="margin-left: 200px; margin-top: 100px;">
	<div class="bigdiv">
	<div class="easyui-panel" title="请提交正确格式的Excel文件！" style="width:100%;max-width:800px; padding:30px 60px;">
	
		<div style="margin-bottom:20px">
			<input name="file" class="easyui-filebox"  data-options="prompt:'Choose a file...'" style="width:100%;height: 50px;">
		</div>
		
		<div>
		<input type="submit" class="easyui-linkbutton" style="width:100%;height: 35px;" value="Upload" id="upload">
		</div>
	</div>
	</div>
	</form>
</body>
</html>