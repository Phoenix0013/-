<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">

</style>
</head>
<body>
<img alt="" src="/Ball/images/404.jpg" style="margin-top: 60px;margin-left: 350px;">
<form>
     <div>
         <p style="color: red;font-size: 20px; margin-left: 550px;margin-top: 20px;"><span id="skip"></span>秒后跳转到主页面</p>
     </div>
 </form>
 <script type="text/javascript">
     var t = 5;
     function showTime() {
         t -= 1;
         document.getElementById('skip').innerHTML = t;
         if (t == 0) {
             window.location.href = '${pageContext.request.contextPath }/player/queryAllPlayer.action';         }
         setTimeout("showTime()",1000);
     }
     showTime();
 </script>
</body>
</html>