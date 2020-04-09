<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="${pageContext.request.contextPath}/js/jquery-3.4.1.min.js"></script>
<script src="${pageContext.request.contextPath}/js/echarts.min.js"></script>
</head>
<body>
<div id="main" style="width:1300px; height:600px;"></div>
  
<script type="text/javascript">
var myChart = echarts.init($('#main')[0]); // 注意：这里init方法的参数的javascript对象，使用jQuery获取标签时，要将jQuery对象转成JavaScript对象；

    $(function () {
        // 配置图标参数
        var options = {
            title: {
                text: '球员数据一览',
                show: true, // 是否显示标题
                textStyle: {
                    fontSize: 18 // 标题文字大小
                }
            },
            tooltip: {
                trigger: 'axis',
                axisPointer: {
                    type: 'shadow'
                }
            },
            legend: {
                data: ['进球数','助攻数','拦截数']
            },
            // X轴
            xAxis: {
                data: [] // 异步请求时,这里要置空
            },
            // Y轴
            yAxis: {},
            series: [{
                name: '进球数',
                type: 'bar', // 设置图表类型为柱状图
                data: [] // 设置纵坐标的刻度(异步请求时,这里要置空)
            }]
        };
        // 给图标设置配置的参数
        myChart.setOption(options);
        myChart.showLoading(); // 显示加载动画
// 异步请求加载数据
        $.ajax({
            url: '${pageContext.request.contextPath}/player/chart.action',
            type: 'post',
            dataType: 'json',
            success: function (data) {
                var player_Ename = [];
                var player_goal = [];
                var player_assists=[];
                var player_interceptions=[];
                $.each(data, function (index, obj) {
                	player_Ename.push(obj.player_Ename);
                	player_goal.push(obj.player_goal);
                	player_assists.push(obj.player_assists);
                	player_interceptions.push(obj.player_interceptions);
                })
 
                myChart.hideLoading(); // 隐藏加载动画
                myChart.setOption({
                    legend: {
                        data: ['进球数','助攻数','拦截数']
                    },
                    xAxis: {
                        data: player_Ename
                    },
       
                    series: [{
                        name: '进球数',
                        type: 'bar', // 设置图表类型为柱状图
                        data: player_goal,// 设置纵坐标的刻度
                        color:'red'
                    },
                    {
                    	name:'助攻数',
                    	type:'bar',
                    	data:player_assists,
                    	color:'green'
                    },
                    {
                    	name:'拦截数',
                    	type:'bar',
                    	data:player_interceptions,
                    	color:'blue'
                    }
                    ]
                });
            }
        });
    });
    
</script>
</body>
</html>