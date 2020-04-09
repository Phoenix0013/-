<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/bootStrapCss/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/easyui.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/icon.css">
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.easyui.min.js"></script>
	<script src="${pageContext.request.contextPath}/bootStrapJs/bootstrap.min.js"></script>
    <style type="text/css">
    .easyui-panel{
    text-align: right;
    }
    </style>
    <script type="text/javascript">
		$(function(){
			$(".menuA").click(function(){
				var contentText = this.innerHTML;
				var url = this.href;
				createTab(contentText,url);
				// 超链接就不跳转
				return false;
			});
		});
		
		function createTab(contentText,url){
			// 判断选项卡是否存在:
			var flag = $("#tt").tabs("exists",contentText);
			if(flag){
				// 如果已经存在，让其被选中
				$("#tt").tabs("select",contentText);
			}else{
				// 如果不存在,创建新的选项卡
				$('#tt').tabs('add',{    
				    title:contentText,    
				    content:createUrl(url),    
				    closable:true   
				}); 
			} 
		}
		
		function createUrl(url){
			return "<iframe src='"+url+"' style='width:100%;height:95%;border:none;'></iframe>";
		} 
	</script>
	<style type="text/css">
	.foot{
	width: 100%;
	height: 100%;
	}
	</style>
</head>
<body>
		  
<p class="bg-info" style="height: 50px; text-align: right; font-size: 20px; "><span style="margin-right: 996px;font-size: 25px;color: SkyBlue;"><b> 足球信息管理系统</b></span>欢迎当前用户--<img alt="" src="${member.img }" style="height: 50px;width: 50px; margin-left: 10px;" >${user.user_phone}</p>
	<div class="easyui-layout" data-options="fit:true"style="margin-top: -10px;">
	   <!--  西部区域-->
		<div data-options="region:'west',split:true" title="系统菜单" style="width:200px;">
	<div class="easyui-accordion" style="width:500px;height:500px;">
		<div title="球员管理" data-options="iconCls:'icon-reload'" style="overflow:auto;padding:10px;">
		<a href="${pageContext.request.contextPath }/player/queryAllPlayer.action" class="menuA" style="margin-left: 30px;">球员信息管理</a>
		</div>
		<div title="精准搜索" data-options="iconCls:'icon-search'" style="padding:10px 0;">
		<a href="${pageContext.request.contextPath}/jsp/search.jsp" class="menuA" style="margin-left: 30px;">实时搜索</a>
		</div>
		
		<div title="报表中心" data-options="iconCls:'icon-search'" style="padding:10px 0;">
		<a href="${pageContext.request.contextPath}/jsp/barChart.jsp" class="menuA" style="margin-left: 30px;">球员数据一览</a><br>
		<a href="${pageContext.request.contextPath}/jsp/pieChart.jsp" class="menuA" style="margin-left: 30px;">球员能力分析</a>
		</div>
		<div title="文件中心" data-options="iconCls:'icon-search'" style="padding:10px 0;">
		<a href="${pageContext.request.contextPath}/jsp/inExcel.jsp" class="menuA" style="margin-left: 30px;">导入Excel数据</a><br>
		<a href="${pageContext.request.contextPath}/jsp/playerList.jsp" class="menuA" style="margin-left: 30px;">导出Excel文件</a><br>
		<a href="${pageContext.request.contextPath}/jsp/outPartExcel.jsp" class="menuA" style="margin-left: 30px;">分类导出Excel</a><br>
		</div>
		<div title="赛事中心" data-options="iconCls:'icon-reload'" style="padding:10px;">
		<a href="${pageContext.request.contextPath }/schedule/Laliga.action" class="menuA" style="margin-left: 30px;">赛事一览</a><br>
		<a href="${pageContext.request.contextPath }/schedule/Laliga.action" class="menuA" style="margin-left: 30px;">足球竞彩</a><br>
		<a href="${pageContext.request.contextPath }/schedule/Laliga.action" class="menuA" style="margin-left: 30px;">足球论坛</a><br>
		<a href="${pageContext.request.contextPath }/ord/selectByUserPhone.action" class="menuA" style="margin-left: 30px;">订单中心</a>
		</div>
		<div title="账号升级" data-options="iconCls:'icon-search'" style="padding:10px 0;">
		<a href="${pageContext.request.contextPath}/jsp/member.jsp" class="menuA" style="margin-left: 30px;">账号审核</a>
		</div>
		<div title="其他操作" data-options="iconCls:'icon-search'" style="padding:10px 0;">
		<a href="${pageContext.request.contextPath}/user/logout.action" class="menuA" style="margin-left: 30px;">退出系统</a>
		</div>
	</div>
		</div>
		${alluser.user_state}
		
		<!-- 中央区域 -->
		<div data-options="region:'center',title:'',iconCls:'icon-ok'">
	<div class="easyui-tabs" data-options="fit:true" id="tt">
		<div title="数据区域">
		<img alt="" src="/Ball/images/liansai.jpg" class="foot">
		</div>
	</div>
		</div>
	</div>
</body>
</html>