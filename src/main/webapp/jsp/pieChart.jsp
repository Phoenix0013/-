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
<div id="pid-div" style="width:1300px; height:600px;"></div>
 
<script type="text/javascript">
    $(function () {
        var myChart = echarts.init($('#pid-div')[0]);
        var option = {
            title: {
                text: '球员位置与进球数量关系图之饼图',
                x: 'center',
                textStyle: {
                    fontSize: 18 // 标题文字大小
                }
            },
            tooltip: {
                trigger: 'item',
                formatter: "{a} <br/>{b} : {c} ({d}%)"
            },
            legend: {
                orient: 'vertical',
                left: 'left',
                data: []
            },
            series: [
                {
                    name: '访问来源',
                    type: 'pie',
                    radius: '55%', // 饼状图的大小
                    center: ['50%', '60%'], // 饼状图的位置
                    data: [],
                    itemStyle: {
                        emphasis: {
                            shadowBlur: 10,
                            shadowOffsetX: 0,
                            shadowColor: 'rgba(0, 0, 0, 0.5)'
                        }
                    }
                }
            ]
        };
        myChart.setOption(option);
        myChart.showLoading(); // 显示动画
 
        $.ajax({
            url: '${pageContext.request.contextPath}/player/chart.action',
            type: 'post',
            dataType: 'json',
            success: function (data) {
            	var player_Ename = [];
                var player_goal = [];
                $.each(data, function (index, obj) {
                	player_Ename.push(obj.player_Ename);
                	player_goal.push({name: obj.player_Ename, value: obj.player_goal});
                })
 
                myChart.hideLoading(); // 隐藏加载动画
                myChart.setOption({
                    legend: {
                        data: player_Ename
                    },
                    series: [{
                        name: '年龄',
                        type: 'pie', // 设置图表类型为柱状图
                        data: player_goal // 设置纵坐标的刻度
                    }]
                });
            }
        });
    });
</script>
</body>
</html>